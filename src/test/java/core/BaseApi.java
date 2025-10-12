package core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {
    protected final RequestSpecification req;
    public static final String BASE_URL = "https://petstore.swagger.io/v2";

    protected BaseApi() {
        this.req = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
