package futsal.yo.com.yofutsal.Api;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import futsal.yo.com.yofutsal.Helper.AccessToken;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yo-futsal on 15/04/18.
 */

public class ApiHandler {
    public static final String BASE_URL = "http://192.168.43.217:8000/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getApi(final Context context){

        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        //getAccessToken is your own accessToken(retrieve it by saving in shared preference or any other option )
                        String accessToken = AccessToken.getToken(context);
                        if(accessToken.isEmpty()){
                            Log.e("retrofit 2","Authorization header is already present or token is empty....");
                            return chain.proceed(chain.request());
                        }
                        Request authorisedRequest = chain.request().newBuilder()
                                .addHeader("Authorization", accessToken)
                                .addHeader("Accept", "application/json")
                                .build();
                        Log.e("retrofit 2","Authorization header is added to the url...." + accessToken);
                        return chain.proceed(authorisedRequest);
                    }}).build();
//
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(defaultHttpClient)
                    .build();
        }
        return retrofit;
    }
}
