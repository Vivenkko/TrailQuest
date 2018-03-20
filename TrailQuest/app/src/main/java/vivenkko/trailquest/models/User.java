package vivenkko.trailquest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by magomez on 20/03/2018.
 */

public class User {
    @SerializedName("displayName")
    @Expose
    private String displayName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("isAdmin")
    @Expose
    private Boolean isAdmin;

    public User() {

    }

    public User(String displayName, String email, String avatar, String password, Boolean isAdmin) {
        this.displayName = displayName;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (displayName != null ? !displayName.equals(user.displayName) : user.displayName != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (avatar != null ? !avatar.equals(user.avatar) : user.avatar != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        return isAdmin != null ? isAdmin.equals(user.isAdmin) : user.isAdmin == null;
    }

    @Override
    public int hashCode() {
        int result = displayName != null ? displayName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

}
