package lk.sampath_autocare.asset.serviceType.controller;


import lk.sampath_autocare.asset.common_asset.model.Enum.LiveDead;
import lk.sampath_autocare.asset.serviceType.entity.ServiceType;
import lk.sampath_autocare.asset.serviceType.service.ServiceTypeService;
import lk.sampath_autocare.asset.service_type_parameter.service.ServiceTypeParameterService;
import lk.sampath_autocare.asset.vehicle.entity.Enum.VehicleModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/serviceType")
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;
    private final ServiceTypeParameterService serviceTypeParameterService;

    public ServiceTypeController(ServiceTypeService serviceTypeService, ServiceTypeParameterService serviceTypeParameterService) {
        this.serviceTypeService = serviceTypeService;
        this.serviceTypeParameterService = serviceTypeParameterService;
    }

    private String commonThing(Model model, Boolean booleanValue, ServiceType serviceType) {
        model.addAttribute("addStatus", booleanValue);
        model.addAttribute("serviceType", serviceType);
        model.addAttribute("vehicleModels", VehicleModel.values());
        model.addAttribute("serviceTypeParameters", serviceTypeParameterService.findAll() .stream()
            .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
            .collect(Collectors.toList()));
        return "serviceType/addServiceType";
    }


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("serviceTypes", serviceTypeService.findAll()
            .stream()
            .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
            .collect(Collectors.toList()));
        return "serviceType/serviceType";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThing(model, false, new ServiceType());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("serviceTypeDetail", serviceTypeService.findById(id));
        return "serviceType/serviceType-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThing(model, true, serviceTypeService.findById(id));
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute ServiceType serviceType, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return commonThing(model, true, serviceType);
        }
        redirectAttributes.addFlashAttribute("serviceTypeDetail", serviceTypeService.persist(serviceType));
        return "redirect:/serviceType";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        serviceTypeService.delete(id);
        return "redirect:/serviceType";
    }
}
