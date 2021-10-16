import { React, useState, useContext, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo';
import './DropCv.css'

const DropCv = () => {
    const [etudiant, setEtudiant] = useState()
    const [cvs, setCvs] = useState([]);
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [files, setFile] = useState(null)
    const OnInputChange = (e) => {

        console.log(e.target.files)
        setFile(e.target.files[0])
    }

    const fileToBase64 = (file, cb) => {
        const reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload = function () {
            cb(null, reader.result)
        }
        reader.onerror = function (error) {
            cb(error, null)
        }
    }

    const OnSubmit = (e) => {
        e.preventDefault()

        fileToBase64(files, (err, result) => {
            if (result) {
                result = result.substring(28)
                console.log(result)

                if (loggedUser.isLoggedIn) {
                    fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                        .then(res => {
                            return res.json();
                        })
                        .then(data => {
                            console.log(data, "data")
                            console.log(data.id)
                            setEtudiant(data)

                            var request = new XMLHttpRequest();
                            request.open('POST', 'http://localhost:9191/stage/cv', true);
                            request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
                            let cv = { data: result, etudiant: data }
                            cv = JSON.stringify(cv);
                            request.send(cv)
                        })
                }
            }
        })

    }

    const cvList = cvs.map((cv) =>
        <tr key={cv.id.toString()}>
            <td colSpan='3'>{cv.id}</td>
            <td colSpan='3'>{cv.dateSoumission}</td>
        </tr>);

    const fetchEtudiant = async () => {
        const res = await fetch(`http://localhost:9191/user/${loggedUser.courriel}`);
        const data = await res.json()
        return data
    }

    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    setEtudiant(data)
                    fetch(`http://localhost:9191/stage/cv/etudiant/${data.id}`)
                        .then(res => {
                            return res.json()
                        })
                        .then(data => {
                            console.log(data)
                            setCvs(data)
                        })
                })
        }
    }, [])

    return (
        <div>
            <form method="post" action="#" id="#" onSubmit={OnSubmit}>

                <div class="form-group files">
                    <label>Upload Your File </label>
                    <input type="file" onChange={OnInputChange} class="form-control" multiple="" />
                </div>
                <button type="submit">Submit</button>
            </form>
            {cvList}
        </div>
    )
}

export default DropCv

