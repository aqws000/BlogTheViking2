package com.example.hayden.blogtest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.hayden.blogtest.R.id.button2;


public class MainActivity extends AppCompatActivity {


    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         mAuth = FirebaseAuth.getInstance();
         mAuthListener = new FirebaseAuth.AuthStateListener() {
             @Override
             public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 //Firebase Auth returns the result if we are logged in or not

                 if(firebaseAuth.getCurrentUser() == null){
                     Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                     loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(loginIntent);

             }
             }
         };



        Button Button = (Button)findViewById(button2);

        Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.salemstate.edu/calendar"));
                startActivity(intent);
            }
        });


        mBlogList = findViewById(R.id.blog_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(mLayoutManager);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");


       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
          //      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
           //             .setAction("Action", null).show();




    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener); //Listening for login

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
            Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {

            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
               final String post_key = getRef(position).getKey().toString();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());


            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);

           mView = itemView;
        }

        public void setTitle(String title) {

            TextView post_title = mView.findViewById(R.id.post_title);
           post_title.setText(title);

        }


        public void setDesc(String desc){

            TextView post_desc = mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);

        }


public void setImage(Context ctx, String image){
    ImageView post_image = mView.findViewById(R.id.post_image);
    Picasso.with(ctx).load(image).into(post_image);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(MainActivity.this, PostActivity.class));
            //noinspection SimplifiableIfStatement
            //  if (id == R.id.action_settings) {
            //      return true;
        }



        return super.onOptionsItemSelected(item);

    }

}



