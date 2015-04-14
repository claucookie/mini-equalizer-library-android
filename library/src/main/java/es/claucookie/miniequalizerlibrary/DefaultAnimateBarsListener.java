package es.claucookie.miniequalizerlibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.animation.LinearInterpolator;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class DefaultAnimateBarsListener implements EqualizerView.OnAnimateBarsListener {
    private AnimatorSet playingSet;
    private AnimatorSet stopSet;

    @Override
    public void onAnimateBars(EqualizerView view) {
        if (playingSet == null) {
            ObjectAnimator scaleYbar1 = ObjectAnimator.ofFloat(view.musicBar1, "scaleY", 0.2f, 0.8f, 0.1f, 0.1f, 0.3f, 0.1f, 0.2f, 0.8f, 0.7f, 0.2f, 0.4f, 0.9f, 0.7f, 0.6f, 0.1f, 0.3f, 0.1f, 0.4f, 0.1f, 0.8f, 0.7f, 0.9f, 0.5f, 0.6f, 0.3f, 0.1f);
            scaleYbar1.setRepeatCount(ValueAnimator.INFINITE);

            ObjectAnimator scaleYbar2 = ObjectAnimator.ofFloat(view.musicBar2, "scaleY", 0.2f, 0.5f, 1.0f, 0.5f, 0.3f, 0.1f, 0.2f, 0.3f, 0.5f, 0.1f, 0.6f, 0.5f, 0.3f, 0.7f, 0.8f, 0.9f, 0.3f, 0.1f, 0.5f, 0.3f, 0.6f, 1.0f, 0.6f, 0.7f, 0.4f, 0.1f);
            scaleYbar2.setRepeatCount(ValueAnimator.INFINITE);

            ObjectAnimator scaleYbar3 = ObjectAnimator.ofFloat(view.musicBar3, "scaleY", 0.6f, 0.5f, 1.0f, 0.6f, 0.5f, 1.0f, 0.6f, 0.5f, 1.0f, 0.5f, 0.6f, 0.7f, 0.2f, 0.3f, 0.1f, 0.5f, 0.4f, 0.6f, 0.7f, 0.1f, 0.4f, 0.3f, 0.1f, 0.4f, 0.3f, 0.7f);
            scaleYbar3.setRepeatCount(ValueAnimator.INFINITE);

            playingSet = new AnimatorSet();
            playingSet.playTogether(scaleYbar2, scaleYbar3, scaleYbar1);
            playingSet.setDuration(view.duration);
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

    @Override
    public void onStopBars(EqualizerView view) {
        if (playingSet != null && playingSet.isRunning() && playingSet.isStarted()) {
            if (Build.VERSION.SDK_INT < 19) {
                playingSet.end();
            } else {
                playingSet.pause();
            }
        }

        if (stopSet == null) {
            // Animate stopping bars
            ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(view.musicBar1, "scaleY", 0.1f);
            ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(view.musicBar2, "scaleY", 0.1f);
            ObjectAnimator scaleY3 = ObjectAnimator.ofFloat(view.musicBar3, "scaleY", 0.1f);
            stopSet = new AnimatorSet();
            stopSet.playTogether(scaleY3, scaleY2, scaleY1);
            stopSet.setDuration(200);
            stopSet.start();
        } else if (!stopSet.isStarted()) {
            stopSet.start();
        }
    }
}
