# io_project
SETUP
1. Create MySQL database and run the scripts in io_project_db.sql
2. Set credentials for database in application.properties in PeopleService
3. Run both modules
4. Type in the browser http://localhost:8080
FUNCTIONALITY
1. Show All - shows all records for persons in the database
   1.1 Delete - deletes the selected person
   1.2 Edit - edits THE EXISTING field of selected person
   1.3 Show - shows all the info about the selected person
     1.3.1 Add Address - adds new address to the existing
     1.3.2 Add Email - adds new email to the existing
     1.3.3 Delete - deletes current person
2. Search - needs some text to be typed in the input field
3. Add New - adds new person to the database. Initially the person can have only one email and only one address.
