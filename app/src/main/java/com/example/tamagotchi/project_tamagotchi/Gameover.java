package com.example.tamagotchi.project_tamagotchi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Gameover extends AppCompatActivity {

    ImageView ghost;
    ImageView tombstone;
    TextView finshText;
    Button share;
    Button mainMenu;

    AnimationDrawable ghostAnimation;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    View rootView;


    int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_gameover);
         rootView = getWindow().getDecorView().findViewById(android.R.id.content);


        //initiate the objects
        finshText = findViewById(R.id.finishText);
        ghost = (ImageView) findViewById(R.id.ghost_id);
        tombstone = (ImageView) findViewById(R.id.tombstone_id);
        mainMenu = (Button) findViewById(R.id.mainmenuButton);
        share = (Button) findViewById(R.id.shareButton);

        //init facebook
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);




        //Start the ghost animation
        ghost.setBackgroundResource(R.drawable.ghost);
        ghostAnimation = (AnimationDrawable) ghost.getBackground();
        ghostAnimation.start();


        //set the text for our finishing text
        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        level = sharedPref.getInt("level", 0);

        finshText.setText("Your pet has died of neglect. \n You reached level :  " + level + " ! \n congrats");




    }


    public void goToMainMenu (View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void shareOnFb (View view) {

        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(getScreenShot(view))
                .build();

        if (ShareDialog.canShow(SharePhotoContent.class)) {
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(sharePhoto)
                    .build();
            shareDialog.show(content);

        }
    }


    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

}
