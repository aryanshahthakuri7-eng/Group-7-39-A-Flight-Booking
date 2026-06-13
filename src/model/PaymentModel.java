package model;

/**
 * Model class representing the card details collected on the payment form.
 * Follows Java Bean conventions for MVC compatibility.
 */
public class PaymentModel {

    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private boolean saveCard;

    public PaymentModel() {
    }

    public PaymentModel(String cardHolderName, String cardNumber, String expiryDate, String cvv, boolean saveCard) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.saveCard = saveCard;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isSaveCard() {
        return saveCard;
    }

    public void setSaveCard(boolean saveCard) {
        this.saveCard = saveCard;
    }
}
