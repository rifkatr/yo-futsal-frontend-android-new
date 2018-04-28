package futsal.yo.com.yofutsal.Api;


import futsal.yo.com.yofutsal.Model.ApiResponse;
import java.util.HashMap;
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
    public Call<ApiResponse> registerAccount(@Field("name") String name,
                                             @Field("email") String email,
                                             @Field("phone_number") String phone_number,
                                             @Field("password") String password,
                                             @Field("c_password") String c_password,
                                             @Field("birthdate") String birthdate,
                                             @Field("gender") String gender,
                                             @Field("scope") String scope);

}
