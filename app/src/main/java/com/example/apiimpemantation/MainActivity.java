/*
        API Implementation Steps:

        1.	Create an Android Studio Project
        2.	Add Internet Permission in Manifest
        3.	Add retrofit Libraries (
                                    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
                                    implementation 'com.squareup.retrofit2:converter-gson:2.9.0')
        4.	Create class for Retrofit Instance
        5.	Interface for end points
        6.	Create Pojo (Plain old java object) Class for API response (Search online JSON to Java -> Paste the JSON in editor then you will get Java class)
        7.	Call API in Activity/Fragment
*/


package com.example.apiimpemantation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);


        apiInterface.getPosts().enqueue(new Callback<List<PostModal>>() {
            @Override
            public void onResponse(Call<List<PostModal>> call, Response<List<PostModal>> response) {

                if (response.body().size() > 0) {
                    List<PostModal> lists = response.body();

                    for (PostModal list :
                            lists) {
                        Log.d("getId", String.valueOf(list.getId()));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Data not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostModal>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}