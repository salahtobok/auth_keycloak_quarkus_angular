import { AuthConfig } from 'angular-oauth2-oidc';

export const   authConfig: AuthConfig = {
  issuer: 'http://localhost:8543/realms/quarkus',
  redirectUri: window.location.origin + "/",
  clientId: 'backend-service',
  scope: 'openid',
  responseType: 'code',
  strictDiscoveryDocumentValidation : true,
  // at_hash is not present in id token in older versions of keycloak.
  // use the following property only if needed!
  // disableAtHashCheck: true,
  showDebugInformation: true
}
