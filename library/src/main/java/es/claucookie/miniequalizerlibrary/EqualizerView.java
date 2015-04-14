package es.claucookie.miniequalizerlibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by claucookie on 01/02/15.
 * Modified by vsmaks on 15/04/15.
 */
public class EqualizerView extends LinearLayout {

    View musicBar1;
    View musicBar2;
    View musicBar3;

    boolean animating = false;
    int foregroundColor;
    int duration;

    // flag to prevent  infinite recursion when animateBars() or stopBars() called from a listener
    private Boolean flag = false;
    private OnAnimateBarsListener mOnAnimateBarsListener;

    public EqualizerView(Context context) {
        super(context);
        initViews();
    }

    public EqualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public EqualizerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAttrs(context, attrs);
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EqualizerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
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

        setForegroundColor(foregroundColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            musicBar1.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutListener(musicBar1));
            musicBar2.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutListener(musicBar2));
            musicBar3.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutListener(musicBar3));
            setOnAnimateBarsListener(new DefaultAnimateBarsListener());
        } else {
            musicBar1.getViewTreeObserver().addOnGlobalLayoutListener(new SupportGlobalLayoutListener(musicBar1));
            musicBar2.getViewTreeObserver().addOnGlobalLayoutListener(new SupportGlobalLayoutListener(musicBar2));
            musicBar3.getViewTreeObserver().addOnGlobalLayoutListener(new SupportGlobalLayoutListener(musicBar3));
            setOnAnimateBarsListener(new SupportAnimateBarsListener());
        }
    }

    public void animateBars() {
        if (!animating ) {
            if (flag) {
                return;
            }
            flag = true;
            if (mOnAnimateBarsListener != null) {
                mOnAnimateBarsListener.onAnimateBars(this);
            }
            flag = false;
        }
        animating = true;
    }

    public void stopBars() {
        if (animating ) {
            if (flag) {
                return;
            }
            flag = true;
            if (mOnAnimateBarsListener != null) {
                mOnAnimateBarsListener.onStopBars(this);
            }
            flag = false;
        }
        animating = false;
    }

    public boolean isAnimating() {
        return animating;
    }

    public void setOnAnimateBarsListener(OnAnimateBarsListener listener) {
        mOnAnimateBarsListener = listener;
    }

    public void setForegroundColor(int color) {
        foregroundColor = color;
        musicBar1.setBackgroundColor(foregroundColor);
        musicBar2.setBackgroundColor(foregroundColor);
        musicBar3.setBackgroundColor(foregroundColor);
    }

    public int getForegroundColor() {
        return foregroundColor;
    }

    public void setDuration(int milliseconds) {
        duration = milliseconds;
    }

    public int getDuration() {
        return duration;
    }

    public View[] getBars() {
        return new View[]{musicBar1, musicBar2, musicBar3};
    }


    public interface OnAnimateBarsListener {
        void onAnimateBars(EqualizerView view);
        void onStopBars(EqualizerView view);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private View view;

        public GlobalLayoutListener(View view) {
            this.view = view;
        }

        @Override
        public void onGlobalLayout() {
            if (view.getHeight() > 0) {
                view.setPivotY(view.getHeight());
                if (Build.VERSION.SDK_INT >= 16) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        }
    }


    public static class SupportGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private View view;

        public SupportGlobalLayoutListener(View view) {
            this.view = view;
        }

        @Override
        public void onGlobalLayout() {
            if (view.getHeight() > 0) {
                ViewHelper.setPivotY(view, view.getHeight());
            }
        }
    }
}
