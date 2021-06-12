package com.dio.spotifyclonetostudie.presenter.model.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dio.spotifyclonetostudie.R
import com.dio.spotifyclonetostudie.data.SpotifyAPI
import com.dio.spotifyclonetostudie.data.retrofit
import com.dio.spotifyclonetostudie.presenter.model.data.Albums
import com.dio.spotifyclonetostudie.presenter.model.data.Categorie
import com.dio.spotifyclonetostudie.presenter.model.data.DataCategories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.*
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.categories_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {


    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        fun newInstance(): Home {
            val fragmentHome = Home()
            val arguments = Bundle()
            fragmentHome.arguments = arguments
            return fragmentHome
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categories = arrayListOf<Categorie>()
        categoriesAdapter = CategoriesAdapter(categories)
        rv_categories.adapter = categoriesAdapter
        rv_categories.layoutManager = LinearLayoutManager(context)


        retrofit().create(SpotifyAPI::class.java)
            .ListCategories()
            .enqueue(object : Callback<DataCategories>{
                override fun onFailure(call: Call<DataCategories>, t: Throwable) {
                    Toast.makeText(context, "Error server response", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<DataCategories>, response: Response<DataCategories>) {
                    if(response.isSuccessful){
                       response.body()?.let {
                           categoriesAdapter.categories.clear()
                            categoriesAdapter.categories.addAll(it.categories)
                           categoriesAdapter.notifyDataSetChanged()
                        }
                }
//                override fun onFailure(call: Call<Categorie>, t: Throwable) {
//                    Toast.makeText(context, "Error server response", Toast.LENGTH_SHORT).show()
//                }

//                override fun onResponse(call: Call<Categorie>, response: Response<Categorie>) {
//                    if(response.isSuccessful){
//                        response.body()?.let {
//                            categoriesAdapter.categories.clear()
//                            categoriesAdapter.categories.addAll(it.albums)
//                            categoriesAdapter.categories.addAll(it.title)
//                            categoriesAdapter.notifyDataSetChanged()
//                        }
//                    }
                }


            })

    }

    private inner class CategoriesAdapter(internal val categories: MutableList<Categorie>) :
        RecyclerView.Adapter<CategoriesHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
            return CategoriesHolder(layoutInflater.inflate(R.layout.categories_item, parent, false))
        }

        override fun getItemCount(): Int = categories.size



        override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
            val categorie = categories[position]
            holder.bind(categorie)

        }
    }

    private inner class CategoriesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categorie: Categorie){
            itemView.tv_categories_title.text = categorie.title
            itemView.rv_albums.adapter = AlbumsAdapter(categorie.albums)
            itemView.rv_albums.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        }

    }


//-----------------------------------------------------------------------------------------------

    private inner class AlbumsAdapter( private val albums: List<Albums>): RecyclerView.Adapter<AlbumsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder {
            return AlbumsHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
        }

        override fun getItemCount(): Int = albums.size


        override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
            val albums = albums[position]
            holder.bind(albums)
        }
    }

    private inner class AlbumsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(albums: Albums) {
            Picasso.get().load(albums.imagem).placeholder(R.drawable.placeholder).fit().into(itemView.iv_album)

        }
    }
}