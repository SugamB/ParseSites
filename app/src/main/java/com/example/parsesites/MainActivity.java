package com.example.parsesites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems= new ArrayList<>();
    private ProgressBar progressBar;
    public String url;
    public String[] order;
    private Button Button;

    int limit1 = new Random().nextInt(3)+1;
    int limit2 = new Random().nextInt(3)+1;
    int limit = limit1+limit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button = (Button) findViewById(R.id.button2);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }

        });

        Intent intent = getIntent();
        order = getResources().getStringArray(R.array.Order);
        int randomIndex = new Random().nextInt(order.length);
        String randomName = order[randomIndex];

        String Genre = intent.getStringExtra("Genre");
        String Rating = intent.getStringExtra("Rating");

        url = "https://yts.pm/browse-movies/all/all/"+Genre.toLowerCase()+"/"+Rating.toLowerCase()+"/"+randomName.toLowerCase();

        //Toast.makeText(MainActivity.this,"URL Check: "+ randomName  ,Toast.LENGTH_LONG).show();


        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ParseAdapter(parseItems, this,limit);
        recyclerView.setAdapter(adapter);
//        Toast.makeText(MainActivity.this,"limit Check: "+ limit  ,Toast.LENGTH_LONG).show();
        Content content = new Content ();
        content.execute();

    }

    private  class Content extends AsyncTask<Void, Void, Void>
    {
        private static final String TAG = "MainActivity";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           try {

//               final Document doc = Jsoup.connect("https://yts.pm/browse-movies/all/all/all/0/latest").get();
               final Document doc = Jsoup.connect(url).get();

               Elements number = doc.getElementsByClass("pagination-bordered");
               String value = number.text();
               Log.d(TAG, "value == "+value);

               if(value != null && !value.isEmpty()){
                   String currentString1 = value;
                   String[] sep = currentString1.split("of ");
//                   Log.d(TAG, "sep[0]: "+sep[0]+" sep[1]: "+sep[1]);
                   String[] sep2 = sep[1].split(" ");
//                   Log.d(TAG, "sep2[0]: "+sep2[0]+" sep2[1]: "+sep2[1]);
                   value = sep2[0];
                   Log.d(TAG, "if condition == " + value);
                   Integer intvalue = Integer.parseInt(value);
                   if(intvalue>=500){
                       int randomIndex = new Random().nextInt(500)+1;
                       value = Integer.toString(randomIndex);
                       Log.d(TAG, "greater than 500 == " + value);
                   }
                   else{
                       int randomIndex = new Random().nextInt(intvalue)+1;
                       value = Integer.toString(randomIndex);
                       Log.d(TAG, "lower than 500 == " + value);

                   }

               }
               else{
                   value = "1";
                   Log.d(TAG, "else condition == " + value);
               }

               String url2 = url + "/?page="+value;
               final Document doc2 = Jsoup.connect(url2).get();

               Elements img = doc2.getElementsByClass("img-responsive");

               //Elements test = doc2.getElementsByClass("browse-movie-wrap.col-xs-10.col-sm-4.col-md-5.col-lg-4");

               for (Element el: img){

                   String src=el.attributes().get("src");
                   String title = el.attributes().get("alt");
                   Integer len = src.length();
                   String src2 = TextUtils.substring(src,2,len);
                   String http = "https://";
                   String imgUrl = http + src2;

                   String currentString = title;

                   String[] separated = currentString.split("\\)");
                   separated[0] = separated[0].trim();
                   title = separated[0].concat(")");

                   String httplink1= el.parent().parent().attributes().get("href");
                   String yts = "https://yts.pm";
                   String httplink = yts + httplink1;

                   String imdb = el.parent().getElementsByClass("rating").text();

                   String genre = el.parent().select("h4:nth-of-type(2)").text();
              //     Log.d(TAG, "test: "+genre);

                  // Log.d(TAG, "imdb: "+imdb);
                   parseItems.add(new ParseItem(imgUrl, title,httplink,imdb,genre));
                   Collections.shuffle(parseItems);

               }
//
//               Elements GenreData = doc2.select("h4:nth-of-type(2)");
//               String src=GenreData.text();
//               Integer len = src.length()/2 ;
//               String src2 = TextUtils.substring(src,0,len);
//               Log.d(TAG, "genredata: "+src2);


               if(parseItems.size() == 0){
                   startActivity(new Intent(MainActivity.this, Dropdown.class));
                   finish();
               }


           }

           catch (IOException  e) {
               e.printStackTrace();
           }

            return null;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Dropdown.class));
        finish();
    }

}
