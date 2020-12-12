package fca.unam.mx;

import io.quarkus.test.junit.QuarkusTest;
import fca.unam.mx.utils.TokenUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class UserResourceTest {

    @Inject
    TokenUtils tokenUtils;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }


    @Test
    public void testCreateUser() {

        String bodyString = "{\"name\":\"John\",\"role\":\"USER\"}";

        given()
                .header("Content-type", "application/json")
                .body(bodyString)
                .when().post("/user/create")
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body(containsString("data"));

    }


    @Test
    public void testGetUsersAsUser() {

        given()
                .header("Content-type", "application/json")
                .when().get("/user/all")
                .then()
                .statusCode(401);

    }


    @Test
    public void testGetUsersAsAdmin() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setRole("ADMIN");

        var token = tokenUtils.generateToken(userDto, 3600 * 24L);

        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/user/all")
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body(containsString("data"));

    }

}