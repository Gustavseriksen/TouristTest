package com.example.touristtest.Controller;

import com.example.touristtest.Model.TouristAttraction;
import com.example.touristtest.Service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService touristService;

    // DI (Dependency Injection)
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction("", "", "", new ArrayList<>()));
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getTags());
        return "add-attraction";
    }


    @PostMapping("/save")
    public String addAttraction(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String city,
            @RequestParam List<String> tags) {

        // Sørger for, at tags ikke er null
        if (tags == null) {
            tags = new ArrayList<>();
        }

        TouristAttraction newAttraction = new TouristAttraction(name, description, city, tags);
        touristService.addAttraction(newAttraction);
        return "redirect:/attractions";
    }


    @GetMapping
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }


    @GetMapping("/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);

        if (attraction != null) {
            model.addAttribute("attraction", attraction);
            return "attraction-details";
        } else {
            model.addAttribute("error", "Attraktionen blev ikke fundet");
            return "error";
        }
    }


    @GetMapping("/{name}/tags")
    public String showTagsForAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);

        if (attraction != null) {
            model.addAttribute("attraction", attraction);
            model.addAttribute("tags", attraction.getTags()); // Henter tags
            return "tags"; // Viser tags.html
        } else {
            model.addAttribute("error", "Attraktionen blev ikke fundet");
            return "error";
        }
    }


    @GetMapping("/edit/{name}")
    public String showUpdateForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);

        if (attraction != null) {
            model.addAttribute("attraction", attraction);
            model.addAttribute("cities", touristService.getCities());
            model.addAttribute("tags", touristService.getTags());
            return "edit-attraction";
        } else {
            model.addAttribute("error", "Attraktionen blev ikke fundet");
            return "error";
        }
    }


    @PostMapping("/update")
    public String updateAttraction(
            @RequestParam String name,
            @RequestParam String newDescription,
            @RequestParam String newCity,
            @RequestParam List<String> newTags,
            Model model) {

        // Sørger for, at tags ikke er null
        if (newTags == null) {
            newTags = new ArrayList<>();
        }

        TouristAttraction updatedAttraction = touristService.updateAttraction(name,newDescription, newCity, newTags);

        if (updatedAttraction != null) {
            return "redirect:/attractions";
        } else {
            model.addAttribute("error", "Attraktionen blev ikke fundet.");
            return "error";
        }

    }


    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name, Model model) {
        boolean deleted = touristService.deleteAttraction(name);

        if (deleted) {
            return "redirect:/attractions";
        } else {
            model.addAttribute("error", "Attraktionen kunne ikke slettes.");
            return "error";
        }
    }

}
