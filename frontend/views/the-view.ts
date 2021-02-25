import "@polymer/iron-icon";
import "@vaadin/vaadin-button";
import "@vaadin/vaadin-date-picker";
import "@vaadin/vaadin-form-layout";
import "@vaadin/vaadin-grid";
import { GridElement } from "@vaadin/vaadin-grid/vaadin-grid";
import "@vaadin/vaadin-grid/vaadin-grid-sort-column";
import "@vaadin/vaadin-icons";
import "@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout";
import "@vaadin/vaadin-split-layout";
import "@vaadin/vaadin-text-field";
import "@vaadin/vaadin-upload";
import ProductNamePriceCategorySale from "Frontend/generated/com/vaadin/artur/datausecases/data/endpoint/ProductNamePriceCategorySale";
import { customElement, html, internalProperty, query } from "lit-element";
import * as ProductNamePriceCategorySaleEndpoint from "../../generated/ProductNamePriceCategorySaleEndpoint";
import { View } from "./view";
import { endPointDataProvider } from "./griddataprovider";

@customElement("the-view")
export class GridWithaAgregatedInfo extends View {
  @query("#grid")
  private grid!: GridElement;

  @internalProperty()
  private gridSize = 0;

  render() {
    return html`
      <vaadin-split-layout class="full-size">
        <div class="grid-wrapper">
          <vaadin-grid
            id="grid"
            class="full-size"
            theme="no-border"
            .size="${this.gridSize}"
            .dataProvider="${endPointDataProvider(
              ProductNamePriceCategorySaleEndpoint
            )}"
            @active-item-changed=${this.itemSelected}
          >
            <vaadin-grid-sort-column
              auto-width
              path="name"
            ></vaadin-grid-sort-column
            ><vaadin-grid-sort-column
              auto-width
              path="price"
            ></vaadin-grid-sort-column
            ><vaadin-grid-sort-column
              auto-width
              path="category"
            ></vaadin-grid-sort-column>
          </vaadin-grid>
        </div>
      </vaadin-split-layout>
    `;
  }

  async connectedCallback() {
    super.connectedCallback();
    this.gridSize = await ProductNamePriceCategorySaleEndpoint.count();
  }

  private async itemSelected(event: CustomEvent) {
    const item: ProductNamePriceCategorySale = event.detail
      .value as ProductNamePriceCategorySale;
    this.grid.selectedItems = item ? [item] : [];

    // if (item) {
    //   const fromBackend = await ProductNamePriceCategorySaleEndpoint.get(
    //     item.id!
    //   );
    //   fromBackend ? this.binder.read(fromBackend) : this.refreshGrid();
    // } else {
    //   this.clearForm();
    // }
  }

  // private refreshGrid() {
  //   this.grid.selectedItems = [];
  //   this.grid.clearCache();
  // }
}
