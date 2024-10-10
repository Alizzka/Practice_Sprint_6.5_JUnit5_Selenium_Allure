import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Variant2Test {
    WebDriver driver;
    @BeforeEach
    public void setup() {
        // Используем WebDriverManager для автоматического управления драйверами
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
    @CsvSource({
            "\'Сколько это стоит? И как оплатить?\', \'Сутки — 400 рублей. Оплата курьеру — наличными или картой.\'",
            "\'Хочу сразу несколько самокатов! Так можно?\', \'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.\'",
            "\'Как рассчитывается время аренды?\', \'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.\'",
            "\'Можно ли заказать самокат прямо на сегодня?\', \'Только начиная с завтрашнего дня. Но скоро станем расторопнее.\'",
            "\'Можно ли продлить заказ или вернуть самокат раньше?\', \'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.\'",
            "\'Вы привозите зарядку вместе с самокатом?\', \'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.\'",
            "\'Можно ли отменить заказ?\', \'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.\'",
            "\'Я жизу за МКАДом, привезёте?\', \'Да, обязательно. Всем самокатов! И Москве, и Московской области.\'"
    })
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
