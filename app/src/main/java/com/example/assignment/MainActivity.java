package com.example.assignment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CrewViewModel model;
    CrewDatabase db;
    CrewDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
         db= CrewDatabase.getInstance(this);
         dao = db.crewDao();
        final CustomAdapter adapter = new CustomAdapter(this);
        recyclerView.setAdapter(adapter);

        model = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(CrewViewModel.class);


        model.getAllMembers().observe(this, new Observer<List<Crew>>() {
            @Override
            public void onChanged(List<Crew> crews) {
                adapter.setCrewList(crews);
            }
        });
        callAPi();
        Button refresh = findViewById(R.id.refresh);
        Button delete = findViewById(R.id.delete);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAPi();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAll();
            }
        });

    }
    private void callAPi(){
        String url = "https://api.spacexdata.com/v4/crew";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int n=response.length();
                for(int i=0;i<n;i++){
                    try {
                        JSONObject ob = response.getJSONObject(i);
                        insert(ob);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MAINACTIVITY", "onErrorResponse: "+error.toString());
            }
        });
    }
    private void insert(JSONObject ob) throws JSONException {
        new CrewRepository(getApplication())
                .insert(new Crew(
                ob.getString("name"),
                ob.getString("agency"),
                ob.getString("wikipedia"),
                ob.getString("status"),
                ob.getString("image")
                ));
    }
    private void deleteAll(){
        new CrewRepository(getApplication()).deleteAll();
    }
}
//         "name": "Robert Behnken",
//        "agency": "NASA",
//        "image": "https://imgur.com/0smMgMH.png",
//        "wikipedia": "https://en.wikipedia.org/wiki/Robert_L._Behnken",
//        "launches": [
//        "5eb87d46ffd86e000604b388"
//        ],
//        "status": "active",
//        "id": "5ebf1a6e23a9a60006e03a7a"