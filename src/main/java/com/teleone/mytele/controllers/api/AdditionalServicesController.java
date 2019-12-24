package com.teleone.mytele.controllers.api;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@RequestMapping("/api/additional-services/*")
public class AdditionalServicesController extends AbstractController {

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<AdditionalService>> getAdditionalServices() {
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