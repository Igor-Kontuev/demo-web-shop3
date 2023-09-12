package demowebshop.page_object;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class RegisterPage {

  private final ElementsCollection radioButtonNames = $$x(
      "//label[contains(@class, 'forcheckbox')]");
  private final SelenideElement radioButtonMale = $x(
      "//label[text()='Male']//..//input[@type='radio']");
  private final SelenideElement radioButtonFemale = $x(
      "//label[text()='Female']//..//input[@type='radio']");
  private final SelenideElement inputFirstName = $x("//input[@id='FirstName']");
  private final SelenideElement inputLastName = $x("//input[@id='LastName']");
  private final SelenideElement inputEmail = $x("//input[@id='Email']");
  private final SelenideElement inputPassword = $x("//input[@id='Password']");
  private final SelenideElement inputConfirmPassword = $x("//input[@id='ConfirmPassword']");
  private final SelenideElement buttonRegister = $x("//input[@id='register-button']");

  @Step("Проверить, что отображается {blockName}")
  public RegisterPage blockShould(Condition condition, String blockName) {
    $x(String.format("//strong[text()='%s']", blockName))
        .should(condition);

    return this;
  }

  @Step("Проверить, что отображается {names}")
  public RegisterPage blockYourPersonalDetailsShouldFieldsNames(List<String> names) {
    $$x("//strong[text()='Your Personal Details']//..//..//label[not(contains(@class, 'forcheckbox'))]")
        .should(CollectionCondition.texts(names));

    return this;
  }

  @Step("Проверить, что отображается {names}")
  public RegisterPage blockYourPersonalDetailsShouldGenderRadioButtons(List<String> names) {
    radioButtonNames.should(CollectionCondition.texts(names));
    radioButtonMale.should(visibleAndClickable);
    radioButtonFemale.should(visibleAndClickable);

    return this;
  }

  @Step("Проверить, что отображается поле FirstName, LastName, Email")
  public RegisterPage blockYourPersonalDetailsShouldInputs(Condition condition) {
    inputFirstName.should(condition);
    inputLastName.should(condition);
    inputEmail.should(condition);

    return this;
  }

  @Step("Проверить, что отображается астериски")
  public RegisterPage blockYourPersonalDetailsShouldInputsAsterisks(Condition condition) {
    inputFirstName.parent()
        .findAll("span")
        .find(text("*"))
        .should(condition);
    inputLastName.parent()
        .findAll("span")
        .find(text("*"))
        .should(condition);
    inputEmail.parent()
        .findAll("span")
        .find(text("*"))
        .should(condition);

    return this;
  }

  @Step("Проверить, что отображается {names}")
  public RegisterPage blockYourPasswordShouldFieldNames(List<String> names) {
    $$x("//strong[text()='Your Password']//..//..//label")
        .should(CollectionCondition.texts(names));
    return this;
  }

  @Step("Проверить, что отображается Password, Confirm Password")
  public RegisterPage blockYourPasswordShouldInputs(Condition condition) {
    inputPassword.should(condition);
    inputConfirmPassword.should(condition);

    return this;
  }

  @Step("Проверить, что отображается астериски")
  public RegisterPage blockYourPasswordShouldInputsAsterisks(Condition condition) {
    inputPassword.parent()
        .findAll("span")
        .find(text("*"))
        .should(condition);
    inputConfirmPassword.parent()
        .findAll("span")
        .find(text("*"))
        .should(condition);

    return this;
  }

  @Step("Проверить, что отображается кнопка Register")
  public RegisterPage shouldRegisterButton() {
    buttonRegister.should(visibleAndClickable);

    return this;
  }

  @Step("Ввести значение в поле Password")
  public RegisterPage setValuePassword(String value) {
    inputPassword.setValue(value);

    return this;
  }

  @Step("Ввести значение в поле Confirm Password")
  public RegisterPage setValueConfirmPassword(String value) {
    inputConfirmPassword.setValue(value);
    return this;
  }

  @Step("Проверить, что пароль замаскирован")
  public RegisterPage checkValuesMasked() {
    inputPassword.should(attribute("type", "password"));
    inputConfirmPassword.should(attribute("type", "password"));

    return this;
  }

  @Step("Ввести значение в поле FirstName")
  public RegisterPage setValueFirstName(String value) {
    inputFirstName.setValue(value)
        .should(attribute("value", value));

    return this;
  }

  @Step("Ввести значение в поле LastName")
  public RegisterPage setValueLastName(String value) {
    inputLastName.setValue(value)
        .should(attribute("value", value));

    return this;
  }

  @Step("Ввести значение в поле Email")
  public RegisterPage setValueEmail(String value) {
    inputEmail.setValue(value)
        .should(attribute("value", value));

    return this;
  }

  @Step("Нажать на кнопку Register")
  public RegisterPage clickOnRegisterButton() {
    buttonRegister.click();
    return this;
  }

  @Step("Выбрать радио-кнопку Male")
  public RegisterPage selectMaleRadioButton(Condition condition) {
    radioButtonMale.click(ClickOptions.usingDefaultMethod())
        .should(condition);

    return this;
  }

  @Step("Выбрать радио-кнопку Female")
  public RegisterPage selectFemaleRadioButton(Condition condition) {
    radioButtonFemale.click(ClickOptions.usingDefaultMethod())
        .should(condition);

    return this;
  }

  @Step("Проверить, что отображаются ошибки валидации {errors}")
  public RegisterPage checkValidationFieldsErrors(List<String> errors) {
    $$("[class='field-validation-error']")
        .shouldHave(CollectionCondition.texts(errors));

    return this;
  }

  @Step("Проверить, что отображаются ошибка валидации {error}")
  public RegisterPage checkValidationSummary(String error) {
    $(".validation-summary-errors")
        .should(text(error));

    return this;
  }
}
