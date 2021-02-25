import {
  GridDataProvider,
  GridDataProviderCallback,
  GridDataProviderParams,
} from "@vaadin/vaadin-grid";
import ProductNamePriceCategorySale from "Frontend/generated/com/vaadin/artur/datausecases/data/endpoint/ProductNamePriceCategorySale";
import { ReadInterface } from "./ReadInterface";

type T = ProductNamePriceCategorySale;
type ID = number;

export const endPointDataProvider = (
  endpoint: ReadInterface<T, ID>
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
