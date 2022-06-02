# Student Information Service
This REST service brings student information and allows you to add new students to the system or remove students from the system.

A POST operation is used to add students to the system.
GET operations are used to get students by student number or email. Students are also brought according to their GPAs.
The student's collection in the MongoDB database has the data about each student’s name, student number, email, course list and GPA.
Everything stored, received, and returned by the service is formatted as JSON.
