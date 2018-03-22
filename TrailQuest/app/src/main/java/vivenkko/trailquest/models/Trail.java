package vivenkko.trailquest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by magomez on 20/03/2018.
 */

public class Trail {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("author")
    @Expose
    private User author;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("locations")
    @Expose
    private List<String> locations = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Trail{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", picture='" + picture + '\'' +
                ", rate=" + rate +
                ", distance=" + distance +
                ", author=" + author +
                ", difficulty='" + difficulty + '\'' +
                ", v=" + v +
                ", locations=" + locations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trail trail = (Trail) o;

        if (id != null ? !id.equals(trail.id) : trail.id != null) return false;
        if (title != null ? !title.equals(trail.title) : trail.title != null) return false;
        if (description != null ? !description.equals(trail.description) : trail.description != null)
            return false;
        if (city != null ? !city.equals(trail.city) : trail.city != null) return false;
        if (country != null ? !country.equals(trail.country) : trail.country != null) return false;
        if (picture != null ? !picture.equals(trail.picture) : trail.picture != null) return false;
        if (rate != null ? !rate.equals(trail.rate) : trail.rate != null) return false;
        if (distance != null ? !distance.equals(trail.distance) : trail.distance != null)
            return false;
        if (author != null ? !author.equals(trail.author) : trail.author != null) return false;
        if (difficulty != null ? !difficulty.equals(trail.difficulty) : trail.difficulty != null)
            return false;
        if (v != null ? !v.equals(trail.v) : trail.v != null) return false;
        return locations != null ? locations.equals(trail.locations) : trail.locations == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (locations != null ? locations.hashCode() : 0);
        return result;
    }
}

