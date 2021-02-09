import { Router } from '@vaadin/router';
import { routes } from './routes';
import { appState } from './store/appstate';

export const router = new Router(document.querySelector('#outlet'));
router.setRoutes(routes);

window.addEventListener('vaadin-router-location-changed', (e) => {
  appState.setLocation(e.detail.location);
});
