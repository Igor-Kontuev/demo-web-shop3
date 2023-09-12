package demowebshop.test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

  static Faker faker = new Faker();

  @BeforeAll
  static void beforeAll() {
    Configuration.browser = Browsers.CHROME;
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://demowebshop.tricentis.com";
  }

  public void shouldUrl(String expectedUrl) {
    webdriver().shouldHave(url(expectedUrl), Duration.ofSeconds(3));
  }

  public void shouldUrlPath(String expectedUrlPath) {
    webdriver().shouldHave(urlContaining(expectedUrlPath), Duration.ofSeconds(3));
  }

  @AfterEach
  void tearDown() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();

  }
}
