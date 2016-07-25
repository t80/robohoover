package com.yoti.robohoover.controllers;

import com.yoti.robohoover.client.RoomHooverRequest;
import com.yoti.robohoover.client.RoomHooverResponse;
import com.yoti.robohoover.services.HooveringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;

@RestController("/robohoover/clean")
public class RobohooverController {

    private final HooveringService hooveringService;

    @Autowired
    public RobohooverController(HooveringService hooveringService) {
        this.hooveringService = hooveringService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomHooverResponse cleanRoom(@Validated @RequestBody RoomHooverRequest roomHooverRequest) {
        // validate request
        return hooveringService.hooverRoom(roomHooverRequest);
    }
}
