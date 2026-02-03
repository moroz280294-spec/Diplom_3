package steps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.User;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static utils.Constants.*;

public class UserSteps {

    //("Логин пользователя через API")
    public Response login(User user) {
        return given()
                .body(user)
                .when()
                .post("/api/auth/login")
                .then()
                .extract()
                .response();
    }


   // ("Создание пользователя")
    public Response createUser(User user) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());
        requestBody.put("name", user.getFirstName());

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(REGISTER_USER);
    }

    //("Логин пользователя")
    public Response loginUser(User user) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(USER_LOGIN);
    }

   // ("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER);

    }

    }
