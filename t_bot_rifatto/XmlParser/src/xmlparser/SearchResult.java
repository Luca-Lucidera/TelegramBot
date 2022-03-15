/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucid
 */
public class SearchResult {
    public List<Place> places;
    public SearchResult(){
        places = new ArrayList<Place>();
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Place> getPlaces() {
        return places;
    }
    
}
