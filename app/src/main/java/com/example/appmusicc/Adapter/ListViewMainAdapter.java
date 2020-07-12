package com.example.appmusicc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmusicc.Model.Menu;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListViewMainAdapter extends BaseAdapter {
    private Context context;
    private List<Menu> menuList;

    public ListViewMainAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }


    @Override
    public int getCount() {

        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        ImageView imgPicture;
        TextView txtName;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// truyền vào layout để chứa context

            convertView = inflater.inflate(R.layout.item_listview_main,null);
            holder = new ViewHolder();
            //ánh xạ lai
            holder.txtName =  convertView.findViewById(R.id.txtNameMenu);
            holder.imgPicture = convertView.findViewById(R.id.imgPictureMain);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Menu me = menuList.get(position);

        holder.txtName.setText(me.getmName());
        Picasso.with(context).load(me.getmPicture()).into(holder.imgPicture);


        return convertView;

    }
}