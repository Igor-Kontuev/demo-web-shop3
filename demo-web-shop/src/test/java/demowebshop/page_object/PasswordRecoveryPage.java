package demowebshop.page_object;

import static com.codeborne.selenide.Selenide.$;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

@SuppressWarnings("UnusedReturnValue")
public class PasswordRecoveryPage {

  private final SelenideElement inputEmail = $("#Email");
  private final SelenideElement buttonRecovery = $("[class~='password-recovery-button'");

  @Step("Проверить, что отображается поле {name}")
  public PasswordRecoveryPage shouldInput(String name, Condition condition) {
    inputEmail.should(condition)
        .parent()
        .should(Condition.text(name));

    return this;
  }

  @Step("Проверить, что отображается кнопка Recovery")
  public PasswordRecoveryPage shouldButtonRecovery(){
    buttonRecovery.should(visibleAndClickable);

    return this;
  }

}
