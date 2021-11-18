const urlBase = 'http://localhost:9191/entrevue'
const EntrevueService = {
    addEntrevue: async (entrevue) => {
        const res = await fetch(urlBase,
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(entrevue)
            })
        const data = await res.json()
        return data
    }
}

export default EntrevueService