package net.mattiascibien.mattiascibienweb.core.blog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.mattiascibien.mattiascibienweb.R;

import java.util.List;

/**
 * Created by matti on 09/03/2016.
 */
public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.BlogPostViewHolder> {


    private List<BlogPost> posts;

    public BlogPostAdapter(List<BlogPost> posts) {

        this.posts = posts;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onBindViewHolder(BlogPostAdapter.BlogPostViewHolder holder, int position) {
        BlogPost post = posts.get(position);
        holder.vExcerpt.setText(post.excerpt);
        holder.vTitle.setText(post.title);
    }

    @Override
    public BlogPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card, parent, false);

        return new BlogPostViewHolder(itemView);
    }

    public static class BlogPostViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTitle;
        protected TextView vExcerpt;

        public BlogPostViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.title);
            vExcerpt = (TextView) v.findViewById(R.id.excerpt);
        }
    }
}
