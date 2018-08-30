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
	float qiu_size = 16;// 球得大小
	float qiu_sudu = 3;// 球的速度
	float qiu_shang = 90;// 球上升的帧数
	float qiuX;
	float qiuY;
	// 柱子得变量
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
	boolean jieshu = false;// 判断游戏是否结束
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 使游戏全屏运行
		getWindow().setFlags(	
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 让游戏横屏
		// 获取窗口管理器
		mygameView = new MygameView(this);
		setContentView(mygameView);
		// 获取窗口的一个管理器
		WindowManager manager = getWindowManager();
		Display display = manager.getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);
		// 获取一个屏幕的宽和高
		zmkuang = displayMetrics.widthPixels;
		zmGao = displayMetrics.heightPixels;
		play();
	
	}
	
	// 写一个游戏开始的方法
	public void play() {
		jieshu = false;
		// 设置两根柱子的初始位置
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
				//手指点击
					qiuY=qiuY-qiu_shang;
					handler.sendEmptyMessage(0*123);
					
					break;
				}
				return true;
			}
		};
		//写一个手势识别
		mygameView.setOnTouchListener(shoushi);
		qiu_sudu = 3.5f;
		qiu_shang = 90;
		num = 0;
		// 球的初始位置
		qiuX = 50;
		qiuY = zmGao / 2;
		handler.sendEmptyMessage(0*123);
		// 写一个定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

		

			@Override
			public void run() {
				// 设置小球跟柱子的坐标
				qiuY = qiuY + qiu_sudu;
				zhuX = zhuX - zhu_sudu;
				zhuX_2 = zhuX_2 - zhu_sudu;
				//如果柱子碰到屏幕边缘
				if(zhuX<=0){
					zhuX = zmkuang - zhu_kuan;
					zhuX_2 = zmkuang - zhu_kuan;
				}
				//判断小球是否碰到屏幕边缘
				if(qiuY>=zmGao){
					jieshu=true;
				}
				if(qiuX>=zhuX){
					if(qiuY<zhu_gao||qiuY>zhu_gao_2 ){
						jieshu=true;
						
					}
					//随机柱子的高
					Random random=new Random();
					int height = random.nextInt(250)+100;
					// 设置球穿过两根柱子后位置
					zhuX = zmkuang - zhu_kuan;
					zhuY = 0;
					zhuX_2 = zmkuang - zhu_kuan;
					zhuY_2 = zmGao;
					zhu_gao = zmGao / 2 - height;
					zhu_gao_2 = zmGao - zhu_gao - height;
					zhu_sudu = 5;
					if(jieshu==false){
						//写一个过柱子时声音的方法	
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
	// handler消息机制
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

	// 写一个画板
	class MygameView extends View {
		// 写一个画笔的对象
		Paint paint = new Paint();
		

		public MygameView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			//插入背景图片
			canvas.drawRGB(0,0,0);
			// 画笔的默认样式
			paint.setStyle(Paint.Style.FILL);
			// 设置抗拒齿
			paint.setAntiAlias(true);
			if (jieshu) {
				// 执行游戏结束得效果
			
				Intent intent=new Intent(MainActivity.this, TwoActivity.class);	
				intent.putExtra("sum", String.valueOf(num));
				startActivity(intent);
				finish();
			} else {
				// 执行游戏进行时的效果
				// 设置球的颜色
				paint.setColor(Color.rgb(251, 178, 23));
				paint.setTextSize(60);
				// 设置画笔的大小
				// 画一个圆的小球
				canvas.drawCircle(qiuX, qiuY, qiu_size, paint);
				paint.setColor(Color.rgb(244, 96, 108));
				paint.setTextSize(60);
				// 开始写字
				canvas.drawText(num+"", zmkuang / 2 - 10, 60, paint);
				
				// 绘制上柱子
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(zhuX, zhuY, zhuX + zhu_kuan, zhuY + zhu_gao,
						paint);
				// 绘制下柱子
				canvas.drawRect(zhuX_2, zhuY_2 - zhu_gao_2,
						zhuX_2 + zhu_kuan_2, zhuY_2 + zhu_gao_2, paint);

			}
		}

	}
	//写一个背景音乐
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
