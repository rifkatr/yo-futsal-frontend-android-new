package futsal.yo.com.yofutsal.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rizalsidikp on 15/04/18.
 */

public class ApiHandler {
    public static final String BASE_URL = "http://192.168.43.49:8000/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getApi(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
