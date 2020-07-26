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

        String newScoreString;

        newScoreString = (info.name + " : got " + info.correct + " of " + info.tries + " correct, scoring " + info.score + " %");

        // add case / swith statement

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