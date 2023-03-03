import {Component, OnInit} from '@angular/core';
import Keycloak from "keycloak-js";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'admin';


  keycloak = new Keycloak({
    clientId: "backend-service", url: "http://localhost:8543/",
    "realm": "quarkus",
});

  ngOnInit(): void {
    console.log("============================")
    this.keycloak.init({
      onLoad: 'login-required',
      silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
    })
  }
}
