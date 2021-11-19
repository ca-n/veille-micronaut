import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { UserInfoContext } from '../../contexts/UserInfo'
import ContratService from '../../services/ContratService'

const ListEntrepriseToEvaluate = ({contrats, onClick}) => {
    return (
        <div>
            <h1>Liste des entreprises à évaluer</h1>
            <table>
                <thead>
                    <tr>
                        <th>Nom d'entreprise</th>
                    </tr>
                </thead>
                <tbody>
                    {contrats.length > 0 ? contrats.map(contrat => 
                        <tr key={contrat.id}>
                            <td onClick={() => onClick(contrat.id)}>{contrat.moniteur.nomEntreprise}</td>
                        </tr>
                    )
                    : <tr><td>Aucun entreprise à afficher</td></tr>}
                </tbody>
            </table>
            {contrats.length > 0 ? <></>
            : ''}
        </div>
    )
}

export default ListEntrepriseToEvaluate
