package futsal.yo.com.yofutsal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rizalsidikp on 17/04/18.
 */

public class ApiError {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("code")
    @Expose
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage(){
        return message;
    }

    public String getError(){
        return error;
    }
}
