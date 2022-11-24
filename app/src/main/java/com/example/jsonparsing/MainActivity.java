package com.example.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String url = "https://v2.jokeapi.dev/joke/Any?amount=10";
    private List<Joke> jokeList= new ArrayList<Joke>();
    private RequestQueue mQueue;
    private JokeListAdapter adapter = new JokeListAdapter();
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        RecyclerView rView = findViewById(R.id.rview);
        rView.setAdapter(adapter);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jokeList.clear();
                jsonParse();
            }
        });

        adapter.setmLoader(new JokeListAdapter.loader() {
            @Override
            public void loadData() {
                jsonParse();
            }
        });

        jsonParse();

    }

    private void jsonParse() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("error")){
                        Log.d(TAG, "onResponse: Cannot get jokes , api not working");
                        return;
                    }

                    JSONArray jokesArray = response.getJSONArray("jokes");
                    for(int i=0;i<jokesArray.length();i++){
                            JSONObject joke = jokesArray.getJSONObject(i);
                            String type = joke.getString("type");
                            JSONObject flags = joke.getJSONObject("flags");
                            String lang = joke.getString("lang");
                            int id = joke.getInt("id");
                            String category = joke.getString("category");
                            Boolean safe = joke.getBoolean("safe");

                            Boolean nsfw,religious, political, racist, sexist, explicit;
                            nsfw = flags.getBoolean("nsfw");
                            religious = flags.getBoolean("religious");
                            political = flags.getBoolean("political");
                            racist = flags.getBoolean("racist");
                            sexist = flags.getBoolean("sexist");
                            explicit = flags.getBoolean("explicit");

                            Joke curJoke;
                            if(type.equals("single")){
                                 curJoke = new Joke(
                                        category, type,null, null, joke.getString("joke"), nsfw, religious, political, racist, sexist, explicit, id, safe, lang);
                            }else{
                                 curJoke = new Joke(
                                        category, type,joke.getString("setup"), joke.getString("delivery"), null, nsfw, religious,
                                        political, racist, sexist, explicit, id, safe, lang);
                            }

                            jokeList.add(curJoke);


                        Log.d(TAG, "onResponse: " + curJoke);

                    }
                    adapter.setJokesList(jokeList);
                    swipeRefreshLayout.setRefreshing(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}