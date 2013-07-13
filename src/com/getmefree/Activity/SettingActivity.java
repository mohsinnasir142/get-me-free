 package com.getmefree.Activity;

 import android.R.color;
import android.app.Activity;
import android.app.IntentService;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingActivity extends Activity implements OnSeekBarChangeListener,OnClickListener {
	
	
	TabHost tab1host;
	TabSpec tspec1,tspec2,tspec3,tspec4;
	SeekBar volSB,brightSB,contrastSB;
	int brightness=101;
	int finalBirghtness;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		 		
		tab1host=(TabHost) findViewById(R.id.tabHost);
		tab1host.setup();
//
//	    android:textColor="@color/textBlack"
//	      android:textAppearance="?android:attr/textAppearanceMedium" 

		volSB=(SeekBar) findViewById(R.id.volumeSB);
		brightSB=(SeekBar) findViewById(R.id.brightnessSB);
		contrastSB=(SeekBar) findViewById(R.id.contrastSB);
		volSB.setOnSeekBarChangeListener(this);
		//for brightness sb action
		brightSB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				//finalBirghtness=  brightness-progress;
				
				setBrightnesss(progress);
			}
		});
		

    //for contrast SB action
		
		contrastSB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
			
				
			}
		});
    	
		
		
		tspec1=tab1host.newTabSpec("tab 1");
		tspec1.setContent(R.id.tab1);
		tspec1.setIndicator("Sound",getResources().getDrawable(R.drawable.sound));
		
		
		tspec2=tab1host.newTabSpec("tab 2");
		tspec2.setIndicator("Language", getResources().getDrawable(R.drawable.onebit));
		tspec2.setContent(R.id.tab2);

		tspec3=tab1host.newTabSpec("tab 3");
		tspec3.setIndicator("",getResources().getDrawable(R.drawable.brightness));
		tspec3.setContent(R.id.tab3);
		
		tspec4=tab1host.newTabSpec("tab 4");
		tspec4.setIndicator("",getResources().getDrawable(R.drawable.vibra));
		tspec4.setContent(R.id.tab4);
		
		tab1host.setBackgroundDrawable(getResources().getDrawable(R.drawable.menu));
		tab1host.setPadding(0, -10, 0, 0);

		tab1host.addTab(tspec1);
		tab1host.addTab(tspec2);
		tab1host.addTab(tspec3);
		tab1host.addTab(tspec4);
		
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	     Toast.makeText(SettingActivity.this, "Seekbar Value : " + progress, Toast.LENGTH_SHORT).show();
		
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		   Toast.makeText(SettingActivity.this, "Started Tracking Seekbar", Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	     volSB.setSecondaryProgress(seekBar.getProgress());
	        Toast.makeText(SettingActivity.this, "Stopped Tracking Seekbar", Toast.LENGTH_SHORT).show();
//	        Window MyWindow = getWindow();
//	        WindowManager.LayoutParams winParams = MyWindow.getAttributes();
//	        winParams.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF;
//	        MyWindow.setAttributes(winParams);
	}

	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    
	    if (on) {
	        // Enable vibrate
	    	
	    } else {
	        // Disable vibrate
	    }
	}
	
	

	public void showAds() {
		
	}
	public void  setBrightnesss(int value) {
		 WindowManager.LayoutParams lp = getWindow().getAttributes();

	        lp.screenBrightness = 100 / value;

	        getWindow().setAttributes(lp);
	}
	public boolean getVibration(boolean status) {
		return true;
	}
	public void  setVolume(int value) {
		
	}
	public void  setContrast(int value) {
		
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
}
