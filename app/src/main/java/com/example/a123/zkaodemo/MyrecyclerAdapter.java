package com.example.a123.zkaodemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ASUS on 2017/12/6.
 */

public class MyrecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<MyBeans.DataBean.ObjectsBean> objects;
    private Context context;
    private static int ITEM_ONE = 1;
    private static int ITEM_TWO = 2;

    public MyrecyclerAdapter( List<MyBeans.DataBean.ObjectsBean> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == ITEM_ONE) {
            view = inflater.inflate(R.layout.recycler_itemone, parent, false);
            holder = new HolderOne(view);
        } else {
            view = inflater.inflate(R.layout.recycler_itemtwo, parent, false);
            holder = new HolderTwo(view);
        }
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyBeans.DataBean.ObjectsBean objectsBean = objects.get(position);
        if (holder instanceof HolderOne) {
            Glide.with(context).load(objectsBean.getGmall_product().getPic_url()).into(((HolderOne) holder).recycler_img_one);
            ((HolderOne) holder).recycler_text_one.setText(objectsBean.getGmall_product().getTitle());
            ((HolderOne)holder).itemView.setTag(position);
        } else {
            Glide.with(context).load(objectsBean.getGmall_product().getPic_url()).into(((HolderTwo) holder).recycler_img_two);
            ((HolderTwo) holder).recycler_text_two.setText(objectsBean.getGmall_product().getTitle());
            ((HolderTwo)holder).itemView.setTag(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ITEM_ONE;
        } else {
            return ITEM_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }



    class HolderOne extends RecyclerView.ViewHolder {
        private ImageView recycler_img_one;
        private TextView recycler_text_one;

        public HolderOne(View itemView) {
            super(itemView);
            recycler_img_one = (ImageView) itemView.findViewById(R.id.recycler_img_one);
            recycler_text_one = (TextView) itemView.findViewById(R.id.recycler_text_one);
        }
    }

    class HolderTwo extends RecyclerView.ViewHolder {
        private ImageView recycler_img_two;
        private TextView recycler_text_two;

        public HolderTwo(View itemView) {
            super(itemView);
            recycler_img_two = (ImageView) itemView.findViewById(R.id.recycler_img_two);
            recycler_text_two = (TextView) itemView.findViewById(R.id.recycler_text_two);
        }
    }
    private colock colock;
    public interface colock{
        void colock(View view, int position);
    }
    public  void setcolock(colock colock){
        this.colock = colock;
    }
    @Override
    public void onClick(View v) {
        colock.colock(v, (Integer) v.getTag());
    }
}
