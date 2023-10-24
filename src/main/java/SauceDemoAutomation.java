import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;

public class SauceDemoAutomation {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver-win64/chromedriver.exe");

        String baseUrl = "https://www.saucedemo.com";

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(baseUrl);

        loginWithValidCredentials(driver);
        checkProductDetails(driver);
        addProductToCart(driver);
        testCheckout(driver);
        logout(driver);
        loginWithInvalidCredentials(driver);

        driver.quit();
    }

    public static void logout(WebDriver driver) {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    public static void loginWithInvalidCredentials(WebDriver driver) {
        String username = "standard_user";
        String password = "secretsauce";

        // Memasukkan username dan password
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        // Klik button Login
        driver.findElement(By.id("login-button")).click();

        boolean isErrorDisplayed = driver.findElement(By.className("error-message-container error")).isDisplayed();
        Assert.assertTrue(isErrorDisplayed);

    }

    public static void loginWithValidCredentials(WebDriver driver) {
        String username = "standard_user";
        String password = "secret_sauce";

        // Memasukkan username dan password
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        // Klik button Login
        driver.findElement(By.id("login-button")).click();

        boolean isLoggedin = driver.findElement(By.id("inventory_container")).isDisplayed();
        Assert.assertTrue(isLoggedin);
        // Assert.assertEquals("www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    public static void checkProductDetails(WebDriver driver) {
        driver.findElement(By.linkText("Sauce Labs Backpack")).click();

        boolean isProductDetailDisplayed = driver.findElement(By.id("inventory_item_container")).isDisplayed();
        Assert.assertTrue(isProductDetailDisplayed);
        // Assert.assertEquals(driver.findElement(By.className("inventory_details_name")),"Sauce Labs Backpack");
    }

    public static void addProductToCart(WebDriver driver) {
        // driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();

        boolean isProductAddedToChart = driver.findElement(By.className("shopping_cart_badge")).getText().contains("1");
        Assert.assertTrue(isProductAddedToChart);

        //Assert.assertTrue(driver.findElement(By.className("shopping_cart_badge")).getText().contains("1"));
        // Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")), 1);
    }

    public static void testCheckout(WebDriver driver) {
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.id("continue")).click();

        boolean isSuccess = driver.getCurrentUrl().contains("https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(isSuccess);
    }
}
