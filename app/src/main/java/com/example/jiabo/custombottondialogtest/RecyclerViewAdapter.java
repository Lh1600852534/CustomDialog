package com.example.jiabo.custombottondialogtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/3/26
 * Description :
 * Modify by
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private static final int NOT_SELECT_ITEM = 1;
    private static final int SELECT_ITEM = 2;
    private List<String> list = new ArrayList<>();
    private Context mContext;
    private int secondPosition;

    RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    void setSecondPosition(int position) {
        this.secondPosition = position + 1;
        getItemViewType(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NOT_SELECT_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_list_not_select, parent, false);

            return new CustomViewHolder(view);
        } else if (viewType == SELECT_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_dialog__list_selected, parent, false);

            return new CustomViewHolder(view);
        } else {
            Log.e(TAG, "onCreateViewHolder: unknown type!");
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_dialog__list_selected, parent, false);

            return new CustomViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == secondPosition) {
            return SELECT_ITEM;
        } else {
            return NOT_SELECT_ITEM;
        }
    }

    public void fillList(List<String> listData) {
        if (listData == null) {
            Log.e(TAG, "fillList: listData is null!");
            return;
        }
        this.list = listData;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.itemText.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;

        public CustomViewHolder(View view) {
            super(view);
            itemText = view.findViewById(R.id.list_text);
        }
    }
}
