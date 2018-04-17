package futsal.yo.com.yofutsal.Api;

import java.util.HashMap;

import futsal.yo.com.yofutsal.Model.ApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by rizalsidikp on 16/04/18.
 */

public interface RegisterInterface {

//    @Headers({
//            "Content-Type: application/json",
//    })
    @FormUrlEncoded
    @POST("register")
    public Call<ApiResponse> registerAccount(@Field("name") String id,
                                             @Field("email") String secret,
                                             @Field("phone_number") String phone_number,
                                             @Field("password") String password,
                                             @Field("c_password") String c_password,
                                             @Field("scope") String scope);

}
