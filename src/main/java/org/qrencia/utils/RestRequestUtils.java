package org.qrencia.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestRequestUtils
{
    public Response sendGetRequest(String apiEndPoint)
    {
        return RestAssured.given().get(apiEndPoint);
    }
}
