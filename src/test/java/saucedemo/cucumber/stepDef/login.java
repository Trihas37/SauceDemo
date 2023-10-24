package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.Console;
import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("Saucedemo login page")
    public void loginPage() {
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @When("Input valid username")
    public void inputValidUsername() {
        String username = "standard_user";

        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("Input valid password")
    public void inputValidPassword() {
        String password = "secret_sauce";
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("Click login button")
    public void clickLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User in dashboard page")
    public void userInDashboardPage() {
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @And("Input invalid password")
    public void inputInvalidPassword() {
        String password = "secretsauce";
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("User get error message")
    public void getErrorMessage() {
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", driver.findElement(By.className("error-message-container")).getText());
    }


}
