package task2;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Vadym Nastoiashchyi
 */

public class WebDriverProperties {

    public WebDriver driver;

    @Before
    public void setConnection() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void closeConnection() {
        driver.quit();
    }


}