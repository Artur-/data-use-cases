import { RouteWithComponent } from '@vaadin/router';

export interface RouteWithTitle extends RouteWithComponent {
  title?: string;
  children?: RouteWithTitle[];
}
export const routes: RouteWithTitle[] = [
  {
    path: '',
    component: 'main-view',
    action: async () => {
      await import('./views/main/main-view');
    },
    children: [
      {
        path: 'hello',
        component: 'hello-world-view',
        title: 'Hello World',
        action: async () => {
          await import('./views/helloworld/hello-world-view');
        },
      },
      {
        path: 'about',
        component: 'about-view',
        title: 'About',
        action: async () => {
          await import('./views/about/about-view');
        },
      },
    ],
  },
];

export const getViewRoutes = (): RouteWithTitle[] => {
  return routes[0].children!;
};
