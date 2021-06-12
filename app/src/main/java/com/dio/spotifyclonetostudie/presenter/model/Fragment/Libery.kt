package com.dio.spotifyclonetostudie.presenter.model.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dio.spotifyclonetostudie.R
import com.dio.spotifyclonetostudie.presenter.model.FragmentsTab.Albums
import com.dio.spotifyclonetostudie.presenter.model.FragmentsTab.Artists
import com.dio.spotifyclonetostudie.presenter.model.FragmentsTab.Playlists
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_libery.*

class Libery : Fragment(){

    companion object{
        fun newInstance(): Libery{
            val fragmentLibery = Libery()
            val arguments = Bundle()
            fragmentLibery.arguments = arguments
            return fragmentLibery
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_libery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)
            .add("Playlists", Playlists::class.java)
            .add("Artists", Artists::class.java)
            .add("Albums", Albums::class.java)
            .create())

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }
}