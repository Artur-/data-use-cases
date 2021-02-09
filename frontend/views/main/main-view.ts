import '@vaadin/vaadin-app-layout';
import { AppLayoutElement } from '@vaadin/vaadin-app-layout/src/vaadin-app-layout';
import '@vaadin/vaadin-app-layout/vaadin-drawer-toggle';
import '@vaadin/vaadin-avatar/vaadin-avatar';
import '@vaadin/vaadin-tabs';
import '@vaadin/vaadin-tabs/vaadin-tab';
import { getViewRoutes } from 'Frontend/routes';
import { appState } from 'Frontend/store/appstate';
import { customElement, html } from 'lit-element';
import { router } from '../../index';
import { Layout } from '../view';
import styles from './main-view.css';

@customElement('main-view')
export class MainView extends Layout {
  static get styles() {
    return styles;
  }

  render() {
    return html`
      <vaadin-app-layout primary-section="drawer">
        <header slot="navbar" theme="dark">
          <vaadin-drawer-toggle></vaadin-drawer-toggle>
          <h1>${appState.currentView?.title}</h1>
          <vaadin-avatar></vaadin-avatar>
        </header>

        <div slot="drawer">
          <div id="logo">
            <img src="images/logo.png" alt="${appState.applicationName} logo" />
            <span>${appState.applicationName}</span>
          </div>
          <hr />
          <vaadin-tabs orientation="vertical" theme="minimal" .selected=${this.getSelectedViewRoute()}>
            ${getViewRoutes().map(
              (viewRoute) => html`
                <vaadin-tab>
                  <a href="${router.urlForPath(viewRoute.path)}" tabindex="-1">${viewRoute.title}</a>
                </vaadin-tab>
              `
            )}
          </vaadin-tabs>
        </div>
        <slot></slot>
      </vaadin-app-layout>
    `;
  }
  getSelectedViewRoute(): number {
    return getViewRoutes().findIndex((viewRoute) => viewRoute == appState.currentView);
  }
  connectedCallback() {
    super.connectedCallback();

    this.reaction(
      () => appState.location,
      () => {
        AppLayoutElement.dispatchCloseOverlayDrawerEvent;
      }
    );
  }
}
