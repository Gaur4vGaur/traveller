package models.repositories;

/**
 * Model class to keep track of all places and date of travels
 */
public class TravelStats {

    private String place;
    private String date;

    /**
     * Constructor to help create an object with parameters
     * @param place - place of travel
     * @param date - date of travel
     */
    public TravelStats(String place, String date) {
        this.place = place;
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
