package service;

import models.TravelDetail;
import models.repositories.TravelStats;
import org.junit.Before;
import org.junit.Test;
import service.exceptions.DateParadoxException;
import service.exceptions.PlaceParadoxException;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TravelServiceTest {

    private TravelService ts;

    @Before
    public void setUp() {
        ts = new TravelService();
        ts.cache().clear();
    }

    @Test
    public void testSuccessfullyUpdatedCache() {
        TravelDetail td = createDummyTravelDetails();
        ts.validateAndSaveTravel(td);
        assertNotNull("cache must be updated", ts.pgiTravelStats(td.getPgi()));

        List<String> placeList = ts.pgiTravelStats(td.getPgi()).stream()
                .map(TravelStats::getPlace).collect(Collectors.toList());
        assertTrue("cache must have expected value", placeList.contains(td.getPlace()));
    }

    @Test
    public void testTravellingToMultiplePlaces() {
        TravelDetail td = createDummyTravelDetails();
        TravelDetail tdLondon = createDummyTravelDetails("London", "2018-01-02");
        TravelDetail tdNcl = createDummyTravelDetails("Newcastle Upon Tyne", "2018-01-03");

        ts.validateAndSaveTravel(td);
        ts.validateAndSaveTravel(tdLondon);
        ts.validateAndSaveTravel(tdNcl);
        assertNotNull("cache must be updated", ts.pgiTravelStats(td.getPgi()));

        List<String> placeList = ts.pgiTravelStats(td.getPgi()).stream()
                .map(TravelStats::getPlace).collect(Collectors.toList());
        assertEquals("there must be 3 records", 3, placeList.size());
        assertTrue("cache must have expected place Newcastle Upon Tyne", placeList.contains(td.getPlace()));
        assertTrue("cache must have expected place London", placeList.contains(td.getPlace()));
    }

    @Test(expected = PlaceParadoxException.class)
    public void testTravellingToSamePlaceOnSameDate() {
        TravelDetail td = createDummyTravelDetails();
        ts.validateAndSaveTravel(td);
        assertNotNull("cache must be updated", ts.pgiTravelStats(td.getPgi()));

        ts.validateAndSaveTravel(td);
    }

    @Test(expected = PlaceParadoxException.class)
    public void testTravellingToSamePlaceButNextDate() {
        TravelDetail td = createDummyTravelDetails();
        TravelDetail tdNcl = createDummyTravelDetails("Newcastle Upon Tyne", "2018-01-02");

        ts.validateAndSaveTravel(td);
        assertNotNull("cache must be updated", ts.pgiTravelStats(td.getPgi()));

        ts.validateAndSaveTravel(tdNcl);
    }

    @Test(expected = DateParadoxException.class)
    public void testTavellingWithPreviousDate() {
        TravelDetail td = createDummyTravelDetails();
        TravelDetail tdLondon = createDummyTravelDetails("London", "2017-01-01");

        ts.validateAndSaveTravel(td);
        assertNotNull("cache must be updated", ts.pgiTravelStats(td.getPgi()));

        ts.validateAndSaveTravel(tdLondon);
    }

    private TravelDetail createDummyTravelDetails() {
        TravelDetail td = new TravelDetail();
        td.setPgi("Some123");
        td.setPlace("Newcastle Upon Tyne");
        td.setDate("2018-01-01");
        return td;
    }

    private TravelDetail createDummyTravelDetails(String place, String date) {
        TravelDetail td = new TravelDetail();
        td.setPgi("Some123");
        td.setPlace(place);
        td.setDate(date);
        return td;
    }


}
