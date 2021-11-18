import React from 'react'
import { useState, useEffect, useRef } from 'react'
import SessionService from '../../services/SessionService'
import AllSessionEtudiant from './AllSessionEtudiant'
import AllSessionMoniteur from './AllSessionMoniteur'
import AllSessionOffre from './AllSessionOffre'
import AllSessionSuperviseur from './AllSessionSuperviseur'
import Swal from 'sweetalert2'


const AllSessionInfo = () => {
    const [allSessions, setAllSessions] = useState([])
    const selectedSession = useRef("All")
    const [reloadList, setReloadList] = useState(false)
    const [elementsPerPage, setElementsPerPage] = useState(10)
    const [currentSession, setCurrentSession] = useState({})



    useEffect(async () => {
        await getAllSessions()
        await getCurrentSession()
    }, [])

    const getAllSessions = async () => {
        const allSessionsData = await SessionService.getAllSessions()
        setAllSessions(allSessionsData)

    }

    const triggerReloadList = () => {
        setReloadList(!reloadList)
    }

    const getCurrentSession = async () => {
        const currentSession = await SessionService.getCurrentSession()
        setCurrentSession(currentSession)
    }


    const onSelectedSession = (e) => {
        selectedSession.current = e.target.value
        triggerReloadList()
    }

    const onNewSessionClick = () => {

        Swal.fire({
            title: 'Create new session (ex: AUT-2021, HIVER-2021)',
            input: 'text',
            showCancelButton: true,
            confirmButtonText: 'Add Session'
        }).then(async result => {
            console.log(result)
            if (result.isConfirmed) {
                if (newSessionValidation(result.value)) {
                    addNewSession(result.value)
                    await getAllSessions()
                    await getCurrentSession()
                    setElementsPerPage(elementsPerPage) //FORCE rerender because otherwise its 1 render behind...
                } else {
                    Swal.fire(
                        'Cancelled',
                        'Your Session Name was invalid',
                        'error'
                    )
                }
            }
        })
    }


    const newSessionValidation = (newSessionName) => {
        if (newSessionName === "") {
            return false
        }
        if (newSessionName === null) {
            return false
        }

        return true
    }

    const getListForSpecificSession = (listEntite) => {
        if (selectedSession.current !== "All") {
            return listEntite.filter((entite) => {
                if (entite.session === selectedSession.current) {
                    return entite
                }
            })
        }
        return listEntite
    }

    const addNewSession = async (newSessionName) => {
        await SessionService.addNewSession(newSessionName)

    }



    return (
        <div>
            <div className="row">
                <div className="col-1"></div>
                <button className="btn bg-danger text-white col-1 m-3" onClick={onNewSessionClick}>Start New Session </button>
                <div className="col-2"></div>
                <div className="col-3 text-center">
                    <h2>Session Actuelle: <strong>{currentSession.nomSession}</strong></h2>
                </div>
                <div className="col-2"></div>
                <select className="m-4 h4 col-2" onChange={onSelectedSession}>
                    <option value="All" selected>Tout Session</option>
                    {allSessions.map(session =>
                        <option key={session.id} value={session.nomSession}>{session.nomSession}</option>
                    )};
                </select>
                <div className="col-1"></div>
            </div>
            <AllSessionEtudiant getListForSpecificSession={getListForSpecificSession} reloadList={reloadList} elementsPerPage={elementsPerPage} />
            <br />
            <AllSessionSuperviseur getListForSpecificSession={getListForSpecificSession} reloadList={reloadList} elementsPerPage={elementsPerPage} />
            <br />
            <AllSessionMoniteur getListForSpecificSession={getListForSpecificSession} reloadList={reloadList} elementsPerPage={elementsPerPage} />
            <br />
            <AllSessionOffre getListForSpecificSession={getListForSpecificSession} reloadList={reloadList} elementsPerPage={elementsPerPage} />
        </div>
    )
}

export default AllSessionInfo
