package com.mnjy.hackmit;

import android.app.Fragment;
import android.os.Bundle;
import android.test.UiThreadTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Created by mnjy on 9/19/2015.
 */
// used to display the post in the list
public class Post extends Fragment {
    public static final String MESSAGE = "MESSAGE";
    public static final String LOCATION = "LOCATION";
    public static final String AUTHOR = "AUTHOR";
    private String message;
    private String location; //TODO: set this
    private String author;
    private int upvotes = 0;
    private int downvotes = 0;
    private float score = 0f;
    protected Button upvoteButton;
    protected Button downvoteButton;

    public Post(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        message = savedInstanceState.getString(MESSAGE);
        location = savedInstanceState.getString(LOCATION);
        author = savedInstanceState.getString(AUTHOR);

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

    public static Post createPost(String message, String location, String author){
        Post post = new Post();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, message);
        bundle.putString(LOCATION, location);
        bundle.putString(AUTHOR, author);
        post.setArguments(bundle);
        return post;
    }

    // TODO: make sure this only happens on UI thread!
    private int updateScore(){
        int score = upvotes - downvotes; //TODO: sth more fancy
        //TODO: set upvotes, downvotes, score on UI
        return score;
    }

    public ParseObject getParseObject(){
        ParseObject p = new ParseObject("Post");
        p.put("message", message);
        p.put("location", location);
        p.put("score", 0);
        p.put("post", this);
        return p;
    }
}
