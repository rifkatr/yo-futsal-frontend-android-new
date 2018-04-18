package futsal.yo.com.yofutsal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rizalsidikp on 15/04/18.
 */

public class ApiResponse {
    @SerializedName("success")
    @Expose
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

}
