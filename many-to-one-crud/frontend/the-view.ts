import { showNotification } from '@vaadin/flow-frontend/a-notification';
import { field } from '@vaadin/flow-frontend/form';
import { gridColumns, gridData } from '@vaadin/flow-frontend/util/grid-util';
import { EndpointLazyList } from '@vaadin/flow-frontend/util/lazy-list';
import { View } from '@vaadin/flow-frontend/util/view';
import { Binder } from '@vaadin/form';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-grid';
import { GridActiveItemChanged, GridSelectedItemsChanged } from '@vaadin/vaadin-grid';
import '@vaadin/vaadin-grid/vaadin-grid-sort-column';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-ordered-layout/vaadin-vertical-layout';
import '@vaadin/vaadin-select';
import '@vaadin/vaadin-text-field';
import { CategoryEndpoint } from 'Frontend/generated/CategoryEndpoint';
import { ProductEndpoint } from 'Frontend/generated/ProductEndpoint';
import { html } from 'lit';
import { customElement, state } from 'lit/decorators';
import CategoryModel from './generated/com/vaadin/artur/datausecases/manytoonecrud/data/entity/CategoryModel';
import StrippedProduct from './generated/com/vaadin/artur/datausecases/manytoonecrud/Util/StrippedProduct';
import StrippedProductModel from './generated/com/vaadin/artur/datausecases/manytoonecrud/Util/StrippedProductModel';
import { entityStore, selectOptions } from './util';

@customElement('the-view')
export class FormEntityWithRelations extends View {
  private binder: Binder<StrippedProduct, StrippedProductModel> = new Binder(this, StrippedProductModel);

  private products: EndpointLazyList<StrippedProduct> = EndpointLazyList.connect(ProductEndpoint);

  @state()
  selected: StrippedProduct[] = [];

  render() {
    return html`
      <vaadin-horizontal-layout>
        <vaadin-grid
          ${gridData(this.products)}
          @active-item-changed="${(e: GridActiveItemChanged) => {
            const item = e.detail.value as StrippedProduct;
            this.selected = item ? [item] : [];
          }}"
          .selectedItems=${this.selected}
          @selected-items-changed="${this.itemSelected}"
        >
          ${gridColumns(StrippedProductModel, entityStore)}
        </vaadin-grid>
        <vaadin-vertical-layout>
          <vaadin-text-field label="Name" ${field(this.binder.model.name)}></vaadin-text-field>
          <vaadin-text-field label="Price" ${field(this.binder.model.price)}></vaadin-text-field>
          <vaadin-select
            label="Category"
            ${field(this.binder.model.category.id)}
            ${selectOptions(CategoryEndpoint, CategoryModel, entityStore)}
          >
          </vaadin-select>
          <vaadin-horizontal-layout>
            <vaadin-button @click=${this.delete} theme="danger">Delete</vaadin-button>
            <vaadin-button @click=${this.cancel}>Cancel</vaadin-button>
            <vaadin-button @click=${this.save} theme="primary">Save</vaadin-button>
          </vaadin-horizontal-layout>
        </vaadin-vertical-layout>
      </vaadin-horizontal-layout>
    `;
  }

  async itemSelected(e: GridSelectedItemsChanged) {
    const item = e.detail.value as StrippedProduct[];
    if (item.length > 0) {
      const product = item[0];
      if (product) {
        this.binder.read(product);
      } else {
        showNotification(`Product not found`);
        this.products.refreshAll();
      }
    } else {
      this.binder.clear();
    }
  }
  async delete() {
    await ProductEndpoint.delete(this.binder.value.id!);
    this.products.refreshAll();
  }
  cancel() {
    this.selected = [];
  }
  async save() {
    // const saved =
    await this.binder.submitTo(ProductEndpoint.update);
    // TODO Handle optimistic locking errors
    // this.products.refresh(saved);
  }
}
