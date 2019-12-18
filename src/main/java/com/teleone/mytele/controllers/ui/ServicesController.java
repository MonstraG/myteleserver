package com.teleone.mytele.controllers.ui;

import com.teleone.mytele.db.service.AdditionalService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/services/*")
public class ServicesController {

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @GetMapping("/list")
    public String services(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        Set<AdditionalService> additionalServices = additionalServicesService.getAdditionalServices();
        model.addAttribute("services", additionalServices);
        model.addAttribute("hasContent", additionalServices.size() > 0);
        return "/services/list";
    }

    @GetMapping("/add")
    public String addTariff(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("service", new AdditionalService());
        return "/services/add";
    }

    @PostMapping("/create")
    public String getAdditionalServices(ModelMap model, @ModelAttribute AdditionalService additionalService) {
        additionalServicesService.create(additionalService);
        return services(model);
    }
}
