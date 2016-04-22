package com.rushabh.sampledictionary.wordsmanager;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rushabh on 22/04/16.
 */
public class WordListModel {

    @SerializedName("words")
    ArrayList<WordInformation> listOfWords;
}
