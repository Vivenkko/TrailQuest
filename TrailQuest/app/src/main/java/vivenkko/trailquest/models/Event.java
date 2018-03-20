package vivenkko.trailquest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by magomez on 20/03/2018.
 */

public class Event {
    @SerializedName("title_event")
    @Expose
    private String title;

    @SerializedName("description_event")
    @Expose
    private String description;

    @SerializedName("city_event")
    @Expose
    private String city;

    @SerializedName("country_event")
    @Expose
    private String country;

    @SerializedName("location_event")
    @Expose
    private String location;

    @SerializedName("picture_event")
    @Expose
    private String picture;

    @SerializedName("date_event")
    @Expose
    private Date date;

    public Event() {

    }

    public Event(String title, String description, String city, String country, String location, String picture, Date date) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.country = country;
        this.location = location;
        this.picture = picture;
        this.date = date;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null)
            return false;
        if (city != null ? !city.equals(event.city) : event.city != null) return false;
        if (country != null ? !country.equals(event.country) : event.country != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null)
            return false;
        if (picture != null ? !picture.equals(event.picture) : event.picture != null) return false;
        return date != null ? date.equals(event.date) : event.date == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", location='" + location + '\'' +
                ", picture='" + picture + '\'' +
                ", date=" + date +
                '}';
    }

}
