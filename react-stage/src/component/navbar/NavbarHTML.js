import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Nav ,Navbar, Container, NavDropdown } from 'react-bootstrap'
import './NavbarCSS.css'
import logo from './logo.svg'
import { Link } from 'react-router-dom'
import {CopyToClipboard} from 'react-copy-to-clipboard';



const NavbarHTML = () => {

  const myFunction = () => {
    const url = encodeURIComponent("http://localhost:3000/moniteur");
    window.open(`mailto:user@example.com?subject=${url}&body=Voici le lien pour vous inscrire`)
  }
  


    return (
        <Navbar bg="dark" variant="dark" expand="lg">
          <Navbar.Brand href="/"><img src={logo} className="App-logo" alt="logo" /></Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <NavDropdown title="Form" id="basic-nav-dropdown">
                <NavDropdown.Item href="/etudiant">Form Etudiant</NavDropdown.Item>
                <NavDropdown.Item href="/superviseur">Form Superviseur</NavDropdown.Item>
                <NavDropdown.Item href="/moniteur">Form Moniteur</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="/login">Login</NavDropdown.Item>
              </NavDropdown>
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
