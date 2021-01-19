package com.example.appmusicc.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appmusicc.Adapter.SearchSongAdapter
import com.example.appmusicc.Model.Song
import com.example.appmusicc.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.lang.reflect.Type
import java.util.*


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    var songAdapter: SearchSongAdapter? = null
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = context!!.getSharedPreferences("myShare", Context.MODE_PRIVATE);
    }

    override fun onResume() {
        super.onResume()
        loadData()
        songAdapter?.notifyDataSetChanged()
    }

    private fun loadData() {
        songAdapter = SearchSongAdapter(context)
        val gson = Gson()
        val json = sharedPreferences.getString("mySong", "[]")
        val type: Type = TypeToken.getArray(Song::class.java).type
        val array: Array<Song> = gson.fromJson(json, type)
        songAdapter?.arrayBHSearch?.clear()
        songAdapter?.arrayBHSearch?.addAll(array)
        if (songAdapter != null) {
            recyclerViewFavorite.adapter = songAdapter
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
