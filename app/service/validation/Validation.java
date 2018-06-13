package service.validation;

import models.TravelDetail;
import models.repositories.TravelStats;

import java.time.LocalDate;
import java.util.function.BiFunction;

/**
 * Validation to validate incoming travel details
 *  * @param <T>
 * @param <U>
 */
public interface Validation<T, U> extends BiFunction<T, U, Boolean> {
    Validation<TravelDetail, TravelStats> isTravellingToSamePlace = (travelDetail, travelStats) ->
            travelDetail.getPlace().toLowerCase().equals(travelStats.getPlace().toLowerCase());

    Validation<TravelDetail, TravelStats> isTravellingToBackDate = (travelDetail, travelStats) -> {
        LocalDate detailDate = LocalDate.parse(travelDetail.getDate());
        LocalDate statsDate = LocalDate.parse(travelStats.getDate());

        return statsDate.isAfter(detailDate);
    };

}
