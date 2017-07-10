package denny.com.badge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleHolder> {



    @Override
    public SampleAdapter.SampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SampleHolder(parent);
    }

    @Override
    public void onBindViewHolder(SampleAdapter.SampleHolder holder, int position) {
        holder.setText("SampleText By Badge View "+ position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class SampleHolder extends RecyclerView.ViewHolder{

        private TextView mView;

        public SampleHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent ,false));
            mView = (TextView) itemView.findViewById(R.id.text);
        }

        public void setText(String text){
            mView.setText(text);
        }
    }
}
