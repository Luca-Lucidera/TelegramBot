/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlparser;

/**
 *
 * @author lucid
 */
public class Place {

    private String city, county, state, postcode, country, country_code, lat, lon;

    public Place() {
        city = "";
        county = "";
        state = "";
        postcode = "";
        country = "";
        country_code = "";
        lat = "";
        lon = "";
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

        
    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_code() {
        return country_code;
    }

    @Override
    public String toString() {
        return "Citta: " + city + "  County: " + county + " State: " + state+ " Postcode: " + postcode+ " Country: " + country+ " CountryCode: " + country_code+ " lat: "+ lat + "Long: " + lon;
    }
     
     
}
