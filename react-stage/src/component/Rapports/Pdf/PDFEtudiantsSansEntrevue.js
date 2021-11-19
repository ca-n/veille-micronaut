import { React, useState } from 'react'
import { Document, Page } from 'react-pdf/dist/esm/entry.webpack'


const PDFEtudiantsSansEntrevue = () => {
    const [numPages, setNumPages] = useState(null);
    const [pageNumber, setPageNumber] = useState(1);

    function onDocumentLoadSuccess({ numPages }) {
        setNumPages(numPages);
    }
    return (
        <div>
            < div >
                <Document
                    file={'http://localhost:9191/rapport/pdf/etudiantsSansEntrevue'}
                    onLoadSuccess={onDocumentLoadSuccess}
                >
                    <Page pageNumber={pageNumber} />
                </Document>
                {/* <p>Page {pageNumber} of {numPages}</p> */}
            </div >
        </div>
    )
}

export default PDFEtudiantsSansEntrevue
