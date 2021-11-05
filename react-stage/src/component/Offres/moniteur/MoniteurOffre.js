import React, { useContext, useEffect, useState } from 'react'
import { useHistory, useParams } from 'react-router';
import { UserInfoContext } from '../../../contexts/UserInfo';
import OffreService from '../../../services/OffreService';

const MoniteurOffre = () => {
    const [offre, setOffre] = useState({
        id: Number,
        titre: String,
        description: String,
        entreprise: String,
        valid: Boolean,
        adresse: String,
        dateDebut: String,
        dateFin: String,
        nbTotalSemaine: Number,
        nbTotalHeuresParSemaine: Number,
        horaire: String,
        tauxHoraire: String,
        gestionnaire: Object,
        moniteur: Object,
        whitelist: Array,
        applicants: Array
    });
    const { id } = useParams()
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const history = useHistory()

    useEffect(() => {
        if (!loggedUser.isLoggedIn || loggedUser.role !== "MONITEUR") history.push("/login")

        const fetchOffre = async () => {
            const dbOffre = await OffreService.getOffre(id)
            console.log(dbOffre + "blablabla")
            setOffre(dbOffre)
        }
        fetchOffre()
    }, [loggedUser, history, id])

    return (
        <div>
            {offre.titre}
        </div>
    )
}

export default MoniteurOffre
