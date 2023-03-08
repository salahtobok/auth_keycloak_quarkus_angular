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

  ngOnInit(): void {
  }


  public login() {
    this.oauthService.initLoginFlow();
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
    this.adminService.getRooms()
      .subscribe(rooms => console.log(rooms));
  }


}
