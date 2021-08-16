package io.speedy.poc.core.ports.in.report;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.io.Files;
import org.apache.http.HttpHeaders;
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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

@ActiveProfiles("mock")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionReportControllerTest {
    @Value("${api.mock.email}")
    private String email;

    @Value("${api.mock.password}")
    private String password;

    private String baseVersion = "/api/v1";

    @Value("${api.speedy.transactions_report.path}")
    private String path;

    private String pathLogin = "/api/v1/merchant/user/login?";
    private String accessToken = "";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8888));
    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8089));
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @BeforeEach
    private void beforeEach() {
        String newPathLogin = pathLogin + String.format("email=%s&password=%s", email, password);

//        accessToken = given().contentType("application/json")
//                .port(port)
//                .when()
//                .post(newPathLogin)
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .path("token");
    }

    @Test
    public void shouldReturnOkWhenGetReport() throws URISyntaxException {
//        {
//            "code": 9,
//                "status": "DECLINED",
//                "message": "10.72.23.66:27017: The 'cursor' option is required, except for aggregate with the explain argument"
//        }


        wireMockServer.stubFor(post(urlPathEqualTo("/api/v3/transactions/report")).willReturn(
                aResponse().withStatus(SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .withBodyFile("transaction/get-report-mock.json")));

        wireMockServer.start();


        URIBuilder builder = new URIBuilder();
        builder.setPath(baseVersion + path);
        builder.addParameter("fromDate", "2015-01-01");
        builder.addParameter("toDate", "2021-08-15");

        given().contentType("application/json")
                .port(port)
                .header("Authorization", accessToken)
                .when()
                .post(builder.build().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("response.size()", is(2))
                .body("status", is("APPROVED"));

        wireMockServer.stop();
    }
}
