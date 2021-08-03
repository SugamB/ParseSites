package com.example.parsesites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import org.apache.commons.text.StringEscapeUtils;

public class GalleryActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView,textView2, textView3, textView4;
    private String moviedetail,src2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageView = findViewById(R.id.imageViewMovie);
        textView = findViewById(R.id.textViewMovie);
        textView2 = findViewById(R.id.textView2Movie);
        textView3 = findViewById(R.id.textView3Movie);
        textView4 = findViewById(R.id.textView4Movie);

        textView4.setText(getIntent().getStringExtra("imdb"));
        textView.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);

        Content content = new Content();
        content.execute();

    }

    private  class Content extends AsyncTask<Void, Void, Void>
    {
        private static final String TAG = "MainActivity";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView2.setText(moviedetail);
            textView3.setText(src2);
         }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = getIntent().getStringExtra("httplink");
                final Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("p.hidden-xs");
                String substr = data.select("p.hidden-xs").text();

                String moviedetail2 = data.select("p.hidden-xs.hidden-sm").text();
                Integer movielen = moviedetail2.length()+1;
                String sub2 = substr.substring(movielen);
                moviedetail=StringEscapeUtils.unescapeHtml4(sub2);

                Elements GenreData = doc.select("h2:nth-of-type(2)");
                String src=GenreData.text();
                Integer len = src.length()/2 ;
                src2 = TextUtils.substring(src,0,len);
              //  Log.d(TAG, "genre2: "+src2);

//                Elements data2 = doc.select("p.hidden-sm.hidden-md.hidden-lg");
//                String sub2 = data2.select("p.hidden-sm.hidden-md.hidden-lg").text();
//                moviedetail = StringEscapeUtils.unescapeHtml4(sub2);






//                Log.d(TAG, "synopsis: " + moviedetail);
//                Log.d(TAG, "synopsis2 "+ moviedetail2);

                //Log.d(TAG, "parsing: "+sub2);

            }

            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
