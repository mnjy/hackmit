package com.mnjy.hackmit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Created by mnjy on 9/19/2015.
 */
public class CreateFragment extends Fragment {
    protected Button submitButton;
    protected EditText messageText;

    public CreateFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_fragment, container, false);
        submitButton = (Button) view.findViewById(R.id.submitButton);
        messageText = (EditText) view.findViewById(R.id.messageText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageText.getText().toString();
                if (message == null | message.length() == 0) {
                    return;
                }
                makeNewPost(message);
                // Get out of here!

            }
        });
        return view;
    }

    public void makeNewPost(String message){
        Post post = Post.createPost(message, "LOCATION TODO", ParseUser.getCurrentUser().toString()); //TODO: better def of user, TODO: SET LOCATION!
        ParseObject p = post.getParseObject();

        // Put the post in the database
        p.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), "Post successful", Toast.LENGTH_SHORT).show();
                    try {
                        wait(1000);
                    } catch (InterruptedException ie){ }
                    ((MainActivity) getActivity()).popBackStack();

                } else {
                    Toast.makeText(getActivity(), "Post failed. Please check your connection and try again later.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
