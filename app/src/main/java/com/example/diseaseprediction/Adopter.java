package com.example.diseaseprediction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class Adopter extends ArrayAdapter<String> {

    String[] list;
    Context context;
    LayoutInflater inflater;
    int layout;


    public Adopter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);

        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list=objects;
        this.context=context;
        this.layout=resource;
    }
    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public String getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int position, View view, @NonNull ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View v = inflater.inflate(layout,null);
        TextView textView=v.findViewById(R.id.text);
        final CheckBox checkBox=v.findViewById(R.id.checkbox);
        MainActivity.mInput[0][position]=0.0f;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox.isChecked()){

                    MainActivity.mInput[0][position]=1.0f;

                    Log.w("Adopter",String.valueOf(MainActivity.mInput[0][position]));



                }

            }
        });

        textView.setText(position+ ". "+ list[position].toUpperCase().replace("_"," "));
        v.setFocusable(false);


        return v;
    }
}
