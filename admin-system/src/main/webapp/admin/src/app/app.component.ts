import {Component, OnInit} from '@angular/core';
import Keycloak from "keycloak-js";
import {AuthConfig, OAuthService} from "angular-oauth2-oidc";
import {AdminService} from "../admin.service";
import {authConfig} from "../auth.config";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'admin';


  constructor(private oauthService: OAuthService,private adminService : AdminService) {
    this.configure();
  }

  keycloak = new Keycloak({
    clientId: "backend-service", url: "http://localhost:8543/",
    "realm": "quarkus",
  });

  ngOnInit(): void {
/*    console.log("============================")
    this.keycloak.init({
      onLoad: 'login-required',
      silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
    })*/
  }

  public login() {
    this.oauthService.initLoginFlow();
    // this.oauthService.initCodeFlow();
  }

  public logoff() {
    this.oauthService.logOut();
  }

  private configure() {
    this.oauthService.configure(authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
    this.oauthService.setupAutomaticSilentRefresh();
  }



  fetchRooms(): void {
    console.log(this.keycloak.token)

    this.adminService.getRooms()
      .subscribe(rooms => console.log(rooms));
  }


}
