package ak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ak.entity.ListEntity;
import ak.service.ListService;

@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    private ListService listService;

    // For rendering the list view
    @GetMapping
    public String listSection(Model model) {
        model.addAttribute("lists", listService.getAllLists());
        return "list"; 
    }


    @GetMapping("/api/list_items")
    @ResponseBody 
    public List<ListEntity> getListItems() {
        return listService.getAllLists();
    }
}
