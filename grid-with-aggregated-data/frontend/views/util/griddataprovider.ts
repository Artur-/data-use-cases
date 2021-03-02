import {
  GridDataProvider,
  GridDataProviderCallback,
  GridDataProviderParams,
  GridSorterDirection,
} from "@vaadin/vaadin-grid";
import { ListInterface } from "./list-interface";
import Sort from "Frontend/generated/org/springframework/data/domain/Sort";
import Direction from "Frontend/generated/org/springframework/data/domain/Sort/Direction";
import NullHandling from "Frontend/generated/org/springframework/data/domain/Sort/NullHandling";
import FakePageable from "Frontend/generated/com/vaadin/artur/datausecases/util/FakePageable";

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
