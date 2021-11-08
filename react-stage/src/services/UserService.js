const UserService = {
    getUserByEmail: async (email) => {
        const res = await fetch('http://localhost:8080/users/' + email)
        const data = await res.json()
        return data
    },

    getListAllEtudiants: async () => {
        const res = await fetch('http://localhost:8080/users/etudiants')
        const data = await res.json()
        return data
    }
}

export default UserService