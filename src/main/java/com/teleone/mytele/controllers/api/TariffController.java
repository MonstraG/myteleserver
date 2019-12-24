package com.teleone.mytele.controllers.api;

import com.teleone.mytele.db.tariff.Tariff;
import com.teleone.mytele.db.tariff.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/tariffs/*")
public class TariffController extends AbstractController {

    @Autowired
    private TariffService tariffsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Tariff>> getAdditionalServices() {
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
