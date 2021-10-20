import React from 'react'
import { useState, useEffect } from 'react'
import CVService from '../../services/CVService'

const VerificationCVList = () => {
    const [cvList, setCVList] = useState([])

    useEffect(() => {
        const getAllCVs = async () => {
            const pending = await CVService.getAllCVs()
            setCVList(pending);
        }
        getAllCVs();
    }, [])
    
    return (
        <div className="">
            
        </div>
    )
}

export default VerificationCVList
