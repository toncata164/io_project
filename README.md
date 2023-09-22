# io_project
<h3>SETUP</h3>
<ol>
<li>Create MySQL database and run the scripts in io_project_db.sql</li>
<li>Set credentials for database in application.properties in PeopleService</li>
<li>Run both modules</li>
<li>Type in the browser http://localhost:8080</li>
</ol>
<h3>FUNCTIONALITY</h3>
<ol>
<li>Show All - shows all records for persons in the database
   <ol><li>Delete - deletes the selected person</li>
   <li>Edit - edits THE EXISTING field of selected person</li>
   <li>Show - shows all the info about the selected person
     <ol><li>Add Address - adds new address to the existing</li>
     <li>Add Email - adds new email to the existing</li>
     <li>Delete - deletes current person</li>
      </ol></li></ol></li>
<li>Search - needs some text to be typed in the input field</li>
<li>Add New - adds new person to the database. Initially the person can have only one email and only one address.</li>
</ol>
