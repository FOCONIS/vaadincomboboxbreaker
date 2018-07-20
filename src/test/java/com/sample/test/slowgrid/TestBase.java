package com.sample.test.slowgrid;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ComboBoxElement;

import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * Base class for all our tests, allowing us to change the applicable driver,
 * test URL or other configurations in one place.
 */
public class TestBase extends TestBenchTestCase {
    public static final String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }
    
    @Before
    public void setUp() throws Exception {
  	
        // Create a new Selenium driver - it is automatically extended to work
        // with TestBench
        driver = new ChromeDriver();
        setDriver(driver);

        // Open the test application URL with the ?restartApplication URL
        // parameter to ensure Vaadin provides us with a fresh UI instance.
        getDriver().get(baseUrl + "?restartApplication");
        // Set a fixed view port of 1024x768 pixels for comparison

        // If you deploy using WTP in Eclipse, this will fail. You should
        // update baseUrl to point to where the app is deployed.
        String pageSource = getDriver().getPageSource();
        String errorMsg = "Application is not available at " + baseUrl + ". Server not started?";
        assertFalse(errorMsg, pageSource.contains("HTTP Status 404")
        		|| pageSource.contains("can't establish a connection to the server"));
    }
    
    @Test
    public void testToBreakCombobox() {
    	String reason = "There should be four suggestions plus an empty one = five alltogether.";
    	// expect the four entries plus an empty one = 5
    	assertThat(reason, getComboBox().getPopupSuggestionElements().size(), is(5));
    	// restart the application and break the ComboBox
    	getDriver().get(baseUrl + "?restartApplication");
    	getComboBox().sendKeys("A" + Keys.ENTER);
    	getComboBox().clear();
    	getComboBox().sendKeys("Fir");
    	assertThat(reason, getComboBox().getPopupSuggestionElements().size(), is(1));
    }

    @After
    public void tearDown() throws Exception {

        // Calling quit() on the driver closes the test browser.
        // When called like this, the browser is immediately closed on _any_
        // error. If you wish to take a screenshot of the browser at the time
        // the error occurred, you'll need to add the ScreenshotOnFailureRule
        // to your test and remove this call to quit().
    	getDriver().quit();
    }
    
    private ComboBoxElement getComboBox() {
    	return $(ComboBoxElement.class).id("testbox");
    }
    
}