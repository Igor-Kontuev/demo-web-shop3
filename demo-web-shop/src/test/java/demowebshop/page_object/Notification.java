package demowebshop.page_object;

import static com.codeborne.selenide.Selenide.$;
import static demowebshop.util.Conditions.visibleAndClickable;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

@SuppressWarnings("UnusedReturnValue")
public class Notification {
  private final SelenideElement barNotification = $("#bar-notification");
  private final SelenideElement close = $(".close");

  @Step("Проверить, что у нотификации текст {notificationText}")
  public Notification should(String notificationText) {
    barNotification
        .should(Condition.visible)
        .should(Condition.text(notificationText));

    return this;
  }

  @Step("Проверить, что у нотификации link {link}")
  public Notification shouldLink(String link) {
    barNotification
        .find("a")
        .should(Condition.text(link))
        .should(visibleAndClickable);

    return this;
  }

  @Step("Проверить, что у нотификации отображается кнопка закрыть")
  public Notification shouldHaveCloseButton() {
    close
        .should(visibleAndClickable);

    return this;
  }
}
