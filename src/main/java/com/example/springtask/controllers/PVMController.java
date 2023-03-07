package com.example.springtask.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PVMController {
    @GetMapping("/")
    public String home(Model model) {return "Input";}

    @PostMapping("/")
    public String calculate(
            @RequestParam("price") double price,
            @RequestParam("amount") Integer amount,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        double priceWithoutPVM = Math.round((price/121.0*100.0)*100.0)/100.0;
        double PVM = Math.round((price-priceWithoutPVM)*100.0)/100.0;
        double sum = price*amount;
        double PVMsum = PVM*amount;
        double sumWithoutPVM = sum-PVMsum;

        redirectAttributes.addFlashAttribute("price", price);
        redirectAttributes.addFlashAttribute("amount", amount);
        redirectAttributes.addFlashAttribute("PVM", PVM);
        redirectAttributes.addFlashAttribute("priceWithoutPVM", priceWithoutPVM);
        redirectAttributes.addFlashAttribute("sum", sum);
        redirectAttributes.addFlashAttribute("PVMsum", PVMsum);
        redirectAttributes.addFlashAttribute("sumWithoutPVM", sumWithoutPVM);
        return "redirect:/Output";
    }


    @GetMapping("/Output")
    public String Output(Model model) {
        return "Output";
    }
}
