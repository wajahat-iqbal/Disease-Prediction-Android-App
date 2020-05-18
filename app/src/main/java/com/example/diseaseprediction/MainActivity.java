package com.example.diseaseprediction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Classifier classifier;
    ListView listView;
    Button button;
    public static float[][] mInput=new float[1][264];
    float[][] mOutput;
    AlertDialog.Builder builder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            classifier=new Classifier(MainActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        button=findViewById(R.id.button);
        listView=findViewById(R.id.list);
        builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Adopter adopter=new Adopter(MainActivity.this,R.layout.layout
                                ,Constent.symp);
                        mInput=new float[1][264];
                        listView.setAdapter(adopter);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Adopter adopter=new Adopter(MainActivity.this,R.layout.layout
                                ,Constent.symp);
                        mInput=new float[1][264];
                        listView.setAdapter(adopter);
                    }
                });


        Adopter adopter=new Adopter(MainActivity.this,R.layout.layout
        ,Constent.symp);

        listView.setAdapter(adopter);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mOutput=classifier.classify(mInput);
               int indx=-1;
               for(int i=0 ; i< mOutput[0].length; i++){


                   if(mOutput[0][i] >0.8f){
                       indx=i;

                   }

               }
               if(indx!=-1) {
                   Log.w("Disease ::", Constent.labels[indx]);
                   builder1.setMessage("Disease ::"+ Constent.labels[indx]);
                   AlertDialog alert11 = builder1.create();
                   alert11.show();

               }else{

                   builder1.setMessage("Disease ::"+ "No Disease Found ");
                   AlertDialog alert11 = builder1.create();
                   alert11.show();

               }
           }
       });


    }
}
