package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class DataAdapter extends ArrayAdapter {
    List<TestScoreData> myData;

    public DataAdapter(Context context, List<TestScoreData> myData){
        super(context,0,myData);
        this.myData = myData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Log.d("skip", "data adpt");
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        /*
        TextView name = convertView.findViewById(R.id.name);
        TextView tries = convertView.findViewById(R.id.tries);
        TextView correct = convertView.findViewById(R.id.correct);
        TextView passTest = convertView.findViewById(R.id.passTest);
        */

        TextView level = convertView.findViewById(R.id.level);
        TextView tries = convertView.findViewById(R.id.tries);
        TextView correct = convertView.findViewById(R.id.correct);
        TextView score = convertView.findViewById(R.id.score);

        /*
        TestScoreData info = myData.get(position);
        name.setText(info.name);
        tries.setText(String.valueOf(info.tries));
        correct.setText(String.valueOf(info.correct));
        passTest.setText(String.valueOf(info.passTest));
        */

        TestScoreData info = myData.get(position);
        level.setText(info.level);
        tries.setText(String.valueOf(info.tries));
        correct.setText(String.valueOf(info.correct));
        score.setText(String.valueOf(info.score));

        if(position%2==0){
            //  name.setBackgroundColor(0xffff0000);  // set backround color to red for odd index num
            // ??  name.setBackgroundColor(Color.parseColor("#33FFD7"));
            convertView.setBackgroundColor(Color.parseColor("#3498DB"));
        }else{
            //  name.setBackgroundColor(0xff0000ff);  // set backround color to blue for even index num
            //  ??  name.setBackgroundColor(Color.parseColor("#A0DB8E"));
            convertView.setBackgroundColor(Color.parseColor("#66CCFF"));
        }
        return convertView;
    }
}

/*

 copied from list_item.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="name"
        android:layout_gravity="center"
        android:layout_margin="12dp"
        android:textStyle="bold"
        android:textColor="#000000"
        android:textSize="24sp" />

    <TextView
        android:id="@+id/tries"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="try"
        android:layout_gravity="center"
        android:layout_margin="12dp"
        android:textStyle="bold"
        android:textColor="#000000"
        android:textSize="24sp" />

    <TextView
        android:id="@+id/correct"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="correct"
        android:layout_gravity="center"
        android:layout_marginRight="12dp"
        android:textStyle="bold"
        android:textColor="#000000"
        android:textSize="24sp" />

    <TextView
        android:id="@+id/passTest"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="score"
        android:layout_gravity="center"
        android:layout_margin="12dp"
        android:textStyle="bold"
        android:textColor="#000000"
        android:textSize="24sp" />

</LinearLayout>

*/