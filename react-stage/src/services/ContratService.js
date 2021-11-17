const urlBase = 'http://localhost:9191/'

const ContratService = {
    getAllContrats: async () => {
        const res = await fetch(urlBase + 'contrats');
        const data = await res.json();
        return data;
    },

    getContrat: async (email) => {
        //const res = await fetch(url + email)
        //const data = await res.json()
        //return data
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
}

export default ContratService