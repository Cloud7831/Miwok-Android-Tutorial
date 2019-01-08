package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{
    private int colour;

    public WordAdapter(Activity context, ArrayList<Word> words, int c) {
        super(context, 0, words);
        colour = c;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view. In this case it points to a word.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            //false because we don't want to attach the list item view to the parent list view just yet.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        Word currentWord = getItem(position);



        //Find the view where we want to store the Default Translation. Using list_item.xml.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        //Find the view where we want to store the Miwok Translation. Using list_item.xml.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            iconView.setVisibility(View.VISIBLE);
            iconView.setImageResource(currentWord.getImageResourceID());
        }
        else{
            iconView.setVisibility(View.GONE);
        }

        View textContainer = (View) listItemView.findViewById(R.id.text_container);

        textContainer.setBackgroundColor(ContextCompat.getColor(getContext(), colour));



        return listItemView;//We only return one view, and that's listItem view which contains all the other views we want.
    }

}
