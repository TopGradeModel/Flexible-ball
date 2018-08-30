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
		// ʹ��Ϸȫ������
				getWindow().setFlags(	
						WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
				//дһ������
				score = new ArrayList<String>();
				//�Լ����е�Ԫ�ؽ�������
				Collections.sort(score);
		//��ÿؼ�
		TextView end_TextView = (TextView) findViewById(R.id.end_TextView);
		TextView sum_TextView = (TextView) findViewById(R.id.sum_TextView);
		Button start_btn1 = (Button) findViewById(R.id.start_btn1);
		Button upload_btn2 = (Button) findViewById(R.id.upload_btn2);
		Button check_btn3 = (Button) findViewById(R.id.check_btn3);
		sum_value = getIntent().getStringExtra("sum");
		end_TextView.setText("Game Over");
		sum_TextView.setText("��ĳɼ�Ϊ:"+sum_value);
		//д���¿�ʼ�ĵ����¼�
		start_btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			Intent intent=new Intent(TwoActivity.this, MainActivity.class);
			startActivity(intent);	
			}
		});
		//д�ϴ��ɼ��ĵ����¼�
		upload_btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(tab==1){
					score.add(sum_value);
					Toast.makeText(TwoActivity.this, "�ϴ��ɹ���", 1).show();
					tab++;
				}else{
					Toast.makeText(TwoActivity.this, "���Ѿ��ϴ���", 1).show();
				}
				
			}
		});
		//д�鿴�񵥵ĵ����¼�
		check_btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StringBuffer buffer=new StringBuffer();
				for (int i = 0; i < score.size(); i++) {
					buffer.append(score.get(i)+"\n");
				}
				Toast.makeText(TwoActivity.this, "����Ϊ:"+"\n"+buffer, 1).show();
			}
		});
		
	}

}
