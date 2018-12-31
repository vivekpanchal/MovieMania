package com.panchal.vivek.moviemania.utils;

import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.animation.DecelerateInterpolator;

public class MiscUtils {

    public static Transition enterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(500000);

        return bounds;
    }

    public static Transition returnTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setInterpolator(new DecelerateInterpolator());
        bounds.setDuration(500000);

        return bounds;
    }
}
