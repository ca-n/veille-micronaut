const url = 'http://localhost:9191/contrat'

const ContratService = {
    saveContrat: async (contrat) => {
        const res = await fetch(url, 
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(contrat)
        })
        const data = await res.json()
        return data
    },

    getMoniteurContrats: async (moniteurCourriel) => {
        const res = await fetch(url + '/moniteur/courriel/' + moniteurCourriel)
        const data = await res.json()
        return data
    },

    getSuperviseurContrats: async (superviseurCourriel) => {
        const res = await fetch(url + '/superviseur/courriel/' + superviseurCourriel)
        const data = await res.json()
        return data
    }
}

export default ContratService