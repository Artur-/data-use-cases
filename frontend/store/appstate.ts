import { RouterLocation } from '@vaadin/router';
import { RouteWithTitle } from 'Frontend/routes';
import { makeAutoObservable } from 'mobx';

export class AppState {
  applicationName: string = 'data-use-cases';

  // The location, relative to the base path, e.g. "hello" when viewing "/hello"
  location: string = '';

  currentViewTitle?: string;

  constructor() {
    makeAutoObservable(this);
  }

  setLocation(location: RouterLocation) {
    if (location.route) {
      this.location = location.route.path;
    } else {
      if (location.pathname.startsWith(location.baseUrl)) {
        this.location = location.pathname.substr(location.baseUrl.length);
      } else {
        this.location = location.pathname;
      }
    }
    this.currentViewTitle = (location?.route as RouteWithTitle)?.title;
  }
}
export const appState = new AppState();
