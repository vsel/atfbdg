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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class BombActivity extends Activity {
	private AnimationDrawable bombAnim;
	//Media Player
	MediaPlayer mpintro,mpoutro;
	// Gesture Detector
	private GestureDetector mGestureDetector;
	//TODO - don`t rerun in portrait mode. Block portrait mode.
	private void setupGestureDetector() {
	
	mGestureDetector = new GestureDetector(this,
	
		new GestureDetector.SimpleOnGestureListener() {
		
		// If a fling gesture starts on a BubbleView then change the
		// BubbleView's velocity
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			//if(event.ACTION_POINTER_UP(event.));
			Random r=new Random();
			int bombTimer=(r.nextInt(50)+10);
			mpintro.setLooping(true);
			mpintro.start();
			bombAnim.start();
			// TODO - Add start of tick-tack
			Timer timer = new Timer();
		    MyTimerTask myTimerTask = new MyTimerTask();
		    timer.schedule(myTimerTask, bombTimer*1000);
		    //log("test"+bombTimer*100);
		    return false;
		}
	});
	}
	public boolean onTouchEvent(MotionEvent event) {
		// TODO - delegate the touch to the gestureDetector 
		return mGestureDetector.onTouchEvent(event);
	}
	private void log(String msg) {
		Log.i("AAA", msg);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
		imageView.setBackgroundResource(R.drawable.view_animation);
		
		bombAnim = (AnimationDrawable) imageView.getBackground();
		
		mpintro = MediaPlayer.create(this, R.raw.tictak);
		mpoutro = MediaPlayer.create(this, R.raw.bah);
		setupGestureDetector();
		
	}
	protected void onPause() {
		// TODO - Release all SoundPool resources
		super.onPause();
		mpintro.reset();
		mpintro=null;
		mpoutro.reset();
		mpoutro=null;
	}
	public void onResume(Bundle savedInstanceState) {
		super.onResume();
		//setupGestureDetector();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		/*
		//Because of getting to gesture
		Random r=new Random();
		int bombTimer=(r.nextInt(50)+10);
		if (hasFocus) {
		//mpintro.setLooping(true);
		       mpintro.start();
		bombAnim.start();
		// TODO - Add start of tick-tack
		Timer timer = new Timer();
		   MyTimerTask myTimerTask = new MyTimerTask();
		   timer.schedule(myTimerTask, bombTimer*1000);
		   //log("test"+bombTimer*100);
		    * 
		    */
	}
	
	class MyTimerTask extends TimerTask {
	
	 @Override
	 public void run() {
		 bombAnim.stop();
		 mpintro.pause();
		 mpoutro.start();	
	 }	
	
	}
}