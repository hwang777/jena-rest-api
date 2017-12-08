package com.hornellp.cdbg.test.ui;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestUI {
    //@Inject
    private WebDriver driver;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/apps/webdrive/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void completingAForm() throws Exception {
    	driver.get("https://hornellpdev.appiancloud.com/suite/sites/horne-cdbg/page/cases");

        WebElement email = driver.findElement(By.id("un"));
        Thread.sleep(10000);
        email.clear();
        email.sendKeys("henry.wang@bestit.com");
        Thread.sleep(10000);
        WebElement password = driver.findElement(By.id("pw"));
        password.clear();
        password.sendKeys("best@123");
        Thread.sleep(10000);
        
        driver.findElement(By.cssSelector("input[type='submit']"))
            .click();
    }
}
