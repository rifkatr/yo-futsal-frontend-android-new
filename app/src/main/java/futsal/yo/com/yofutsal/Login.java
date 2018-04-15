package futsal.yo.com.yofutsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rifka on 11/04/2018.
 */

public class Login extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    TextView signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        signup = (TextView)findViewById(R.id.tv_signup);
        login = (Button) findViewById(R.id.btn_login);
        signup.setOnClickListener(this);

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
                intent = new Intent(Login.this, TabMenu.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
