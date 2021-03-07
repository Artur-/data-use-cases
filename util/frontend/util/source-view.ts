import { View } from './view';
import { html, PropertyValues } from 'lit-element';
import { customElement, property, query, state } from 'lit/decorators';
import * as Prism from 'prismjs';
import 'prismjs/components/prism-java.js';
import 'prismjs/components/prism-typescript.js';

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
  @state()
  language: string = 'java';

  @query('#code')
  private code!: HTMLElement;

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
          style="border: 1px solid black;
    padding: 1em;
    position: absolute;
    z-index: 1;
    background: white;
    bottom: 0;
    overflow: auto;
    top: 100px;"
          id="code"
          ?hidden=${!this.show}
        >
          <pre class="language-${this.language}"><code .textContent=${this.contents}></code></pre>
        </div>
      </div>
    `;
  }

  async firstUpdated(_changedProperties: PropertyValues) {
    super.firstUpdated(_changedProperties);

    const resp = await fetch('https://raw.githubusercontent.com/Artur-/data-use-cases/master/' + this.file);
    this.contents = await resp.text();
    const filename = this.file.substring(this.file.lastIndexOf('/') + 1);
    this.text = filename;
    [, this.language] = filename.split('.');
    await this.updateComplete;
    Prism.highlightAllUnder(this.code);
  }
}
