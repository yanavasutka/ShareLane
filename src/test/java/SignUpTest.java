import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SignUpTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty ("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void zipCode1 () {

        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Ввести 5 цифр, например 12345
        4. Нажать Continue
        5. Проверить, что кнопка Register видна
        6. Закрыть браузер
         */

        driver.get ("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement (By.name("zip_code")).sendKeys("12345");
        driver.findElement (By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement (By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isDisplayed, "Error message is not displayed");
    }

    @Test
    public void zipCodeShouldNotBeAcceptedWith4Digits () {

        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Ввести 4 цифр, например 1234
        4. Нажать Continue
        5. Проверить, что уведомление об ошибке "Oops, error on page. ZIP code should have 5 digits" видно
        6. Закрыть браузер
         */

        driver.get ("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement (By.name("zip_code")).sendKeys("1234");
        driver.findElement (By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement (By.className("error_message")).isDisplayed();
        assertTrue(isDisplayed);
    }

    @Test
    public void zipCodeShouldNotBeAcceptedWith6Digits () {

        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Ввести 6 цифр, например 123456
        4. Нажать Continue
        5. Проверить, что сообщение об ошибке "Oops, error on page. ZIP code should have 5 digits" видно
        6. Закрыть браузер
         */

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        assertTrue(isDisplayed);
    }

    @Test
    public void registrationShouldBeSuccessful () {

        /*
        1. Открыть браузер
        2. Перейти по ссылке https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        3. В поле First Name ввести Yana
        4. В поле Last Name ввести Vasyutich
        5. В поле Email ввести yana@mail.ru
        6. В поле Password ввести 123456
        7. В поле Confirm Password ввести 123456
        4. Нажать Register
        5. Проверить, что сообщение  "Account is created!" видно
        6. Закрыть браузер
         */

        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Yana");
        driver.findElement(By.name("last_name")).sendKeys("Vasyutich");
        driver.findElement(By.name("email")).sendKeys("yana@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isDisplayed = driver.findElement(By.className("confirmation_message")).isDisplayed();
        assertTrue(isDisplayed);
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
