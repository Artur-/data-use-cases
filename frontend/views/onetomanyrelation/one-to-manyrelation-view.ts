import '!style-loader!css-loader!./one-to-manyrelation-view.css';
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
import ProductWithTags from '../../generated/com/vaadin/artur/datausecases/data/entity/ProductWithTags';
import ProductWithTagsModel from '../../generated/com/vaadin/artur/datausecases/data/entity/ProductWithTagsModel';
import * as ProductWithTagsEndpoint from '../../generated/ProductWithTagsEndpoint';
import { View } from '../view';

@customElement('one-to-manyrelation-view')
export class OneToManyrelationView extends View {
  @query('#grid')
  private grid!: GridElement;

  @property({ type: Number })
  private gridSize = 0;

  private gridDataProvider = this.getGridData.bind(this);

  private binder = new Binder<ProductWithTags, ProductWithTagsModel>(this, ProductWithTagsModel);

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
            ><vaadin-grid-sort-column auto-width path="tags"></vaadin-grid-sort-column>
          </vaadin-grid>
        </div>
        <div id="editor-layout">
          <div id="editor">
            <vaadin-form-layout
              ><vaadin-text-field label="Name" id="name" ...="${field(this.binder.model.name)}"></vaadin-text-field
              ><vaadin-text-field label="Tags" id="tags" ...="${field(this.binder.model.tags)}"></vaadin-text-field
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
    const data = await ProductWithTagsEndpoint.list(index, params.pageSize, params.sortOrders as any);
    callback(data);
  }

  async connectedCallback() {
    super.connectedCallback();
    this.gridSize = await ProductWithTagsEndpoint.count();
  }

  private async itemSelected(event: CustomEvent) {
    const item: ProductWithTags = event.detail.value as ProductWithTags;
    this.grid.selectedItems = item ? [item] : [];

    if (item) {
      const fromBackend = await ProductWithTagsEndpoint.get(item.id!);
      fromBackend ? this.binder.read(fromBackend) : this.refreshGrid();
    } else {
      this.clearForm();
    }
  }

  private async save() {
    try {
      const isNew = !this.binder.value.id;
      await this.binder.submitTo(ProductWithTagsEndpoint.update);
      if (isNew) {
        // We added a new item
        this.gridSize++;
      }
      this.clearForm();
      this.refreshGrid();
      showNotification('ProductWithTags details stored.', { position: 'bottom-start' });
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
