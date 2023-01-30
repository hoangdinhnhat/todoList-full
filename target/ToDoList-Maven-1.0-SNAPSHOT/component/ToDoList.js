import html from "../library/core.js";
import ToDoItem from "./ToDoItem.js";
import {connect} from "../library/store.js"

function ToDoList({todos, filter, filters})
{
    return html`
    <section class="main">
				<input 
					id="toggle-all" 
					class="toggle-all" 
					type="checkbox" 
					${todos.filter(filters.completed).length === todos.length && 'checked'}
					onclick="dispatch('markAllComplete', )"
				>
				<label for="toggle-all">Mark all as complete</label>
				<ul class="todo-list">
					${todos.filter(filters[filter]).map((todo) => ToDoItem({todo}))}
				</ul>
	</section>
    `
}

export default connect()(ToDoList)