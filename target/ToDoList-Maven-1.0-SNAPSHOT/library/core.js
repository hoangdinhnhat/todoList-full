export default function html([first, ...strings], ...values)
{
    return values.reduce((acc, curr) => 
        acc.concat(curr, strings.shift())
    , [first])
    .filter(x => x && x !== true || x === 0)
    .join('')
}

export function createStore(reducer)
{
    let state = reducer()
    let roots = new Map()

    function render()
    {
        roots.forEach((component, root) =>
        {
            let output = component()
            root.innerHTML = output
        })
    }

    const store = {
        attach(component, root, initData)
        {
            roots.set(root, component);
            render()
        },
        connect(selector = state => state)
        {
            return (component) => (props, ...args) => 
                component(Object.assign({}, props, selector(state), ...args))
        },
        dispatch(action, ...values)
        {
            state = reducer(state, action, values)
            render()
        }
    }
    
    return store
}
