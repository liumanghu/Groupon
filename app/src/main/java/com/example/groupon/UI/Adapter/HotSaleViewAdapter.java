package com.example.groupon.UI.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupon.MyApplication;
import com.example.groupon.R;
import com.example.groupon.logic.ResponseModel.RecommendShore;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotSaleViewAdapter extends RecyclerView.Adapter<HotSaleViewAdapter.HotFaleViewHolder> {
    private List<RecommendShore> recommendShores;

    public HotSaleViewAdapter(List<RecommendShore> recommendShores) {
        this.recommendShores = recommendShores;
    }

    @NonNull
    @Override
    public HotFaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(MyApplication.mContext).inflate(R.layout.hot_sale_item, parent, false);
        return new HotFaleViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull HotFaleViewHolder holder, int position) {
        holder.hotSaleName.setText(recommendShores.get(position).getShoreName());
        holder.hotSaleAddress.setText(recommendShores.get(position).getShoreAddress());
        holder.hotSaleConsume.setText("￥ "+recommendShores.get(position).getShoreConsume());
        holder.hotSales.setText("已售" + recommendShores.get(position).getSales());
        //设置网络图片链接
        holder.hotSaleImg.setImageURI(Uri.parse(recommendShores.get(position).getImgUrl()));
    }

    @Override
    public int getItemCount() {
        return recommendShores.size();
    }

    class HotFaleViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView hotSaleImg;
        private TextView hotSaleName;
        private TextView hotSaleAddress;
        private TextView hotSaleConsume;
        private TextView hotSales;

        public HotFaleViewHolder(@NonNull View itemView) {
            super(itemView);
            hotSaleImg = itemView.findViewById(R.id.HotSaleImg);
            hotSaleName = itemView.findViewById(R.id.HotSaleName);
            hotSaleAddress = itemView.findViewById(R.id.HotSaleAddress);
            hotSaleConsume = itemView.findViewById(R.id.HotSaleConsum);
            hotSales = itemView.findViewById(R.id.HotSales);
        }

    }
}
