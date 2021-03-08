import '@polymer/iron-icon';
import { endpointData, gridColumns } from '@vaadin/flow-frontend/util/grid-util';
import { View } from '@vaadin/flow-frontend/util/view';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-date-picker';
import '@vaadin/vaadin-form-layout';
import '@vaadin/vaadin-grid';
import '@vaadin/vaadin-grid/vaadin-grid-sort-column';
import '@vaadin/vaadin-icons';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-split-layout';
import '@vaadin/vaadin-text-field';
import '@vaadin/vaadin-upload';
import ProductWithSalesModel from 'Frontend/generated/com/vaadin/artur/datausecases/gridaggregateddata/data/endpoint/ProductWithSalesModel';
import * as ProductSalesEndpoint from 'Frontend/generated/ProductSalesEndpoint';
import { html } from 'lit';
import { customElement } from 'lit/decorators';

@customElement('the-view')
export class GridWithaAgregatedInfo extends View {
  render() {
    return html` <vaadin-grid ${endpointData(ProductSalesEndpoint)}>
      ${gridColumns(ProductWithSalesModel)}
    </vaadin-grid>`;
  }
}
