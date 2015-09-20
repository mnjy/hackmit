package com.mnjy.hackmit;

import android.app.Fragment;
import android.os.Bundle;
import android.test.UiThreadTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mnjy on 9/19/2015.
 */
public class Post extends Fragment {
    public static final String MESSAGE = "MESSAGE";
    private String message;
    private String location; //TODO: set this
    private int upvotes = 0;
    private int downvotes = 0;
    protected Button upvoteButton;
    protected Button downvoteButton;

    public Post(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        message = savedInstanceState.getString(MESSAGE);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.post, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: open detailed view (with comments etc.)
                //will this interfere with upvote/downvote?
            }
        });

        upvoteButton = (Button) view.findViewById(R.id.upvoteButton);
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upvotes++;
                updateScore();
            }
        });
        downvoteButton = (Button) view.findViewById(R.id.downvoteButton);
        downvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downvotes++;
                updateScore();
            }
        });
        return view;
    }

    // TODO: make sure this only happens on UI thread!
    private int updateScore(){
        int score = upvotes - downvotes; //TODO: sth more fancy
        //TODO: set upvotes, downvotes, score on UI
        return score;
    }
}
