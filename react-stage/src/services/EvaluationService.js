const url = 'http://localhost:9191/evaluations'

const EvaluationService = {
    saveEvaluationEtudiant: async (evaluation) => {
        const res = await fetch(url + '/etudiant',
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(evaluation)
        })
        const data = await res.json()
        return data
    },

    saveEvaluationEntreprise: async (evaluation) => {
        const res = await fetch(url + '/entreprise',
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(evaluation)
        })
        const data = await res.json()
        return data
    },

    getCurrentEvaluationsEtudiant: async () => {
        const res = await fetch(url + '/etudiant')
        const data = res.json()
        return data
    },

    getCurrentEvaluationsEntreprise: async () => {
        const res = await fetch(url + '/entreprise')
        const data = res.json()
        return data
    },

    getAllEvaluationsEtudiant: async () => {
        const res = await fetch(url + '/etudiant/allSessions')
        const data = res.json()
        return data
    },

    getAllEvaluationsEntreprise: async () => {
        const res = await fetch(url + '/entreprise/allSessions')
        const data = res.json()
        return data
    },

    getEvaluationEtudiant: async (evalId) => {
        const res = await fetch(url + '/etudiant/' + evalId)
        const data = res.json()
        return data
    },

    getEvaluationEntreprise: async (evalId) => {
        const res = await fetch(url + '/entreprise/' + evalId)
        const data = res.json()
        return data
    }
}

export default EvaluationService