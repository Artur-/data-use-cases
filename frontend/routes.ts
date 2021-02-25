import { Route } from '@vaadin/router';
import './views/basiccrud/basic-crud-view';
import './views/main/main-view';

export type ViewRoute = Route & { title?: string; children?: ViewRoute[] };

export const views: ViewRoute[] = [
  // for client-side, place routes below (more info https://vaadin.com/docs/v18/flow/typescript/creating-routes.html)
  {
    path: '',
    component: 'basic-crud-view',
    title: '',
  },
  {
    path: 'Basic-CRUD',
    component: 'basic-crud-view',
    title: 'Basic CRUD',
  },
  {
    path: 'ManyToOne',
    component: 'manyto-one-view',
    title: 'ManytoOne',
    action: async () => {
      await import('./views/manytoone/manyto-one-view');
    },
  },
  {
    path: 'Grid-with-aggregated',
    component: 'gridwithaggregatedinfo-view',
    title: 'Grid with aggregated info',
    action: async () => {
      await import('./views/gridwithaggregatedinfo/gridwithaggregatedinfo-view');
    },
  },
  {
    path: 'dependent-fields',
    component: 'dependentfields-view',
    title: 'Dependent fields',
    action: async () => {
      await import('./views/dependentfields/dependentfields-view');
    },
  },
  {
    path: 'hidden-fields',
    component: 'hiddenfields-view',
    title: 'Hidden fields',
    action: async () => {
      await import('./views/hiddenfields/hiddenfields-view');
    },
  },
  {
    path: 'onetomany-owned',
    component: 'one-to-manyowned-view',
    title: 'OneToMany owned',
    action: async () => {
      await import('./views/onetomanyowned/one-to-manyowned-view');
    },
  },
  {
    path: 'onetomany-relation',
    component: 'one-to-manyrelation-view',
    title: 'OneToMany relation',
    action: async () => {
      await import('./views/onetomanyrelation/one-to-manyrelation-view');
    },
  },
  {
    path: 'many-to-many-multiple',
    component: 'many-to-manymultiple-view',
    title: 'ManyToMany multiple',
    action: async () => {
      await import('./views/manytomanymultiple/many-to-manymultiple-view');
    },
  },
  {
    path: 'many-to-many-self',
    component: 'many-to-manyself-view',
    title: 'ManyToMany self',
    action: async () => {
      await import('./views/manytomanyself/many-to-manyself-view');
    },
  },
];
export const routes: ViewRoute[] = [
  {
    path: '',
    component: 'main-view',
    children: [...views],
  },
];
