package com.getmefree.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LevelActivity extends Activity implements android.view.View.OnClickListener {

	ImageButton ib11,ib12,ib13,ib21,ib22,ib23,ib31,ib32,ib33,ib41,ib42,ib43,ibmm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		this.intialize();
		ib11.setOnClickListener(this);
		
	}
	
	public void intialize(){
		ib11=(ImageButton) findViewById(R.id.imageButton11);
		ib12=(ImageButton) findViewById(R.id.imageButton12);
		ib12=(ImageButton) findViewById(R.id.imageButton13);
		ib21=(ImageButton) findViewById(R.id.imageButton21);
		ib22=(ImageButton) findViewById(R.id.imageButton22);
		ib23=(ImageButton) findViewById(R.id.imageButton23);
		ib31=(ImageButton) findViewById(R.id.imageButton31);
		ib32=(ImageButton) findViewById(R.id.imageButton32);
		ib33=(ImageButton) findViewById(R.id.imageButton33);
		ib41=(ImageButton) findViewById(R.id.imageButton41);
		ib42=(ImageButton) findViewById(R.id.imageButton42);
		ib43=(ImageButton) findViewById(R.id.imageButton43);
		ibmm=(ImageButton) findViewById(R.id.imageButtonMM);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageButton11:
			
			
			break;

		}
		
	}
public void loadads(){}
}
