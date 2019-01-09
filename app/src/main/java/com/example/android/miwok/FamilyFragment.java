package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {


    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager am;
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mp.pause();
                mp.seekTo(0); //If our sound gets cut off for a small amount, we would want to play the sound from the beginning, because our sound is small.
            }
            else if(i == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
            else if(i == AudioManager.AUDIOFOCUS_GAIN){
                mp.start(); //Start is the same as resume. You'll continue where you left off, if needed.
            }
        }
    };


    public FamilyFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Array of words

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("father", "әpә", R.raw.family_father, R.drawable.family_father));
        words.add(new Word("mother", "әṭa", R.raw.family_mother, R.drawable.family_mother));
        words.add(new Word("son", "angsi", R.raw.family_son, R.drawable.family_son));
        words.add(new Word("daughter", "tune", R.raw.family_daughter, R.drawable.family_daughter));
        words.add(new Word("older brother", "taachi", R.raw.family_older_brother, R.drawable.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.raw.family_younger_brother, R.drawable.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.raw.family_older_sister, R.drawable.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.raw.family_younger_sister, R.drawable.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.raw.family_grandmother, R.drawable.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.raw.family_grandfather, R.drawable.family_grandfather));


        Log.i("MyActivity", "Word at index 5: " + words.get(5));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mp = MediaPlayer.create(getActivity(), words.get(i).getAudioResourceID());
                    mp.start();
                    mp.setOnCompletionListener(completionListener);
                }
            }
        });

        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;

            am.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onStop() {
        releaseMediaPlayer();
        super.onStop();
    }

}
