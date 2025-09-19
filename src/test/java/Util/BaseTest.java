package Util;

import Util.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("api.base.uri");
    }
}