package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
//    public String empID = "24";
    public Logger logger;


    @BeforeClass
    public void setUp()
    {
        logger = Logger.getLogger(getClass().getName());

//        String log4jConfPath = "/Users/skalaskar/Desktop/Sandeep/RestAutomationFramework/src/main/resources/log4j.properties";
        PropertyConfigurator.configure("/Users/skalaskar/Desktop/Sandeep/RestAutomationFramework/src/main/resources/log4j.properties");
        logger.setLevel(Level.DEBUG);

    }
}
