package testCases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RestUtils;

public class TC_002_Post_New_Emp extends TestBase {

    String empName = RestUtils.empName();
    String empAge = RestUtils.empAge();
    String empSal = RestUtils.empSal();


    @BeforeClass
    void post_emp() throws Exception
    {
        logger.info("----Started Post Employee TC------");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest =  RestAssured.given();
        JSONObject reqParams = new JSONObject();
        reqParams.put("name",empName);
        reqParams.put("age",empAge);
        reqParams.put("salary",empSal);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(reqParams.toJSONString());
        response = httpRequest.request(Method.POST,"/create");

        String respBody = response.getBody().asString();
        logger.info(respBody);
        Thread.sleep(3000);

    }
    @Test
    void checkStatusCode()
    {
        logger.info("Checking the Status code");
        Assert.assertEquals(response.getStatusCode(), 201);
        logger.info("Status Code is => "+ response.getStatusCode());
    }
    @Test
    void checkResponseBody()
    {
        logger.info("Checking the Response Body--");
        String resBody = response.getBody().asString();
        JsonPath jpath = response.jsonPath();
        Assert.assertEquals(jpath.get("data.name"), empName);
        logger.info("Generated ID is: "+jpath.get("data.id"));
    }


    @AfterClass
    void tearDown()
    {
        logger.info("Finished execution "+ getClass());
    }
}
