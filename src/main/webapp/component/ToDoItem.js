import html from "../library/core.js";
import { connect } from "../library/store.js";

function ToDoItem({todo, todos})
{
    return html`
		<li${todo.completed && ' class="completed"'}>
			<div class="view">
				<input 
					class="toggle" 
					type="checkbox" 
					${todo.completed && 'checked'}
					onclick="dispatch('markComplete', ${todos.indexOf(todo)})"
				>
				<label>${todo.title}</label>
				<button class="destroy" onclick="dispatch('destroy', ${todos.indexOf(todo)})"></button>
			</div>
			<input class="edit" value="${todo.title}">
		</li>
    `
}

export default connect()(ToDoItem)