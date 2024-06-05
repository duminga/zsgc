package com.example.score.controller;



import com.example.score.model.Tea;
import com.example.score.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeaController {
    @Autowired
    private TeaService teaService;

    @GetMapping("/teaindex")
    public String viewHomePage(Model model) { return findPaginated(1, "name", "asc", model); }

    @GetMapping("/showTeaForm")
    public String showTeaForm(Model model) {
        Tea tea = new Tea();
        model.addAttribute("tea", tea);
        return "new_tea";
    }

    @PostMapping("/saveTea")
    public String saveTea(@ModelAttribute("tea") Tea tea) {
        teaService.saveTea(tea);
        return "redirect:/teaindex";
    }

    @GetMapping("/showFormForUpdateTea/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Tea teas = teaService.getTeaById(id);
        model.addAttribute("tea", teas);
        return "updata_tea";
    }

    @GetMapping("/deleteTea/{id}")
    public String deleteTea(@PathVariable(value = "id") long id) {
        this.teaService.deleteTeaById(id);
        return "redirect:/teaindex";
    }

    @GetMapping("/queryTea{newName}")
    public String query(@PathVariable(value = "newName") String newName, Model model) {
        List<Tea> listGoodsNumber = teaService.findByName(newName);
        model.addAttribute("listTea", listGoodsNumber);
        return "teaindex";
    }


    //获取分页数据
    @GetMapping("/pageTea/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 3;

        Page<Tea> page = teaService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Tea> listTea = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTea", listTea);
        return "teaindex";
    }
}