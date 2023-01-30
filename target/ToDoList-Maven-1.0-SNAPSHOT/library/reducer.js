import dataAPI from '../util/db.js'

var init =
{
    todos: [],
    filter: 'all',
    filters: {
        all: () => true,
        completed: (todo) => todo.completed,
        active: (todo) => !todo.completed
    }
}

const actions = {
    add(state, values) {
        let [newTodo] = values
        state.todos = [...state.todos, { title: newTodo, completed: false }]
        dataAPI.set(state.todos)
    },
    destroy(state, values) {
        let [deleteIndex] = values
        state.todos.splice(deleteIndex, 1)
        dataAPI.set(state.todos)
    },
    markComplete(state, values) {
        let [clickIndex] = values
        state.todos[clickIndex].completed = !state.todos[clickIndex].completed
        dataAPI.set(state.todos)
    },
    markAllComplete(state, values) {
        let todos = state.todos
        let filters = state.filters
        let isAll = (todos.filter(filters.completed).length === todos.length)
        if (isAll) {
            todos.forEach((todo) => {
                todo.completed = false
            })
        } else {
            todos.forEach((todo) => {
                todo.completed = true
            })
        }
        dataAPI.set(state.todos)
    },
    changeFilter(state, values) {
        let [newFilter] = values
        state.filter = newFilter
        dataAPI.set(state.todos)
    },
    clearCompleted(state, values) {
        let completeds = state.todos.filter(state.filters.completed)
        state.todos = state.todos.filter((todo) => completeds.indexOf(todo) === -1)
        dataAPI.set(state.todos)
    },
    createInitData(state, values)
    {
        let [initData] = values
        state.todos = initData
    }
}

export default function reducer(state = init, action, values) {
    actions[action] && actions[action](state, values)
    return state
}