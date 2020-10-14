package com.example.reciclon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button registrar1;
    Button ingresar;
    TextView ed_usuario;
    EditText tv_passwork;
    private ScrollView FormLogin;
    private ProgressBar pbLogin;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        registrar1=findViewById(R.id.btn_registrar1);
        ed_usuario=findViewById(R.id.ed_usuario);
        tv_passwork=findViewById(R.id.ed_passwork);
        ingresar=findViewById(R.id.btn_ingresar);
        FormLogin= findViewById(R.id.FormLogin);
        pbLogin = findViewById(R.id.progressBarLogin);
        changeLoginFormVisibility(true);

        registrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intentReg= new Intent(MainActivity.this, Registro.class);
                 MainActivity.this.startActivity(intentReg);
            }

        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectOra conn = new ConnectOra("10.0.2.2", "orcl", "ADMIN_RECICLON",
                        "12345");
                Boolean result= conn.Login((EditText) ed_usuario, tv_passwork);
                if(result==true){
                    Intent intentReg= new Intent(MainActivity.this, Menu.class);
                    MainActivity.this.startActivity(intentReg);
                }
            }
        });

    }

    private void evento(){
        registrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuar = ed_usuario.getText().toString();
                String pass = tv_passwork.getText().toString();

                changeLoginFormVisibility(false);
            }
        });
    }

    private void changeLoginFormVisibility(boolean showForm) {
        pbLogin.setVisibility(showForm ? View.GONE : View.VISIBLE);
        FormLogin.setVisibility(showForm ? View.VISIBLE : View.GONE);
    }

}