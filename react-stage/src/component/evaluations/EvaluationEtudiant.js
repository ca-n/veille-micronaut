import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { UserInfoContext } from '../../contexts/UserInfo'
import FormEvaluationEtudiant from './FormEvaluationEtudiant'
import ListEtudiantToEvaluate from './ListEtudiantToEvaluate'

const EvaluationEtudiant = () => {
    const loggedUser = useContext(UserInfoContext)
    const history = useHistory()
    const [contrats, setContrats] = useState([])
    const [currentContrat, setCurrentContrat] = useState()

    useEffect(() => {
        //if (!loggedUser.isLoggedIn || loggedUser.role !== "MONITEUR") history.push("/login")

        const getContrats = async () => {
            //const contratList = await ContratService.getMoniteurContratsToEvaluate(loggedUser.courriel)
            const res = await fetch("http://localhost:9191/contrat")
            const contratList = await res.json()
            //
            console.log(contratList)
            setContrats(contratList)
        }
        getContrats()
        
    }, [loggedUser, history])

    const selectContrat = (id) => {
        console.log(id)
        setCurrentContrat(contrats.find(contrat => contrat.id === id))
    }

    return (
        <div>
            {currentContrat === undefined ? <ListEtudiantToEvaluate contrats={contrats} onClick={selectContrat}/> : <FormEvaluationEtudiant contrat={currentContrat} />}
        </div>
    )
}

export default EvaluationEtudiant
