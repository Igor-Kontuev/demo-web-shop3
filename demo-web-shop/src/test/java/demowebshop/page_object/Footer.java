package demowebshop.page_object;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class Footer {

  SelenideElement poweredBy = $(".footer-poweredby");
  SelenideElement disclaimer = $(".footer-disclaimer");

  @Step("Проверить, что в {columnName} отображаются кликабельные ссылки {expectedLinksNames}")
  public Footer columnShould(String columnName, List<String> expectedLinksNames) {
    $x(String.format("//h3[text()='%s']", columnName))
        .should(Condition.visible)
        .scrollIntoView(true)
        .parent()
        .findAll("li > a")
        .should(CollectionCondition.texts(expectedLinksNames))
        .asFixedIterable()
        .forEach(name -> name.should(visibleAndClickable));

    return this;
  }

  @Step("Проверить, что отображается копирайт с текстом {expectedText}")
  public Footer poweredByShould(String expectedText) {
    poweredBy.should(Condition.text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается копирайт с текстом {expectedText}")
  public Footer disclaimerShould(String expectedText) {
    disclaimer.should(Condition.text(expectedText));

    return this;
  }


}
