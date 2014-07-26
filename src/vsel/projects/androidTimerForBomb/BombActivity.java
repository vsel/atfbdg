/**
 * @author vsel
 *
 */

package vsel.projects.androidTimerForBomb;

import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.vsel.atfbdg.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BombActivity extends Activity {
	private AdView adView;
	
	private AnimationDrawable bombAnim;
	//Media Player
	MediaPlayer mpintro,mpoutro;
	// Gesture Detector
	private GestureDetector mGestureDetector;
	private static final int BOOM = 1;
    	// this handler will receive a delayed message
    	@SuppressLint("HandlerLeak")
		private Handler mHandler = new Handler() {
    		@Override
			public void handleMessage(Message msg) {
	            // Do task here
	            if (msg.what == BOOM){
	            	ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
		        	imageView.setBackgroundResource(R.drawable.view_not_animation);
		        	bombAnim = (AnimationDrawable) imageView.getBackground();
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
			if (!bombAnim.isRunning()){
				adView.loadAd(new AdRequest.Builder().build());
				Random r=new Random();
				int bombTimer=(r.nextInt(50)+10);
				
				mpintro.setLooping(true);
				mpintro.start();
				
				ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
				imageView.setBackgroundResource(R.drawable.view_animation);
				
				bombAnim = (AnimationDrawable) imageView.getBackground();
				bombAnim.start();
				
				mHandler.sendEmptyMessageDelayed(BOOM, bombTimer*1000);	
			}
		    
		  return false;
		}
		});
		}
		public boolean onTouchEvent(MotionEvent event) {
			return mGestureDetector.onTouchEvent(event);
		}
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			// Create an ad.
		    adView = new AdView(this);
		    adView.setAdSize(AdSize.BANNER);
		    adView.setAdUnitId("ca-app-pub-8434465505627354/3980880420");

		    // Add the AdView to the view hierarchy. The view will have no size
		    // until the ad is loaded.
		    LinearLayout layout = (LinearLayout) findViewById(R.id.frame);
		    layout.addView(adView);

		    // Create an ad request. Check logcat output for the hashed device ID to
		    // get test ads on a physical device.
		    AdRequest adRequest = new AdRequest.Builder()
		        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		        //.addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
		        .build();

		    // Start loading the ad in the background.
		    adView.loadAd(adRequest);
			
			ImageView imageView = (ImageView) findViewById(R.id.countdown_frame);
			imageView.setBackgroundResource(R.drawable.view_animation);
			
			bombAnim = (AnimationDrawable) imageView.getBackground();
			
			mpintro = MediaPlayer.create(this, R.raw.tictak);
			mpoutro = MediaPlayer.create(this, R.raw.bah);
			setupGestureDetector();
			
			//Start when create
			Random r=new Random();
			int bombTimer=(r.nextInt(50)+10);
			
			mpintro.setLooping(true);
			mpintro.start();

			bombAnim.start();
			
			mHandler.sendEmptyMessageDelayed(BOOM, bombTimer*1000);
		}
		
		@Override
		protected void onStop() {
			//Release all SoundPool resources
			super.onStop();
			if (mpintro != null){
				mpintro.release();
				mpintro=null;	
			}
			if (mpoutro != null){
				mpoutro.release();
				mpoutro=null;
			}
			bombAnim.stop();
			if (mHandler != null){
				mHandler.removeCallbacks(null);
				mHandler.removeMessages(BOOM);
				mHandler = null;	
			}
		}
		
		@Override
		protected void onRestart() {
			super.onRestart();
			Intent intent = new Intent(this,BombActivity.class);
	        startActivity(intent);      
	        finish();
		}
		
		 @Override
		  public void onPause() {
		    adView.pause();
			bombAnim.stop();
			//mHandler.removeMessages(BOOM);
		    super.onPause();
		  }

		  @Override
		  public void onResume() {
		    super.onResume();
		    adView.resume();
		  }

		  @Override
		  public void onDestroy() {
		    adView.destroy();
		    super.onDestroy();
		  }

}
