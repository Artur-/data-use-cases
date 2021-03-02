import FakePageable from "Frontend/generated/com/vaadin/artur/datausecases/util/FakePageable";

export interface ListInterface<T> {
  list(pageable: FakePageable): Promise<Array<T>>;
}
