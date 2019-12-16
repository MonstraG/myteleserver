package com.teleone.mytele.controllers;

import com.teleone.mytele.db.tariff.Tariff;
import com.teleone.mytele.db.tariff.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Service
public class TariffController extends AbstractController {

    @Autowired
    private TariffService tariffsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Set<Tariff>> getAdditionalServices() {
        return new ResponseEntity<>(tariffsService.getTariffs(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> getAdditionalServices(@RequestBody Tariff tariff) {
        return booleanResponse(tariffsService.create(tariff));
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public ResponseEntity<String> getAdditionalServices(@PathVariable Long id) {
        return booleanResponse(tariffsService.remove(id));
    }
}
