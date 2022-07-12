package com.example.program6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void xml(View view){
   Intent intent=new Intent(this,parsing.class);
   intent.putExtra("mode",1);
   startActivity(intent);
    }
    public void json(View view){
        Intent intent=new Intent(this,parsing.class);
        intent.putExtra("mode",2);
        startActivity(intent);
    }
}