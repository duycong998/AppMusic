package com.example.appmusicc.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Adapter.SearchBaiHatAdapter;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    Toolbar toolbar;
    RecyclerView rcViewSearchBH;
    TextView txtNoData;
    View view;
    SearchBaiHatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar        = view.findViewById(R.id.toolbarSearchBH);
        rcViewSearchBH = view.findViewById(R.id.rcViewSearchBH);
        txtNoData      = view.findViewById(R.id.txtNoData);
        //set lại toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                searchKeyBaiHat(query);
//                return true;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchKeyBaiHat(newText);
                return true;
//                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchKeyBaiHat(String key){
        DataServiec data = APIService.getData();
        Call<List<BaiHat>> callBack = data.getSearchBaiHat(key);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrayBHSearch = (ArrayList<BaiHat>) response.body();
                if(arrayBHSearch.size()  > 0){
                    adapter = new SearchBaiHatAdapter(getActivity(),arrayBHSearch);
                    LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getActivity());
                    rcViewSearchBH.setLayoutManager(linearLayoutManager);
                    rcViewSearchBH.setAdapter(adapter);
                    txtNoData.setVisibility(View.GONE);
                    rcViewSearchBH.setVisibility(View.VISIBLE);
                }else {
                    rcViewSearchBH.setVisibility(View.GONE);
                    txtNoData.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
