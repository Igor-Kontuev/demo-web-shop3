package demowebshop.page_object;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class ShoppingCartPage {

  private ElementsCollection cartProductsNames = $$(".cart-item-row .product .product-name");

  @Step("Проверить, что в карточке товаров {names}")
  public ShoppingCartPage cartProductsShould(List<String> names) {

    cartProductsNames
        .should(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(names));

    return this;
  }

}
