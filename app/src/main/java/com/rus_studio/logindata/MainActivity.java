package com.rus_studio.logindata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());
        final EditText username = findViewById(R.id.txt_username);
        final EditText password = findViewById(R.id.txt_password);
        Button buton = findViewById(R.id.btn_login);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post("http://192.168.6.236/Chyntya/login.php")
                        .addBodyParameter("username", username.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    if (hasil.getBoolean("sukses")){
                                        Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "password atau username salah", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "password atau username salah", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle erro

                                Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
