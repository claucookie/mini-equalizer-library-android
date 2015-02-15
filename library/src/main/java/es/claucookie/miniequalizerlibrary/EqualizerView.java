package es.claucookie.miniequalizerlibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

/**
 * Created by claucookie on 01/02/15.
 */
public class EqualizerView extends LinearLayout {

    View musicBar1;
    View musicBar2;
    View musicBar3;

    AnimatorSet playingSet;
    AnimatorSet stopSet;
    Boolean animating = false;

    int foregroundColor;
    int duration;


    public EqualizerView(Context context) {
        super(context);
        initViews();
    }

    public EqualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
        initViews();
    }

    public EqualizerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAttrs(context, attrs);
        initViews();
    }

    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EqualizerView,
                0, 0);

        try {
            foregroundColor = a.getInt(R.styleable.EqualizerView_foregroundColor, Color.BLACK);
            duration = a.getInt(R.styleable.EqualizerView_animDuration, 3000);

        } finally {
            a.recycle();
        }
    }

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_equalizer, this, true);
        musicBar1 = findViewById(R.id.music_bar1);
        musicBar2 = findViewById(R.id.music_bar2);
        musicBar3 = findViewById(R.id.music_bar3);
        musicBar1.setBackgroundColor(foregroundColor);
        musicBar2.setBackgroundColor(foregroundColor);
        musicBar3.setBackgroundColor(foregroundColor);
        setPivots();
    }


    private void setPivots() {
        musicBar1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (musicBar1.getHeight() > 0) {
                    musicBar1.setPivotY(musicBar1.getHeight());
                    if (Build.VERSION.SDK_INT >= 16) {
                        musicBar1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }

            }
        });
        musicBar2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (musicBar2.getHeight() > 0) {
                    musicBar2.setPivotY(musicBar2.getHeight());
                    if (Build.VERSION.SDK_INT >= 16) {
                        musicBar2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }

            }
        });
        musicBar3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (musicBar3.getHeight() > 0) {
                    musicBar3.setPivotY(musicBar3.getHeight());
                    if (Build.VERSION.SDK_INT >= 16) {
                        musicBar3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }

            }
        });
    }

    public void animateBars() {
        animating = true;
        if (playingSet == null) {
            ObjectAnimator scaleYbar1 = ObjectAnimator.ofFloat(musicBar1, "scaleY", 0.2f, 0.8f, 0.1f, 0.1f, 0.3f, 0.1f, 0.2f, 0.8f, 0.7f, 0.2f, 0.4f, 0.9f, 0.7f, 0.6f, 0.1f, 0.3f, 0.1f, 0.4f, 0.1f, 0.8f, 0.7f, 0.9f, 0.5f, 0.6f, 0.3f, 0.1f);
            scaleYbar1.setRepeatCount(ValueAnimator.INFINITE);
            ObjectAnimator scaleYbar2 = ObjectAnimator.ofFloat(musicBar2, "scaleY", 0.2f, 0.5f, 1.0f, 0.5f, 0.3f, 0.1f, 0.2f, 0.3f, 0.5f, 0.1f, 0.6f, 0.5f, 0.3f, 0.7f, 0.8f, 0.9f, 0.3f, 0.1f, 0.5f, 0.3f, 0.6f, 1.0f, 0.6f, 0.7f, 0.4f, 0.1f);
            scaleYbar2.setRepeatCount(ValueAnimator.INFINITE);
            ObjectAnimator scaleYbar3 = ObjectAnimator.ofFloat(musicBar3, "scaleY", 0.6f, 0.5f, 1.0f, 0.6f, 0.5f, 1.0f, 0.6f, 0.5f, 1.0f, 0.5f, 0.6f, 0.7f, 0.2f, 0.3f, 0.1f, 0.5f, 0.4f, 0.6f, 0.7f, 0.1f, 0.4f, 0.3f, 0.1f, 0.4f, 0.3f, 0.7f);
            scaleYbar3.setRepeatCount(ValueAnimator.INFINITE);

            playingSet = new AnimatorSet();
            playingSet.playTogether(scaleYbar2, scaleYbar3, scaleYbar1);
            playingSet.setDuration(duration);
            playingSet.setInterpolator(new LinearInterpolator());
            playingSet.start();

        } else if (Build.VERSION.SDK_INT < 19) {
            if (!playingSet.isStarted()) {
                playingSet.start();
            }
        } else {
            if (playingSet.isPaused()) {
                playingSet.resume();
            }
        }

    }

    public void stopBars() {
        animating = false;
        if (playingSet != null && playingSet.isRunning() && playingSet.isStarted()) {
            if (Build.VERSION.SDK_INT < 19) {
                playingSet.end();
            } else {
                playingSet.pause();
            }
        }

        if (stopSet == null) {
            // Animate stopping bars
            ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(musicBar1, "scaleY", 0.1f);
            ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(musicBar2, "scaleY", 0.1f);
            ObjectAnimator scaleY3 = ObjectAnimator.ofFloat(musicBar3, "scaleY", 0.1f);
            stopSet = new AnimatorSet();
            stopSet.playTogether(scaleY3, scaleY2, scaleY1);
            stopSet.setDuration(200);
            stopSet.start();
        } else if (!stopSet.isStarted()) {
            stopSet.start();
        }
    }

    public Boolean isAnimating() {
        return animating;
    }


}
