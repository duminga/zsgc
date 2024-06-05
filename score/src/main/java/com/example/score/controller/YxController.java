package com.example.score.controller;



import com.example.score.model.Yx;
import com.example.score.service.YxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class YxController {
    @Autowired
    private YxService yxService;

    @GetMapping("/yxindex")
    public String viewHomePage(Model model) { return findPaginated(1, "name", "asc", model); }

    @GetMapping("/showYxForm")
    public String showYxForm(Model model) {
        Yx yx = new Yx();
        model.addAttribute("yx", yx);
        return "new_yx";
    }

    @PostMapping("/saveYx")
    public String saveYx(@ModelAttribute("yx") Yx yx) {
        yxService.saveYx(yx);
        return "redirect:/yxindex";
    }

    @GetMapping("/showFormForUpdateYx/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Yx yxs = yxService.getYxById(id);
        model.addAttribute("yx", yxs);
        return "updata_yx";
    }

    @GetMapping("/deleteYx/{id}")
    public String deleteYx(@PathVariable(value = "id") long id) {
        this.yxService.deleteYxById(id);
        return "redirect:/yxindex";
    }

    @GetMapping("/queryYx{newName}")
    public String query(@PathVariable(value = "newName") String newName, Model model) {
        List<Yx> listGoodsNumber = yxService.findByName(newName);
        model.addAttribute("listYx", listGoodsNumber);
        return "yxindex";
    }


    //获取分页数据
    @GetMapping("/pageYx/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 3;

        Page<Yx> page = yxService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Yx> listYx = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listYx", listYx);
        return "yxindex";
    }
}