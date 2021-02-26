import {
  GridDataProvider,
  GridDataProviderCallback,
  GridDataProviderParams,
} from "@vaadin/vaadin-grid";
import { ListInterface } from "./list-interface";

export const endPointDataProvider = <T>(
  endpoint: ListInterface<T>
): GridDataProvider => {
  const dataProvider: GridDataProvider = async (
    params: GridDataProviderParams,
    callback: GridDataProviderCallback
  ): Promise<void> => {
    const index = params.page * params.pageSize;
    const data = await endpoint.list(
      index,
      params.pageSize,
      params.sortOrders as any
    );
    callback(data);
  };
  return dataProvider;
};
