package com.assem.ibrahem.adtask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;

public class DetailsScreen extends AppCompatActivity implements View.OnClickListener {

    TextView Title;
    ImageView adPicture;
    Button ButtonShow;
    ProgressBar LoadingImage;

    String title,urlpicture,urlad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        setViews();
        title=getIntent().getStringExtra("MyTitle");
        urlpicture=getIntent().getStringExtra("MyPicture");
        urlad=getIntent().getStringExtra("MyUrl");
        setData(title,urlpicture,urlad);

        ButtonShow.setOnClickListener(this);
    }


    public void setViews()
    {
        Title = findViewById(R.id.title);
        adPicture = findViewById(R.id.imageAd);
        ButtonShow =  findViewById(R.id.buttonAd);
        LoadingImage = findViewById(R.id.loadingBar);



    }


    /**
     *
     * this is when show ad pressed to redirect the user to the ad's URL
     *
     */
    @Override
    public void onClick(View view) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlad));
        startActivity(browserIntent);
    }


    /**
     *
     * @param title title for the ad
     * @param urlImage the url will be used to load the image
     * @param urlAd the url that will be redirected to when the button pressed
     */
    public void setData(String title, String urlImage,String urlAd ){

        Title.setText(title);



        ///Async function to load the image from URL
        new DownloadImageTask((ImageView) findViewById(R.id.imageAd))
                .execute(urlImage);


    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            LoadingImage.setVisibility(View.INVISIBLE);
        }
    }
}
