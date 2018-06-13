package service;

import models.TravelDetail;
import models.repositories.TravelStats;
import service.exceptions.DateParadoxException;
import service.exceptions.PlaceParadoxException;
import service.interfaces.ITravelService;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static service.validation.Validation.isTravellingToBackDate;
import static service.validation.Validation.isTravellingToSamePlace;

/**
 *  Travel Service assist travellers to validate and keep their travel records and
 */
public class TravelService implements ITravelService {

    /**
     * Travel cache to keep track of all past travels.
     * Could be replaced by any persistent store
     */
    private static ConcurrentMap<String, Stack<TravelStats>> dataMap = new ConcurrentHashMap<>();

    /**
     * The method validates incoming new travel request and save the details into store
     * @param travelDetail issued by traveller
     * @throws PlaceParadoxException
     * @throws DateParadoxException
     */
    public void validateAndSaveTravel(TravelDetail travelDetail) throws PlaceParadoxException, DateParadoxException {

        Stack<TravelStats> stack = dataMap.getOrDefault(travelDetail.getPgi(), new Stack<>());
        TravelStats travelStats = validateTravelDetails(travelDetail, stack);
        stack.add(travelStats);

        dataMap.put(travelDetail.getPgi(), stack);
    }

    /**
     * Can be used to fetch travel history of a traveller
     * @param pgi unique personal galactic identifier
     * @return stack of travel history
     */
    public List<TravelStats> pgiTravelStats(String pgi) {
        return dataMap.get(pgi);
    }

    /**
     * Can be used to observe overall picture of travelling
     * @return map of travelling history of all travellers
     */
    public ConcurrentMap<String, Stack<TravelStats>> cache() {
        return dataMap;
    }

    /**
     * Validate travel details and convert it to stats that could be stored
     * @param travelDetail travel details of traveller
     * @param stats previously persisted travel stats of traveller
     * @return new travel stats of traveller to persist
     * @throws PlaceParadoxException
     * @throws DateParadoxException
     */
    private TravelStats validateTravelDetails(TravelDetail travelDetail, Stack<TravelStats> stats)
            throws PlaceParadoxException, DateParadoxException {
        if (!stats.empty()) {
            if (isTravellingToSamePlace.apply(travelDetail, stats.peek())) {
                throw new PlaceParadoxException();
            } else if (isTravellingToBackDate.apply(travelDetail, stats.peek())) {
                throw new DateParadoxException();
            }
        }

        return new TravelStats(travelDetail.getPlace(), travelDetail.getDate());
    }
}
