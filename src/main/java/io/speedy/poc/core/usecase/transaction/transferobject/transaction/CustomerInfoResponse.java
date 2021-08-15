package io.speedy.poc.core.ports.out.transaction.transferobject.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerInfoResponse {
    private int id;
    private String created_at;
    private String updated_at;
    private Object deleted_at;
    private String number;
    private String expiryMonth;
    private String expiryYear;
    private Object startMonth;
    private Object startYear;
    private Object issueNumber;
    private String email;
    private String birthday;
    private Object gender;
    private Object billingTitle;
    private String billingFirstName;
    private String billingLastName;
    private Object billingCompany;
    private String billingAddress1;
    private Object billingAddress2;
    private String billingCity;
    private String billingPostcode;
    private Object billingState;
    private String billingCountry;
    private Object billingPhone;
    private Object billingFax;
    private Object shippingTitle;
    private String shippingFirstName;
    private String shippingLastName;
    private Object shippingCompany;
    private String shippingAddress1;
    private Object shippingAddress2;
    private String shippingCity;
    private String shippingPostcode;
    private Object shippingState;
    private String shippingCountry;
    private Object shippingPhone;
    private Object shippingFax;
}
