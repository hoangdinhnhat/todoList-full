import requestAPI from "./requestToApi.js";

var dataAPI = {
    datas: [],
    load(data)
    {
        this.datas = data
    },
    get()
    {
        return this.datas;
    },
    set(data)
    {
        console.log(data);
        requestAPI('todos', 'POST', data)
        .then((response) => response.json())
    }
}

export default dataAPI