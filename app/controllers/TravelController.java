package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.TravelDetail;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import service.exceptions.DateParadoxException;
import service.exceptions.PlaceParadoxException;
import service.interfaces.ITravelService;

import javax.inject.Inject;

/**
 * This controller contains action to handle HTTP requests
 * for the travel details.
 */
public class TravelController extends Controller {

    @Inject
    ITravelService ts;

    /**
     * An action that returns successful response when valid travel
     * details are submitted via Json.
     * Successful travel details must contains:
     * - valid Personal Galactic Number
     * - place where you are traveling to
     * - travel date
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result travelDetails() {
        try {
            JsonNode jsonNode = request().body().asJson();
            TravelDetail travelDetail = Json.fromJson(jsonNode, TravelDetail.class);
            ts.validateAndSaveTravel(travelDetail);
            return ok(Json.toJson(travelDetail));
        } catch (PlaceParadoxException | DateParadoxException pe){
            return badRequest(pe.getMessage());
        } catch (RuntimeException re){
            return badRequest("Invalid arguments - Please provide valid request");
        }
    }
}
