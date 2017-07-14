package cn.demo.badgeview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

class RecycleDragDispatcher extends DragDispatcher implements RecyclerView.OnItemTouchListener{

    private Rect mTarget;
    private boolean mTouchBadge;

    public RecycleDragDispatcher(Moveable moveable, Rect target) {
        super(moveable);
        mTarget = target;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_DOWN){
            return touchOnBadge(e);
        }else if(e.getAction() == MotionEvent.ACTION_MOVE){
            return mTouchBadge;
        }
        return false;
    }

    private boolean touchOnBadge(MotionEvent event) {
        mTouchBadge = mTarget.contains((int)event.getX(), (int)event.getY());
        if(mTouchBadge){
            onTouch(null, event);
        }
        return mTouchBadge;
    }
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        onTouch(rv, e);
        rv.invalidate();
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
