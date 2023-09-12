package demowebshop.util;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Condition;

public class Conditions {
  public static Condition visibleAndClickable = and("visible and clickable", visible, enabled);
}
