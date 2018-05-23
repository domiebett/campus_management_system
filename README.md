# Campus Management System.
System to manage all information for campuses, that is Students, Lecturers, Staff, Courses, classes, offices, hostels and all buildings.

## Set Up.

This program is a maven java application. Therefore, you will need to have `maven` installed. You also need to have a minimum of `Java version 8` installed. You should also have a database installed, preferrably `postgres` and input your database details in the `hibernate.cfg.xml`.

You will need to create a database before running this application.

### Creating the database.
If you are using postgres database, which is the recommended, then you can create a database using the following command:
> psql

> `postgres=#` CREATE DATABASE campus;

### Installing dependencies.

You can install dependencies by running the command:
> mvn install

or

> mvn packages

### Running migrations

Run migrations to populate your database with tables using the following command.
> mvn flyway:migrate

Once you have successfully run your migrations, the program is ready to run.

### Compiling project.

This is optional. If you wish to view how the application works, you can compile it using the mvn compile command. You will find the compiled application in the target folder within your project folder.
