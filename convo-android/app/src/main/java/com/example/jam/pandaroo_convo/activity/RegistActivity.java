package com.example.jam.pandaroo_convo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jam.pandaroo_convo.R;
import com.example.jam.pandaroo_convo.entity.User;

public class RegistActivity extends AppCompatActivity {
    private AutoCompleteTextView reg_un;
    private EditText reg_pwd;
    private EditText reg_pwd2;
    private EditText reg_rname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        reg_un = findViewById(R.id.reg_un);
        reg_pwd = findViewById(R.id.reg_pwd);
        reg_pwd2 = findViewById(R.id.reg_pwd2);
        reg_rname = findViewById(R.id.reg_rname);

        Button reg_btn =findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegist();
            }
        });
    }

    private void attemptRegist() {
        reg_un.setError(null);
        reg_pwd.setError(null);
        reg_pwd2.setError(null);
        String username = reg_un.getText().toString();
        String password = reg_pwd.getText().toString();
        String password2 = reg_pwd2.getText().toString();
        String rname = reg_rname.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if ( !TextUtils.isEmpty(password) && !isPasswordValid(password) ) {
            reg_pwd.setError(getString(R.string.error_invalid_password));
            focusView = reg_pwd;
            cancel = true;
        }

        if(!password2.equals(password)){
            reg_pwd2.setError("两次密码不一致");
            focusView = reg_pwd2;
            cancel = true;
        }


        if ( TextUtils.isEmpty(username) ) {
            reg_un.setError(getString(R.string.error_field_required));
            focusView = reg_un;
            cancel = true;
        }




        if(rname.equals("")){
            reg_rname.setError("真实姓名不能为空");
            focusView = reg_rname;
            cancel = true;
        }



        if ( cancel ) {
            focusView.requestFocus();
        } else {

            String musername = reg_un.getText().toString();
            String mpassword = reg_pwd.getText().toString();
            String mrname = reg_rname.getText().toString();
            User person=new User(musername,mpassword,mrname);

            //PersonDAO personDAO=new PersonDAO();
            boolean isSucess=false;

            //if(personDAO.checkUsername(musername)){
              //  Toast.makeText(RegistActivity.this, "Username is Exist Please Check it Out!", Toast.LENGTH_SHORT).show();

            //}
            //else{

                //isSucess= personDAO.insert(person); //添加到数据库

                if(isSucess){  //
                    Toast.makeText(RegistActivity.this, "Sign Up SUCCESS Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(RegistActivity.this,LoginActivity.class);//转到登陆
                    RegistActivity.this.startActivity(intent);
                    finish();
                    /*-------------------------------*/
                }
                else {
                    Toast.makeText(RegistActivity.this, "Information is Wrong Please Check out", Toast.LENGTH_SHORT).show();
                }
            }


        }


    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

}