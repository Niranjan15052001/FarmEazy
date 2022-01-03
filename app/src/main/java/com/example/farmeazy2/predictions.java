package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class predictions extends AppCompatActivity {
    EditText n,p,k,temp,hum,ph,rainfall;
    Button b;
    private OkHttpClient okhttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predictions);
        getSupportActionBar().hide();
        b=findViewById(R.id.predict_button);
        n=findViewById(R.id.nitrogen);
        p=findViewById(R.id.phosphorous);
        k=findViewById(R.id.Potassium);
        temp=findViewById(R.id.Temperature);
        hum=findViewById(R.id.editTextNumberDecimal);
        ph=findViewById(R.id.editTextNumberDecimal2);
        rainfall=findViewById(R.id.editTextNumberDecimal3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String j="";
                j=j+n.getText().toString()+" ";
                j=j+p.getText().toString()+" ";
                j=j+k.getText().toString()+" ";
                j=j+temp.getText().toString()+" ";
                j=j+hum.getText().toString()+" ";
                j=j+ph.getText().toString()+" ";
                j=j+rainfall.getText().toString()+" ";
                okhttp=new OkHttpClient();
                String dummy="hello";

                RequestBody body=new FormBody.Builder().add("Sample",j).build();
                Request request=new Request.Builder().url("http://niranjan.pythonanywhere.com/debug").post(body).build();
                okhttp.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Serverdown", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String k=response.body().string();
                        if(response.isSuccessful()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Successful "+k, Toast.LENGTH_SHORT).show();
                                    //Intent intent=new Intent(MainActivity.this,Dynamic.class);
                                    //ntent.putExtra("key1",k);
                                    //startActivity(intent);
                                }
                            });
                        }
                    }
                });


            }

        });
    }
}