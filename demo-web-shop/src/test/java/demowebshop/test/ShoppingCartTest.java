package demowebshop.test;

import static com.codeborne.selenide.Selenide.open;

import demowebshop.dto.ProductCart;
import demowebshop.page_object.Header;
import demowebshop.page_object.ProductListPage;
import demowebshop.page_object.MenuButton;
import demowebshop.page_object.Notification;
import demowebshop.page_object.ShoppingCartPage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest extends BaseTest {

  private final MenuButton menuButton = new MenuButton();
  private final ProductListPage productListPage = new ProductListPage();
  private final Notification notification = new Notification();
  private final Header header = new Header();
  private final ShoppingCartPage shoppingCartPage = new ShoppingCartPage();


  @BeforeEach
  void setUp() {
    open("/");
  }

  @Test
  void checkNotificationBar() {
    menuButton
        .clickOnButton("Books");
    productListPage
        .clickOnAddToCartButtonForProduct("Computing and Internet");
    notification
        .should("The product has been added to your shopping cart")
        .shouldLink("shopping cart")
        .shouldHaveCloseButton();
  }

  @Test
  @Tag("without-auth")
  void checkShoppingCartCounterInHeader() {
    menuButton
        .clickOnButton("Digital downloads");
    productListPage
        .clickOnAddToCartButtonForProduct("3rd Album");
    menuButton
        .clickOnButton("Books");
    productListPage
        .clickOnAddToCartButtonForProduct("Computing and Internet");
    header
        .cartButtonShould("Shopping cart (2)");
  }

  @Test
  @Tag("without-auth")
  void checkShoppingCartAddedProducts() {
    String thirdAlbum = "3rd Album";
    String computingAndInternet = "Computing and Internet";

    menuButton
        .clickOnButton("Digital downloads");
    productListPage
        .clickOnAddToCartButtonForProduct(thirdAlbum);
    notification
        .should("The product has been added to your shopping cart");
    menuButton
        .clickOnButton("Books");
    productListPage
        .clickOnAddToCartButtonForProduct(computingAndInternet);
    notification
        .should("The product has been added to your shopping cart");
    header
        .clickHeaderButton("Shopping cart");
    shoppingCartPage
        .cartProductsShould(List.of(thirdAlbum, computingAndInternet));
  }

  @Test
  @Tag("without-auth")
  void checkAddingProductToShoppingCartWithoutAuth() {
    menuButton
        .clickOnButton("Books");
    productListPage
        .clickOnAddToCartButtonForProduct("Computing and Internet");
  }

  @Test
  @Tag("without-auth")
  void checkProductCartHeaderHover() {
    String thirdAlbum = "3rd Album";
    String computingAndInternet = "Computing and Internet";

    menuButton
        .clickOnButton("Digital downloads");
    productListPage
        .clickOnAddToCartButtonForProduct(thirdAlbum);
    notification
        .should("The product has been added to your shopping cart");
    menuButton
        .clickOnButton("Books");
    productListPage
        .clickOnAddToCartButtonForProduct(computingAndInternet);
    notification
        .should("The product has been added to your shopping cart");
    header
        .hoverOnShoppingCart()
        .miniShoppingCartShouldDisplayed()
        .miniShoppingCartShouldCount("There are 2 item(s) in your cart.")
        .miniShoppingCartShouldProducts(
            ProductCart.builder()
                .name(computingAndInternet)
                .unitPrice("Unit price: 10.00")
                .quantity("Quantity: 1")
                .build(),
            ProductCart.builder()
                .name(thirdAlbum)
                .unitPrice("Unit price: 1.00")
                .quantity("Quantity: 1")
                .build());
  }

}
