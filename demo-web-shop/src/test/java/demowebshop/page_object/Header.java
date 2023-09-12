package demowebshop.page_object;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import demowebshop.dto.ProductCart;
import io.qameta.allure.Step;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

@SuppressWarnings("UnusedReturnValue")
public class Header {

  SelenideElement logo = $("img[alt='Tricentis Demo Web Shop']");
  SelenideElement registerButton = $("a[class='ico-register']");
  SelenideElement loginButton = $("a[class='ico-login']");
  SelenideElement cartButton = $("a[class='ico-cart']");
  SelenideElement wishlistButton = $("a[class='ico-wishlist']");
  SelenideElement searchInput = $("input[id='small-searchterms']");
  SelenideElement searchButton = $("input[class~='search-box-button']");
  SelenideElement menu = $("[class='header-menu']");
  SelenideElement miniShoppingCart = $(".mini-shopping-cart");
  ElementsCollection miniShoppingCartItems = miniShoppingCart.$$("[class~='item']");


  @Step("Проверить, что отображается logo")
  public Header logoShould(Condition condition) {
    logo.should(condition);

    return this;
  }

  @Step("Проверить, что отображается линк Register")
  public Header registrationButtonShould(String expectedText) {
    registerButton.should(text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается линк Log in")
  public Header loginButtonShould(Condition condition, String expectedText) {
    loginButton
        .should(condition)
        .should(text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается линк {expectedText}")
  public Header cartButtonShould(String expectedText) {
    cartButton.should(text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается линк {expectedText}")
  public Header wishlistButtonShould(String expectedText) {
    wishlistButton.should(text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается плейсхолдер {expectedPlaceholder} у поля Search")
  public Header searchInputShould(String expectedPlaceholder) {
    searchInput.should(attribute("value", expectedPlaceholder));

    return this;
  }

  @Step("Проверить, что отображается кнопка Search")
  public Header searchButtonShould(Condition condition, String expectedText) {
    searchButton
        .should(condition)
        .should(attribute("value", expectedText));

    return this;
  }

  @Step("Проверить, что отображается кнопка {expectedNames}}")
  public Header menuButtonsShould(Condition condition, List<String> expectedNames) {
    menu.findAll("li > a")
        .filter(visible)
        .should(CollectionCondition.texts(expectedNames))
        .asFixedIterable()
        .forEach(name -> name.should(condition));

    return this;
  }

  @Step("Нажать на кнопку {buttonName}")
  public Header clickHeaderButton(String buttonName) {
    $x(String.format("//div[@class='header-links']//*[contains(text(), '%s')]",
        buttonName)).click();

    return this;
  }

  @Step("Нажать на кнопку {buttonName}")
  public Header clickHeaderMenuButton(String buttonName) {
    $x(String.format("//ul[@class='top-menu']//*[contains(text(), '%s')]",
        buttonName)).click();

    return this;
  }

  @Step("Нажать на кнопку logo")
  public Header clickHeaderLogo() {
    $(".header-logo > a").click();

    return this;
  }

  @Step("Ввести значение {value} в поле Search")
  public Header setValueForSearchInput(String value) {
    searchInput.type(value);

    return this;
  }

  @Step("Нажать кнопку Search")
  public Header clickOnSearchButton() {
    searchButton.click();

    return this;
  }

  @Step("Проверить, что отображаются {hints}")
  public Header searchInputShouldHints(List<String> hints) {
    $("#ui-id-1").should(visible)
        .findAll(".ui-menu-item")
        .should(CollectionCondition.size(7))
        .shouldHave(CollectionCondition.texts(hints));

    return this;
  }

  @Step("Проверить, что отображается кнопка {name}")
  public Header checkButtonDisplayed(String name) {
    $(".header-links")
        .should(text(name))
        .should(visibleAndClickable);

    return this;
  }

  @Step("Навести курсор на Shopping Cart")
  public Header hoverOnShoppingCart() {
    cartButton.hover();

    return this;
  }

  @Step("Проверить, что отображается Mini Shopping Cart")
  public Header miniShoppingCartShouldDisplayed() {
    miniShoppingCart
        .should(visible);

    return this;
  }

  @Step("Проверить, что в Mini Shopping Cart отображается {expected}")
  public Header miniShoppingCartShouldCount(String expected) {
    miniShoppingCart
        .find(".count")
        .should(text(expected));

    return this;
  }

  @Step("Проверить, что в Mini Shopping Cart отображается {expectedCart_1}, {expectedCart_2}")
  public Header miniShoppingCartShouldProducts(ProductCart expectedCart_1, ProductCart expectedCart_2) {
    miniShoppingCartItems
        .should(CollectionCondition.size(2));

    ElementsCollection cartsImages = miniShoppingCart.$$("[class~='item'] img");
    ElementsCollection cartsNames = miniShoppingCart.$$("[class~='item'] .name");
    ElementsCollection cartsPrices = miniShoppingCart.$$("[class~='item'] .price");
    ElementsCollection cartsQuantities = miniShoppingCart.$$("[class~='item'] .quantity");

   cartsImages.get(0).should(visible);
   cartsImages.get(1).should(visible);

    ProductCart actualCart_1 = ProductCart.builder()
       .name(cartsNames.get(0).getText())
       .unitPrice(cartsPrices.get(0).getText())
       .quantity(cartsQuantities.get(0).getText())
       .build();

    ProductCart actualCart_2 = ProductCart.builder()
        .name(cartsNames.get(1).getText())
        .unitPrice(cartsPrices.get(1).getText())
        .quantity(cartsQuantities.get(1).getText())
        .build();

    Assertions.assertThat(actualCart_1)
        .usingRecursiveAssertion()
        .ignoringAllNullFields()
        .isEqualTo(expectedCart_1);
    Assertions.assertThat(actualCart_2)
        .usingRecursiveAssertion()
        .isEqualTo(expectedCart_2);

    return this;
  }


}
