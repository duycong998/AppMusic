package com.example.appmusicc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.ListGenreOfTopicActivity;
import com.example.appmusicc.Model.Topic;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAllTopicAdaper extends RecyclerView.Adapter<ListAllTopicAdaper.ViewHolder> {
    Context context;
    ArrayList<Topic> arrayAllTopic;

    public ListAllTopicAdaper(Context context, ArrayList<Topic> arrayCacTopic) {
        this.context = context;
        this.arrayAllTopic = arrayCacTopic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listalltopic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = arrayAllTopic.get(position);
        holder.txtName.setText(topic.getNameTopic());
        Picasso.with(context).load(topic.getPictureTopic()).into(holder.imgPicture);

    }

    @Override
    public int getItemCount() {
        return arrayAllTopic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPicture;
        TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPicture = itemView.findViewById(R.id.imgListAllTopic);
            txtName = itemView.findViewById(R.id.txtNameListAllTopic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListGenreOfTopicActivity.class);
                    intent.putExtra("genretopic", arrayAllTopic.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
