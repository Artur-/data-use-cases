import { Context, RouteWithComponent } from '@vaadin/router';
import { setTitle } from '.';

export interface RouteWithTitle extends RouteWithComponent {
  title?: string;
  children?: RouteWithTitle[];
}
export const routes: RouteWithTitle[] = [
  {
    path: '',
    component: 'main-view',
    action: async (context: Context) => {
      await import('./views/main/main-view');
      const result = (await context.next()) as any;
      if (result.route) {
        const title = (result.route as RouteWithTitle).title;
        setTitle(title);
      }
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
