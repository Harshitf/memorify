import Keycloak from 'keycloak-js';

const keycloakConfig = {
    url: 'http://localhost:8762/',
    realm: 'master',
    clientId: 'memorify-app',
};

const keycloak = new Keycloak(keycloakConfig);

// Set init options
export const initOptions = {
    onLoad: 'login-required',
    pkceMethod: 'S256', // PKCE is crucial for SPA security
    silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html', // For silent refresh
};

export default keycloak;