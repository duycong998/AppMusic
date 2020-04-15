package com.example.appmusicc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusicc.Activity.DanhSachBaiHatQCActivity;
import com.example.appmusicc.Activity.DanhSachCacPlayListActivity;
import com.example.appmusicc.Adapter.PlaylistAdapter;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lsvPlaylist;
    TextView txtTitle,txtViewPlaylist;
    PlaylistAdapter adapter;
    ArrayList<PlayList> arrayPlaylist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        lsvPlaylist     = view.findViewById(R.id.lsvPlaylist);
        txtViewPlaylist = view.findViewById(R.id.txtViewPlaylist);
        txtTitle        = view.findViewById(R.id.txtTitlePlaylist);
        getData();
        txtViewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachCacPlayListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<PlayList>> callBack = data.getDataPlayListDay();
        callBack.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                 arrayPlaylist = (ArrayList<PlayList>) response.body();
                 adapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayPlaylist);
                 lsvPlaylist.setAdapter(adapter);
                 setListViewHeightBasedOnChildren(lsvPlaylist);
                 lsvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         Intent intent = new Intent(getActivity(), DanhSachBaiHatQCActivity.class);
                         intent.putExtra("itemplaylist",arrayPlaylist.get(position));
                         startActivity(intent);
                     }
                 });

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
