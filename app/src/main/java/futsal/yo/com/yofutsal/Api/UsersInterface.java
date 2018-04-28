package futsal.yo.com.yofutsal.Api;


import futsal.yo.com.yofutsal.Model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

/**
 * Created by rizalsidikp on 15/04/18.
 */

public interface UsersInterface {

//  getAllusers
    @GET("users")
    Call<ApiResponse> getUsers();

//    getUserLogin
    @GET("user")
    Call<ApiResponse> getUser();

//    setUser
    @FormUrlEncoded
    @PATCH("user")
    public Call<ApiResponse> updateUser(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("phone_number") String phone_number,
                                 @Field("birthdate") String birthdate,
                                 @Field("gender") String gender);
}
