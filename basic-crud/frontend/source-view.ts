import { View } from '@vaadin/flow-frontend/util/view';
import { html, PropertyValues } from 'lit-element';
import { customElement, property, state } from 'lit/decorators';

@customElement('source-view')
export class SourceView extends View {
  @state()
  contents: string = '';
  @state()
  show: boolean = false;
  @property({ type: String })
  text: string = '';
  @property({ type: String })
  file: string = '';

  render() {
    return html`
      <div
        @click="${() => (this.show = true)}"
        @mouseover="${() => (this.show = true)}"
        @mouseout="${() => (this.show = false)}"
        style="display:inline-block;padding-right: 1em;padding-left: 1em;"
        id="text"
      >
        <u>${this.text}</u>
        <div
          style="border: 1px solid black;padding: 1em;position:absolute;z-index:1;background:white;"
          id="popup"
          ?hidden=${!this.show}
        >
          <pre>${this.contents}</pre>
        </div>
      </div>
    `;
  }

  async firstUpdated(_changedProperties: PropertyValues) {
    super.firstUpdated(_changedProperties);

    const resp = await fetch('https://raw.githubusercontent.com/Artur-/data-use-cases/master/' + this.file);
    this.contents = await resp.text();
  }
}
