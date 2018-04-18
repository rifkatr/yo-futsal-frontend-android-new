package futsal.yo.com.yofutsal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rizalsidikp on 15/04/18.
 */

public class Success {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("users")
    @Expose
    private List<Users> users = null;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user")
    @Expose
    private Users user;

    public Users getUser(){ return user; }

    public void setUser(){ this.user = user; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
