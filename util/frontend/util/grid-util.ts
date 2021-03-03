import {
  GridDataProvider,
  GridDataProviderCallback,
  GridDataProviderParams,
  GridElement,
  GridSorterDirection,
} from "@vaadin/vaadin-grid";
import { ListInterface } from "./list-interface";
import Sort from "Frontend/generated/org/springframework/data/domain/Sort";
import Direction from "Frontend/generated/org/springframework/data/domain/Sort/Direction";
import NullHandling from "Frontend/generated/org/springframework/data/domain/Sort/NullHandling";
import FakePageable from "Frontend/generated/com/vaadin/artur/datausecases/util/FakePageable";
import { html } from "lit";
import {
  ChildPartInfo,
  directive,
  Directive,
  ElementPartInfo,
  PartInfo,
  PartType,
} from "lit/directive";
import { ModelConstructor } from "Frontend/../target/flow-frontend/form";
import "@vaadin/vaadin-grid/vaadin-grid-sort-column";

const toFakePageable = (params: GridDataProviderParams): FakePageable => {
  const sort: Sort = {
    orders: params.sortOrders.map((sortOrder) => ({
      direction: toDirection(sortOrder.direction),
      property: sortOrder.path,
      ignoreCase: false,
      nullHandling: NullHandling.NATIVE,
    })),
  };
  return {
    pageNumber: params.page,
    pageSize: params.pageSize,
    sort: sort,
  };
};
const toDirection = (direction: GridSorterDirection): Direction => {
  if (direction == null || direction == "asc") {
    return Direction.ASC;
  }
  return Direction.DESC;
};

export const endPointDataProvider = <T>(
  endpoint: ListInterface<T>
): GridDataProvider => {
  const dataProvider: GridDataProvider = async (
    params: GridDataProviderParams,
    callback: GridDataProviderCallback
  ): Promise<void> => {
    const data = await endpoint.list(toFakePageable(params));
    let size;

    const firstIndex = params.page * params.pageSize;
    if (data.length == params.pageSize) {
      size = firstIndex + data.length + 1;
    } else {
      size = firstIndex + data.length;
    }

    callback(data, size);
  };
  return dataProvider;
};

export const syncGridSelection = (event: CustomEvent) => {
  const item: any = event.detail.value;
  const grid = event.target as GridElement;
  grid.selectedItems = item ? [item] : [];
};

export const endpointData = directive(
  class extends Directive {
    partInfo: ElementPartInfo;
    constructor(partInfo: PartInfo) {
      super(partInfo);
      if (partInfo.type !== PartType.ELEMENT) {
        throw new Error(
          "Use as <vaadin-grid ${endpointData(...)}></vaadin-grid>"
        );
      }
      this.partInfo = partInfo;
    }
    render<T>(endpoint: ListInterface<T>) {
      const grid = (this.partInfo as any).element as GridElement;
      grid.dataProvider = endPointDataProvider(endpoint);
    }
  }
);
export const gridColumns = directive(
  class extends Directive {
    partInfo: ChildPartInfo;
    constructor(partInfo: PartInfo) {
      super(partInfo);
      if (partInfo.type !== PartType.CHILD) {
        throw new Error(
          "Use as <vaadin-grid>${gridColumns(...)}</vaadin-grid>"
        );
      }
      this.partInfo = partInfo;
    }
    render(Model: ModelConstructor<any, any>) {
      const properties = Object.keys(
        Object.getOwnPropertyDescriptors(Model.prototype)
      ).filter((p) => p !== "constructor");

      return properties.map(
        (p) =>
          html`<vaadin-grid-sort-column path="${p}"></vaadin-grid-sort-column>`
      );
    }
  }
);
