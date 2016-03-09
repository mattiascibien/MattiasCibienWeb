package net.mattiascibien.mattiascibienweb.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkmmte.pkrss.PkRSS;

import net.mattiascibien.mattiascibienweb.R;
import net.mattiascibien.mattiascibienweb.core.blog.BlogPost;
import net.mattiascibien.mattiascibienweb.core.blog.BlogPostAdapter;

import java.util.ArrayList;
import java.util.List;


public class BlogFragment extends Fragment {

    private  SwipeRefreshLayout swipeContainer;

     public BlogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                PkRSS.with(getContext()).load("https://www.mattiascibien.net/rss.xml").async();
                swipeContainer.setRefreshing(false);
            }
        });

        RecyclerView recList = (RecyclerView)view.findViewById(R.id.blogRecyclerView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        BlogPostAdapter ba = new BlogPostAdapter(createList(30));
        recList.setAdapter(ba);

        return view;
    }

    private List<BlogPost> createList(int size) {
        List<BlogPost> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {
            BlogPost ci = new BlogPost();
            ci.title = BlogPost.TITLE_PREFIX + i;
            ci.excerpt = BlogPost.EXCERPT_PREFIX + i;

            result.add(ci);
        }

        return result;
    }
}
