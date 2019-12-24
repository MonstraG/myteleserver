package com.teleone.mytele.controllers.ui;

import com.teleone.mytele.db.tariff.Tariff;
import com.teleone.mytele.db.tariff.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tariffs/*")
public class TariffsController {

    @Autowired
    private TariffService tariffsService;

    @GetMapping("/list")
    public String tariffs(ModelMap model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        List<Tariff> tariffs = tariffsService.getTariffs();
        model.addAttribute("tariffs", tariffs);
        model.addAttribute("hasContent", tariffs.size() > 0);
        return "/tariffs/list";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("tariff", new Tariff());
        return "/tariffs/add";
    }

    @PostMapping("/create")
    public String save(ModelMap model, @ModelAttribute Tariff tariff) {
        tariffsService.create(tariff);
        return tariffs(model);
    }

    @GetMapping("/delete/{id}")
    public String delete(ModelMap model, @PathVariable Long id) {
        tariffsService.remove(id);
        return tariffs(model);
    }
}
