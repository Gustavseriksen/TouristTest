package com.example.touristtest.Repository;

import com.example.touristtest.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();


    //Constructor til at tilføje nogle TouristAttraction objekter
    public TouristRepository() {
        touristAttractions.add(new TouristAttraction("Tivoli", "Forlystelsespark midt i København centrum", "København", List.of("Børnevenlig", "Underholdning", "Koncert")));
        touristAttractions.add(new TouristAttraction("Den Lille Havfrue", "En lille statue ved havnen", "København", List.of("Skulptur", "Gratis", "Ikonisk")));
        touristAttractions.add(new TouristAttraction("Nyhavn", "Farverige huse og restauranter ved vandet", "København", List.of("Restaurant", "Historisk", "Udsigt")));
        touristAttractions.add(new TouristAttraction("SMK", "Statens Museum for Kunst", "København", List.of("Kunst", "Museum", "Gratis")));
    }

    // Create - Tilføjer en ny attraktion
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
    }

    // Read - Henter alle attraktioner
    public List<TouristAttraction> getAllTouristAttractions() {
        return new ArrayList<>(touristAttractions); // Returnerer en kopi for at undgå direkte ændringer
    }

    // Read - Finder en attraktion ved navn
    public TouristAttraction getTouristAttractionByName(String name) {
        for (TouristAttraction i : touristAttractions){
            if (i.getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return null; // Returnér null, hvis ingen attraktion blev fundet
    }


    // Update - Opdater en eksisterende attraktion
    public TouristAttraction updateTouristAttraction(String name, String newDescription, String newCity, List<String> newTags) {
        for (TouristAttraction i : touristAttractions){
            if (i.getName().equalsIgnoreCase(name)){
                i.setDescription(newDescription);
                i.setCity(newCity);
                i.setTags(newTags);
                return i;
            }
        }
        return null;
    }


    // Delete - Sletter en attraktion
    public boolean deleteTouristAttraction(String name) {
        return touristAttractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));
    }


    public List<String> getCities() {
        return List.of("København", "Odense", "Aarhus", "Aalborg");
    }

    public List<String> getTags() {
        return List.of("Børnevenlig", "Gratis", "Kunst", "Museum", "Natur", "Underholdning", "Historisk", "Restaurant");
    }

}
