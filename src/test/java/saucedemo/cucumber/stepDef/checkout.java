package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class checkout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("Chart page")
    public void chartPageDisplayed() {
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        driver.findElement(By.linkText("Sauce Labs Backpack")).click();
        Assert.assertEquals("https://www.saucedemo.com/inventory-item.html?id=4", driver.getCurrentUrl());

        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
        Assert.assertEquals("1", driver.findElement(By.className("shopping_cart_badge")).getText());

        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @When("User click checkout button")
    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    @And("Input firstname")
    public void inputFirstname() {
        driver.findElement(By.id("first-name")).sendKeys("John");
    }

    @And("Input lastname")
    public void inputLastname() {
        driver.findElement(By.id("last-name")).sendKeys("Doe");
    }

    @And("Input postal code")
    public void inputPostalCode() {
        driver.findElement(By.id("postal-code")).sendKeys("12345");
    }

    @And("Click continue button")
    public void clickContinue() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("Checkout overview page displayed")
    public void checkoutOverviewDisplayed() {
        Assert.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
        driver.quit();
    }
}
