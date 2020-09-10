package com.app.ranger.techtrix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.app.ranger.techtrix.adapter.FeedAdapter;
import com.app.ranger.techtrix.authentication.SignUpActivity;
import com.app.ranger.techtrix.model.Feed;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_logout;
    RecyclerView rv_feed;


    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    private String URL_JSON = "https://jsonplaceholder.typicode.com/photos";
    private JsonArrayRequest arrayRequest;
    private RequestQueue requestQueue;
    private List<Feed> lstfeed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();


        fab_logout = findViewById(R.id.fab_logout);
        rv_feed = findViewById(R.id.rv_feed);

        fab_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        JsonRequest();

    }

    public void JsonRequest() {


        arrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

//                Log.e("response", response.toString());


                for (int i = 0; i < response.length(); i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Feed feed = new Feed();

                        feed.setAlbumID(jsonObject.getInt("albumId"));
                        feed.setId(jsonObject.getInt("id"));
                        feed.setTitle(jsonObject.getString("title"));
                        feed.setThumbUrl(jsonObject.getString("thumbnailUrl"));
                        feed.setImageUrl(jsonObject.getString("url"));

                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        lstfeed.add(feed);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


//                Toast.makeText(MainActivity.this, "Size of List " + String.valueOf(lstfeed.size()), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, lstfeed.get(1).toString(), Toast.LENGTH_SHORT).show();
//
                setRvAdapter(lstfeed);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);
    }

    public void setRvAdapter(List<Feed> list) {

        FeedAdapter myAdapter = new FeedAdapter(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_feed.setLayoutManager(manager);
        rv_feed.setHasFixedSize(true);
        rv_feed.setAdapter(myAdapter);


    }


}