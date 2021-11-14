import React from 'react'
import { useState, useEffect, useRef } from 'react'
import SessionService from '../../services/SessionService'
import AllSessionEtudiant from './AllSessionEtudiant'
import AllSessionMoniteur from './AllSessionMoniteur'
import AllSessionOffre from './AllSessionOffre'
import AllSessionSuperviseur from './AllSessionSuperviseur'

const AllSessionInfo = () => {
    const [allSessions, setAllSessions] = useState([])
    const selectedSession = useRef("All")
    const [reloadList, setReloadList] = useState(false)
    const [elementsPerPage, setElementsPerPage] = useState(10)



    useEffect(async () => {
        const allSessionsData = await SessionService.getAllSessions()
        setAllSessions(allSessionsData)
    }, [])

    const triggerReloadList = () => {
        setReloadList(!reloadList)
    }


    const onSelectedSession = (e) => {
        selectedSession.current = e.target.value
        triggerReloadList()
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


    useEffect(() => {
        console.log(allSessions)
    }, [allSessions])

    return (
        <div>
            <div className="row">
                <div className="col-9"></div>
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
