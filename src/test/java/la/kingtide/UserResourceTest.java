package la.kingtide;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class UserResourceTest {

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

}