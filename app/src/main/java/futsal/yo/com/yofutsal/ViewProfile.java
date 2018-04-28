package futsal.yo.com.yofutsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import futsal.yo.com.yofutsal.Api.ApiHandler;
import futsal.yo.com.yofutsal.Api.UsersInterface;
import futsal.yo.com.yofutsal.Helper.AccessToken;
import futsal.yo.com.yofutsal.Model.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rifka on 13/04/2018.
 */

public class ViewProfile extends AppCompatActivity{

    private UsersInterface usersInterface;
    TextView name, email, phone, gender, birthdate;
    LinearLayout l_edit, l_back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        name = (TextView) findViewById(R.id.vp_name);
        email = (TextView) findViewById(R.id.vp_email);
        phone = (TextView) findViewById(R.id.vp_phone);
        gender = (TextView) findViewById(R.id.vp_gender);
        birthdate = (TextView) findViewById(R.id.vp_birthdate);
        l_edit = (LinearLayout) findViewById(R.id.LinearEdit);
        l_back = (LinearLayout) findViewById(R.id.LinearBack);

        l_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ViewProfile.this, EditProfile.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("gender", gender.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("birthdate", birthdate.getText().toString());
                startActivity(intent);
            }
        });

        l_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getUserData();
    }

    private void getUserData(){
        usersInterface = ApiHandler.getApi(ViewProfile.this).create(UsersInterface.class);
        Call<ApiResponse> getUserResponse = usersInterface.getUser();
        getUserResponse.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if(response.isSuccessful()){
                        name.setText(response.body().getSuccess().getUser().getName());
                        email.setText(response.body().getSuccess().getUser().getEmail());
                        phone.setText(response.body().getSuccess().getUser().getPhoneNumber());
                        gender.setText(response.body().getSuccess().getUser().getGender());
                        birthdate.setText(response.body().getSuccess().getUser().getBirthdate());
                    }else{
                        Toast.makeText(ViewProfile.this, response.errorBody().string() , Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ViewProfile.this, "catch : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(ViewProfile.this, "fail : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
