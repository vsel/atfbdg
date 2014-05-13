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
import android.os.Handler;
import android.os.Message;
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
	
	private static final int DISPLAY_DATA = 1;
    // this handler will receive a delayed message
    private Handler mHandler = new Handler() {
        @Override
		public void handleMessage(Message msg) {
            // Do task here
            if (msg.what == DISPLAY_DATA){
            	ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
	        	imageView.setBackgroundResource(R.drawable.view_animation2);
	        	bombAnim = (AnimationDrawable) imageView.getBackground();
	        	//bombAnim.setVisible(false, false);
	                //bombAnim.stop();
	    		mpintro.pause();
	    		mpoutro.start();	
            }
        }
	
    };
        
	private void setupGestureDetector() {
	
	mGestureDetector = new GestureDetector(this,
	
		new GestureDetector.SimpleOnGestureListener() {
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			//if(event.ACTION_POINTER_UP(event.));
			Random r=new Random();
			int bombTimer=(r.nextInt(50)+10);
			mpintro.setLooping(true);
			mpintro.start();
			ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
			imageView.setBackgroundResource(R.drawable.view_animation);
			bombAnim = (AnimationDrawable) imageView.getBackground();
			bombAnim.start();
			mHandler.sendEmptyMessageDelayed(DISPLAY_DATA, bombTimer*1000);
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
		mpintro.reset();
		mpintro=null;
		mpoutro.reset();
		mpoutro=null;
		super.onPause();
	}
	public void onResume(Bundle savedInstanceState) {
		super.onResume();
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
	
}
