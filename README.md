# P6-Full-Stack-reseau-dev

### Dependencies

mariadb 11.1.2
maven 3.9.6
angular-cli 15.2.10
npm 9.5.0

## Set-up the database

The project uses mariadb 11.1.2. You need to have it installed on your machine.

There is an SQL script for creating the schema: `resources/base.sql`
And there is one to add example data: `resources/example_data.sql`

You can import all of this by creating a database through your mysql console. 

First let's open the mysql console with (using your credentials):
`mysql -u USERNAME -pPASSWORD`

and then you can create a database like this (the user you're using must have the rights to do so):
`CREATE DATABASE mdd;`

if you want to use another name than "mdd" for the database you can choose whatever you want).

Once done, you can quit the mysql console (Ctrl+D).

And then you can launch this command (still using your credentials and the name you gave to your DB instead of mdd if neeeded):
`mysql -u USERNAME -pPASSWORD mdd < resources/base.sql`

You should now have everything you need in your db. 

Now think about editing the file `back/src/main/resources/application.properties`, replacing "mdd" by your database name, "user" by your actual username and password by your actual password:
spring.datasource.url=jdbc:mysql://localhost:3306/mdd?allowPublicKeyRetrieval=true
spring.datasource.username=user
spring.datasource.password=123456


## Back

The back is a Spring Boot project (with Maven).


### Build

You can build the project using this command:

`mvn clean:install`

### Javadoc

You can build the javadoc using this command;

`mvn javadoc:javadoc`

## Front

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.2.10.

Don't forget to install your node_modules before starting (`npm install`).

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

