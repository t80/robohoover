package com.yoti.robohoover.controllers;

import com.yoti.robohoover.client.RobohooverRequest;
import com.yoti.robohoover.client.RobohooverResponse;
import com.yoti.robohoover.controllers.adapters.HooverServiceAdapter;
import com.yoti.robohoover.services.HooverService;
import com.yoti.robohoover.domain.RobohooverServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/robohoover/clean")
public class RobohooverController {

    private final HooverService hooverService;
    private final HooverServiceAdapter hooverServiceAdapter;

    @Autowired
    public RobohooverController(HooverService hooverService, HooverServiceAdapter hooverServiceAdapter) {
        this.hooverService = hooverService;
        this.hooverServiceAdapter = hooverServiceAdapter;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RobohooverResponse cleanRoom(@Validated @RequestBody RobohooverRequest robohooverRequest) {
        // validate request
        RobohooverServiceResponse serviceResponse = hooverService.hooverRoom(hooverServiceAdapter.adapt((robohooverRequest)));
        return hooverServiceAdapter.adapt(serviceResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> errorHandler(HttpServletRequest req, Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
