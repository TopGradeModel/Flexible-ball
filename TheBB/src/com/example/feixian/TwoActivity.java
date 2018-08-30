package com.example.feixian;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TwoActivity extends Activity {
	private String sum_value;
	private ArrayList<String> score;
	private int tab=1; 
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 使游戏全屏运行
				getWindow().setFlags(	
						WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
				//写一个集合
				score = new ArrayList<String>();
				//对集合中的元素进行排序
				Collections.sort(score);
		//获得控件
		TextView end_TextView = (TextView) findViewById(R.id.end_TextView);
		TextView sum_TextView = (TextView) findViewById(R.id.sum_TextView);
		Button start_btn1 = (Button) findViewById(R.id.start_btn1);
		Button upload_btn2 = (Button) findViewById(R.id.upload_btn2);
		Button check_btn3 = (Button) findViewById(R.id.check_btn3);
		sum_value = getIntent().getStringExtra("sum");
		end_TextView.setText("Game Over");
		sum_TextView.setText("你的成绩为:"+sum_value);
		//写重新开始的单击事件
		start_btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			Intent intent=new Intent(TwoActivity.this, MainActivity.class);
			startActivity(intent);	
			}
		});
		//写上传成绩的单击事件
		upload_btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(tab==1){
					score.add(sum_value);
					Toast.makeText(TwoActivity.this, "上传成功！", 1).show();
					tab++;
				}else{
					Toast.makeText(TwoActivity.this, "您已经上传！", 1).show();
				}
				
			}
		});
		//写查看榜单的单击事件
		check_btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StringBuffer buffer=new StringBuffer();
				for (int i = 0; i < score.size(); i++) {
					buffer.append(score.get(i)+"\n");
				}
				Toast.makeText(TwoActivity.this, "排行为:"+"\n"+buffer, 1).show();
			}
		});
		
	}

}
