import { React, useState, useRef } from 'react'
import { saveAs } from 'file-saver'
import "./RapportCSS.css"

const Rapports = () => {
    const downloadOffresValid = () => {
        saveAs("http://localhost:9191/rapport/pdf/offresValide")
    }
    const downloadOffresInvalid = () => {
        saveAs("http://localhost:9191/rapport/pdf/offresInvalid")
    }
    const downloadEtudiantsInscrient = () => {
        saveAs("http://localhost:9191/rapport/pdf/etudiantsInscrient")
    }
    const downloadCVPendingRejected = () => {
        saveAs("http://localhost:9191/rapport/pdf/cvPendingRejected")
    }
    const downloadEtudiantsSansCv = () => {
        saveAs("http://localhost:9191/rapport/pdf/etudiantsSansCv")
    }
    const downloadEtudiantsSansEntrevue = () => {
        saveAs("http://localhost:9191/rapport/pdf/etudiantsSansEntrevue")
    }
    const downloadEtudiantsAttenteEntrevue = () => {
        saveAs("http://localhost:9191/rapport/pdf/etudiantsAttenteEntrevue")
    }
    const downloadEtudiantsAttenteReponseEntrevue = () => {
        saveAs("http://localhost:9191/rapport/pdf/etudianntsAttenteReponse")
    }
    const downloadEtudiantsTrouveStage = () => {
        saveAs("http://localhost:9191/rapport/pdf/etudiantsTrouveStage")
    }




    return (
        <div>
            <h1></h1>
            <table>
                <tr>
                    <th colSpan="2">Offres valide</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadOffresValid} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/offresValides", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Offres invalide</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadOffresInvalid} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/offresInvalides", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants inscrient</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadEtudiantsInscrient} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/etudiantsInscrient", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants ayant un cv rejeté ou en attente</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadCVPendingRejected} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/cvPendingRejected", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants ayant aucun cv</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadEtudiantsSansCv} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/etudiantsSansCV", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants ayant aucune entrevue</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadEtudiantsSansEntrevue} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/etudiantsSansEntrevue", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants en attente de leur(leurs) entrevue(s)</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadEtudiantsAttenteEntrevue} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/etudiantsAttenteEntrevue", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants de la réponse de leur entrevue</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadEtudiantsAttenteReponseEntrevue} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/etudiantsAttenteReponseEntrevue", "_blank")}>Open</button>
                    </td>
                </tr>

                <tr>
                    <th colSpan="2">Étudiants ayant trouvé un stage</th>
                </tr>
                <tr>
                    <td>
                        <button onClick={downloadEtudiantsTrouveStage} >Download</button>
                    </td>
                    <td>
                        <button onClick={() => window.open("http://localhost:3000/rapports/etudiantsTrouveStage", "_blank")}>Open</button>
                    </td>
                </tr>
            </table>
        </div>







    )

}

export default Rapports
