package futsal.yo.com.yofutsal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;

import futsal.yo.com.yofutsal.Api.ApiHandler;
import futsal.yo.com.yofutsal.Api.LoginInterface;
import futsal.yo.com.yofutsal.Helper.AccessToken;
import futsal.yo.com.yofutsal.Helper.ButtonHandler;
import futsal.yo.com.yofutsal.Model.ApiError;
import futsal.yo.com.yofutsal.Model.ApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Rifka on 11/04/2018.
 */

public class Login extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    TextView signup;
    Button login;
    EditText email, password;

    private LoginInterface loginInterface;
    private AccessToken accessToken;

    public static Activity loginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        loginPage = this;

        signup = (TextView)findViewById(R.id.tv_signup);
        login = (Button) findViewById(R.id.btn_login);
        signup.setOnClickListener(this);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);

        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signup:
                intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                onLogin(email.getText().toString(), password.getText().toString());
                break;
            default:
                break;
        }
    }

    private void onLogin(String email, String password){
        if(email.equals("")){
            Toast.makeText(Login.this, "You must insert email", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(Login.this, "You must insert password", Toast.LENGTH_SHORT).show();
        }else{
            ButtonHandler.setDisableButton(login);
            loginInterface = ApiHandler.getApi(Login.this).create(LoginInterface.class);
            Call<ApiResponse> loginApi = loginInterface.loginAccount(email, password, "player");
            loginApi.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    try{
                        if(response.isSuccessful()){
                            accessToken.setToken(Login.this, response.body().getSuccess().getToken());
                            intent = new Intent(Login.this, TabMenu.class);
                            startActivity(intent);
                            finish();
                        }else{
                            try{
                                Converter<ResponseBody, ApiError> converter = ApiHandler.getApi(Login.this).responseBodyConverter(ApiError.class, new Annotation[0]);
                                ApiError error = converter.convert(response.errorBody());
                                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                Toast.makeText(Login.this, response.errorBody().string() , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(Login.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                    ButtonHandler.setEnableButton(login, R.string.login);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage() , Toast.LENGTH_SHORT).show();
                    ButtonHandler.setEnableButton(login, R.string.login);
                }
            });
        }
    }
}
