import React, { useContext } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Nav, Navbar, NavDropdown } from 'react-bootstrap'
import './NavbarCSS.css'
import logo from './logo.svg'
import { Link } from 'react-router-dom';
import { UserInfoContext } from '../../contexts/UserInfo';
import { CopyToClipboard } from 'react-copy-to-clipboard';





const NavbarHTML = () => {
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext)

  const myFunction = () => {
    const url = encodeURIComponent("http://localhost:3000/moniteur");
    window.open(`mailto:?subject=${url}&body=Voici le lien pour vous inscrire`)
  }



  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Navbar.Brand as={Link} to="/dashboard"><img src={logo} className="App-logo" alt="logo" /></Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <NavDropdown title="Form" id="basic-nav-dropdown">
            <NavDropdown.Item><Link to="/etudiant">Form Etudiant</Link></NavDropdown.Item>
            <NavDropdown.Item><Link to="/superviseur">Form Superviseur</Link></NavDropdown.Item>
            <NavDropdown.Item><Link to="/moniteur">Form Moniteur</Link></NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item><Link to="/login">Login</Link></NavDropdown.Item>
          </NavDropdown>
          {loggedUser.isLoggedIn ?
            <Nav.Item>
              <Nav.Link as={Link} to="/account">Account details</Nav.Link>
            </Nav.Item>
            :
            null
          }
          {loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE" ?
            <NavDropdown  title="Url Inscription" id="basic-nav-dropdown">
              <NavDropdown.Item ><button  className="text-white"onClick={myFunction}>Email link</button></NavDropdown.Item>
              <NavDropdown.Item ><CopyToClipboard text={"http://localhost:3000/moniteur"}><button className="text-white">Copy to clipboard</button></CopyToClipboard></NavDropdown.Item>
            </NavDropdown>
            :
            null
          }
          {(loggedUser.isLoggedIn && loggedUser.role !== "SUPERVISEUR") ?
              <Nav.Link as={Link} to="/offres">Offres</Nav.Link>
            : null}

          {loggedUser.isLoggedIn && (loggedUser.role === "GESTIONNAIRE" ||  loggedUser.role === "MONITEUR")  ?
          <Nav.Link as={Link} to="/newOffre" >Créer offre de stage</Nav.Link>
          :
          null
        }
        {loggedUser.isLoggedIn && loggedUser.role === "ETUDIANT" ?
          <Nav.Link as={Link} to="/dropCv" >Ajouter ou voir cv</Nav.Link>
          :
          null
        }
        {loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE" ? 
        <Nav.Link as={Link} to="/gestion/cv">Voir et valider les CV</Nav.Link> : null
        }

        {loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE" ? 
        <Nav.Link as={Link} to="/gestion/superviseur">Gestion Superviseur</Nav.Link> : null
        }
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  )
}

export default NavbarHTML
