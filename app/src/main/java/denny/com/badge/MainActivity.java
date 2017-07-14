package denny.com.badge;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import cn.demo.badgeview.Align;
import cn.demo.badgeview.Badge;
import cn.demo.badgeview.BadgeView;
import cn.demo.badgeview.Mode;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BadgeView mIv;
    private Handler mHandler;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIv = (BadgeView) findViewById(R.id.iv);

        final Badge badge = Badge.makeBadge(findViewById(R.id.lltv1)).setText("1").setAlign(Align.LEFT);

        Badge.makeBadge(findViewById(R.id.lltv2))
                .setText("2")
                .setAlign(Align.BOTTOM)
                .setTextSize((int) getResources().getDimension(R.dimen.badge_text_size))
                .show();
        Badge.makeBadge(findViewById(R.id.lltv3)).setText("3").setAlign(Align.RIGHT| Align.BOTTOM).show();
        Badge.makeBadge(findViewById(R.id.lltv4)).setText("4").setAlign(Align.RIGHT).show();

        Badge.makeBadge(findViewById(R.id.lltv11)).setText("1").setAlign(Align.LEFT).setMode(Mode.COSSING).show();
        Badge.makeBadge(findViewById(R.id.lltv12)).setText("2").setAlign(Align.BOTTOM).setMode(Mode.COSSING).show();
        Badge.makeBadge(findViewById(R.id.lltv13)).setText("3").setAlign(Align.RIGHT | Align.BOTTOM).setMode(Mode.COSSING).show();
        Badge.makeBadge(findViewById(R.id.lltv14)).setText("4").setAlign(Align.RIGHT).setMode(Mode.COSSING).show();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setAdapter(new SampleAdapter());
        Badge.makeBadge(mRecyclerView, 5, R.id.text).show();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int next;
                if(msg.what == 1) {
                    badge.show();
                    next = 0;
                }else{
                    badge.dismiss();
                    next = 1;
                }
                mHandler.sendEmptyMessageDelayed(next, 1000);
            }
        };
//        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
