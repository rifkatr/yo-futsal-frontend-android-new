package futsal.yo.com.yofutsal;

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

import java.util.HashMap;

import futsal.yo.com.yofutsal.Api.ApiHandler;
import futsal.yo.com.yofutsal.Api.RegisterInterface;
import futsal.yo.com.yofutsal.Helper.AccessToken;
import futsal.yo.com.yofutsal.Helper.ButtonHandler;
import futsal.yo.com.yofutsal.Model.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rifka on 11/04/2018.
 */

public class Signup extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    TextView login;
    Button signup;
    EditText e_name, e_email, e_phone, e_password, e_c_password;
    private RegisterInterface registerInterface;

    private String name="", email="", phone="", password="", c_password="";

    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);

        e_name = (EditText)findViewById(R.id.tv_fullname);
        e_email = (EditText)findViewById(R.id.tv_email);
        e_phone = (EditText)findViewById(R.id.tv_phone);
        e_password = (EditText)findViewById(R.id.tv_password);
        e_c_password = (EditText)findViewById(R.id.tv_re_password);

        login = (TextView)findViewById(R.id.tv_login);
        login.setOnClickListener(this);

        signup = (Button)findViewById(R.id.btn_signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                finish();
                break;
            case R.id.btn_signup:
                validateInput();
                break;
            default:
                break;
        }
    }

    private void validateInput(){
        name = e_name.getText().toString();
        email = e_email.getText().toString();
        phone = e_phone.getText().toString();
        password = e_password.getText().toString();
        c_password = e_c_password.getText().toString();

        if(name.equals("")){
            allertValidate("Please input name");
        }else if(email.equals("")){
            allertValidate("Please input email");
        }else if(phone.equals("")){
            allertValidate("Please input phone");
        }else if(password.equals("")){
            allertValidate("Please input password");
        }else if(!c_password.equals(password)){
            allertValidate("Re password must be same with password, === " + password + " === " + c_password);
        }else{
            onRegister();
        }

    }

    private void allertValidate(String message){
        Toast.makeText(Signup.this, message, Toast.LENGTH_SHORT).show();
    }

    private void onRegister(){
//        HashMap<String, String> params = new HashMap<>();
//        params.put("name", "Rizal SP");
//        params.put("email", "rizalsidikp24@gmail.com");
//        params.put("password", "hahahaha");
//        params.put("c_password", "hahahaha");
//        params.put("scope", "scope");

        ButtonHandler.setDisableButton(signup);
        registerInterface = ApiHandler.getApi(Signup.this).create(RegisterInterface.class);
        Call<ApiResponse> register = registerInterface.registerAccount(name, email, phone, password, c_password, "player");
        register.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try{
                    if(response.isSuccessful()){
                        accessToken.setToken(Signup.this, response.body().getSuccess().getToken());
                        intent = new Intent(Signup.this, TabMenu.class);
                        startActivity(intent);
                        Login.loginPage.finish();
                        finish();
                    }else{
                        Toast.makeText(Signup.this, response.errorBody().string() , Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(Signup.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                }
                ButtonHandler.setEnableButton(signup, R.string.signup);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                ButtonHandler.setEnableButton(signup, R.string.signup);
            }
        });
    }
}
