package com.teleone.mytele.controllers.ui;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/services/*")
public class ServicesController {

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @GetMapping("/list")
    public String services(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        List<AdditionalService> additionalServices = additionalServicesService.getAdditionalServices();
        model.addAttribute("services", additionalServices);
        model.addAttribute("hasContent", additionalServices.size() > 0);
        return "/services/list";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("service", new AdditionalService());
        return "/services/add";
    }

    @PostMapping("/create")
    public String create(ModelMap model, @ModelAttribute AdditionalService additionalService) {
        additionalServicesService.create(additionalService);
        return services(model);
    }

    @GetMapping("/delete/{id}")
    public String delete(ModelMap model, @PathVariable Long id) {
        additionalServicesService.remove(id);
        return services(model);
    }
}
