package demowebshop.test;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static demowebshop.util.Conditions.visibleAndClickable;

import demowebshop.dto.User;
import demowebshop.page_object.Header;
import demowebshop.page_object.LogInPage;
import demowebshop.page_object.PasswordRecoveryPage;
import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LogInPageTest extends BaseTest {

  private final LogInPage logInPage = new LogInPage();
  private final Header header = new Header();
  private final PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage();

  @BeforeEach
  void setUp() {
    open("/login");
  }

  @Test
  void checkLoginPageDisplayed() {
    logInPage
        .checkTitle("Welcome, Please Sign In!")
        .checkNewCustomerTitle("New Customer")
        .checkNewCustomerText(
            "By creating an account on our website you will be able to shop faster, "
                + "be up to date on an orders status, "
                + "and keep track of the orders you have previously made.")
        .checkNewCustomerRegisterButton(visibleAndClickable)
        .checkReturningCustomerTitle("Returning Customer")
        .checkReturningCustomerInputs("Email:", "Password:")
        .checkReturningCustomerCheckbox("Remember me?")
        .checkReturningCustomerForgotPassword("Forgot password?")
        .checkReturningCustomerLogInButton("Log in")
        .checkAboutLoginOrRegistrationTitle("About login / registration")
        .checkAboutLoginOrRegistrationContent("Put your login / registration information here. "
            + "You can edit this in the admin site.");
  }

  @Test
  void checkRedirectToRegisterPage() {
    logInPage
        .clickOnRegister();
    shouldUrlPath("/register");
  }

  @Test
  void checkLogIn() {
    logInPage
        .setEmail("testsuperuser@gmail.com")
        .setPassword("testsuperuser@gmail.com")
        .clickOnLogInButton();
    header
        .checkButtonDisplayed("testsuperuser@gmail.com");
  }

  @ParameterizedTest
  @MethodSource("usersForLogin")
  void checkLoginValidation(User user) {
    logInPage
        .setEmail(user.getEmail())
        .setPassword(user.getPassword())
        .clickOnLogInButton()
        .shouldValidationSummaryErrors("\n"
            + "Login was unsuccessful. Please correct the errors and try again.\n"
            + "No customer account found\n");
  }

  public static Stream<Arguments> usersForLogin() {

    return Stream.of(
        Arguments.of(
            Named.of("with empty fields",
                User.builder()
                    .email("")
                    .password("")
                    .build()
            )),
        Arguments.of(
            Named.of("with non exist email",
                User.builder()
                    .email("b4db0yus3r@mail.ru")
                    .password("b4db0yus3r@mail.ru")
                    .build()
            )),
        Arguments.of(
            Named.of("without email",
                User.builder()
                    .email("")
                    .password("no_email")
                    .build()
            )),
        Arguments.of(
            Named.of("without password",
                User.builder()
                    .email("no_password@mail.ru")
                    .password("")
                    .build()
            ))
    );
  }

  @Test
  void checkEmailValidationError() {
    logInPage
        .setEmail("no_email")
        .setPassword("password")
        .clickOnLogInButton()
        .shouldEmailValidationError("Please enter a valid email address.");
  }

  @Test
  void checkSelectingRememberMeCheckBox() {
    logInPage
        .clickOnCheckboxRememberMeAndCheckboxShould(selected)
        .clickOnCheckboxRememberMeAndCheckboxShould(not(selected));
  }

  @Test
  void checkPasswordRecoveryLink() {
    logInPage
        .clickOnForgotPassword();

    shouldUrlPath("/passwordrecovery");

    passwordRecoveryPage
        .shouldInput("Your email address:", visible)
        .shouldButtonRecovery();

  }

}
