import { toFakePageable } from "./grid-util";
import { ListInterface } from "./crud-interface";
import {
  GridDataProvider,
  GridDataProviderCallback,
  GridDataProviderParams,
  GridElement,
} from "@vaadin/vaadin-grid";
import {
  Directive,
  directive,
  ElementPartInfo,
  PartInfo,
  PartType,
} from "lit/directive";
import FakePageable from "../generated/com/vaadin/artur/datausecases/util/FakePageable";

interface EndpointLazyListListener<T> {
  refresh(dataObject: T): void;
  refreshAll(): void;
}
export class EndpointLazyList<T> implements ListInterface<T> {
  endpoint: ListInterface<T>;
  listeners: EndpointLazyListListener<T>[] = [];

  static connect<T>(endpoint: ListInterface<T>): EndpointLazyList<T> {
    return new EndpointLazyList<T>(endpoint);
  }

  private constructor(endpoint: ListInterface<T>) {
    this.endpoint = endpoint;
  }
  async list(pageable: FakePageable): Promise<Array<T>> {
    return this.endpoint.list(pageable);
  }
  public addListener(listener: EndpointLazyListListener<T>): () => void {
    this.listeners.push(listener);
    return () => this.removeListener(listener);
  }
  private removeListener(listener: EndpointLazyListListener<T>): void {
    this.listeners = this.listeners.filter((l) => listener !== l);
  }
  public refreshAll(): void {
    [...this.listeners].forEach((listener) => listener.refreshAll());
  }
  refresh(dataObject: T) {
    [...this.listeners].forEach((listener) => listener.refresh(dataObject));
  }
}

const lazyListDataProvider = <T>(
  grid: GridElement,
  lazyList: EndpointLazyList<T>
): GridDataProvider => {
  const dataProvider: GridDataProvider = async (
    params: GridDataProviderParams,
    callback: GridDataProviderCallback
  ): Promise<void> => {
    const data = await lazyList.list(toFakePageable(params));
    let size;

    const firstIndex = params.page * params.pageSize;
    if (data.length == params.pageSize) {
      size = firstIndex + data.length + 1;
    } else {
      size = firstIndex + data.length;
    }

    callback(data, size);
  };
  lazyList.addListener({
    refresh: (_dataObject) => {
      grid.clearCache();
    },
    refreshAll: () => {
      grid.clearCache();
    },
  });
  (dataProvider as any).lazyList = lazyList;
  return dataProvider;
};

export const gridData = directive(
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
    render<T>(lazyList: EndpointLazyList<T>) {
      const grid = (this.partInfo as any).element as GridElement;
      if (grid.dataProvider) {
        const currentList = (grid.dataProvider as any).lazyList;
        if (currentList === lazyList) {
          return;
        }
      }
      grid.dataProvider = lazyListDataProvider(grid, lazyList);
    }
  }
);
