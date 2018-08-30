package com.example.feixian;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MainActivity extends Activity {
	private int zmkuang;
	int zmGao;
	float qiu_size = 16;// ��ô�С
	float qiu_sudu = 3;// ����ٶ�
	float qiu_shang = 90;// ��������֡��
	float qiuX;
	float qiuY;
	// ���ӵñ���
	float zhu_gao;
	float zhu_gao_2;
	float zhu_kuan = 40;
	float zhu_kuan_2 = 40;
	float zhuX;
	float zhuY;
	float zhuX_2;
	float zhuY_2;
	float zhu_sudu;
	int num ;
	boolean jieshu = false;// �ж���Ϸ�Ƿ����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ʹ��Ϸȫ������
		getWindow().setFlags(	
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// ����Ϸ����
		// ��ȡ���ڹ�����
		mygameView = new MygameView(this);
		setContentView(mygameView);
		// ��ȡ���ڵ�һ��������
		WindowManager manager = getWindowManager();
		Display display = manager.getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);
		// ��ȡһ����Ļ�Ŀ�͸�
		zmkuang = displayMetrics.widthPixels;
		zmGao = displayMetrics.heightPixels;
		play();
	
	}
	
	// дһ����Ϸ��ʼ�ķ���
	public void play() {
		jieshu = false;
		// �����������ӵĳ�ʼλ��
		zhuX = zmkuang - zhu_kuan;
		zhuY = 0;
		zhuX_2 = zmkuang - zhu_kuan;
		zhuY_2 = zmGao;
		zhu_gao = zmGao / 2 - 200;
		zhu_gao_2 = zmGao - zhu_gao - 200;
		zhu_sudu = 5;
		View.OnTouchListener shoushi=new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch(arg1.getAction()){
				case MotionEvent.ACTION_DOWN:
				//��ָ���
					qiuY=qiuY-qiu_shang;
					handler.sendEmptyMessage(0*123);
					
					break;
				}
				return true;
			}
		};
		//дһ������ʶ��
		mygameView.setOnTouchListener(shoushi);
		qiu_sudu = 3.5f;
		qiu_shang = 90;
		num = 0;
		// ��ĳ�ʼλ��
		qiuX = 50;
		qiuY = zmGao / 2;
		handler.sendEmptyMessage(0*123);
		// дһ����ʱ��
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

		

			@Override
			public void run() {
				// ����С������ӵ�����
				qiuY = qiuY + qiu_sudu;
				zhuX = zhuX - zhu_sudu;
				zhuX_2 = zhuX_2 - zhu_sudu;
				//�������������Ļ��Ե
				if(zhuX<=0){
					zhuX = zmkuang - zhu_kuan;
					zhuX_2 = zmkuang - zhu_kuan;
				}
				//�ж�С���Ƿ�������Ļ��Ե
				if(qiuY>=zmGao){
					jieshu=true;
				}
				if(qiuX>=zhuX){
					if(qiuY<zhu_gao||qiuY>zhu_gao_2 ){
						jieshu=true;
						
					}
					//������ӵĸ�
					Random random=new Random();
					int height = random.nextInt(250)+100;
					// �����򴩹��������Ӻ�λ��
					zhuX = zmkuang - zhu_kuan;
					zhuY = 0;
					zhuX_2 = zmkuang - zhu_kuan;
					zhuY_2 = zmGao;
					zhu_gao = zmGao / 2 - height;
					zhu_gao_2 = zmGao - zhu_gao - height;
					zhu_sudu = 5;
					if(jieshu==false){
						//дһ��������ʱ�����ķ���	
						muck();
						num++;
						
					}
					
				}
				if(jieshu==false){
				handler.sendEmptyMessage(0*123);
				}
			}
		}, 0, 15);
	}
	// handler��Ϣ����
	Handler handler = new Handler() {
	

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0*123) {
				mygameView.invalidate();
			}
		}
	};
	private MygameView mygameView;
	private MediaPlayer mp3;

	// дһ������
	class MygameView extends View {
		// дһ�����ʵĶ���
		Paint paint = new Paint();
		

		public MygameView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			//���뱳��ͼƬ
			canvas.drawRGB(0,0,0);
			// ���ʵ�Ĭ����ʽ
			paint.setStyle(Paint.Style.FILL);
			// ���ÿ��ܳ�
			paint.setAntiAlias(true);
			if (jieshu) {
				// ִ����Ϸ������Ч��
			
				Intent intent=new Intent(MainActivity.this, TwoActivity.class);	
				intent.putExtra("sum", String.valueOf(num));
				startActivity(intent);
				finish();
			} else {
				// ִ����Ϸ����ʱ��Ч��
				// ���������ɫ
				paint.setColor(Color.rgb(251, 178, 23));
				paint.setTextSize(60);
				// ���û��ʵĴ�С
				// ��һ��Բ��С��
				canvas.drawCircle(qiuX, qiuY, qiu_size, paint);
				paint.setColor(Color.rgb(244, 96, 108));
				paint.setTextSize(60);
				// ��ʼд��
				canvas.drawText(num+"", zmkuang / 2 - 10, 60, paint);
				
				// ����������
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(zhuX, zhuY, zhuX + zhu_kuan, zhuY + zhu_gao,
						paint);
				// ����������
				canvas.drawRect(zhuX_2, zhuY_2 - zhu_gao_2,
						zhuX_2 + zhu_kuan_2, zhuY_2 + zhu_gao_2, paint);

			}
		}

	}
	//дһ����������
	public void muck(){
		MediaPlayer mediaplayer;
		mediaplayer=new MediaPlayer();
		mediaplayer.setLooping(true);
		 mp3 = MediaPlayer.create(this, R.drawable.music);
	        try {
				mp3.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        mp3.start();
	        
	        mp3.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	}

}
