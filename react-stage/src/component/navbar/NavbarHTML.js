import React, { useContext } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Nav, Navbar, Container, NavDropdown } from 'react-bootstrap'
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
      <Navbar.Brand as={Link} to="/"><img src={logo} className="App-logo" alt="logo" /></Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">

          <NavDropdown title="Form" id="basic-nav-dropdown">
            <NavDropdown.Item> <Link to="/etudiant">Form Etudiant</Link></NavDropdown.Item>
            <NavDropdown.Item><Link to="/superviseur">Form Superviseur</Link></NavDropdown.Item>
            <NavDropdown.Item><Link to="/moniteur">Form Moniteur</Link></NavDropdown.Item>


            <NavDropdown.Divider />
            <NavDropdown.Item><Link to="/login">Login</Link></NavDropdown.Item>

            <NavDropdown.Divider />
            <NavDropdown.Item href="/newOffre">Créer offre de stage</NavDropdown.Item>
          </NavDropdown>
          <Nav.Item href="/newOffre">Créer offre de stage</Nav.Item>
          {loggedUser.isLoggedIn ?
            <Nav.Item>
              <Nav.Link as={Link} to="/account" >Account details</Nav.Link>
            </Nav.Item>
            :
            null
          }
          <NavDropdown title="Url Inscription" id="basic-nav-dropdown">
            <NavDropdown.Item><button onClick={myFunction}>Email link</button></NavDropdown.Item>
            <NavDropdown.Item><CopyToClipboard text={"http://localhost:3000/moniteur"}><button>Copy to clipboard</button></CopyToClipboard></NavDropdown.Item>
          </NavDropdown>

        </Nav>
      </Navbar.Collapse>
    </Navbar>
  )
}

export default NavbarHTML
