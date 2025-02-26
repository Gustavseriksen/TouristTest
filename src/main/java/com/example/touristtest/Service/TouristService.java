package com.example.touristtest.Service;

import com.example.touristtest.Model.TouristAttraction;
import com.example.touristtest.Repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    // Dependency Injection
    public TouristService(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }


    // CREATE - Tilføjer en ny attraktion
    public void addAttraction(TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
    }


    // READ - Henter alle attraktioner
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllTouristAttractions();
    }


    // READ - Henter en attraktion ved navn
    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getTouristAttractionByName(name);
    }


    // UPDATE - Opdaterer en eksisterende attraktion
    public TouristAttraction updateAttraction(String name, String newDescription, String newCity, List<String> newTags) {
        return touristRepository.updateTouristAttraction(name, newDescription, newCity, newTags);
    }


    // DELETE - Sletter en attraktion
    public boolean deleteAttraction(String name) {
        return touristRepository.deleteTouristAttraction(name);
    }


    public List<String> getCities() {
        return touristRepository.getCities();
    }


    public List<String> getTags() {
        return touristRepository.getTags();
    }

}
