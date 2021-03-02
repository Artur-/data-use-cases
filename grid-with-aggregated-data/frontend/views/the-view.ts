import "@polymer/iron-icon";
import { endPointDataProvider } from "@vaadin/flow-frontend/util/grid-util";
import { View } from "@vaadin/flow-frontend/util/view";
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
import * as ProductSalesEndpoint from "Frontend/generated/ProductSalesEndpoint";
import { customElement, html } from "lit-element";

@customElement("the-view")
export class GridWithaAgregatedInfo extends View {
  description() {
    return html`<h1 class="header">
      Use case: Show custom data in a read only grid in an efficient way that
      supports lazy loading
    </h1>`;
  }
  render() {
    return html`
      ${this.description()}
      <vaadin-split-layout class="full-size">
        <div class="grid-wrapper">
          <vaadin-grid
            id="grid"
            .dataProvider="${endPointDataProvider(ProductSalesEndpoint)}"
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
}
