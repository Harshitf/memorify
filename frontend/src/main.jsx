import {createRoot} from 'react-dom/client';
import {ReactKeycloakProvider} from '@react-keycloak/web';
import keycloack,{initOptions} from './app/utils/keycloack/keycloak.js'
// import './index.css'
import Routes from "./Routes.jsx";

createRoot(document.getElementById('root')).render(
    <ReactKeycloakProvider authClient={keycloack} initOptions={initOptions}>
        <Routes/>
    </ReactKeycloakProvider>)
