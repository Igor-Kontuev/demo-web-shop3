package demowebshop.page_object;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

@SuppressWarnings("UnusedReturnValue")
public class LogInPage {

  private final SelenideElement title = $(".page-title");
  private final SelenideElement registerBlock = $("[class~='register-block']");
  private final SelenideElement returningCustomer = $(".returning-wrapper");
  private final SelenideElement buttonRegister = $x("//input[contains(@class,'register-button')]");
  private final SelenideElement inputEmail = $("#Email");
  private final SelenideElement inputPassword = $("#Password");
  private final SelenideElement checkboxRememberMe = $("#RememberMe");
  private final SelenideElement forgotPassword = $(".forgot-password a");
  private final SelenideElement buttonLogin = $("[class~='login-button']");
  private final SelenideElement aboutLoginOrRegistration = $(".topic-html-content");
  private final SelenideElement validationSummaryErrors = $(".validation-summary-errors");
  private final SelenideElement emailValidationError = $(
      ".field-validation-error > span[for='Email']");


  @Step("Проверить отображение тайтл")
  public LogInPage checkTitle(String expectedTitle) {
    title.should(text(expectedTitle))
        .should(visible);

    return this;
  }

  @Step("Проверить New Customer отображение тайтл")
  public LogInPage checkNewCustomerTitle(String expectedTitle) {
    registerBlock.find(".title")
        .should(visible)
        .should(text(expectedTitle));

    return this;
  }

  @Step("Проверить New Customer отображение контент")
  public LogInPage checkNewCustomerText(String expectedText) {
    registerBlock.find(".text")
        .should(visible)
        .should(text(expectedText));

    return this;
  }

  @Step("Проверить New Customer отображение кнопку регистрации")
  public LogInPage checkNewCustomerRegisterButton(Condition condition) {
    buttonRegister.should(condition);
    return this;
  }

  @Step("Проверить Returning Customer отображение тайтл")
  public LogInPage checkReturningCustomerTitle(String expectedTitle) {
    returningCustomer
        .find(".title")
        .should(text(expectedTitle));

    return this;
  }

  @Step("Проверить Returning Customer отображение поля {email}, {password}")
  public LogInPage checkReturningCustomerInputs(String email, String password) {
    inputEmail
        .should(visible)
        .parent()
        .should(text(email));
    inputPassword
        .should(visible)
        .parent()
        .should(text(password));

    return this;
  }

  @Step("Проверить Returning Customer отображение чекбокс {checkbox}")
  public LogInPage checkReturningCustomerCheckbox(String checkbox) {
    checkboxRememberMe
        .should(visibleAndClickable)
        .should(not(selected))
        .parent()
        .should(text(checkbox));

    return this;
  }

  @Step("Проверить в Returning Customer отображение Forgot Password линк {forgotPassword}")
  public LogInPage checkReturningCustomerForgotPassword(String forgotPasswordText) {
    forgotPassword
        .should(visibleAndClickable)
        .should(text(forgotPasswordText));

    return this;
  }

  @Step("Проверить Returning Customer отображение кнопки регистрации")
  public LogInPage checkReturningCustomerLogInButton(String text) {
    buttonLogin
        .should(visibleAndClickable)
        .should(attribute("value", text));

    return this;
  }

  @Step("Проверить отображение тайтл в блоке About login / registration")
  public LogInPage checkAboutLoginOrRegistrationTitle(String title) {
    aboutLoginOrRegistration
        .find(".topic-html-content-title")
        .should(visible)
        .should(text(title));

    return this;
  }

  @Step("Проверить отображение контент в блоке About login / registration")
  public LogInPage checkAboutLoginOrRegistrationContent(String content) {
    aboutLoginOrRegistration
        .find(".topic-html-content-body")
        .should(visible)
        .should(text(content));

    return this;
  }

  @Step("Нажать на кнопку Register")
  public LogInPage clickOnRegister() {
    buttonRegister.click();

    return this;
  }

  @Step("Ввести в поле email значение {email}")
  public LogInPage setEmail(String email) {
    inputEmail.setValue(email);

    return this;
  }

  @Step("Ввести в поле password значение {password}")
  public LogInPage setPassword(String password) {
    inputPassword.setValue(password);

    return this;
  }

  @Step("Нажать кнопку Log in")
  public LogInPage clickOnLogInButton() {
    buttonLogin.click();

    return this;
  }

  @Step("Проверить, что отображается ошибка валидации {expectedText}")
  public LogInPage shouldValidationSummaryErrors(String expectedText) {
    validationSummaryErrors
        .should(visible)
        .should(text(expectedText));

    return this;
  }

  @Step("Проверить, что отображается ошибка валидации email {expectedText}")
  public LogInPage shouldEmailValidationError(String expectedText) {
    emailValidationError
        .should(visible)
        .should(text(expectedText));

    return this;
  }

  @Step("Нажать на чекбокс Remember me? и проверить что чекбокс выбран")
  public LogInPage clickOnCheckboxRememberMeAndCheckboxShould(Condition condition) {
    checkboxRememberMe.click(ClickOptions.usingDefaultMethod())
        .should(condition);

    return this;
  }

  @Step("Нажать на линк Forgot password?")
  public LogInPage clickOnForgotPassword() {
    forgotPassword.click();

    return this;
  }
}
