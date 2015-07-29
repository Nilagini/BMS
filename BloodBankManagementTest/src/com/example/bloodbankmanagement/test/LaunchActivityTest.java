package com.example.bloodbankmanagement.test;

import org.junit.Test;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bloodbankmanagement.FourthActivity;
import com.example.bloodbankmanagement.LoginActivity;
import com.example.bloodbankmanagement.R;
import com.example.bloodbankmanagement.ThirdActivity;
public class LaunchActivityTest extends ActivityUnitTestCase<FourthActivity> {
	public LaunchActivityTest() {
	    super(FourthActivity.class);
	}
public LaunchActivityTest(Class<FourthActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

public void testOpenNextActivity() {
	  // register next activity that need to be monitored.
	  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ThirdActivity.class.getName(), null, false);

	  // open current activity.
	  FourthActivity myActivity = getActivity();
	  final Button button = (Button) myActivity.findViewById(R.id.signup);
	  myActivity.runOnUiThread(new Runnable() {
	    @Override
	    public void run() {
	      // click button and open next activity.
	      button.performClick();
	    }
	  });

	  ThirdActivity nextActivity = (ThirdActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
	  // next activity is opened and captured.
	  assertNotNull(nextActivity);
	  nextActivity .finish();
	}
}
/*public class LaunchActivityTest extends ActivityUnitTestCase<ThirdActivity> {
	Intent mLaunchIntent;
	public LaunchActivityTest(Class<ThirdActivity> activityClass) {
		super(activityClass);
		
		// TODO Auto-generated constructor stub
	}
	@Override
    protected void setUp() throws Exception {
        super.setUp();
         mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(),ThirdActivity.class);
        startActivity(mLaunchIntent, null, null);
        final Button launchNextButton =
                (Button) getActivity().findViewById(R.id.button1);
    }
	
	@Test
	public void testNextActivityWasLaunchedWithIntent() {
	    startActivity(mLaunchIntent, null, null);
	    final Button launchNextButton =
	            (Button) getActivity()
	            .findViewById(R.id.button1);
	    launchNextButton.performClick();

	    final Intent launchIntent = getStartedActivityIntent();
	    assertNotNull("Intent was null", launchIntent);
	    assertTrue(isFinishCalled());

	    final String payload =
	            launchIntent.getStringExtra(ThirdActivity.EXTRAS_PAYLOAD_KEY);
	    assertEquals("Payload is empty", ThirdActivity.STRING_PAYLOAD, payload);
	}
	
	
}*/

















