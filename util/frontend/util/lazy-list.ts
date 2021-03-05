import FakePageable from 'Frontend/generated/com/vaadin/artur/datausecases/util/FakePageable';
import { ListInterface } from './crud-interface';

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
