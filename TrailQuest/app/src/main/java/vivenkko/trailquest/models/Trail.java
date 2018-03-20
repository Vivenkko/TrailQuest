package vivenkko.trailquest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by magomez on 20/03/2018.
 */

public class Trail {
    @SerializedName("title_trail")
    @Expose
    private String title;

    @SerializedName("description_trail")
    @Expose
    private String description;

    @SerializedName("city_trail")
    @Expose
    private String city;

    @SerializedName("country_trail")
    @Expose
    private String country;

    @SerializedName("rate_trail")
    @Expose
    private Double rate;

    @SerializedName("picture_trail")
    @Expose
    private String picture;

    @SerializedName("distance_trail")
    @Expose
    private Double distance;

    @SerializedName("difficulty_trail")
    @Expose
    private String difficulty;

    @SerializedName("locations_trail")
    @Expose
    private List<String> locations;

    @SerializedName("author_trail")
    @Expose
    private User author;

    public Trail() {

    }

    public Trail(String title, String description, String city, String country, Double rate, String picture, Double distance, String difficulty, List<String> locations, User author) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.country = country;
        this.rate = rate;
        this.picture = picture;
        this.distance = distance;
        this.difficulty = difficulty;
        this.locations = locations;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trail trail = (Trail) o;

        if (title != null ? !title.equals(trail.title) : trail.title != null) return false;
        if (description != null ? !description.equals(trail.description) : trail.description != null)
            return false;
        if (city != null ? !city.equals(trail.city) : trail.city != null) return false;
        if (country != null ? !country.equals(trail.country) : trail.country != null) return false;
        if (rate != null ? !rate.equals(trail.rate) : trail.rate != null) return false;
        if (picture != null ? !picture.equals(trail.picture) : trail.picture != null) return false;
        if (distance != null ? !distance.equals(trail.distance) : trail.distance != null)
            return false;
        if (difficulty != null ? !difficulty.equals(trail.difficulty) : trail.difficulty != null)
            return false;
        if (locations != null ? !locations.equals(trail.locations) : trail.locations != null)
            return false;
        return author != null ? author.equals(trail.author) : trail.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (locations != null ? locations.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trail{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", rate=" + rate +
                ", picture='" + picture + '\'' +
                ", distance=" + distance +
                ", difficulty='" + difficulty + '\'' +
                ", locations=" + locations +
                ", author=" + author +
                '}';
    }

}
