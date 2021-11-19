const urlBase = 'http://localhost:9191/'

const ContratService = {

    getContrat: async (email) => {
        //const res = await fetch(url + email)
        //const data = await res.json()
        //return data
    },
    getAllContrats: async () => {
        const res = await fetch(urlBase + 'contrats');
        const data = await res.json();
        return data;
    },

    getContratsByMoniteurEmail: async (email) => {
        const res = await fetch(urlBase + 'contrats/moniteur/' + email);
        const data = await res.json();
        return data;
    },

    getContratsByEtudiantEmail: async (email) => {
        const res = await fetch(urlBase + 'contrats/etudiant/' + email);
        const data = await res.json();
        return data;
    },

    saveContrat: async (values) => {
        const res = await fetch(urlBase + 'contrat',
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(values)
            })
        const data = await res.json()
        return data
    },
}

export default ContratService