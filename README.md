# National Registry

This is the API responsible for simulating the CRM operations. It is a cloud native api and is currently deployed on Heroku. The complete documentation was defined according to Open API documentation:
https://aw-national-registry-api.herokuapp.com/swagger-ui/index.html 

# National Registry deployment process to Heroku

The National Registry was deployed to Heroku according to the following steps:

The cloud provider chosen is Heroku because its configurattion its simple and with not cost. The database chosen is Postgresql because it's provided as default database.
However we have to change some implementation details on our entities classes and the application.yaml file to allow a correct integration between our application and Postgresql service provided by Heroku.

# Changes applied to entities classes:

Since Postgresql doesn't have the auto increment concept, we need to define the GenericGenerator that will get the latest index and increment it by 1. The example is displayed below:
```
@Entity
@Getter
@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String firstName;
    private String lastName;
    private String nationalIdentificationNumber;
    private String birthDate;
    private String email;

}
```

# Changes applied to application.yaml

```
spring:
  jpa:
    show-sql: true
    generate-ddl: true
  datasource:
    url: ${JDBC_DATASOURCE_URL}
    
 ```
 
 # Steps to deploy "National Registry" to Heroku.

1- Create a Heroku account by going to:
```
https://dashboard.heroku.com/apps
```

2- Push all your code to github repository;

3- Login to Heroku:
```
heroku login
```

4- Create a new app at Heroku environment:

```
heroku create aw-national-registry-api
```

5- Assign a Postgresql database to your app
```
heroku addons:create heroku-postgresql:hobby-dev
```

6- Push your application to Heroku repository
```
git push heroku main
```

7- In case you want to check the logs, type:
```
heroku logs --tail
```

8- In order to open your Heroku app, type:
```
heroku open
```