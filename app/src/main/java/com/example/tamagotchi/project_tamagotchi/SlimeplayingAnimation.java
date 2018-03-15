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
public class SlimeplayingAnimation extends Fragment {

    AnimationDrawable slimePlaying;


    public SlimeplayingAnimation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slimeplaying_animation, container, false);

        ImageView animation = (ImageView) view.findViewById(R.id.playingAnimation_id);

        animation.setBackgroundResource(R.drawable.slimeplaying);
        slimePlaying = (AnimationDrawable) animation.getBackground();
        slimePlaying.start();




        return view;
    }

}
