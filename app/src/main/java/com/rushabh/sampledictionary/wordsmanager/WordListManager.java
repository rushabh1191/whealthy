package com.rushabh.sampledictionary.wordsmanager;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.rushabh.sampledictionary.helper.Logger;
import com.rushabh.sampledictionary.networkrequest.VolleyRequest;
import com.rushabh.sampledictionary.networkrequest.VolleyResponseListener;

import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by rushabh on 22/04/16.
 */
public class WordListManager implements VolleyResponseListener {

    WordListListener listListener;
    RealmConfiguration realmConfig;

    ProgressDialog dialog;
    Context context;
    public WordListManager(Context context, WordListListener listListener) {
        realmConfig = new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded()
                .build();
        this.context=context;
        this.listListener = listListener;
    }

    public void fetchWordFromList() {
        dialog=ProgressDialog.show(context,"","Fetching...");
        Logger.log("beta","Server request");
        new VolleyRequest(1, this);
    }

    public void saveWordList(final ArrayList<WordInformation> wordList) {
        Realm realm = Realm.getInstance(realmConfig);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealm(wordList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                getWordList();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
            }
        });


    }

    public void getWordList() {

        Realm realm = Realm.getInstance(realmConfig);



        RealmResults<WordInformation> listOfWords = realm.where(WordInformation.class).
                greaterThanOrEqualTo("ratio", 0.0).findAll();
        if (listOfWords.size() == 0) {
            fetchWordFromList();
        } else {
            if(dialog!=null){
                if(dialog.isShowing())
                    dialog.dismiss();
            }
            if (listListener != null) {
                listListener.wordListFetched(listOfWords);
            }

        }
    }

    @Override
    public void responseRecieved(JSONObject jsonObject, int requestId) {
        Gson gson = new Gson();

        WordListModel wordListModel = gson.fromJson(jsonObject.toString(), WordListModel.class);
        saveWordList(wordListModel.listOfWords);


    }

    @Override
    public void errorRecieved(VolleyError error, int requestId) {

    }
}

