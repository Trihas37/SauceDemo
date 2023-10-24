package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class order {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com";

    @Given("Dashboard page")
    public void dashboardPage() {
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
    }

    @When("User click a product name link")
    public void clickProductDetails() {
        driver.findElement(By.linkText("Sauce Labs Backpack")).click();
    }

    @Then("User in product detail page")
    public void detailProduct() {
        Assert.assertEquals("https://www.saucedemo.com/inventory-item.html?id=4", driver.getCurrentUrl());
        driver.quit();
    }

    @When("User click add to chart button")
    public void addProductToChart() {
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @Then("Product added to chart")
    public void productAddedToChart() {
        Assert.assertEquals("1", driver.findElement(By.className("shopping_cart_badge")).getText());
        driver.quit();
    }
}
