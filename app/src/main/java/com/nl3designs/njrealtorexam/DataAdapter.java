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

        TextView lbEntry = convertView.findViewById(R.id.leaderBoardEntry);
        TestScoreData info = myData.get(position);

        // add case / swith statement
        int grade = info.score;
        String letGrade = "S";

        /*
        switch (grade) {
            case (grade >= 90):
                Log.d("skip", " grade is: " + grade + " you have a = " + "AA");
                break;
            case (grade >= 80):
                Log.d("skip", " grade is: " + grade + " you have a = " + "BB");
                break;
            case (grade >= 70):
                Log.d("skip", " grade is: " + grade + " you have a = " + "CC");
                break;
        }
        */

        if (grade >= 90) {
            letGrade = "A";
            Log.d("skip", " grade is: " + grade + " you have a = " + "AA");
        } else if (grade >= 80) {
            letGrade = "B";
            Log.d("skip", " grade is: " + grade + " you have a = " + "BB");
        } else if (grade >= 70) {
            letGrade = "C";
            Log.d("skip", " grade is: " + grade + " you have a = " + "CC");
        }

        String newScoreString;

        newScoreString = (info.name + " : got " + info.correct + " of " + info.tries + " correct, scoring " + info.score + " %  " + letGrade);

        //  lbEntry.setText("info");
        lbEntry.setText(newScoreString);

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