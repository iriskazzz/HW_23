package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        step("Ввод значения Appium в строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка, что открывшийся список больше нуля", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void checkErrorTextTest() {
        step("Ввод значения Appium в строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Клик по строке поиска с содержанием текста введенного запроса", () -> {
                    $(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text("Appium")).click();
                }
        );
        step("Проверка видимости сообщения об ошибке", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible);
                }
        );
    }

}