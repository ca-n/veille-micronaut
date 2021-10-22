import React, { useState, useEffect, useContext } from 'react'
import { useParams, useHistory } from 'react-router-dom'
import { Document, Page } from 'react-pdf/dist/esm/entry.webpack'
import { UserInfoContext } from '../../contexts/UserInfo';
import CVService from '../../services/CVService.js'
import './VerificationCV.css'

const VerificationCV = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [cv, setCV] = useState()
    const [numPages, setNumPages] = useState(0)
    const [page, setPage] = useState(1)
    const [pdfScale, setPdfScale] = useState(1)
    const { id } = useParams()
    const history = useHistory()

    useEffect(() => {
        if (!loggedUser.isLoggedIn || loggedUser.role !== "GESTIONNAIRE") history.push("/login")

        const getCV = async () => {
            const cv = await CVService.getCV(id)
            setCV(cv)
        }
        getCV()
    }, [id, loggedUser, history])

    const onDocumentLoad = ({ numPages }) => {
        setNumPages(numPages)
    }

    const onNextPage = () => {
        if (page >= numPages) return
        setPage(current => current + 1)
    }

    const onPrevPage = () => {
        if (page <= 1) return
        setPage(current => current - 1)
    }

    const onAccept = async () => {
        await CVService.acceptCV(cv)
        history.push("/gestion/cv")
    }

    const onReject = async () => {
        await CVService.rejectCV(cv)
        history.push("/gestion/cv")
    }

    const renderPageControls = (
        <div className="container">
            <div className="row center">
                <button className="btn btn-primary col-1 prevPage" onClick={onPrevPage}>&lt;</button>
                <div className="col-2 pages">Page {page} of {numPages}</div>
                <button className="btn btn-primary col-1 nextPage" onClick={onNextPage}>&gt;</button>
            </div>
        </div>
    )

    const renderScaleControls = (
        <div className="container">
            <div className="row center">
                <button className="btn btn-danger col-1 prevPage" onClick={onReject}>Rejeter</button>
                <div className="col-1" />
                <button className="btn btn-secondary btn-sm col-1" onClick={() => setPdfScale(scale => scale - 0.5)}>-</button>
                <div className="col-1 pages">{pdfScale * 100}%</div>
                <button className="btn btn-secondary btn-sm col-1" onClick={() => setPdfScale(scale => scale + 0.5)}>+</button>
                <div className="col-1" />
                <button className="btn btn-success col-1 nextPage" onClick={onAccept}>Accepter</button>
            </div>
        </div>
    )

    return (
        <div className="dark">
            {!cv ? '' :
                <div>
                    {renderScaleControls}
                    <Document file={`data:application/pdf;base64,${cv.data}`} onLoadSuccess={onDocumentLoad}>
                        <Page pageNumber={page} scale={pdfScale} renderAnnotationLayer={false} />
                    </Document>
                    {numPages > 1 ? renderPageControls : ''}
                </div>
            }
        </div>
    )
}

export default VerificationCV
