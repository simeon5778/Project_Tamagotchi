package com.example.tamagotchi.project_tamagotchi;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class SlimeeatingAnimation extends Fragment {

    AnimationDrawable slimeEating;


    public SlimeeatingAnimation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slimeeating_animation, container, false);

        ImageView animation = (ImageView) view.findViewById(R.id.eatingAnimation_id);

        animation.setBackgroundResource(R.drawable.slimeeating);
        slimeEating = (AnimationDrawable) animation.getBackground();
        slimeEating.start();


        return  view;
    }
}