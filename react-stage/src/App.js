import './App.css';
import FormEtudiant from './component/inscriptionEtudiant/FormEtudiant'
import FormSuperviseur from './component/inscriptionSuperviseur/FormSuperviseur';
import FormMoniteur from './component/inscriptionMoniteur/FormMoniteur'
import LoginUser from './component/loginUser/LoginUser';
import Navbar from './component/navbar/NavbarHTML'
import Home from './component/Home/Home';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import FormOffre from "./component/deposeOffreDeStage/FormOffre";



function App() {
  return (
    <Router>
    <div className="App">
      <Navbar/>
      <Switch>
        <Route exact path="/"><Home/></Route>
        <Route exact path="/etudiant"><FormEtudiant/></Route>
        <Route exact path="/superviseur"><FormSuperviseur/></Route>
        <Route exact path="/moniteur"><FormMoniteur/></Route>
        <Route exact path="/login"><LoginUser/></Route>
        <Route exact path="/newOffre"><FormOffre/></Route>
      </Switch>
    </div>
    </Router>
  );
}


export default App;
