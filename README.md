*Članovi tima 41

  Student 1 - Vanja Teodorovic RA 45/2019
  
  Student 2 - Dusko Radicic RA 129/2019
  
  Student 3 - Jelena Dinic RA 44/2019
  
  Student 4 - Stefan Tosic RA 29/2019
  
  
*Tehnologije

    - Java Spring Boot 
    - Angular 
    - PostgreSQL 


*Pokretanje projekta

    1) Pokrenuti IntelliJ IDEA i importovati BloodBankBackend aplikaciju
    
    2) Aplikaciju pokrenuti na Run
    
    3) Pokrenuti Visual Studio Code i otvoriti BloodBankFrontend aplikaciju
    
    4) Instalirati potrebne module sa npm install
    
    5) Aplikaciju startovati sa ng serve 
    
    6) U browseru na putanju http://localhost:4200 je aplikacija
    
    
*SQL skripta za unos podataka se pokrece automatski kad se pokrene bekend aplikacija.
    
*Email-ovi i lozinke za tipove korisnika:

      Staff:
        jelena@gmail.com 
        jelena
      Registered user: 
        stefan@gmail.com
        stefke       
        vanjateodorovic00@gmail.com
        vanjilica
      System admin: 
        dusko@gmail.com
        dusko
        
*Potrebno je instalirati POSTGRESQL bazu i podesiti svoj username i password u Pgadminu. Application.properties fajl koji je u gitignore zbog razlicitih passworda:
      server.port=8082
      spring.datasource.url=jdbc:postgresql://localhost:5432/bloodbank
      spring.datasource.username=postgres
      spring.datasource.password= VAS_PASSWORD
      spring.jpa.show-sql=true
      spring.jpa.hibernate.ddl-auto = create-drop
      spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL95Dialect
      spring.jpa.properties.hibernate.format_sql=true
      spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
      spring.jpa.open-in-view=false

      spring.mail.host=smtp.gmail.com
      spring.mail.port=587
      spring.mail.username=bloodbankisa@gmail.com
      spring.mail.password=vbbbtxssyhsmvdzr
      spring.mail.properties.mail.smtp.auth=true
      spring.mail.properties.mail.smtp.starttls.enable=true

      server.error.include-message=always

