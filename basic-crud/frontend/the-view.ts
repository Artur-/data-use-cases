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
import '@vaadin/vaadin-text-field';
import { ProductEndpoint } from 'Frontend/generated/ProductEndpoint';
import { html } from 'lit';
import { customElement, state } from 'lit/decorators';
import Product from './generated/com/vaadin/artur/datausecases/gridwithaggregateddata/data/entity/Product';
import ProductModel from './generated/com/vaadin/artur/datausecases/gridwithaggregateddata/data/entity/ProductModel';

@customElement('the-view')
export class FormEntityWithRelations extends View {
  private binder: Binder<Product, ProductModel> = new Binder(this, ProductModel);

  private products: EndpointLazyList<Product> = EndpointLazyList.connect(ProductEndpoint);

  @state()
  selected: Product[] = [];

  render() {
    return html`
      <vaadin-horizontal-layout>
        <vaadin-grid
          ${gridData(this.products)}
          @active-item-changed="${(e: GridActiveItemChanged) => {
            const item = e.detail.value as Product;
            this.selected = item ? [item] : [];
          }}"
          .selectedItems=${this.selected}
          @selected-items-changed="${this.itemSelected}"
        >
          ${gridColumns(ProductModel)}
        </vaadin-grid>
        <vaadin-vertical-layout>
          <vaadin-text-field label="Name" ${field(this.binder.model.name)}></vaadin-text-field>
          <vaadin-text-field label="Price" ${field(this.binder.model.price)}></vaadin-text-field>
          <vaadin-horizontal-layout>
            <vaadin-button @click=${this.delete} theme="danger">Delete</vaadin-button>
            <vaadin-button @click=${this.cancel}>Cancel</vaadin-button>
            <vaadin-button @click=${this.save} theme="primary">Save</vaadin-button>
          </vaadin-horizontal-layout>
        </vaadin-vertical-layout>
      </vaadin-horizontal-layout>
    `;
  }

  itemSelected(e: GridSelectedItemsChanged) {
    const item = e.detail.value as Product[];
    if (item.length > 0) {
      this.binder.read(item[0]);
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
    const saved = await this.binder.submitTo(ProductEndpoint.update);
    // TODO Handle optimistic locking errors
    this.products.refresh(saved);
  }
}
