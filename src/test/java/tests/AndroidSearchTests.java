package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.SearchPage;
import pages.VikiPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {

  private SearchPage searchPage = new SearchPage();
  private VikiPage vikiPage = new VikiPage();

  @Test
  @DisplayName("Проверка успешной работы поиска")
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
  @DisplayName("Проверка отображения сообщения об ошибке на странице Википедии")
  void checkErrorTextTest() {
    step("Ввод значения Appium в строку поиска", () -> {
              searchPage.clickOnFirstSearch()
              .clickOnSecondSearch("Appium");
    });
    step("Клик по строке поиска с содержанием текста введенного запроса", () -> {
              searchPage.clickOnLineSearch("Appium");
            }
    );
    step("Проверка видимости сообщения об ошибке", () -> {
              vikiPage.checkErrorText("An error occurred")
                      .checkErrorButton("GO BACK");
            }
    );
  }

}