/**
 * @author vsel
 *
 */

package vsel.projects.androidTimerForBomb;

import com.google.android.gms.ads.*;
import com.vsel.atfbdg.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Developers extends Activity {
	private AdView adView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.developers);
		
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
	  }

	  @Override
	  public void onPause() {
	    adView.pause();
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
