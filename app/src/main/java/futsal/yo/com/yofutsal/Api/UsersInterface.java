package futsal.yo.com.yofutsal.Api;


import futsal.yo.com.yofutsal.Model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rizalsidikp on 15/04/18.
 */

public interface UsersInterface {

    @GET("users")
    Call<ApiResponse> getUsers();

}
