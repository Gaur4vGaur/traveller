package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TravelDetailTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullPgiTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPgiTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonAlphaStartPgiTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi("123some");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonAlphaNumericPgiTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi("123 some");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooShortPgiTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi("val");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLongPgiTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi("SomeLargeValue1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPlaceTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPlace(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDateTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setDate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDateTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setDate("2018-1-1");
    }

    @Test
    public void testValidTravelDetail() {
        TravelDetail td = new TravelDetail();
        td.setPgi("SomeVal123");
        td.setPlace("Some place");
        td.setDate("2018-01-01");
        assertEquals("Travel details must be created ",td.getPgi(), "SomeVal123");
    }
}
