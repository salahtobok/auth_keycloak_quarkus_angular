import { Component } from '@angular/core';
import Keycloak from "keycloak-js";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent {

   keycloak = new Keycloak({
     clientId: "", url: "",
     "realm": "quarkus",
       });

}
