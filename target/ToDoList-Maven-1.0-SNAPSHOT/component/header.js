import html from "../library/core.js";

function header()
{
    return html`
    <header class="header">
        <h1>todos</h1>
        <input 
            class="new-todo" 
            placeholder="What needs to be done?" 
            autofocus
            onkeyup="event.keyCode === 13 && this.value.trim().length > 0 && dispatch('add', this.value.trim())"
        >
    </header>
    `
}

export default header