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
            </table>
        </div>







    )

}

export default Rapports
