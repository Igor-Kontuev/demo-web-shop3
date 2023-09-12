package demowebshop.page_object;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

@SuppressWarnings("UnusedReturnValue")
public class MenuButton {

  private final SelenideElement headerMenu = $(".header-menu");

  @Step("Нажать на кнопку {name}")
  public MenuButton clickOnButton(String name) {
    headerMenu
        .findAll("a")
        .find(Condition.text(name))
        .click();

    return this;
  }
}
