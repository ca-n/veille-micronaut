const urlBase = 'http://localhost:9191/user'

const UserService = {
    getUserByEmail: async (email) => {
        const res = await fetch(urlBase + '/' + email)
        const data = await res.json()
        return data
    },

    getListAllEtudiants: async () => {
        const res = await fetch(urlBase + '/etudiants')
        const data = await res.json()
        return data
    },

    getListEtudiantWithoutSuperviseur: async () => {
        const res = await fetch(urlBase + '/etudiants/nosuperviseur')
        const data = await res.json()
        return data
    },

    getListAllSuperviseurs: async () => {
        const res = await fetch(urlBase + '/superviseurs')
        const data = await res.json()
        return data
    },

    getListEtudiantSuperviseur: async (superviseurId) => {
        const res = await fetch(urlBase + '/superviseur/etudiants/' + superviseurId)
        const data = await res.json()
        return data
    },
    getMoniteur: async (id) => {
        //const res = await fetch(urlBase + '/moniteur/' + id)
        //const data = await res.json()
        //return data
    },

}

export default UserService