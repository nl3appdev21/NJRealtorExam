package com.nl3designs.njrealtorexam;

import android.content.Context;
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

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView lbEntry = convertView.findViewById(R.id.leaderBoardEntry);
        TestScoreData info = myData.get(position);
        int grade = info.score;
        String newScoreString;
        newScoreString = (info.name + " : " + info.tries + "/" + info.correct + " correct, scoring " + info.score + "% ");
        lbEntry.setText(newScoreString);

        if (grade >= 90) {   // make color gold
            convertView.setBackgroundResource(R.mipmap.goldwinner);
        } else if (grade >= 80) {   // make color silve
            convertView.setBackgroundResource(R.mipmap.silverwinner);
        } else if (grade >= 70) {   // make color bronze
            convertView.setBackgroundResource(R.mipmap.bronzewinner);
        }

        return convertView;
    }
}