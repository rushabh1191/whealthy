package com.rushabh.sampledictionary.wordsmanager;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by rushabh on 22/04/16.
 */
public class WordInformation extends RealmObject {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public String getImage(){
        return "http://appsculture.com/vocab/images/"+id+".png";
    }


    public void setWord(String word) {
        this.word = word;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @SerializedName("id")
    public int id;
    @SerializedName("word")
    public  String word;
    @SerializedName("variant")
    public  int variant;
    @SerializedName("meaning")
    public String meaning;
    @SerializedName("ratio")
    public double ratio;

    public String imageUrl;
}
