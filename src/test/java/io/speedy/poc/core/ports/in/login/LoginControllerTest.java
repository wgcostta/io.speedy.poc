package io.speedy.poc.core.ports.in.login;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.speedy.poc.core.usecase.login.transferobject.AccessToken;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.jayway.restassured.RestAssured.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8888));
    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8089));
    @LocalServerPort
    private int port;

    private static final String RESOURCE_DIRECTORY = "src/test/resources";

    @Value("${api.mock.email}")
    private String email;

    @Value("${api.mock.password}")
    private String password;

    private String path = "/api/v1/merchant/user/login";

    @Before
    public void setUp() {
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @Test
    public void shouldReturnOkWhenGetAccessToken() {
        String newPath = path + String.format("?email=%s&password=%s", email, password);

        AccessToken result =
                given().contentType("application/json")
                        .port(port)
                        .when()
                        .post(newPath)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(AccessToken.class);

        Assertions.assertThat(result.getStatus()).isEqualTo("APPROVED");
        Assertions.assertThat(result.getToken()).isNotEmpty();
    }

    @Test
    public void shouldReturnUnauthorizedUserWhenGetAccessToken() {
        String newPath = path + String.format("?email=%s&password=%s", email + "X", password);

        given().contentType("application/json")
                .port(port)
                .when()
                .post(newPath)
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    public void shouldReturnFailedWhenGetAccessTokenToLoadParams() {
        given().contentType("application/json")
                .port(port)
                .when()
                .post(path)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
