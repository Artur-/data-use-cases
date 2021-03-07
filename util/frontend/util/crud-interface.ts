import FakePageable from 'Frontend/generated/com/vaadin/artur/datausecases/util/FakePageable';

export interface ListInterface<T> {
  list(pageable: FakePageable): Promise<Array<T>>;
}
export interface GetInterface<T, ID> {
  get(id: ID): T | undefined;
}
export interface ListInterface<T> {
  list(pageable: FakePageable): Promise<Array<T>>;
}

export interface WriteInterface<T, ID> {
  update(entity: T): T;

  delete(id: ID): boolean;
}
