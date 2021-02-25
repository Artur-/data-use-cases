import '!style-loader!css-loader!./manyto-one-view.css';
import '@polymer/iron-icon';
import { EndpointError } from '@vaadin/flow-frontend';
import { showNotification } from '@vaadin/flow-frontend/a-notification';
import { Binder, field } from '@vaadin/form';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-date-picker';
import '@vaadin/vaadin-form-layout';
import '@vaadin/vaadin-grid';
import { GridDataProviderCallback, GridDataProviderParams, GridElement } from '@vaadin/vaadin-grid/vaadin-grid';
import '@vaadin/vaadin-grid/vaadin-grid-sort-column';
import '@vaadin/vaadin-icons';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-split-layout';
import '@vaadin/vaadin-text-field';
import '@vaadin/vaadin-upload';
import { customElement, html, property, query } from 'lit-element';
import ProductNamePriceCategory from '../../generated/com/vaadin/artur/datausecases/data/entity/ProductNamePriceCategory';
import ProductNamePriceCategoryModel from '../../generated/com/vaadin/artur/datausecases/data/entity/ProductNamePriceCategoryModel';
import * as ProductNamePriceCategoryEndpoint from '../../generated/ProductNamePriceCategoryEndpoint';
import { View } from '../view';

@customElement('manyto-one-view')
export class ManytoOneView extends View {
  @query('#grid')
  private grid!: GridElement;

  @property({ type: Number })
  private gridSize = 0;

  private gridDataProvider = this.getGridData.bind(this);

  private binder = new Binder<ProductNamePriceCategory, ProductNamePriceCategoryModel>(
    this,
    ProductNamePriceCategoryModel
  );

  render() {
    return html`
      <vaadin-split-layout class="full-size">
        <div class="grid-wrapper">
          <vaadin-grid
            id="grid"
            class="full-size"
            theme="no-border"
            .size="${this.gridSize}"
            .dataProvider="${this.gridDataProvider}"
            @active-item-changed=${this.itemSelected}
          >
            <vaadin-grid-sort-column auto-width path="name"></vaadin-grid-sort-column
            ><vaadin-grid-sort-column auto-width path="price"></vaadin-grid-sort-column
            ><vaadin-grid-sort-column auto-width path="category"></vaadin-grid-sort-column>
          </vaadin-grid>
        </div>
        <div id="editor-layout">
          <div id="editor">
            <vaadin-form-layout
              ><vaadin-text-field label="Name" id="name" ...="${field(this.binder.model.name)}"></vaadin-text-field
              ><vaadin-text-field label="Price" id="price" ...="${field(this.binder.model.price)}"></vaadin-text-field
              ><vaadin-text-field
                label="Category"
                id="category"
                ...="${field(this.binder.model.category)}"
              ></vaadin-text-field
            ></vaadin-form-layout>
          </div>
          <vaadin-horizontal-layout id="button-layout" theme="spacing">
            <vaadin-button theme="primary" @click="${this.save}">Save</vaadin-button>
            <vaadin-button theme="tertiary" @click="${this.cancel}">Cancel</vaadin-button>
          </vaadin-horizontal-layout>
        </div>
      </vaadin-split-layout>
    `;
  }

  private async getGridData(params: GridDataProviderParams, callback: GridDataProviderCallback) {
    const index = params.page * params.pageSize;
    const data = await ProductNamePriceCategoryEndpoint.list(index, params.pageSize, params.sortOrders as any);
    callback(data);
  }

  async connectedCallback() {
    super.connectedCallback();
    this.gridSize = await ProductNamePriceCategoryEndpoint.count();
  }

  private async itemSelected(event: CustomEvent) {
    const item: ProductNamePriceCategory = event.detail.value as ProductNamePriceCategory;
    this.grid.selectedItems = item ? [item] : [];

    if (item) {
      const fromBackend = await ProductNamePriceCategoryEndpoint.get(item.id!);
      fromBackend ? this.binder.read(fromBackend) : this.refreshGrid();
    } else {
      this.clearForm();
    }
  }

  private async save() {
    try {
      const isNew = !this.binder.value.id;
      await this.binder.submitTo(ProductNamePriceCategoryEndpoint.update);
      if (isNew) {
        // We added a new item
        this.gridSize++;
      }
      this.clearForm();
      this.refreshGrid();
      showNotification('ProductNamePriceCategory details stored.', { position: 'bottom-start' });
    } catch (error) {
      if (error instanceof EndpointError) {
        showNotification('Server error. ' + error.message, { position: 'bottom-start' });
      } else {
        throw error;
      }
    }
  }

  private cancel() {
    this.grid.activeItem = undefined;
  }

  private clearForm() {
    this.binder.clear();
  }

  private refreshGrid() {
    this.grid.selectedItems = [];
    this.grid.clearCache();
  }
}
