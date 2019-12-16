package com.teleone.mytele.controllers;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Service
@RequestMapping("/additional-services/*")
public class AdditionalServicesController extends AbstractController {

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Set<AdditionalService>> getAdditionalServices() {
        return new ResponseEntity<>(additionalServicesService.getAdditionalServices(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> getAdditionalServices(@RequestBody AdditionalService additionalService) {
        return booleanResponse(additionalServicesService.create(additionalService));
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public ResponseEntity<String> getAdditionalServices(@PathVariable Long id) {
        return booleanResponse(additionalServicesService.remove(id));
    }
}