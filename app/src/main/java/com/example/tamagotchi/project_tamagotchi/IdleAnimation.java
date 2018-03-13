package com.example.tamagotchi.project_tamagotchi;


import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class IdleAnimation extends Fragment {

    AnimationDrawable pigEating;
    AnimationDrawable pig;


    public IdleAnimation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idle_animation, container, false);

        ImageView animation = (ImageView) view.findViewById(R.id.idleAnimation);

        animation.setBackgroundResource(R.drawable.pig);
        pig = (AnimationDrawable) animation.getBackground();
        pig.start();


        return  view;
    }

}
