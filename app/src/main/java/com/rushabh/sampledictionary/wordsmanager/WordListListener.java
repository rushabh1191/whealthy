package com.rushabh.sampledictionary.wordsmanager;

import io.realm.RealmResults;

/**
 * Created by rushabh on 22/04/16.
 */
public interface WordListListener {
    void wordListFetched(RealmResults<WordInformation> wordList);
}
