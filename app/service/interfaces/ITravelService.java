package service.interfaces;

import com.google.inject.ImplementedBy;
import models.TravelDetail;
import models.repositories.TravelStats;
import service.TravelService;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentMap;

@ImplementedBy(TravelService.class)
public interface ITravelService {

    void validateAndSaveTravel(TravelDetail travelDetail);

    List<TravelStats> pgiTravelStats(String pgi);

    ConcurrentMap<String, Stack<TravelStats>> cache();
}
