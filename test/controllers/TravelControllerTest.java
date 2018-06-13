package controllers;

import models.TravelDetail;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class TravelControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testTravelDetails() {
        TravelDetail detail = new TravelDetail();
        detail.setPgi("Some123");
        detail.setPlace("earth");
        detail.setDate("2018-01-01");

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/details")
                .bodyJson(Json.toJson(detail));

        Result result = route(app, request);
        assertEquals("request should not failed to register the traveller",OK, result.status());
        assertEquals(Json.toJson(detail).toString(), Helpers.contentAsString(result));
    }

}
