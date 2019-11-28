# CV Generator

Spring-based backend code for the CV Generator project.

## Docker setup
Run the following command to bring up the 
database and app containers (and apply any database 
migrations via flyway).

```docker-compose up```

## CV sample data
The following data is currently being used as sample data 
returned from a dockerised instance of Imposter.

```
{
       "id": 1,
       "firstName": "Robert",
       "lastName": "Bushmills",
       "email": "r.bushmills@gmail.com",
       "qualifications": [
           {
               "institution": "Top Uni",
               "type": "Degree",
               "name": "Software Development",
               "startDate": "01-09-2010",
               "endDate": "01-06-2014"
           }
       ],
       "work": [
           {
               "company": "Top Company",
               "position": "Junior Engineer",
               "startDate": "01-07-2014",
               "endDate": "30-04-2016"
           },
           {
               "company": "Another Top Company",
               "position": "QA Engineer",
               "startDate": "10-05-2016",
               "endDate": "Present"
           }
       ]
   }
```

Please set up a dockerised Imposter on port 8082 via the
instructions here - https://hub.docker.com/r/outofcoffee/imposter/

##Example
Current WIP implementation returns the parsed cv html, 
and stores a generated PDF in the `generatedpdf` folder of app.

Example API call -
http://localhost:8080/generatepdf?userId=1&templateId=2

Note - `userId` query parameter is currently non functional
but will be required in future. `templateId` of value 2 picks up
current test template that is added via `V4__Add_test_templates.sql`