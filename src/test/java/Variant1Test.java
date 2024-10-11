import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.support.PageFactory;
import pageobjects.MainPage;

public class Variant1Test {
    //WebDriver driver;

    private MainPage mainPage;

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Используем WebDriverManager для автоматического управления драйверами
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("Самокат")
    @Description("Проверка вопросов и ответов на сайте")
    @Feature("Список вопросов")
    @Step("Открыть вопрос и ответ на него")
    @ParameterizedTest
    @CsvFileSource(resources = "/questions.csv")
    public void allQuestionsHaveAnswerText(String question, String answer) {
        Actions actions = new Actions(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.findElement(By.cssSelector(".App_CookieButton__3cvqF")).click();
        WebElement questionElement = driver.findElement(By.xpath(String.format("//div[@class='accordion__button' and text()='%s']", question)));
        actions.moveToElement(questionElement);
        actions.perform();
        questionElement.click();
        WebElement answerElement = driver.findElement(By.xpath(String.format("//p[text()='%s']", answer)));
        actions.moveToElement(answerElement);
        actions.perform();
        assertTrue(answerElement.isDisplayed());
    }
}
