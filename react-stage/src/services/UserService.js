const urlBase = 'http://localhost:9191/user/'

const UserService = {
    getUserByEmail: async (email) => {
        const res = await fetch(urlBase + email)
        const data = await res.json()
        return data
    },

    getListAllEtudiants: async () => {
        const res = await fetch(urlBase + '/etudiants')
        const data = await res.json()
        return data
    },

    getEtudiantsForSuperviseur: async (idSuperviseur) => {
        console.log(urlBase + 'etudiants/' + idSuperviseur)
        const res = await fetch(urlBase + 'etudiants/' + idSuperviseur)
        const data = await res.json()
        console.log(data, "getEtudiantsForSuperviseur")
        return data
    }
}

export default UserService