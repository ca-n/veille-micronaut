import React from 'react'

const Offre = ({offre}) => {
    return (
        <div className='container' key={offre.id}>
            <li><span>{offre.titre}</span>|<span>{offre.entreprise}</span><span>{offre.description}</span></li>
        </div>
    )
}

export default Offre
