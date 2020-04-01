## Getting Started

Multitenant Spring JPA with multi database Project

### Prerequisites

* Java 8
* Spring Boot 2
* MySQL
* Not mandatory, but you can use any suitable IDE like Intellij


## Authors

* **Irfan Nasim** - *Initial work* - [Irfan Nasim](https://pk.linkedin.com/in/irfannasim)



## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details



## Configuration

* **Database** - Run script available in the project in script folder, and change the database credentials in database as well as properties files


## Run the Application

* **Start** - Just click on start button to start 

## API Call 

* **Postman call** - Hit following url http://localhost:8080/MultiTenant/user/all
* **Headers** -  X-TENANT-ID : his_tenant_1,
                 Authorization: YWRtaW46YWRtaW4= (base64 string of admin:admin)
            
    X-TENANT-ID is a key which represent the tenant id and tenant reflect the database. This configuration is available into shared "his_master_db" --> tenants table.
    
       
                 






