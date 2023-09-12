package demowebshop.page_object;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

@SuppressWarnings("UnusedReturnValue")
public class ProductListPage {

  private final ElementsCollection productList = $$(".item-box");

  @Step("Нажать на Add To Cart у товара {name}")
  public ProductListPage clickOnAddToCartButtonForProduct(String name) {
    productList
        .find(Condition.text(name))
        .parent()
        .find(".product-box-add-to-cart-button")
        .click();

    return this;
  }






}
