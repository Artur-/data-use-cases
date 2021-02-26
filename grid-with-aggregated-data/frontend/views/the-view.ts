import "@polymer/iron-icon";
import { showNotification } from "@vaadin/flow-frontend/a-notification";
import "@vaadin/vaadin-button";
import "@vaadin/vaadin-date-picker";
import "@vaadin/vaadin-form-layout";
import "@vaadin/vaadin-grid";
import { GridDataProvider, GridElement } from "@vaadin/vaadin-grid/vaadin-grid";
import "@vaadin/vaadin-grid/vaadin-grid-sort-column";
import "@vaadin/vaadin-icons";
import "@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout";
import "@vaadin/vaadin-split-layout";
import "@vaadin/vaadin-text-field";
import "@vaadin/vaadin-upload";
import ProductWithSales from "Frontend/generated/com/vaadin/artur/datausecases/gridwithaggregateddata/data/endpoint/ProductWithSales";
import * as ProductSalesEndpoint from "Frontend/generated/ProductSalesEndpoint";
import { customElement, html, internalProperty, query } from "lit-element";
import { endPointDataProvider } from "./util/griddataprovider";
import { View } from "./util/view";

@customElement("the-view")
export class GridWithaAgregatedInfo extends View {
  @query("#grid")
  private grid!: GridElement;

  @internalProperty()
  private gridSize = 0;

  @internalProperty()
  private dataProvider: GridDataProvider = endPointDataProvider(
    ProductSalesEndpoint
  );

  render() {
    return html`
      <vaadin-split-layout class="full-size">
        <div class="grid-wrapper">
          <vaadin-grid
            id="grid"
            .size="${this.gridSize}"
            .dataProvider="${this.dataProvider}"
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

  async connectedCallback() {
    super.connectedCallback();
    this.gridSize = await ProductSalesEndpoint.count();
  }

  private async itemSelected(event: CustomEvent) {
    const item: ProductWithSales = event.detail.value as ProductWithSales;
    this.grid.selectedItems = item ? [item] : [];

    if (item) {
      showNotification(
        `If there was a form, now I would fetch more data from the backend`
      );
    }
  }
}
