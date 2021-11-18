# PetStore Application

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## How to run tests

    ./gradlew test

## API Endpoints

### PETS Endpoints

Get All Pets:

    curl -X GET "http://localhost:8080/v1/pets"

Get Pet By Id:

    curl -X GET "http://localhost:8080/v1/pets/1"

Get Pets By Name:

    curl -X GET "http://localhost:8080/v1/pets/getByName/Boola"

Get Pets By Age:

    curl -X GET "http://localhost:8080/v1/pets/getByAge/3"

Create Pet

    curl -X POST "http://localhost:8080/v1/pets" -H "Content-Type: application/json" --data-raw "{\"petAge\": 1,\"petName\": \"Lacy\",\"petType\": \"Dog\"}"

Update Pet

    curl -X PUT "http://localhost:8080/v1/pets/1" -H "Content-Type: application/json" --data-raw "{\"petAge\": 3,\"petName\": \"Lazy\",\"petType\": \"Dog\"}"

Delete Pet By Id:

    curl -X DELETE "http://localhost:8080/v1/pets/1"

### PET Types Endpoints

Get All Pet Typess:

    curl -X GET "http://localhost:8080/v1/petTypes"

Get Pet Type By Id:

    curl -X GET "http://localhost:8080/v1/petTypes/1"

Create Pet Type

    curl -X POST "http://localhost:8080/v1/petTypes" -H "Content-Type: application/json" --data-raw "{\"petTypeName\": \"DogNew\"}"

Update Pet Type

    curl -X PUT "http://localhost:8080/v1/petTypes" -H "Content-Type: application/json" --data-raw "{\"petTypeName\": \"DogNew\"}"

Delete Pet Type By Id:

    curl -X DELETE "http://localhost:8080/v1/petTypes/1"
