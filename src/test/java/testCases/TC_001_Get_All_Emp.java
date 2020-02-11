package testCases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_001_Get_All_Emp extends TestBase {

    @BeforeClass
    void getAllEmps() throws InterruptedException
    {
        System.out.println(System.getProperty("user.dir"));
        logger.info("*****Started TC_001_Get_All_Emp ********");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employees");

        Thread.sleep(3);
    }
    @Test
    void check_ResponseBody()
    {
        logger.info("****Checking Response Body*****");
        String respBody = response.getBody().asString();
        logger.info("Response Body is => "+respBody);
        Assert.assertTrue(respBody != null);
    }
    @Test
    void check_StatusCode()
    {
        logger.info("****Checking Status Code*****");
        int statuscode = response.getStatusCode();
        logger.info("Status Code is => "+statuscode);
        Assert.assertEquals(statuscode,200);
    }
    @AfterClass
    void tearDown()
    {
        logger.info("*****Finished TC_001_Get_All_Emp ********");
    }
}
