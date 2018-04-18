package futsal.yo.com.yofutsal.Api;

import futsal.yo.com.yofutsal.Model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by rizalsidikp on 17/04/18.
 */

public interface LoginInterface {

    @FormUrlEncoded
    @POST("login")
    public Call<ApiResponse> loginAccount(
                                             @Field("email") String secret,
                                             @Field("password") String password,
                                             @Field("scope") String scope);

}
