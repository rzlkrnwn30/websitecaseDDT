package test.java.com.example.stepdefitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.*;

public class AppTest{
    WebDriver driver; 

    @Given("I am on the Sauce Demo login page")
    public void iAmOnTheSaucedemoLoginPage() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        // Buat instance ChromeDriver
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter a locked out user and password")
    public void IEnterALockedOutUserAndPassword() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @When("I enter a invalid username and password")
    public void iEnterAInvalidUsernameAndPassword() {
        driver.findElement(By.id("user-name")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password");
    }

    @When("I enter a valid username and password")
    public void iEnterAValidUsernameAndPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("I click the login button")
    public void iClickTheLoginButton(){
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should see an error message 'Epic sadface: Sorry, this user has been locked out.'")
    public void iShouldSeeAnErrorMessageLockedOutUser(){
        WebElement errorMessage = driver.findElement(By.xpath("//*[@data-test = 'error']"));
        junit.framework.Assert.assertTrue(errorMessage.isDisplayed());
        junit.framework.Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMessage.getText());
        driver.quit();
    }

    @Then("I should see an error message 'Epic sadface: Username and password do not match any user in this service'")
    public void iShouldSeeAnErrorMessageInvalidCredentials(){
        WebElement errorMessage = driver.findElement(By.xpath("//*[@data-test = 'error']"));
        junit.framework.Assert.assertTrue(errorMessage.isDisplayed());
        junit.framework.Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage.getText());
        driver.quit();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage(){
        junit.framework.Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        WebElement inventoryList = driver.findElement(By.className("inventory_list"));
        junit.framework.Assert.assertTrue(inventoryList.isDisplayed());
        driver.quit();
    }

}
