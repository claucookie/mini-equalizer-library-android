package es.claucookie.miniequalizerlibrary;

import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

public class SupportAnimateBarsListener implements EqualizerView.OnAnimateBarsListener
{
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
        }
            if (!playingSet.isStarted()) {
                playingSet.start();
            }
    }

    @Override
    public void onStopBars(EqualizerView view) {
        if (playingSet != null && playingSet.isRunning() && playingSet.isStarted()) {
            playingSet.end();
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
