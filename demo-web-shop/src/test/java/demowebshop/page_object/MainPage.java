package demowebshop.page_object;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;

@SuppressWarnings("UnusedReturnValue")
public class MainPage {

  SelenideElement leftSide = $(".leftside-3");
  SelenideElement rightSide = $(".rightside-3");
  SelenideElement center = $(".center-3");
  String newsletterCssSelector = "[class~='block-newsletter']";
  String pollBlock = "[class~='block-poll']";
  String featuredProductsCssSelector = "[class~=home-page-product-grid]";


  @Step("Проверить, что {listBoxName} содержит список {listBoxNames}")
  public MainPage leftListBoxShould(String listBoxName, List<String> listBoxNames) {
    leftSide.$x(String.format(".//strong[text()='%s']", listBoxName))
        .should(visible, Duration.ofSeconds(5))
        .parent()
        .parent()
        .findAll("ul > li > a")
        .shouldHave(texts(listBoxNames));

    return this;
  }

  @Step("Проверить, что отображается {listBoxName}")
  public MainPage leftListBoxShouldNotEmpty(String listBoxName) {
    leftSide.$x(String.format(".//strong[text()='%s']", listBoxName))
        .should(visible, Duration.ofSeconds(5))
        .parent()
        .parent()
        .findAll("ul > li > a")
        .asFixedIterable()
        .forEach(tag -> tag.should(not(empty)));

    return this;
  }

  @Step("Проверить, что {listBoxName} отображается кнопка {listBoxButtonName}")
  public MainPage leftListBoxShouldHaveButton(String listBoxName, String listBoxButtonName) {
    leftSide.$x(String.format(".//strong[text()='%s']", listBoxName))
        .should(visible)
        .parent()
        .parent()
        .find(By.xpath(String.format(".//a[text()='%s']", listBoxButtonName)))
        .should(visibleAndClickable);

    return this;
  }

  @Step("Проверить, что отображается кнопка {buttonName}")
  public MainPage rightBlockNewsletterShouldHaveSubscribeButton(String buttonName) {
    rightSide.find(newsletterCssSelector)
        .find("#newsletter-subscribe-button")
        .should(attribute("value", buttonName));

    return this;
  }

  @Step("Проверить, что отображается поле {fieldName}")
  public MainPage rightBlockNewsletterShouldHaveSubscribeField(String fieldName) {
    rightSide.find(newsletterCssSelector)
        .should(text("Newsletter"))
        .find("#newsletter-subscribe-block > span")
        .should(text(fieldName))
        .parent()
        .find("#newsletter-email")
        .should(visibleAndClickable);

    return this;
  }

  @Step("Проверить, что отображается {poolName}")
  public MainPage rightBlockCommunityPollShouldHavePoolName(String poolName) {
    rightSide.find(pollBlock)
        .should(text(poolName));

    return this;
  }

  @Step("Проверить, что отображается {poolOptions}")
  public MainPage rightBlockCommunityPollShouldHavePoolOptions(List<String> poolOptions) {
    rightSide.find(pollBlock)
        .findAll(".answer")
        .should(texts(poolOptions));

    rightSide.find(pollBlock)
        .findAll(".answer > input")
        .shouldHave(size(4))
        .asFixedIterable()
        .forEach(option -> option.should(visibleAndClickable));

    return this;
  }

  @Step("Проверить, что отображается центральный блок")
  public MainPage centerBlockShouldSlider() {
    center.find("#nivo-slider")
        .should(visible);

    return this;
  }

  @Step("Проверить, что отображается пагинация")
  public MainPage centerBlockShouldSliderPaginationButtons() {
    center.find(".nivo-controlNav")
        .should(visible);

    return this;
  }

  @Step("Проверить, что отображается топик {expectedText}")
  public MainPage centerBlockShouldSWelcomeTopic(String expectedText) {
    center.find(".topic-html-content")
        .should(text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается блок Featured Products")
  public MainPage centerBlockShouldSFeaturedProducts() {
    center.find(featuredProductsCssSelector)
        .should(visible);

    center.findAll(".item-box")
        .asFixedIterable()
        .forEach(item -> {
          item.find(".picture > a").should(visibleAndClickable);
          item.find(".product-title")
              .should(not(empty))
              .should(visibleAndClickable);
          item.find(".rating").should(visible);
          item.find(".prices").should(not(empty));
          item.find("input").should(visibleAndClickable)
              .should(attribute("value", "Add to cart"));
        });

    return this;
  }
  @Step("Проверить, что отображается тайтл {expectedTitle}")
  public MainPage pageTitleShould(String expectedTitle) {
    $(".page-title").should(text(expectedTitle));

    return this;
  }

  @Step("Навести курсор на кнопку {headerMenuButtonName}")
  public MainPage hoverOnButton(String headerMenuButtonName) {
    $$(".top-menu > li > a")
        .find(text(headerMenuButtonName))
        .hover();

    return this;
  }

  @Step("Проверить, что отображается выпадающий список {expectedList}")
  public MainPage dropDownListShould(List<String> expectedList) {
    $$("[class~='sublist'] > li > a")
        .filter(visible)
        .should(texts(expectedList));

    return this;
  }
}
