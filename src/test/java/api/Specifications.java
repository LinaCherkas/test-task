package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

public class Specifications {
    private final static String TOKEN = "Bearer 40193903e88253d5e4649b6aa85aaa86e9b5b750165833e041e3dc36d1d3afff";

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/")
                .addHeader("authorization", TOKEN)
                .setRelaxedHTTPSValidation()
                .setContentType(JSON)
                .setAccept(JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationScOk() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
}
