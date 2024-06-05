package com.example.score.controller;

import com.example.score.model.Score;
import com.example.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/scoreindex")
    public String viewHomePage(Model model) { return findPaginated(1, "name", "asc", model); }

    @GetMapping("/showScoreForm")
    public String showScoreForm(Model model) {
        Score score = new Score();
        model.addAttribute("score", score);
        return "new_score";
    }

    @PostMapping("/saveScore")
    public String saveScore(@ModelAttribute("score") Score score) {
        scoreService.saveScore(score);
        return "redirect:/scoreindex";
    }

    @GetMapping("/showFormForUpdateScore/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Score scores = scoreService.getScoreById(id);
        model.addAttribute("score", scores);
        return "updata_score";
    }

    @GetMapping("/deleteScore/{id}")
    public String deleteScore(@PathVariable(value = "id") long id) {
        this.scoreService.deleteScoreById(id);
        return "redirect:/scoreindex";
    }

    @GetMapping("/queryScore{newName}")
    public String query(@PathVariable(value = "newName") String newName, Model model) {
        List<Score> listGoodsNumber = scoreService.findByName(newName);
        model.addAttribute("listScore", listGoodsNumber);
        return "scoreindex";
    }


    //获取分页数据
    @GetMapping("/pageScore/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 3;

        Page<Score> page = scoreService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Score> listScore = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listScore", listScore);
        return "scoreindex";
    }
}