package com.mnjy.hackmit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                if (message == null | message.length() == 0){
                    return;
                }
                Post post = new Post();
                Bundle bundle = new Bundle();
                bundle.putString(Post.MESSAGE, message);
                post.setArguments(bundle);
                // TODO: Put the post in some database --> update others
            }
        });
        return view;
    }
}
