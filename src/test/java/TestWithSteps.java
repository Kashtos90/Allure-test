import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestWithSteps {

    private static final String REPOSITORY = "Kashtos90/Allure-test";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void testIssueSearch(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").pressEnter();
        });
        step("Открываем репозиторий", () -> {
            $(By.linkText("Kashtos90/Allure-test")).click();
        });
        step("Проверяем наличие Issues на странице", () -> {
            $(new By.ByPartialLinkText("Issues")).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        TestWithAnnotationStep steps = new TestWithAnnotationStep();
        steps.openMainPage();
        steps.searchForRepository();
        steps.openRepository();
        steps.shouldSeeIssues();
        steps.takeScreenshot();
    }

}
