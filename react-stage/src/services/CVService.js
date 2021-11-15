const url = 'http://localhost:8080/cvs'
const CVService = {
    acceptCV: async (cv) => {
        const res = await fetch(url + '/accept',
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(cv)
        })
        const data = await res.json()
        return data
    },

    rejectCV: async (cv) => {
        const res = await fetch(url + '/reject',
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(cv)
        })
        const data = await res.json()
        return data
    },

    getAllCVs: async () => {
        const res = await fetch(url)
        const data = await res.json()
        return data
    },

    getCV: async (id) => {
        const res = await fetch(url + '/' + id)
        const data = await res.json()
        return data
    }
}

export default CVService