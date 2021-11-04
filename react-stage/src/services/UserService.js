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

    getMoniteur: async (id) => {
        //const res = await fetch(urlBase + '/moniteur/' + id)
        //const data = await res.json()
        //return data
    },
    /*
        getSuperviseur: async (id) => {
            console.log(id, "id")
            const res = await fetch(urlBase + '/superviseur/' + id)
            const data = await res.json()
            return data
        }
    */
}

export default UserService