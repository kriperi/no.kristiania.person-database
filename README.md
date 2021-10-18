* [] Access postgreql fra IntelliJ
* [] Test: Retrieving an inserted person from database
    * Add dependency on Postgreql driver with maven
    * Add AssertJ testing library with maven
* [] Test: list people by last name

##Database

    create user guy with password 'Ax2rw])rJdJJ.5vK';

    create database guy_db with owner guy



    create table people (
    id serial primary key,
    first_name varchar(100),
    last_name varchar(100)
);