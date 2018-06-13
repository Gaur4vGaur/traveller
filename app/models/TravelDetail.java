package models;

import static models.validation.Validation.*;

/**
 * Model class to keep track of travel details
 */
public class TravelDetail {

    private String pgi;
    private String place;
    private String date;

    public String getPgi() {
        return pgi;
    }

    /**
     * pgi setter to validate that pgi must:
     * - start with letter
     * - between 5-10 characters
     * - be alphanumeric string
     * @param pgi - personal galactic identifier
     */
    public void setPgi(String pgi) {
        if (isNullOrEmptyString.apply(pgi) || isValidPgiString.apply(pgi)) {
            throw new IllegalArgumentException("Personal Galactic Identifier cannot be null");
        }

        this.pgi = pgi;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        if (isNullOrEmptyString.apply(place)) {
            throw new IllegalArgumentException("Place cannot be null");
        }

        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (isNullOrEmptyString.apply(date) || !isValidDate.apply(date)) {
            throw new IllegalArgumentException("Please provide valid date of travel");
        }

        this.date = date;
    }
}

