package demowebshop.test;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

import demowebshop.dto.User;
import demowebshop.page_object.Header;
import demowebshop.page_object.RegisterPage;
import demowebshop.page_object.RegisterResultPage;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RegisterPageTest extends BaseTest {

  private final RegisterPage registerPage = new RegisterPage();
  private final RegisterResultPage registerResultPage = new RegisterResultPage();
  private final Header header = new Header();


  @BeforeEach
  void setUp() {
    open("/register");
  }

  @Test
  void checkDisplayedRegistrationForm() {
    registerPage
        .blockShould(visible, "Your Personal Details")
        .blockYourPersonalDetailsShouldFieldsNames(
            List.of("Gender:", "First name:", "Last name:", "Email:"))
        .blockYourPersonalDetailsShouldGenderRadioButtons(List.of("Male", "Female"))
        .blockYourPersonalDetailsShouldInputs(visible)
        .blockYourPersonalDetailsShouldInputsAsterisks(visible)
        .blockShould(visible, "Your Password")
        .blockYourPasswordShouldFieldNames(List.of("Password", "Confirm password"))
        .blockYourPasswordShouldInputs(visible)
        .blockYourPasswordShouldInputsAsterisks(visible)
        .shouldRegisterButton();
  }

  @Test
  void checkChangeSelectingGender() {
    registerPage
        .selectMaleRadioButton(selected)
        .selectFemaleRadioButton(selected);
  }

  @Test
  void checkPasswordsFieldsHasHideValues() {
    registerPage
        .setValuePassword("password")
        .setValueConfirmPassword("password")
        .checkValuesMasked();
  }


  @ParameterizedTest(name = "{0}")
  @MethodSource("usersForRegistration")
  void checkRegistration(User user) {
    registerPage
        .setValueFirstName(user.getFirstName())
        .setValueLastName(user.getLastName())
        .setValueEmail(user.getEmail())
        .setValuePassword(user.getPassword())
        .setValueConfirmPassword(user.getConfirmPassword())
        .clickOnRegisterButton();
    registerResultPage.checkResultMassageIsDisplayed("Your registration completed")
        .continueButtonIsDisplayed();
  }

  public static Stream<Arguments> usersForRegistration() {
    String password = faker.internet().password(6, 999, true, true, true);
    String passwordMin = StringUtils.leftPad("pass", 6, "word");
    String passwordMax = StringUtils.leftPad("pass", 999, "word");

    return Stream.of(
        Arguments.of(
            Named.of("with default user data values",
                User.builder()
                    .firstName(faker.name().nameWithMiddle())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .password(password)
                    .confirmPassword(password)
                    .build()
            )),
        Arguments.of(
            Named.of("with min values",
                User.builder()
                    .firstName("M")
                    .lastName("S")
                    .email(faker.internet().emailAddress())
                    .password(passwordMin)
                    .confirmPassword(passwordMin)
                    .build()
            )),
        //bug when fields have 999 symbols
        Arguments.of(
            Named.of("with max values",
                User.builder()
                    .firstName(StringUtils.leftPad("first", 999, "name"))
                    .lastName(StringUtils.leftPad("first", 999, "name"))
                    .email(faker.internet().emailAddress())
                    .password(passwordMax)
                    .confirmPassword(passwordMax)
                    .build()
            ))
    );
  }

  @Test
  void checkRegistrationFieldsValidation() {
    registerPage
        .setValueFirstName("")
        .setValueLastName("")
        .setValueEmail("")
        .setValuePassword("")
        .setValueConfirmPassword("")
        .clickOnRegisterButton()
        .checkValidationFieldsErrors(
            List.of("First name is required.", "Last name is required.", "Email is required.",
                "Password is required.", "Password is required."));
  }

  @Test
  void checkRegistrationSummaryValidationWithSameEmail() {
    registerPage
        .setValueFirstName(faker.name().nameWithMiddle())
        .setValueLastName(faker.name().lastName())
        .setValueEmail("test99@gmail.com")
        .setValuePassword("password")
        .setValueConfirmPassword("password")
        .clickOnRegisterButton()
        .checkValidationSummary("The specified email already exists");
  }

  @Test
  void checkRegistrationAndLogOut() {
    String password = faker.internet().password(6, 999, true, true, true);

    User user = User.builder()
        .firstName(faker.name().nameWithMiddle())
        .lastName(faker.name().lastName())
        .email(faker.internet().emailAddress())
        .password(password)
        .confirmPassword(password)
        .build();

    registerPage
        .setValueFirstName(user.getFirstName())
        .setValueLastName(user.getLastName())
        .setValueEmail(user.getEmail())
        .setValuePassword(user.getPassword())
        .setValueConfirmPassword(user.getConfirmPassword())
        .clickOnRegisterButton();
    registerResultPage
        .clickContinue();
    header.clickHeaderButton("Log out")
        .loginButtonShould(visible,"Log in");
  }

}
