package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.SearchPage;
import pages.VikiPage;

import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {

  private SearchPage searchPage = new SearchPage();
  private VikiPage vikiPage = new VikiPage();

  private final static String SEARCH_VALUE = "Appium";
  private final static String ERROR_TEXT_VALUE = "An error occurred";
  private final static String ERROR_BUTTON_NAME = "GO BACK";

  @Test
  @DisplayName("Проверка успешной работы поиска")
  void successfulSearchTest() {
    step("Ввод значения Appium в строку поиска", () -> {
            searchPage.clickOnFirstSearch()
              .clickOnSecondSearch(SEARCH_VALUE);
    });
    step("Проверка, что открывшийся список больше нуля", () ->
            searchPage.checkNotNull()
    );
  }

  @Test
  @DisplayName("Проверка отображения сообщения об ошибке на странице Википедии")
  void checkErrorTextTest() {
    step("Ввод значения Appium в строку поиска", () -> {
              searchPage.clickOnFirstSearch()
              .clickOnSecondSearch(SEARCH_VALUE);
    });
    step("Клик по строке поиска с содержанием текста введенного запроса", () -> {
              searchPage.clickOnLineSearch(SEARCH_VALUE);
            }
    );
    step("Проверка видимости сообщения об ошибке", () -> {
              vikiPage.checkErrorText(ERROR_TEXT_VALUE)
                      .checkErrorButton(ERROR_BUTTON_NAME);
            }
    );
  }

}