package com.krislq.blow.snow;

import java.util.Random;

import com.krislq.blow.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @author <a href="mailto:kris@krislq.com">Kris.lee</a>
 * @since Jun 20, 2013
 * @version 1.0.0
 */
public class SnowView extends View {
	int MAX_SNOW_COUNT = 20;
	// ѩ��ͼƬ
	Bitmap bitmap_snows = null;
	// ����
	private final Paint mPaint = new Paint();
	// �漴������
	private static final Random random = new Random();
	// ����λ��
	private Snow[] snows = new Snow[MAX_SNOW_COUNT];
	// ��Ļ�ĸ߶ȺͿ��
	int view_height = 0;
	int view_width = 0;
	int MAX_SPEED = 40;
	boolean draw = false;
	private int gravity = 1;

	/**
	 * ������
	 * 
	 * 
	 */
	public SnowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SnowView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	/**
	 * ������Ůɢ���Ļ�ͼƬ���ڴ���
	 * 
	 */
	public void LoadSnowImage() {
		Resources r = this.getContext().getResources();
		bitmap_snows = ((BitmapDrawable) r.getDrawable(R.drawable.snow))
				.getBitmap();
	}

	/**
	 * ���õ�ǰ�����ʵ�ʸ߶ȺͿ��
	 * 
	 */
	public void SetView(int height, int width) {
		view_height = height - 100;
		view_width = width - 50;

	}
	
	public void setStatus(boolean draw){
		this.draw = draw;
	}

	/**
	 * ��������ɻ����λ��
	 * 
	 */
	public void addRandomSnow() {
		for(int i =0; i< MAX_SNOW_COUNT;i++){
			snows[i] = new Snow(random.nextInt(view_width), view_height,-(random.nextInt(MAX_SPEED)));
		}
	}


	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(draw)
		{
			int outOfBoundCount = 0;
			canvas.drawColor(Color.GRAY);
			for (int i = 0; i < MAX_SNOW_COUNT; i += 1) {
				//�ж��Ƿ�����ʾ����
				if (snows[i].coordinate.x > view_width || snows[i].coordinate.y > view_height) {
					outOfBoundCount++;
					//������е�ѩ����������ʾ�����ˣ����´ξͲ���Ҫ�ٻ���ѩ����
					if(outOfBoundCount >= MAX_SNOW_COUNT){
						canvas.drawColor(Color.WHITE);
						setStatus(false);
					}
					continue;
				}
				//Ϊѩ������������
				snows[i].speed +=gravity;
				// ѩ��������ٶ�
				snows[i].coordinate.y += snows[i].speed;
				//ѩ��Ʈ����Ч��
	
//				// �������һ�����֣���ѩ����ˮƽ�ƶ���Ч��
//				int tmp = MAX_SPEED/2 - random.nextInt(MAX_SPEED);
//				int absSpeed = Math.abs(snows[i].speed);
//				//Ϊ�˶�������Ȼ�ԣ����ˮƽ���ٶȴ���ѩ���������ٶȣ���ôˮƽ���ٶ�����ȡ������ٶȡ�
//				snows[i].coordinate.x += absSpeed < tmp ? absSpeed : tmp;
				canvas.drawBitmap(bitmap_snows, ((float) snows[i].coordinate.x),
						((float) snows[i].coordinate.y), mPaint);
			}
		}else {
			canvas.drawColor(Color.WHITE);
		}

	}

}
