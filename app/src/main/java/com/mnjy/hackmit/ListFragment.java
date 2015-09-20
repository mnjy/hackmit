package com.mnjy.hackmit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mnjy on 9/19/2015.
 */
public class ListFragment extends Fragment {
    private ListView postsListView;

    public ListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        postsListView = (ListView) view.findViewById(R.id.postsList);
        // Populate list
        postsListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_fragment, getPostsInArea()));
        return view;
    }

    private ArrayList<Post> getPostsInArea(){
        //TODO!
        return test();
    }

    private ArrayList<Post> test(){ //for testing purposes only
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        return posts;
    }
}
