package com.example.android.miwok;

import android.media.Image;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default transaltion and a Miwok translation for that word.
 */

public class Word {
    private String defaultTranslation;
    private String miwokTranslation;

    private int audioResourceID = NO_RESOURCE_PROVIDED;
    private int imageResourceID = NO_RESOURCE_PROVIDED;
    private static final int NO_RESOURCE_PROVIDED = -1;


    public Word(String e, String m, int a, int i){
        defaultTranslation = e;
        miwokTranslation = m;
        imageResourceID = i;
        audioResourceID = a;
    }

    public Word(String e, String m, int a){
        defaultTranslation = e;
        miwokTranslation = m;
        audioResourceID = a;
    }

    public Word(String e, String m){
        defaultTranslation = e;
        miwokTranslation = m;
    }

    public String getDefaultTranslation(){
        return defaultTranslation;
    }
    public String getMiwokTranslation(){
        return miwokTranslation;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }
    public int getAudioResourceID(){
        return audioResourceID;
    }

    public boolean hasImage(){
        return imageResourceID != NO_RESOURCE_PROVIDED;
    }
    public boolean hasAudio(){
        return audioResourceID != NO_RESOURCE_PROVIDED;
    }
}
