
const urlBase = 'http://localhost:9191/offres'
const OffreService = {
    // getAllOffres [GET] /offres
    getAllOffres: async () => {
        const res = await fetch(urlBase)
        const data = await res.json()
        return data
    },

    // getEtudiantOffres [GET] /offres/{etudiantCourriel}
    getEtudiantOffres: async (etudiantCourriel) => {
        const res = await fetch(urlBase + '/etudiant/' + etudiantCourriel)
        const data = await res.json()
        return data
    },

    // saveOffre [POST] /offres
    saveOffre: async (offre) => {
        const res = await fetch(urlBase, 
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
    },

    // // saveWhitelist [POST] /stage/whitelist
    // saveWhitelist: async (whitelist) => {
    //     const res = await fetch('http://localhost:9191/stage/whitelist', 
    //     {
    //         method: 'POST',
    //         headers: {
    //             'Content-type': 'application/json'
    //         },
    //         body: JSON.stringify(whitelist)
    //     })
    //     const data = await res.json()
    //     return data
    // }
}

export default OffreService