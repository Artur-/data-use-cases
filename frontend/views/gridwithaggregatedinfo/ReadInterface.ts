import GridSorter from "Frontend/generated/org/vaadin/artur/helpers/GridSorter";

export interface ReadInterface<T, ID> {
  list(
    offset: number,
    limit: number,
    sortOrder: Array<GridSorter>
  ): Promise<Array<T>>;

  get(id: ID): Promise<T | undefined>;
}

// public interface ReadInterface<T, ID> {

//     public List<T> list(int offset, int limit, List<GridSorter> sortOrder);

//     public Optional<T> get(ID id);

// }
