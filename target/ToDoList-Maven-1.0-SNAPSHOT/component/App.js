import html from "../library/core.js";
import { connect } from "../library/store.js";
import header from "./header.js";
import ToDoList from "./ToDoList.js";
import footer from "./footer.js";

function App({todos})
{
    return html`
        ${header()}
        ${ToDoList()}
        ${todos.length > 0 && footer()}
    `
}

export default connect()(App)

