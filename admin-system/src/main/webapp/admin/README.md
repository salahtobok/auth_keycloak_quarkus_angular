Webcodein learning website repository.


    01 - We organize the project by types of files
    02 - Deploy angular ssr app https://www.saninnsalas.com/deploy-angular-universal-to-shared-apache-hosting-with-passenger/
    03 - Add locale & internationalize the app :
        - ng add @angular/localize
        - ng extract-i18n --output-path src/locale
        - cp src/locale/messages.xlf src/locale/messages.es.xlf
        - ng generate component components/header --module modules/common-content
        - npm install --global serve
        - serve .\dist\angular_theme\browser
        - npm install --global lite-server
        - --base-href
        - npm run dev:ssr
--

    - ng serve admin --ssl --live-reload      
    - To build for production  or npm run dev:ssr with edit environment.ts

-- 
    Angular & Quarkus & Keycloak : Authentication & Authorization Config :
    <p>set * Valid Redirect URIs  = http://localhost:4200/*</p> 
    <p>set * Web Origins  = http://localhost:4200</p> 
    <p>http://localhost:4200 is the URL for our Angular Application</p>
