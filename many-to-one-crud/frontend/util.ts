import { ListInterface } from '@vaadin/flow-frontend/util/crud-interface';
import { DataStore } from '@vaadin/flow-frontend/util/grid-util';
import '@vaadin/vaadin-item';
import '@vaadin/vaadin-list-box';
import { SelectElement } from '@vaadin/vaadin-select';
import { html, render } from 'lit';
import { directive, Directive, ElementPartInfo, PartInfo, PartType } from 'lit/directive';
import { ModelConstructor } from '../target/flow-frontend/form';
import AbstractEntity from './generated/com/vaadin/artur/datausecases/util/AbstractEntity';
import EntityReference from './generated/com/vaadin/artur/datausecases/util/EntityReference';

const entityToText = (entity: any) => {
  // FIXME This is Category specific
  return entity.name;
};

export const modelToIdentifier = (_Model: ModelConstructor<any, any>) => {
  // FIXME This would have to be generated in the Model
  return 'Category';
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
    if (ref.name) {
      return ref.name;
    }
    if (this.data[ref.type]) {
      debugger;
      const data: any = this.data[ref.type];
      if (data[ref.id]) {
        return entityToText(data[ref.id]);
      }
    }
    return '';
  }
}
export const entityStore = new EntityStore();

const setRendererIfNeeded = (select: SelectElement, id: string) => {
  if (select.renderer) {
    return;
  }

  const items: any[] = entityStore.data[id];

  select.renderer = (root: HTMLElement, _select?: SelectElement) => {
    render(
      html`<vaadin-list-box
        >${Object.values(items).map(
          (entity) => html`<vaadin-item value="${entity.id}">${entityToText(entity)}</vaadin-item>`
        )}</vaadin-list-box
      >`,
      root
    );
  };
};
export const selectOptions = directive(
  class extends Directive {
    partInfo: ElementPartInfo;
    constructor(partInfo: PartInfo) {
      super(partInfo);
      if (partInfo.type !== PartType.ELEMENT) {
        throw new Error('Use as <vaadin-select>${selectOptions(...)}</vaadin-select>');
      }
      this.partInfo = partInfo;
    }

    render(endpoint: ListInterface<any>, Model: ModelConstructor<any, any>, entityStore: EntityStore) {
      const select = (this.partInfo as any).element as SelectElement;
      if (select.renderer) {
        return;
      }
      const id = modelToIdentifier(Model);
      const items: any[] = entityStore.data[id];
      if (!items) {
        entityStore.load(endpoint).then(() => {
          setRendererIfNeeded(select, id);
        });
      } else {
        setRendererIfNeeded(select, id);
      }
    }
  }
);
