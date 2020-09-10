package com.example.groupon.UI.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupon.R;
import com.example.groupon.logic.ResponseModel.FilmMessage;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HotFilmListAdapter extends RecyclerView.Adapter<HotFilmListAdapter.HotFilmListHolder> {
    List<FilmMessage.SubjectsEntity> subjects;

    public HotFilmListAdapter(List<FilmMessage.SubjectsEntity> filmMessageList) {
        this.subjects = filmMessageList;
    }

    @NonNull
    @Override
    public HotFilmListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_film_list_item, parent, false);
        return new HotFilmListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HotFilmListHolder holder, int position) {
        Uri uri = Uri.parse(subjects.get(position).getImages().getLarge());
        holder.FilmImage.setImageURI(uri);
        holder.FilmNameText.setText(subjects.get(position).getOriginal_title());
        holder.FilmGradeText.setText(String.valueOf(subjects.get(position).getRating().getAverage())+"分");
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    class HotFilmListHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView FilmImage;
        private TextView FilmNameText;
        private TextView FilmGradeText;

        public HotFilmListHolder(@NonNull View itemView) {
            super(itemView);
            FilmImage = itemView.findViewById(R.id.filmImg);
            FilmNameText = itemView.findViewById(R.id.filmName);
            FilmGradeText = itemView.findViewById(R.id.filmGrade);
        }
    }
}
