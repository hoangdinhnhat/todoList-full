export default async function requestAPI(url, method, datas)
{
    let APIs = url

    let options = {
        method: method,
        mode: "no-cors",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(datas)
    }

    const response = await fetch(APIs, options)
    return response.json()
}