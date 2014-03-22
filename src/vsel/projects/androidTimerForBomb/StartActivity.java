/**
 * 
 */
package vsel.projects.androidTimerForBomb;

import com.vsel.atfbdg.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author vsel
 *
 */
public class StartActivity extends Activity {
	private GestureDetector mGestureDetector;
	//TODO - don`t rerun in portrait mode. Block portrait mode.
	private void setupGestureDetector() {
	
	mGestureDetector = new GestureDetector(this,
	
		new GestureDetector.SimpleOnGestureListener() {
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			/*
			Intent intent = new Intent();
			//intent.setClassName("vsel.projects.androidTimerForBomb","BombActivity");
			intent.setClassName("vsel.projects.androidTimerForBomb","BombActivity");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);      
            finish();*/
			intentStarter();
		    return true;
		}
	});
	}
	public void intentStarter(){
		Intent intent = new Intent(this,BombActivity.class);
		//intent.setClassName("vsel.projects.androidTimerForBomb","BombActivity");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);      
        finish();
	}
	public boolean onTouchEvent(MotionEvent event) {
		// TODO - delegate the touch to the gestureDetector 
		return mGestureDetector.onTouchEvent(event);
	}
	
	private final String TAG = "StartActivity";
protected void onCreate(Bundle savedInstanceState) {
		
		// Restore any saved state 
		super.onCreate(savedInstanceState);
		
		// Set content view
		setContentView(R.layout.startlayout);

}
	@Override
	protected void onStart() {
		super.onStart();
		setupGestureDetector();
		Log.i(TAG, "onStart()");
	}

	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, "onRestart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG,
				"onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
	}
}
