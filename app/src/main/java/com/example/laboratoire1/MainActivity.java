package com.example.laboratoire1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.laboratoire1.R.color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    EditText et_email = null;
    EditText et_password = null;
    CheckBox chk_password = null;
    Button btn_validate = null;
    TextView tv_message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        chk_password = (CheckBox) findViewById(R.id.chk_password);
        btn_validate = (Button) findViewById(R.id.btn_validate);
        tv_message = (TextView) findViewById(R.id.tv_message);

        String email = res.getString(R.string.email);
        String password = res.getString(R.string.password);
        String showpassword = res.getString(R.string.showpassword);
        String validatepassword = res.getString(R.string.validatepassword);
        String invalid = res.getString(R.string.invalid);
        String valid = res.getString(R.string.valid);

        int Cinvalid = getResources().getColor(R.color.invalid);
        int Cvalid = getResources().getColor(R.color.valid);

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                String password = et_password.getText().toString();
                String email = et_email.getText().toString();


                if (EmailValidator(email) == true && PasswordValidator(password) == true) {
                    tv_message.setText(valid);
                    tv_message.setTextColor(Cvalid);
                    tv_message.setVisibility(View.VISIBLE);

                } else {
                    tv_message.setText(invalid);
                    tv_message.setTextColor(Cinvalid);
                    tv_message.setVisibility(View.VISIBLE);

                }
            }
        });

        chk_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        chk_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else if (!compoundButton.isChecked()) {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


    }

    public boolean EmailValidator(String email) {
        boolean verdict = true;

        if(!email.isEmpty()) {
            if(email.contains("@")) {
                if (email == null || email.isEmpty()) {
                    verdict = false;
                }
                String[] division = email.split("@");
                if (division.length != 2) {
                    verdict = false;
                }

                if (!division[0].matches("^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*$")) {
                    verdict = false;
                }

                if (!division[1].matches("^[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*$") || !division[1].contains(".")) {
                    verdict = false;
                }
            }else{
                verdict = false;
            }
        }else {
            verdict = false;
        }
        return verdict;
    }

    public boolean PasswordValidator(String password) {
         boolean verdict = true;
        String fullregex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&()\\[\\]{}_=<>+\\-!?*/|:;.,‘\"~^])(?=\\S+$)$";
        Pattern pattern = Pattern.compile(fullregex);
        Matcher matcher = pattern.matcher(password);
        //verdict = () -> return matcher.matches();


        if (!password.isEmpty()) {
            if (!CheckLowercase(password)){
                verdict = false;
            }
            if (!CheckUppercase(password)){
                verdict = false;
            }
            if (!CheckDigit(password)){
                verdict = false;
            }
            if (!CheckSpecial(password)){
                verdict = false;
            }
            if (!(password.length()>=10)){
                verdict =false;
            }
        }else{
            verdict = false;
        }
        return verdict;
    }

    public boolean CheckLowercase(String password){
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean CheckUppercase(String password) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean CheckDigit(String password) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean CheckSpecial(String password) {
        Pattern pattern = Pattern.compile("[@#$%&()\\[\\]{}_=<>+\\-!?*/|:;.,‘\"~^]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}