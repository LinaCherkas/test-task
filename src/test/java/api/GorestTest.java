package api;

import api.Models.CreateUserRequest;
import api.Models.UserData;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.MainClass;

import java.util.UUID;

import static api.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;

public class GorestTest extends MainClass {
    static UserData user;

    public static String generateUserName(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    public static String generateUserEmail(String name){
        return name + "@gmail.com";
    }
    @BeforeAll
    public static void Init() {

        var name = generateUserName();
        var email = generateUserEmail(name);
        var request = new CreateUserRequest(name, email, "male", "active");

        user = given()
                .spec(requestSpecification())
                .body(request)
                .when()
                .post("public/v2/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UserData.class);
    }


    @Test
    @DisplayName("GET users list and check the status code")
    public void getAllUsersList() {
        given()
                .spec(requestSpecification())
                .get("/public/v2/users")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("GET data about specific user and check some keys")
    public void getSpecificUserData() {
        given()
                .spec(requestSpecification())
                .get("public/v2/users/" + user.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("email", Matchers.is(user.getEmail()))
                .body("name", Matchers.is(user.getName()));
    }

    @Test
    @DisplayName("UPDATE any data for one of the user and check it")
    public void updateUserData() {
        user.setStatus("inactive");
        user.setGender("female");

        given()
                .spec(requestSpecification())
                .body(user)
                .put("public/v2/users/" + user.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("status", Matchers.is(user.getStatus()))
                .body("gender", Matchers.is(user.getGender()));
    }

    @Test
    @DisplayName("POST a new user and check user's name")
    public void createNewUser() {
        var name = generateUserName();
        var email = generateUserEmail(name);
        var request = new CreateUserRequest(name, email, "female", "inactive");

        given()
                .spec(requestSpecification())
                .body(request)
                .when()
                .post("public/v2/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("name", Matchers.is(request.getName()));
    }

    @Test
    @DisplayName("DELETE user's record and check the status code")
    public void deleteRequestCheckStatusCode() {
        given()
                .spec(requestSpecification())
                .delete("/public/v2/users/" + user.getId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
