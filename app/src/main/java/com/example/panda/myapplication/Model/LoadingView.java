package com.example.panda.myapplication.Model;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.Tools.DecelerateAccelerateInterpolator;


/**
 * User: lizhangqu(513163535@qq.com)
 * Date: 2015-06-18
 * Time: 14:08
 * 58同城页面加载动画View
 */
public class LoadingView extends RelativeLayout {
    private static final int DEFAULT_VIEW_SIZE = 28;
    private static final int DURATION = 800;
    private static final int TOP_HEIGHT = 80;
    private static final int RATATION_HEIGHT = 20;
    private int mViewSize;
    private ImageView mCircleView;
    private ImageView mRectView;
    private ImageView mTriangleView;
    private ImageView mBottomView;

    private Context mContext;

    private AnimatorSet mAll;
    private AnimatorSet mCircleAnimator;
    private AnimatorSet mRectAnimator;
    private AnimatorSet mTriangleAnimator;

    private Animator.AnimatorListener mCircleListener;
    private Animator.AnimatorListener mRectListener;
    private Animator.AnimatorListener mTriangleListener;
    private boolean isAnimator = false;

    private final float[] TRANSLATIONY = {0f, -dip2px(TOP_HEIGHT), 0f};
    private final float[] SCALEX = {0.9f, 0.5f, 0.2f, 0.1f, 0.05f, 0.1f, 0.2f, 0.3f, 0.5f, 0.7f, 0.9f};
    private final float[] ROTATION_RECT = {0f, 200f};
    private final float[] ROTATION_TRIANGLE = {0f, -90f};

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initAnimatorListener();
        startAnimator();
    }


    private void initView() {
        mViewSize = dip2px(DEFAULT_VIEW_SIZE);
        setGravity(Gravity.CENTER);

        mCircleView = new ImageView(mContext);
        mCircleView.setId(R.id.top);
        mCircleView.setBackgroundResource(R.mipmap.loading_yuan);
        LayoutParams circleParams = new LayoutParams(mViewSize, mViewSize);
        circleParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        circleParams.topMargin = dip2px(TOP_HEIGHT + RATATION_HEIGHT);
        mCircleView.setLayoutParams(circleParams);
        addView(mCircleView);

        mRectView = new ImageView(mContext);
        mRectView.setPivotX(mViewSize / 2);
        mRectView.setPivotY(mViewSize / 2);
        mRectView.setBackgroundResource(R.mipmap.loading_fangxing);
        LayoutParams rectParams = new LayoutParams(mViewSize, mViewSize);
        rectParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        rectParams.topMargin = dip2px(TOP_HEIGHT + RATATION_HEIGHT);
        mRectView.setLayoutParams(rectParams);
        addView(mRectView);

        mTriangleView = new ImageView(mContext);
        mTriangleView.setPivotY(mViewSize / 2);
        mTriangleView.setPivotX(mViewSize / 2);
        mTriangleView.setBackgroundResource(R.mipmap.loading_sanjiao);
        LayoutParams triangleParams = new LayoutParams(mViewSize, mViewSize);
        triangleParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        triangleParams.topMargin = dip2px(TOP_HEIGHT + RATATION_HEIGHT);
        mTriangleView.setLayoutParams(triangleParams);
        addView(mTriangleView);

        mBottomView = new ImageView(mContext);

        mBottomView.setBackgroundResource(R.mipmap.loading_bottom);
        LayoutParams bottomParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        bottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        bottomParams.addRule(RelativeLayout.BELOW, R.id.top);
        mBottomView.setLayoutParams(bottomParams);
        addView(mBottomView);

        mRectView.setVisibility(View.INVISIBLE);
        mTriangleView.setVisibility(View.INVISIBLE);

    }

    private void initAnimatorListener() {
        mCircleListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mTriangleView.setVisibility(View.INVISIBLE);
                mCircleView.setVisibility(View.INVISIBLE);
                mRectView.setVisibility(View.VISIBLE);
            }
        };

        mRectListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCircleView.setVisibility(View.INVISIBLE);
                mTriangleView.setVisibility(View.VISIBLE);
                mRectView.setVisibility(View.INVISIBLE);
            }
        };
        mTriangleListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCircleView.setVisibility(View.VISIBLE);
                mRectView.setVisibility(View.INVISIBLE);
                mTriangleView.setVisibility(View.INVISIBLE);
                isAnimator = false;
                startAnimator();
            }
        };
    }

    public void startAnimator() {
        if (isAnimator) {
            return;
        }
        isAnimator = true;

        mCircleAnimator = new AnimatorSet();
        mCircleAnimator.setDuration(DURATION);
        mCircleAnimator.playTogether(getTranslationAnimator(mCircleView), getBottomViewAnimator());
        mCircleAnimator.addListener(mCircleListener);


        mRectAnimator = new AnimatorSet();
        mRectAnimator.setDuration(DURATION);
        mRectAnimator.setStartDelay(DURATION);
        mRectAnimator.playTogether(getTranslationAnimator(mRectView), getBottomViewAnimator(), getRotationAnimator(mRectView, ROTATION_RECT));
        mRectAnimator.addListener(mRectListener);

        mTriangleAnimator = new AnimatorSet();
        mTriangleAnimator.setDuration(DURATION);
        mTriangleAnimator.setStartDelay(DURATION * 2);
        mTriangleAnimator.playTogether(getTranslationAnimator(mTriangleView), getBottomViewAnimator(), getRotationAnimator(mTriangleView, ROTATION_TRIANGLE));
        mTriangleAnimator.addListener(mTriangleListener);

        mCircleAnimator.start();
        mRectAnimator.start();
        mTriangleAnimator.start();

    }

    public void stopAnimator() {
        if (mCircleAnimator != null) {
            mCircleAnimator.end();
            mCircleAnimator.removeAllListeners();
            mCircleAnimator = null;

        }
        if (mRectAnimator != null) {
            mRectAnimator.end();
            mRectAnimator.removeAllListeners();
            mRectAnimator = null;
        }
        if (mTriangleAnimator != null) {
            mTriangleAnimator.end();
            mTriangleAnimator.removeAllListeners();
            mTriangleAnimator = null;
        }
        isAnimator = false;

    }

    private Animator getBottomViewAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBottomView, "scaleX", SCALEX);
        objectAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        return objectAnimator;
    }

    private Animator getTranslationAnimator(Object object) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(object, "translationY", TRANSLATIONY);
        objectAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        return objectAnimator;
    }

    private Animator getRotationAnimator(Object object, float[] values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(object, "rotation", values);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return objectAnimator;
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            if (!isAnimator) {
                startAnimator();
            } else {
                stopAnimator();
            }
        }
    }


}
