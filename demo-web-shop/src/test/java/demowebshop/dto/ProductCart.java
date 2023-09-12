package demowebshop.dto;

import com.codeborne.selenide.Condition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCart {
  private String name;
  private String unitPrice;
  private String quantity;
}
