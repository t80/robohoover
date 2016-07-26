# Yoti Test

Assumptions

    1. Maven is available
    2. Java 8 is installed    

Running the robohoover service:

    1. Clone the repository
    2. Build with Maven
    3. Navigate to the root of the project in the terminal
    4. From the terminal type: "java -jar robohoover-web/target/robohoover-web-1.0-SNAPSHOT.jar"
    5. The service will now be running on: http://localhost:8080/robohoover/clean
    6. The service uses the POST method and accepts and consumes JSON as per the instructions.


Work omitted due to time constraints

    1. Only very basic in memory persistence was implemented. The integration tests should test against
       a real database.
    2. Exception mapping to status codes and error messages is not thorough.
    3. No logging has been added
    4. Its possible caching would be useful in production. Given the current spec, the responses
       should not change given the same input parameters.
    5  There could be more checking for nulls and valid parameter validity should a defensive approach
       be taken by the service layer


