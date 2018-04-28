package futsal.yo.com.yofutsal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import futsal.yo.com.yofutsal.Helper.AccessToken;

/**
 * Created by Rifka on 26/04/2018.
 */

public class EditProfile extends AppCompatActivity implements View.OnClickListener{

    EditText e_fullname, e_email, e_birthdate, e_phone;
    TextView t_gender;
    Spinner s_gender;
    LinearLayout l_back, l_birtdate;
    Button b_save;

    private int mYear, mMonth, mDay;
    private String name="", email="", phone="", gender="", birthdate="";
    private String a_name="", a_email="", a_phone="", a_gender="", a_birthdate="";

    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        e_fullname = (EditText) findViewById(R.id.tv_fullname);
        e_email = (EditText) findViewById(R.id.tv_email);
        e_birthdate = (EditText) findViewById(R.id.ed_birthdate);
        e_phone = (EditText) findViewById(R.id.tv_phone);
        t_gender = (TextView) findViewById(R.id.tv_gender);
        s_gender = (Spinner) findViewById(R.id.SpinnerGender);
        l_back = (LinearLayout) findViewById(R.id.LinearBack);
        l_birtdate = (LinearLayout) findViewById(R.id.Birthdate);
        b_save = (Button) findViewById(R.id.btn_save);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");
        gender = i.getStringExtra("gender");
        birthdate = i.getStringExtra("birthdate");

        l_birtdate.setOnClickListener(this);
        l_back.setOnClickListener(this);

        e_fullname.setText(name);
        e_email.setText(email);
        e_birthdate.setText(birthdate);
        e_phone.setText(phone);
        s_gender.post(new Runnable() {
            @Override
            public void run() {
                if (gender.equals("male")) {
                    s_gender.setSelection(0);
                } else if (gender.equals("female")) {
                    s_gender.setSelection(1);
                }
            }
        });
    }

    public void Calendar_Birthdate() {
        //Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Tampilkan selected date in pada EditText
                e_birthdate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        dpd.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Birthdate:
                Calendar_Birthdate();
                break;
            case R.id.LinearBack:
                finish();
                break;
            case R.id.btn_save:
                break;
            default:
                break;
        }
    }
}
