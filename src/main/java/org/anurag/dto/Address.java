package org.anurag.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    String city;

    String postcode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
