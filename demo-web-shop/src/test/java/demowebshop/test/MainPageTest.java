package demowebshop.test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.Condition;
import demowebshop.page_object.Footer;
import demowebshop.page_object.Header;
import demowebshop.page_object.MainPage;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTest extends BaseTest {

  private final Header header = new Header();
  private final MainPage mainPage = new MainPage();
  private final Footer footer = new Footer();


  @BeforeEach
  void setUp() {
    open("/");
  }

  @Test
  @Order(1)
  void checkDisplayedAllElementsOnMainPage() {
    header.logoShould(visible)
        .registrationButtonShould("Register")
        .loginButtonShould(visible,"Log in")
        .cartButtonShould("Shopping cart (0)")
        .wishlistButtonShould("Wishlist (0)")
        .searchInputShould("Search store")
        .searchButtonShould(Condition.enabled, "Search")
        .menuButtonsShould(visibleAndClickable,
            List.of("BOOKS",
                "COMPUTERS",
                "ELECTRONICS",
                "APPAREL & SHOES",
                "DIGITAL DOWNLOADS",
                "JEWELRY",
                "GIFT CARDS"));
    mainPage.leftListBoxShould("Categories",
            List.of("BOOKS",
                "COMPUTERS",
                "ELECTRONICS",
                "APPAREL & SHOES",
                "DIGITAL DOWNLOADS",
                "JEWELRY",
                "GIFT CARDS"))
        .leftListBoxShould("Manufacturers", List.of("Tricentis"))
        .leftListBoxShouldNotEmpty("Popular tags")
        .leftListBoxShouldHaveButton("Popular tags", "View all")
        .rightBlockNewsletterShouldHaveSubscribeField("Sign up for our newsletter:")
        .rightBlockNewsletterShouldHaveSubscribeButton("Subscribe")
        .rightBlockCommunityPollShouldHavePoolName("Do you like nopCommerce?")
        .rightBlockCommunityPollShouldHavePoolOptions(
            List.of("Excellent",
                "Good",
                "Poor",
                "Very bad"))
        .centerBlockShouldSlider()
        .centerBlockShouldSliderPaginationButtons()
        .centerBlockShouldSWelcomeTopic(
                "Welcome to our store\n"+
                "Welcome to the new Tricentis store!\n"+
                "Feel free to shop around and explore everything.")
        .centerBlockShouldSFeaturedProducts();
    footer.columnShould("Information",
            List.of("Sitemap",
                "Shipping & Returns",
                "Privacy Notice",
                "Conditions of Use",
                "About Us",
                "Contact Us"))
        .columnShould("Customer service",
            List.of("Search",
                "News",
                "Blog",
                "Recently viewed products",
                "Compare products list",
                "New products"))
        .columnShould("My account",
            List.of("My account",
                "Orders",
                "Addresses",
                "Shopping cart",
                "Wishlist"))
        .columnShould("Follow us",
            List.of("Facebook",
                "Twitter",
                "RSS",
                "YouTube",
                "Google+"))
        .poweredByShould("Powered by nopCommerce")
        .disclaimerShould("Copyright")
        .disclaimerShould("2023 Tricentis Demo Web Shop. All rights reserved.");

    shouldUrl("https://demowebshop.tricentis.com/");

  }

  @Order(2)
  @ParameterizedTest(name = "{0}")
  @MethodSource("buttonsAndExpectedMap")
  void checkRedirectHeaderButtons(String button, Map<String, String> expected) {
    header.clickHeaderButton(button);
    shouldUrlPath(expected.get("urlPath"));
    mainPage.pageTitleShould(expected.get("pageTitle"));
  }

  public static Stream<Arguments> buttonsAndExpectedMap() {
    return Stream.of(
        Arguments.of(
            "Register", Map.of(
                "urlPath", "/register",
                "pageTitle", "Register"
            )),
        Arguments.of(
            "Log in", Map.of(
                "urlPath", "/login",
                "pageTitle", "Welcome, Please Sign In!"
            )),
        Arguments.of(
            "Shopping cart", Map.of(
                "urlPath", "/cart",
                "pageTitle", "Shopping cart"
            )),
        Arguments.of(
            "Wishlist", Map.of(
                "urlPath", "/wishlist",
                "pageTitle", "Wishlist"
            ))
    );
  }

  @Test
  void checkRedirectByClickOnLogo() {
    header.clickHeaderButton("Register");
    mainPage.pageTitleShould("Register");
    header.clickHeaderLogo();
    mainPage.centerBlockShouldSlider();
  }

  @Test
  void checkSearch() {
    header.setValueForSearchInput("Comp")
        .clickOnSearchButton();
    mainPage.pageTitleShould("Search");
  }

  @Test
  void checkSearchHints() {
    header.setValueForSearchInput("cOmp")
        .searchInputShouldHints(List.of(
            "Build your own cheap computer",
            "Build your own computer",
            "Build your own expensive computer",
            "Computing and Internet",
            "Copy of Computing and Internet EX",
            "Simple Computer",
            "TCP Public Complete"
        ));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("menuButtonsAndExpectedMap")
  void checkRedirectHeaderMenuButtons(String button, Map<String, String> expected) {
    header.clickHeaderMenuButton(button);
    shouldUrlPath(expected.get("urlPath"));
    mainPage.pageTitleShould(expected.get("pageTitle"));
  }

  public static Stream<Arguments> menuButtonsAndExpectedMap() {
    return Stream.of(
        Arguments.of(
            "Books", Map.of(
                "urlPath", "/books",
                "pageTitle", "Books"
            )),
        Arguments.of(
            "Computers", Map.of(
                "urlPath", "/computers",
                "pageTitle", "Computers"
            )),
        Arguments.of(
            "Electronics", Map.of(
                "urlPath", "/electronics",
                "pageTitle", "Electronics"
            )),
        Arguments.of(
            "Apparel & Shoes", Map.of(
                "urlPath", "/apparel-shoes",
                "pageTitle", "Apparel & Shoes"
            )),
        Arguments.of(
            "Digital downloads", Map.of(
                "urlPath", "/digital-downloads",
                "pageTitle", "Digital downloads"
            )),
        Arguments.of(
            "Jewelry", Map.of(
                "urlPath", "/jewelry",
                "pageTitle", "Jewelry"
            )),
        Arguments.of(
            "Gift Cards", Map.of(
                "urlPath", "/gift-cards",
                "pageTitle", "Gift Cards"
            ))
    );
  }

  @ParameterizedTest
  @MethodSource("provideHeaderMenuButtonsAndExpectedList")
  void checkDropDownsHeaderMenuButtons(String name, List<String> expectedList) {
    mainPage.hoverOnButton(name)
        .dropDownListShould(expectedList);
  }

  public static Stream<Arguments> provideHeaderMenuButtonsAndExpectedList() {
    return Stream.of(
        Arguments.of(
            "Computers", List.of("Desktops", "Notebooks", "Accessories")),
        Arguments.of(
            "Electronics", List.of("Camera, photo", "Cell phones"))
    );
  }

}
