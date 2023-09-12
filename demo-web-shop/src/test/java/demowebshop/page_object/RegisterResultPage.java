package demowebshop.page_object;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

@SuppressWarnings("UnusedReturnValue")
public class RegisterResultPage {

  private SelenideElement registerResultMessage = $(".result");
  private SelenideElement continueButton = $("[class~='register-continue-button']");

  @Step("Проверить, что отображаются {message}")
  public RegisterResultPage checkResultMassageIsDisplayed(String message) {
    registerResultMessage.should(text(message));

    return this;
  }

  @Step("Проверить, что отображаются кнопка Continue")
  public void continueButtonIsDisplayed() {
    continueButton.should(visibleAndClickable);
  }

  @Step("Нажать на кнопка Continue")
  public RegisterResultPage clickContinue() {
    continueButton.click();

    return this;
  }
}
