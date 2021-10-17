const CVService = {
    acceptCV: async (cv) => {
        const res = await fetch('http://localhost:9191/stage/cv/accept',
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
        const res = await fetch('http://localhost:9191/stage/cv/reject',
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

    getPendingCVs: async () => {
        const res = await fetch('http://localhost:9191/stage/cv/pending')
        const data = await res.json()
        return data
    }
}

export default CVService