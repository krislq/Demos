package com.krislq.blow;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.krislq.blow.snow.SnowView;

public class MainActivity extends Activity implements OnClickListener {
    private SnowView snowView = null;
    private Button btnBlow = null;
    private TextView tvDisplay = null;
    private RecordThread recordThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBlow = (Button) findViewById(R.id.button);
        tvDisplay = (TextView)findViewById(R.id.textView);
        btnBlow.setOnClickListener(this);
        btnBlow.setText("Begin");
        
        tvDisplay.setText("点击按钮开始");
        
        // 获得雪花视图,并加载雪花图片到内存
        snowView = (SnowView) findViewById(R.id.snow);
        snowView.LoadSnowImage();
        // 获取当前屏幕的高和宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        snowView.SetView(dm.heightPixels, dm.widthPixels);
    }

    BlowHandler handler = new BlowHandler();
    class BlowHandler extends Handler {
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(2), delayMillis);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 1:
                // 接收到message后更新UI，并通过isblow停止线程
                tvDisplay.setText("吹了一下:"+System.currentTimeMillis());
                btnBlow.setText("Begin");
                recordThread.stopRecord();
                update();
                break;
            case 2:
                snowView.invalidate();
                sleep(100);
                break;
            default:
                break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (recordThread == null || recordThread.getRecordStatus()) {
            btnBlow.setText("Stop");
            tvDisplay.setText("试试对着电话底部吹一吹吧");
            recordThread = new RecordThread(handler, 1); // 点击按钮，启动线程
            recordThread.start();
            snowView.setStatus(false);
        } else {
            btnBlow.setText("Begin");
            recordThread.stopRecord();
        }
    }
    /**
     * Handles the basic update loop, checking to see if we are in the running
     * state, determining if a move should be made, updating the snake's
     * location.
     */
    public void update() {
        snowView.addRandomSnow();
        snowView.setStatus(true);
        handler.sleep(200);
    }
}
