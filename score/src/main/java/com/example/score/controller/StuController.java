package com.example.score.controller;

import com.example.score.model.Stu;
import com.example.score.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StuController {
    @Autowired
    private StuService stuService;

    @GetMapping("/stuindex")
    public String viewHomePage(Model model) { return findPaginated(1, "name", "asc", model); }

    @GetMapping("/showStuForm")
    public String showStuForm(Model model) {
        Stu stu = new Stu();
        model.addAttribute("stu", stu);
        return "new_stu";
    }

    @PostMapping("/saveStu")
    public String saveStu(@ModelAttribute("stu") Stu stu) {
        stuService.saveStu(stu);
        return "redirect:/stuindex";
    }

    @GetMapping("/showFormForUpdateStu/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Stu stus = stuService.getStuById(id);
        model.addAttribute("stu", stus);
        return "updata_stu";
    }

    @GetMapping("/deleteStu/{id}")
    public String deleteStu(@PathVariable(value = "id") long id) {
        this.stuService.deleteStuById(id);
        return "redirect:/stuindex";
    }

    @GetMapping("/queryStu{newName}")
    public String query(@PathVariable(value = "newName") String newName, Model model) {
        List<Stu> listGoodsNumber = stuService.findByName(newName);
        model.addAttribute("listStu", listGoodsNumber);
        return "stuindex";
    }


    //获取分页数据
    @GetMapping("/pageStu/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 3;

        Page<Stu> page = stuService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Stu> listStu = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listStu", listStu);
        return "stuindex";
    }
}