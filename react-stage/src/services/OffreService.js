
const urlBase = 'http://localhost:9191/offres'
const OffreService = {
    // getAllOffres [GET] /offres
    getAllOffres: async () => {
        const res = await fetch(urlBase)
        const data = await res.json()
        return data
    },

    // getEtudiantOffres [GET] /offres/etudiant/{etudiantCourriel}
    getEtudiantOffres: async (etudiantCourriel) => {
        const res = await fetch(urlBase + '/etudiant/' + etudiantCourriel)
        const data = await res.json()
        return data
    },

    // getMoniteurOffres [GET] /offres/moniteur/{moniteurCourriel}
    getMoniteurOffres: async (moniteurCourriel) => {
        const res = await fetch(urlBase + '/moniteur/' + moniteurCourriel)
        const data = await res.json()
        return data
    },

    // addOffre [POST] /offres/{authorEmail}
    addOffre: async (offre, authorEmail) => {
        const res = await fetch(urlBase + '/' + authorEmail,
        {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(offre)
        })
        const data = await res.json()
        return data
    },

    // updateOffre [PUT] /offres/{id}
    updateOffre: async (offre) => {
        const res = await(fetch(urlBase + '/' + offre.id),
        {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(offre)
        })
        const data = await res.json()
        return data
    },

    // applyForOffre [POST] /offres/{id}/apply 
    // body: email
    applyForOffre: async (id, email) => {
        const res = await fetch(`${urlBase}/${id}/apply`,
        {
            method: 'POST',
            headers: {
                'Content-type': 'text/plain'
            },
            body: email
        })
        const data = await res.json()
        return data
    }
}

export default OffreService