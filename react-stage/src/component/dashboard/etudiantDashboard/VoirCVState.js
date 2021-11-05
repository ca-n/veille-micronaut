import { React, useState, useContext, useEffect } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo';
import { saveAs } from 'file-saver'
import { AiOutlineCheckCircle, AiOutlineCloseCircle, AiOutlineClockCircle } from 'react-icons/ai'

const VoirCVState = () => {
    const [etudiant, setEtudiant] = useState()
    const [cvs, setCvs] = useState([]);
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)

    const updateCvs = async () => {
        fetch(`http://localhost:9191/stage/cv/etudiant/${etudiant.id}`)
            .then(res => {
                return res.json()
            })
            .then(data => {
                setCvs(data)
            })
    }

    const deleteCV = async (cv) => {
        const res = await fetch(`http://localhost:9191/stage/cv/delete/${cv.id}`, { method: 'DELETE' })
        await res.json().then(updateCvs())
    }

    const download = (cv) => {
        saveAs(`http://localhost:9191/stage/cv/pdf/${cv.id}`)
    }

    const getStatusIcon = (status) => {
        switch (status) {
            case "PENDING":
                return <AiOutlineClockCircle color="gold" size="48px" />
            case "ACCEPTED":
                return <AiOutlineCheckCircle color="green" size="48px" />
            case "REJECTED":
                return <AiOutlineCloseCircle color="red" size="48px" />
            default:
                return;
        }
    }
    
    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    setEtudiant(data)
                    fetch(`http://localhost:9191/cv/etudiant/${data.id}`)
                        .then(res => {
                            return res.json()
                        })
                        .then(data => {
                            setCvs(data)
                        })
                })
        }
    }, [])

    const cvList = cvs.map((cv) =>
        <tr key={cv.id.toString()}>
            <td>{cv.nom}</td>
            <td>{cv.dateSoumission}</td>
            <td><button onClick={() => deleteCV(cv)}>effacer</button></td>
            <td><button onClick={() => download(cv)}>télécharger</button></td>
            <td>{getStatusIcon(cv.status)}</td>
        </tr>);


    return (
        <div>
            {cvs.length > 0 ? <table>
                <tr>
                    <th>nom du fichier</th>
                    <th>Date de soumission</th>
                    <th>effacer</th>
                    <th>télécarger</th>
                    <th>Statut du CV</th>
                </tr>
                {cvList}
            </table> : null}
        </div>
    )
}

export default VoirCVState

