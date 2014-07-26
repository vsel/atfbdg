/**
 * @author vsel
 *
 */
package vsel.projects.androidTimerForBomb;

import com.vsel.atfbdg.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class StartActivity extends Activity {
	private GestureDetector mGestureDetector;
	private void setupGestureDetector() {

	mGestureDetector = new GestureDetector(this,

		new GestureDetector.SimpleOnGestureListener() {

		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			intentStarter();
		    return true;
		}
		@Override
		public boolean onDoubleTap(MotionEvent event) {
			developerStarter();
		    return true;
		}
	});
	}

	public void intentStarter(){
		Intent intent = new Intent(this,BombActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);      
        finish();
	}

	public void developerStarter(){
		Intent intent = new Intent(this,Developers.class);
        startActivity(intent);      
        finish();
	}

	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startlayout);

}
	@Override
	protected void onStart() {
		super.onStart();
		setupGestureDetector();
	}
}