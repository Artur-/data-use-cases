import { Router } from '@vaadin/router';
import { routes } from './routes';

export const router = new Router(document.querySelector('#outlet'));
router.setRoutes(routes);

export const projectName = 'data-use-cases';

export const setTitle = (viewTitle?: string) => {
  document.title = viewTitle ? viewTitle + ' | ' + projectName : projectName;
};
