import { ListInterface } from '@vaadin/flow-frontend/util/crud-interface';
import { DataStore } from '@vaadin/flow-frontend/util/grid-util';
import EntityReference from './generated/com/vaadin/artur/datausecases/manytoonecrud/Util/EntityReference';
import AbstractEntity from './generated/com/vaadin/artur/datausecases/util/AbstractEntity';

const entityToText = (entity: any) => {
  // FIXME This is Category specific
  return entity.name;
};

class EntityStore implements DataStore {
  data: any = {};

  async load<T>(endpoint: ListInterface<T & AbstractEntity>) {
    const data = await endpoint.list({
      pageNumber: 0,
      pageSize: 1000,
      sort: { orders: [] },
    });

    this.data.Category = data.reduce((map: any, obj: T & AbstractEntity) => {
      map[obj.id] = obj;
      return map;
    }, {});
  }
  toText(ref: EntityReference) {
    if (this.data[ref.type]) {
      const data: any = this.data[ref.type];
      if (data[ref.value]) {
        return entityToText(data[ref.value]);
      }
    }
    return '';
  }
}
export const entityStore = new EntityStore();
