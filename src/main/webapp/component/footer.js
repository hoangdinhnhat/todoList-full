import html from "../library/core.js";
import { connect } from "../library/store.js";

function footer({todos, filter, filters})
{
    return html`
    <footer class="footer">
			<span class="todo-count"><strong>${todos.filter(filters.active).length}</strong> item left</span>
				<ul class="filters">
                    ${Object.keys(filters).map((item) => 
                        `
                        <li onclick="dispatch('changeFilter', '${item}')">
						    <a 
                                ${item === filter && 'class="selected"'} 
                                href="#/"
                            >${item.charAt(0).toUpperCase() + item.slice(1)}</a>
					    </li>
                        `
                    )}
				</ul>
			<button 
                class="clear-completed" 
                ${todos.filter(filters.completed).length === 0 && 'hidden'}
                onclick="dispatch('clearCompleted')"
            >
                Clear completed
            </button>
	</footer>
    `
}

export default connect()(footer)