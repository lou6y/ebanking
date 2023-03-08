package tn.esprit.spring.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@Setter
@ConstructorBinding
@ConfigurationProperties(prefix = "transactions")
public class TransactionsParams {
  @NonNull private final float rate;
  private final float vipRate;

  public TransactionsParams(@NonNull final float rate, final float vipRate) {
    this.rate = rate;
    this.vipRate = vipRate;
  }
}
