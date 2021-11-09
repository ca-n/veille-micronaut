import { React, useState, useEffect, useContext } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo'


const Entrevue = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [etudiant, setEtudiant] = useState()
    const [entrevues, setEntrevues] = useState([])

    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    setEtudiant(data)
                    fetch(`http://localhost:9191/entrevue/etudiant/${data.id}`)
                        .then(res => {
                            return res.json()
                        })
                        .then(data => {
                            setEntrevues(data)
                            console.log(data, "data")

                        })
                })
        }
    }, [])

    const entrevuesList = entrevues.map((entrevue) =>
        <tr key={entrevue.id.toString()}>
            <td>{entrevue.titre}</td>
            <td>{entrevue.date}</td>
        </tr>);

    return (
        <div>
            <h1>Entrevues</h1>
            <table>
                <tr>
                    <th>Titre</th>
                    <th>Date</th>
                </tr>
                {entrevuesList}
            </table>
        </div>

    )
}

export default Entrevue
