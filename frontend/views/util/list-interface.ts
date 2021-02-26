import GridSorter from "Frontend/generated/org/vaadin/artur/helpers/GridSorter";

export interface ListInterface<T> {
  list(
    offset: number,
    limit: number,
    sortOrder: Array<GridSorter>
  ): Promise<Array<T>>;
}
