import {Component, OnInit} from '@angular/core';
import Keycloak from "keycloak-js";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'admin';


  ngOnInit(): void {

  }
}
