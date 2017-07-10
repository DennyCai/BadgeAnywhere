package cn.demo.badgeview;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class BadgeItemDecoration extends RecyclerView.ItemDecoration
        implements BadgeInterface{


    private final int mAnchorId;
    private final int mAnchorPos;
    private BadgeDrawable mBadgeDrawable;
    private SparseArray<View> mAnchorCache;
    private BadgeLayout mLayout;
    private RecyclerView mRecycler;
    private boolean mHasAttched = false;

    BadgeItemDecoration(int anchorId, int anchorPos) {
        mAnchorId = anchorId;
        mAnchorPos = anchorPos;
        mAnchorCache = new SparseArray<>();
        mLayout = new BadgeLayout();
    }

    void setRecyclerView(RecyclerView view){
        mRecycler = view;
    }



    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Rect childRect = new Rect();
        for (int i = 0, size = parent.getChildCount(); i< size ; ++i){
            if(needBadge(parent, state, i)){
                View anchorView = resolveAnchorView(parent, i);
                View itemView = parent.getChildAt(i);
                Rect rect = resolveAnchorViewPositionInItemView(anchorView, itemView);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(itemView, childRect);
                int y = (int) ViewCompat.getTranslationY(itemView);
                mLayout.setOffset(new Point(0, y + childRect.top));
                mLayout.setTargetRect(rect);
                drawBadge(c, mLayout.resolveBadgePosition(), i);
            }
        }
    }

    private Rect resolveAnchorViewPositionInItemView(View anchorView, View itemView) {
        Rect out = new Rect();
        if(anchorView!=null&& itemView instanceof ViewGroup){
            anchorView.getHitRect(out);
        }
        return out;
    }

    private void drawBadge(Canvas canvas, Rect childRect, int x, int y, int index) {
        if(mBadgeDrawable!=null){
            canvas.save();
            canvas.translate(x,y);
            mBadgeDrawable.draw(canvas);
            canvas.restore();
        }
    }

    private void drawBadge(Canvas canvas, Point pos, int index){
        if(mBadgeDrawable!=null){
            canvas.save();
            canvas.translate(pos.x, pos.y);
            mBadgeDrawable.draw(canvas);
            canvas.restore();
        }
    }

    /**
     * 是否需要标记
     * @param parent
     * @param state
     * @param index
     * @return
     */
    private boolean needBadge(RecyclerView parent, RecyclerView.State state, int index) {
        if(parent.getLayoutManager()==null){
            return false;
        }
        RecyclerView.ViewHolder holder = parent.findContainingViewHolder(parent.getChildAt(index));
        if(holder!=null){
            return holder.getAdapterPosition() == mAnchorPos;
        }
        return false;
    }

    /**
     * 确定要标记的View
     * @param parent
     * @param index
     * @return
     */
    private View resolveAnchorView(RecyclerView parent, int index){
        View anchor;
        anchor = mAnchorCache.get(mAnchorId);
        if(anchor==null){
            anchor = findAnchorInViewHolder(parent, index);
        }
        if(anchor!=null){
            mAnchorCache.put(mAnchorId, anchor);
        }
        return anchor;
    }

    private View findAnchorInViewHolder(RecyclerView parent, int index) {
        RecyclerView.ViewHolder holder = parent.findContainingViewHolder(parent.getChildAt(index));
        if(holder!=null&& holder.itemView!=null){
            return holder.itemView.findViewById(mAnchorId);
        }
        return null;
    }

    @Override
    public void setAlign(@Align.Enum int align) {
        mLayout.setAlign(align);
    }

    @Override
    public void setMode(@Mode.Enum int mode) {
        mLayout.setMode(mode);
    }

    @Override
    public void setOffset(int x, int y) {
        mLayout.setOffset( new Point(x, y));
    }

    @Override
    public void setBadgeDrawbale(BadgeDrawable drawbale) {
        mBadgeDrawable = drawbale;
        mLayout.setBadgeRect(drawbale.getBounds());
    }

    @Override
    public void show() {
        if(mHasAttched)
            return;
        mRecycler.addItemDecoration(this);
        mHasAttched = true;
    }
}
