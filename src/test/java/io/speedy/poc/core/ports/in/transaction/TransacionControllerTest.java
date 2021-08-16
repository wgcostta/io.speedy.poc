package io.speedy.poc.core.ports.in.transaction;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransacionControllerTest {
    @Value("${api.mock.email}")
    private String email;

    @Value("${api.mock.password}")
    private String password;

    private String baseVersion = "/api/v1";

    @Value("${api.speedy.transaction.path}")
    private String path;

    @Value("${api.speedy.transaction_list.path}")
    private String pathList;

    private String pathLogin = "/api/v1/merchant/user/login?";
    private String accessToken = "";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8888));
    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8089));
    @LocalServerPort
    private int port;

    private static final String RESOURCE_DIRECTORY = "src/test/resources";

    @Before
    public void setUp() {
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @BeforeEach
    private void beforeEach() {
        String newPathLogin = pathLogin + String.format("email=%s&password=%s", email, password);

        accessToken = given().contentType("application/json")
                .port(port)
                .when()
                .post(newPathLogin)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .path("token");
    }

    @Test
    public void shouldReturnOkWhenGetTransactionList() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setPath(baseVersion + path + pathList);
        builder.addParameter("fromDate", "2015-01-01");
        builder.addParameter("toDate", "2021-08-15");
        builder.addParameter("merchant", "1293");

        given().contentType("application/json")
                .port(port)
                .header("Authorization", accessToken)
                .when()
                .post(builder.build().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("data.size()", is(50))
                .body("per_page", is(50))
        ;
    }

    @Test
    public void shouldReturnOkWhenGetTransactionListNotReturnItems() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setPath(baseVersion + path + pathList);
        builder.addParameter("fromDate", "2021-08-15");
        builder.addParameter("toDate", "2021-08-15");
        builder.addParameter("merchant", "1293");

        given().contentType("application/json")
                .port(port)
                .header("Authorization", accessToken)
                .when()
                .post(builder.build().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("data.size()", is(0))
                .body("per_page", is(50))
        ;
    }

    @Test
    public void shouldReturnOkWhenGetTransactionFindByTransactionId() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setPath(baseVersion + path);
        builder.addParameter("transactionId", "997875-1517822100-3");

        given().contentType("application/json")
                .port(port)
                .header("Authorization", accessToken)
                .when()
                .post(builder.build().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("customerInfo.id", is(699779))
                .body("customerInfo.created_at", is("2018-02-05 09:14:07"))
                .body("parent.transaction.merchant.id", is(997874))
        ;
    }

    @Test
    public void shouldReturnOkWhenGetTransactionNotFoundFindByTransactionId() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setPath(baseVersion + path);
        builder.addParameter("transactionId", "1-1444392550-1");

        given().contentType("application/json")
                .port(port)
                .header("Authorization", accessToken)
                .when()
                .post(builder.build().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnOkWhenGetTransactionListFilterFieldIncorret() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setPath(baseVersion + path + pathList);
        builder.addParameter("fromDate", "2015-01-01");
        builder.addParameter("toDate", "2021-08-15");
        builder.addParameter("filterField", "Transaction XXX");

        given().contentType("application/json")
                .port(port)
                .header("Authorization", accessToken)
                .when()
                .post(builder.build().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", is("Filter Field is not valid"))
        ;
    }

}
