export default function (reducer)
{
    return (state, action, values) =>
    {
        let nextState = reducer(state, action, values)
        console.group(action)
        console.log('Previous State: ', state)
        console.log('Arguments: ', values)
        console.log('Next State: ', nextState)
        console.groupEnd()
        return nextState
    }
}