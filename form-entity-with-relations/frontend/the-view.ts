import '@polymer/iron-icon';
import { field } from '@vaadin/flow-frontend/form';
import { endpointData, gridColumns } from '@vaadin/flow-frontend/util/grid-util';
import { View } from '@vaadin/flow-frontend/util/view';
import { Binder } from '@vaadin/form';
import '@vaadin/vaadin-grid';
import { GridActiveItemChanged } from '@vaadin/vaadin-grid';
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
  @state()
  selected: Product[] = [];

  private binder: Binder<Product, ProductModel> = new Binder(this, ProductModel);

  render() {
    return html`
      <vaadin-grid ${endpointData(ProductEndpoint)} @active-item-changed="${this.itemSelected}">
        ${gridColumns(ProductModel)}
      </vaadin-grid>
      <vaadin-vertical-layout>
        <vaadin-text-field ${field(this.binder.model.name)}></vaadin-text-field>
      </vaadin-vertical-layout>
    `;
  }

  itemSelected(e: GridActiveItemChanged) {
    const item = e.detail.value as Product;
    this.selected = item ? [item] : [];
  }
}
