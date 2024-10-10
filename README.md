# Спринт 6.5 jUnit5_Selenium
### Задание:

Тебе предстоит сделать несколько проверок сервиса «Самокат» с помощью параметризованных тестов JUnit 5.
Перед нами код, который открывает главную страницу сервиса. 
Создай проект с JUnit 5 в IDE и перенеси уже написанную часть теста в свой тестовый класс. Добавь необходимые зависимости в pom, чтобы сделать доступными функции параметризации тестов. 
Напиши параметризованный тест, который проверяет: на странице каждый вопрос можно раскрыть и получить на него определенный ответ.
Можно использовать любой способ параметризации.

#### Исходный код:

public class YandexTest {

    WebDriver driver;
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
        // Добавь необходимые аннотации и аргументы
    public void allQuestionsHaveAnswerText() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
                // Здесь напиши логику открытия вопросов и проверки ответов
    }
}

#### resources/questions.csv:

"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."

"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."

"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."

"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."

"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."

"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."

"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."

"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."

#### Подсказки:
•	Если параметризуешь через csv, используй “”, чтобы сохранять тексты вопросов и ответов.

•	При открытии страницы нужно нажать на кнопку «Принять cookie»

•	Используй поиск элементов по XPath, чтобы находить элементы с нужным текстом вопроса и ответа

•	Используй класс Actions, чтобы скроллить страницу до найденных элементов

#### Комманды для отчетов о прохождении тестов allure:
mvn allure:report

mvn allure:serve

Открывается сайт с отчетом







