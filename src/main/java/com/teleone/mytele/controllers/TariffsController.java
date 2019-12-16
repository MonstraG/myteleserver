package com.teleone.mytele.controllers;

import com.teleone.mytele.db.tariff.TariffsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TariffsController {

    @Autowired
    private TariffsService tariffsService;
}
