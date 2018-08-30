package com.example.feixian;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;

public class StartMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_main);
		//…Ë÷√”Œœ∑»´∆¡
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				Intent intent=new Intent(StartMainActivity.this, VedioMainActivity.class);
				startActivity(intent);
				finish();
			}
		};
		Timer timer=new Timer();
		timer.schedule(task, 3000);	
	}



}
