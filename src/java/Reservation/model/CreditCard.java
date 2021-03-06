package Reservation.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Rohit Ruparel
 */
@
Entity@ Table(name = "creditcards")
public class CreditCard implements Serializable {

    @
    Id@ Column(name = "credit_card_number")
    private BigInteger creditCardNumber;

    @
    Column(name = "card_type")
    private String cardType;

    @
    Column(name = "expiry_date")
    private int expiryDate;

    @
    Column(name = "cvv")
    private int cvv;

    public CreditCard() {}


    public BigInteger getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public int getCvv() {
        return cvv;
    }


    public void setCreditCardNumber(BigInteger creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

}