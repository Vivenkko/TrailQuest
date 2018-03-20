package vivenkko.trailquest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by magomez on 20/03/2018.
 */

public class Favorite {
    @SerializedName("user_fav")
    @Expose
    private User user;

    @SerializedName("trail_fav")
    @Expose
    private Trail trail;

    public Favorite() {

    }

    public Favorite(User user, Trail trail) {
        this.user = user;
        this.trail = trail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        this.trail = trail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;

        if (user != null ? !user.equals(favorite.user) : favorite.user != null) return false;
        return trail != null ? trail.equals(favorite.trail) : favorite.trail == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (trail != null ? trail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "user=" + user +
                ", trail=" + trail +
                '}';
    }

}
