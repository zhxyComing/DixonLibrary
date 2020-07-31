package com.dixon.dlibrary.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.15
 * Functional desc: 动画工具类
 */
public class AnimationUtil {

    public static Animator height(final View view, float start, float end) {

        return height(view, start, end, 300, null, null);
    }

    public static Animator height(final View view, float start, float end, long time, TimeInterpolator interpolator, AnimatorListenerAdapter adapter) {

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float h = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) h;
                view.setLayoutParams(layoutParams);
            }
        });
        if (adapter != null) {
            animator.addListener(adapter);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.setDuration(time);
        return animator;
    }

    public static Animator alpha(final View view, float start, float end) {

        return alpha(view, start, end, 300, null, null);
    }

    public static Animator alpha(final View view, float start, float end, long time, TimeInterpolator interpolator, AnimatorListenerAdapter adapter) {

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                view.setAlpha(alpha);
            }
        });
        if (adapter != null) {
            animator.addListener(adapter);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.setDuration(time);
        return animator;
    }

    public static Animator tranX(final View view, float start, float end) {

        return tranX(view, start, end, 300, null, null);
    }

    public static Animator tranY(final View view, float start, float end, long time, TimeInterpolator interpolator, AnimatorListenerAdapter adapter) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", start, end);
        if (adapter != null) {
            animator.addListener(adapter);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.setDuration(time);
        return animator;
    }

    public static Animator tranY(final View view, float start, float end) {

        return tranY(view, start, end, 300, null, null);
    }

    public static Animator tranX(final View view, float start, float end, long time, TimeInterpolator interpolator, AnimatorListenerAdapter adapter) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", start, end);
        if (adapter != null) {
            animator.addListener(adapter);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.setDuration(time);
        return animator;
    }


    public static Animator rotate(final View view, float start, float end, long time, TimeInterpolator interpolator, AnimatorListenerAdapter adapter) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", start, end);
        if (adapter != null) {
            animator.addListener(adapter);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.setDuration(time);
        return animator;
    }

    //不使用AnimatorSet 顺序执行动画
    public static class Chain {

        private int index = 0;
        private List<Animator> animators = new ArrayList<>();

        public Chain addAnimator(Animator... animator) {
            animators.addAll(Arrays.asList(animator));
            return this;
        }

        public void reset() {
            index = 0;
        }

        public void start() {
            if (animators.size() > index) {//size!=下标
                Animator animator = animators.get(index++);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        start();
                    }
                });
                animator.start();
            }
        }
    }
}
