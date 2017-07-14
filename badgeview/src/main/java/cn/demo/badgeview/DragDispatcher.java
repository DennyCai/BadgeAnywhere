package cn.demo.badgeview;

import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class DragDispatcher implements View.OnTouchListener {

    private PointF mDownPoint;
    private PointF mSourcePoint;
    private Moveable mMove;

    public DragDispatcher(Moveable moveable){
        mMove = moveable;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                storePoint(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                float px = event.getRawX() - mDownPoint.x;
                float py = event.getRawY() - mDownPoint.y;
                mMove.moveX(mSourcePoint.x + px);
                mMove.moveY(mSourcePoint.y + py);
                return true;
            case MotionEvent.ACTION_UP:
                if(overDistance()){

                }else {
                    mMove.moveX(mSourcePoint.x);
                    mMove.moveY(mSourcePoint.y);
                }
        }
        return false;
    }

    private boolean overDistance() {

        return false;
    }

    private void storePoint(MotionEvent event) {
        if(mDownPoint==null){
            mDownPoint = new PointF();
        }
        if(mSourcePoint==null){
            mSourcePoint = new PointF();
        }
        mDownPoint.set(event.getRawX(), event.getRawY());
        mSourcePoint.set(mMove.getX(), mMove.getY());
    }
}
