

package com.afriland.afbpaypartnerservices.models;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
/**
 * QuoteRequest
 */
@Data
@ToString
public class QuoteRequest {
  @SerializedName("amount")
  private Integer amount = null;

  @SerializedName("payItemId")
  private String payItemId = null;

  public QuoteRequest amount(Integer amount) {
    this.amount = amount;
    return this;
  }

   /**
   * amount to be collected during the payment (in local currency of the payment item). Depends on the payment items amountType. Amount to be provided as a full value without decimals
   * @return amount
  **/
  @Schema(required = true, description = "amount to be collected during the payment (in local currency of the payment item). Depends on the payment items amountType. Amount to be provided as a full value without decimals")
  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public QuoteRequest payItemId(String payItemId) {
    this.payItemId = payItemId;
    return this;
  }

   /**
   * Unique  Payment Item ID identifying the item to request the quote for
   * @return payItemId
  **/
  @Schema(required = true, description = "Unique  Payment Item ID identifying the item to request the quote for")
  public String getPayItemId() {
    return payItemId;
  }

  public void setPayItemId(String payItemId) {
    this.payItemId = payItemId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuoteRequest quoteRequest = (QuoteRequest) o;
    return Objects.equals(this.amount, quoteRequest.amount) &&
        Objects.equals(this.payItemId, quoteRequest.payItemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, payItemId);
  }

}
