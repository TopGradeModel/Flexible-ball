package com.example.feixian;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class VedioMainActivity extends Activity {

	private VideoView video_log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vedio_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		video_log = (VideoView) findViewById(R.id.video_log);
		//设置路径
		Uri uri=Uri.parse("android.resource://com.example.feixian/"+R.raw.video_log);
		video_log.setVideoURI(uri);
		video_log.requestFocus();
		video_log.start();
		//播完自动进入主页面
		video_log.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
			Intent intent=new Intent(VedioMainActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
				
			}
		});
		
		
	}

}
