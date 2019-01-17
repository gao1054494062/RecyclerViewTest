package com.example.lenovo.viewpagergridview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GameRecycleViewAdapter extends RecyclerView.Adapter<GameRecycleViewAdapter.EntranceViewHolder> {

    private Context mContext;
    private int mIndex;
    private int mPageSize;
    private List<GameEntranceItem> gameEntranceList;

    public GameRecycleViewAdapter(MainActivity context, List<GameEntranceItem> datas, int index, int pageSize) {
        mContext = context;
        mIndex = index;
        mPageSize = pageSize;
        gameEntranceList = datas;
    }

    @Override
    public EntranceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EntranceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_entrance, null));
    }

    @Override
    public void onBindViewHolder(EntranceViewHolder holder, final int position) {
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + mIndex * mPageSize，
         */
        final int pos = position + mIndex * (mPageSize - 1);
        if (pos >= gameEntranceList.size() || (position + 1) % mPageSize == 0) {
            holder.entranceNameTextView.setText("管理游戏");
            holder.entranceIconImageView.setImageResource(R.mipmap.add_icon);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "管理游戏", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            holder.entranceNameTextView.setText(gameEntranceList.get(pos).getName());
            holder.entranceIconImageView.setImageResource(gameEntranceList.get(pos).getImage());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GameEntranceItem entrance = gameEntranceList.get(pos);
                    Toast.makeText(mContext, pos + "", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mPageSize;
        if (gameEntranceList.size() + mIndex < (mIndex + 1) * mPageSize) {
            itemCount = (gameEntranceList.size() - mIndex * (mPageSize - 1) + 1);
        }
        return itemCount;
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }

    class EntranceViewHolder extends RecyclerView.ViewHolder {
        private TextView entranceNameTextView;
        private ImageView entranceIconImageView;
        public EntranceViewHolder(View itemView) {
            super(itemView);
            entranceIconImageView = (ImageView) itemView.findViewById(R.id.entrance_image);
            entranceNameTextView = (TextView) itemView.findViewById(R.id.entrance_name);
        }
    }
}