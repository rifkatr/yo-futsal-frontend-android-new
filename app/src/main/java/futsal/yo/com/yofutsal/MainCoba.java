package futsal.yo.com.yofutsal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import futsal.yo.com.yofutsal.Adapter.UsersAdapter;
import futsal.yo.com.yofutsal.Api.ApiHandler;
import futsal.yo.com.yofutsal.Api.UsersInterface;
import futsal.yo.com.yofutsal.Model.Users;
import futsal.yo.com.yofutsal.Model.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rizalsidikp on 15/04/18.
 */

public class MainCoba extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UsersAdapter adapter;
    private List<Users> users;
    private UsersInterface usersInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coba);

        Log.d("hhh", "onCreate: ");

        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        usersInterface = ApiHandler.getApi().create(UsersInterface.class);

        Call<ApiResponse> call = usersInterface.getUsers();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                users = response.body().getSuccess().getUsers();
                adapter = new UsersAdapter(users);
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainCoba.this, response.body().getSuccess().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}
