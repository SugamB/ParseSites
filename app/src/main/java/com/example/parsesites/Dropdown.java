package com.example.parsesites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Dropdown extends AppCompatActivity {

    private static final String TAG = "Dropdown" ;
    Button Button;
    Spinner  Spinner2,Spinner3;
    Spinner Spinner;
    ArrayAdapter<CharSequence> adapter2,adapter3,adapter;
    public String  Genre, Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropdown);

        Button = (Button) findViewById(R.id.button);


//        Spinner = (Spinner) findViewById(R.id.testSpinner);
//        adapter = ArrayAdapter.createFromResource(this,R.array.Genre, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spinner.setAdapter(adapter);
//        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Genre = Spinner.getSelectedItem().toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        Spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.Genre, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(adapter2);
        Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Genre = Spinner2.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Spinner3 = (Spinner) findViewById(R.id.spinner3);
        adapter3 = ArrayAdapter.createFromResource(this,R.array.Rating, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner3.setAdapter(adapter3);
        Spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Rating = Spinner3.getSelectedItem().toString().substring(0,1);
                if(Rating.equals("A")){
                    Rating = "0";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



//        Log.d(TAG, "SpinnerCheck: " + Quality + Genre+Rating+Order);


        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Dropdown.this,"Spinner Check: "+ Quality  ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dropdown.this,MainActivity.class);
                intent.putExtra("Genre", Genre);
                intent.putExtra("Rating", Rating);
                startActivity(intent);
                finish();

            }

        });


    }
}
