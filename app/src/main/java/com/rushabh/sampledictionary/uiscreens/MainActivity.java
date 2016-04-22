package com.rushabh.sampledictionary.uiscreens;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rushabh.sampledictionary.R;
import com.rushabh.sampledictionary.wordsmanager.WordInformation;
import com.rushabh.sampledictionary.wordsmanager.WordListListener;
import com.rushabh.sampledictionary.wordsmanager.WordListManager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements WordListListener {

    WordListManager manager;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    WordListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        manager=new WordListManager(this,this);
        manager.getWordList();


    }

    @Override
    public void wordListFetched(RealmResults<WordInformation> wordList) {
        adapter=new WordListAdapter(wordList,this);
        recyclerView.setAdapter(adapter);
    }
}

class WordView extends RecyclerView.ViewHolder{

    @Bind(R.id.tv_word)
    TextView tvWord;

    @Bind(R.id.tv_meaning)
    TextView tvMeaning;

    @Bind(R.id.image)
    ImageView ivImage;
    public WordView(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
class WordListAdapter extends RecyclerView.Adapter<WordView>{

    RealmResults<WordInformation> wordList;

    LayoutInflater inflater;
    Context context;
    WordListAdapter(RealmResults<WordInformation> wordList, Context context){
        this.wordList=wordList;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context=context;
    }
    @Override
    public WordView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_word, parent, false);
        return new WordView(view);
    }

    @Override
    public void onBindViewHolder(WordView holder, int position) {
        WordInformation word=wordList.get(position);
        holder.tvWord.setText(word.getWord());
        holder.tvMeaning.setText(word.meaning);
        Picasso.with(context).load(word.getImage()).placeholder(R.drawable.ic_launcher).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        if(wordList ==null){
            return 0;
        }
        return  wordList.size();

    }
}