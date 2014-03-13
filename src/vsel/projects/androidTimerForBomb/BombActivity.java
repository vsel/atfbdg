/**
 * 
 */
package vsel.projects.androidTimerForBomb;

/**
 * @author vsel
 *
 */
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.vsel.atfbdg.R;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class BombActivity extends Activity {
	private AnimationDrawable bombAnim;
	// Sound variables

	// AudioManager
	private AudioManager mAudioManager;
	// SoundPool
	private SoundPool mSoundPool;
	// ID for the bubble popping sound
	private int mSoundID;
	// Audio volume
	private float mStreamVolume;
	//TODO - Add gesture. Start on untap.
	
	private void log(String msg) {
		Log.i("AAA", msg);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
		
		imageView.setBackgroundResource(R.drawable.view_animation);

		bombAnim = (AnimationDrawable) imageView.getBackground();
		mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

		mStreamVolume = (float) mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				/ mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //new SoundPool, allowing up to 10 streams
        mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    mSoundID = mSoundPool.load(getApplicationContext(), R.raw.bah, 1);


	}
	protected void onPause() {
 		super.onPause();

	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Random r=new Random();
		int bombTimer=(r.nextInt(50)+10);
		if (hasFocus) {
			bombAnim.start();
			// TODO - Add start of tick-tack
			Timer timer = new Timer();
		    MyTimerTask myTimerTask = new MyTimerTask();
		    timer.schedule(myTimerTask, bombTimer*1000);
		    //log("test"+bombTimer*100);
		}
	}

	class MyTimerTask extends TimerTask {

		  @Override
		  public void run() {
			  bombAnim.stop();
			  //log("working");
			  // TODO - Add stop of tick-tack
		      mSoundPool.play(mSoundID, mStreamVolume, mStreamVolume, 100, 0, 1f);
		  }	
		  

	}
}
