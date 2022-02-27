import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWithAnnotationStep {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").pressEnter();
    }

    @Step("Открываем репозиторий {repo}")
    public void openRepository (String repo) {
        $(By.linkText("Kashtos90/Allure-test")).click();
    }

    @Step("Проверяем наличие Issues на странице")
    public void shouldSeeIssues() {
        $(new By.ByPartialLinkText("Issues")).should(Condition.exist);
    }

    @Step("Делаем скриншот")
    @Attachment(value = "Screenshot",type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
