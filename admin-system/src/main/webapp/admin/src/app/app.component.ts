import {Component, OnInit} from '@angular/core';
import Keycloak from "keycloak-js";
import {AuthConfig, OAuthService} from "angular-oauth2-oidc";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'admin';


/*  keycloak = new Keycloak({
    clientId: "backend-service", url: "http://localhost:8543/",
    "realm": "quarkus",
});*/

  ngOnInit(): void {
/*    console.log("============================")
    this.keycloak.init({
      onLoad: 'login-required',
      silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
    })*/
  }

  constructor(private oauthService: OAuthService) {
    this.configure();
  }

  authConfig: AuthConfig = {
    issuer: 'http://localhost:8543/realms/quarkus',
    redirectUri: window.location.origin + "/",
    clientId: 'backend-service',
    scope: 'openid',
    responseType: 'code',
    // at_hash is not present in id token in older versions of keycloak.
    // use the following property only if needed!
    // disableAtHashCheck: true,
    showDebugInformation: true
  }

  public login() {
    this.oauthService.initLoginFlow();
  }

  public logoff() {
    this.oauthService.logOut();
  }

  private configure() {
    this.oauthService.configure(this.authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }




}
