import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { UserInfoContext } from '../../contexts/UserInfo'
import ContratService from '../../services/ContratService'

const ListEtudiantToEvaluate = ({contrats, onClick}) => {
    return (
        <div>
            <h1>Liste des étudiants à évaluer</h1>
            <table>
                <thead>
                    <tr>
                        <th>Nom</th>
                    </tr>
                </thead>
                <tbody>
                    {contrats.length > 0 ? contrats.map(contrat => 
                        <tr key={contrat.id}>
                            <td onClick={() => onClick(contrat.id)}>{contrat.etudiant.prenom} {contrat.etudiant.nom}</td>
                        </tr>
                    )
                    : <tr><td>Aucun étudiants à afficher</td></tr>}
                </tbody>
            </table>
            {contrats.length > 0 ? <></>
            : ''}
        </div>
    )
}

export default ListEtudiantToEvaluate
