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

    getEtudiantsForSuperviseur: async (idSuperviseur) => {
        const res = await fetch(urlBase + '/superviseur/' + idSuperviseur + '/etudiants')
        const data = await res.json()
        return data
    }
}

export default UserService