package vivenkko.trailquest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by magomez on 20/03/2018.
 */

public class Following {
    @SerializedName("user_follow")
    @Expose
    private User user;

    @SerializedName("event_follow")
    @Expose
    private Event event;

    public Following() {

    }

    public Following(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Following following = (Following) o;

        if (user != null ? !user.equals(following.user) : following.user != null) return false;
        return event != null ? event.equals(following.event) : following.event == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Following{" +
                "user=" + user +
                ", event=" + event +
                '}';
    }

}
