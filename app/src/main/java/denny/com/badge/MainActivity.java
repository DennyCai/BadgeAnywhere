package denny.com.badge;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cn.demo.badgeview.Align;
import cn.demo.badgeview.Badge;
import cn.demo.badgeview.BadgeActivity;
import cn.demo.badgeview.BadgeDrawable;
import cn.demo.badgeview.BadgeItemDecoration;
import cn.demo.badgeview.BadgeView;
import cn.demo.badgeview.Mode;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BadgeView mIv;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIv = (BadgeView) findViewById(R.id.iv);

        Badge.makeBadge(findViewById(R.id.lltv1)).setText("1").setAlign(Align.LEFT).show();
        Badge.makeBadge(findViewById(R.id.lltv2)).setText("2").setAlign(Align.BOTTOM).show();
        Badge.makeBadge(findViewById(R.id.lltv3)).setText("3").setAlign(Align.RIGHT| Align.BOTTOM).show();
        Badge.makeBadge(findViewById(R.id.lltv4)).setText("4").setAlign(Align.RIGHT).show();

        Badge.makeBadge(findViewById(R.id.lltv11)).setText("1").setAlign(Align.LEFT).setMode(Mode.COSSING).show();
        Badge.makeBadge(findViewById(R.id.lltv12)).setText("2").setAlign(Align.BOTTOM).setMode(Mode.COSSING).show();
        Badge.makeBadge(findViewById(R.id.lltv13)).setText("3").setAlign(Align.RIGHT | Align.BOTTOM).setMode(Mode.COSSING).show();
        Badge.makeBadge(findViewById(R.id.lltv14)).setText("4").setAlign(Align.RIGHT).setMode(Mode.COSSING).show();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setAdapter(new SampleAdapter());
        Badge.makeBadge(mRecyclerView, 5, R.id.text).show();
////        new RecyclerBadge(mRecyclerView,11, R.id.text).show();
//        BadgeItemDecoration decoration = new BadgeItemDecoration(R.id.text, 10);
//
//        BadgeDrawable drawable = new BadgeDrawable();
//        drawable.setBackground(Color.RED);
//        drawable.setTextColor(Color.WHITE);
//        drawable.setPadding(getResources().getDimensionPixelSize(R.dimen.badge_padding_size));
//        decoration.setBadgeDrawable(drawable);
//        decoration.setAlign(Align.RIGHT);
//        mRecyclerView.addItemDecoration(decoration);

    }
}
