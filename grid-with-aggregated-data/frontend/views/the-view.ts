import "@polymer/iron-icon";
import { showNotification } from "@vaadin/flow-frontend/a-notification";
import "@vaadin/vaadin-button";
import "@vaadin/vaadin-date-picker";
import "@vaadin/vaadin-form-layout";
import "@vaadin/vaadin-grid";
import "@vaadin/vaadin-grid/vaadin-grid-sort-column";
import "@vaadin/vaadin-icons";
import "@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout";
import "@vaadin/vaadin-split-layout";
import "@vaadin/vaadin-text-field";
import "@vaadin/vaadin-upload";
import ProductWithSales from "Frontend/generated/com/vaadin/artur/datausecases/gridwithaggregateddata/data/endpoint/ProductWithSales";
import * as ProductSalesEndpoint from "Frontend/generated/ProductSalesEndpoint";
import { customElement, html } from "lit-element";
import { endPointDataProvider, syncGridSelection } from "./util/grid-util";
import { View } from "./util/view";

@customElement("the-view")
export class GridWithaAgregatedInfo extends View {
  render() {
    return html`
      <vaadin-split-layout class="full-size">
        <div class="grid-wrapper">
          <vaadin-grid
            id="grid"
            .dataProvider="${endPointDataProvider(ProductSalesEndpoint)}"
            @active-item-changed=${this.itemSelected}
          >
            <vaadin-grid-sort-column
              auto-width
              path="name"
            ></vaadin-grid-sort-column
            ><vaadin-grid-sort-column
              auto-width
              path="price"
            ></vaadin-grid-sort-column>
            <vaadin-grid-sort-column
              auto-width
              path="category"
            ></vaadin-grid-sort-column>
            <vaadin-grid-sort-column
              auto-width
              path="salesLastMonth"
            ></vaadin-grid-sort-column>
          </vaadin-grid>
        </div>
      </vaadin-split-layout>
    `;
  }
  private async itemSelected(event: CustomEvent) {
    syncGridSelection(event);
    const item: ProductWithSales = event.detail.value as ProductWithSales;
    if (item) {
      showNotification(
        `If there was a form, now I would fetch more data from the backend`
      );
    }
  }
}
