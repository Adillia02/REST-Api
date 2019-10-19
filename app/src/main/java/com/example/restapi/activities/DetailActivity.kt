package com.example.restapi.activities

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.POJO.DetailMovie
import com.example.restapi.POJO.Trailers
import com.example.restapi.R
import com.example.restapi.adapter.TrailerAdapter
import com.example.restapi.utils.ApiService
import com.example.restapi.utils.EndPoint
import com.example.restapi.utils.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.movie_about_description.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

//    var imgThumbnail   : ImageView? = null
//    var tvHeaderTitle  : TextView? = null
//    var tvReleseYear   : TextView? = null
//    var rbVotes        : RatingBar? = null
//    var tvVotesResult  : TextView? = null
//    var tvVotesAvarage  : TextView? = null
//    var tvStoryLine    : TextView? = null
//    var tvTrailers     : TextView? = null
//    var tvOriginalTitle: TextView? = null
//    var tvType         : TextView? = null
//    var tvProduction   : TextView? = null
//    var tvPremiere     : TextView? = null
//    var tvDescription  : TextView? = null
//    val idMovie = intent.getStringExtra("id_movie")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra("id_movie",0)
        getDetailData(id)

//        val idMovie = intent.getIntExtra("id_movie",0)
//        val titleMovie = intent.getStringExtra("title_movie")
//        val backdropMovie = intent.getStringExtra("backdrop_movie")
//        val overview = intent.getStringExtra("overview_movie")

    }

    private fun getDetailData(id: Int){
        var api = InitRetrofit().getInitInstance()
        val call = api.requestDetail(id, EndPoint.API_KEY)

        call.enqueue(object : Callback<DetailMovie>{
            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {

            }
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
               if (response != null){
                   if (response.isSuccessful){
                       val data = response.body()
                       votes_result?.text = data!!.vote_count.toString()
                       votes_avarage?.text = data!!.vote_avarage.toString()
                       tv_content_description?.text = data!!.overview
                       release_year?.text = data!!.runtime
                       release_year?.text = data!!.release_date
                       tv_content_premiere?.text = data!!.release_date
                       tv_content_original_title?.text = data!!.original_title
                       toolbar_detail?.title = data!!.original_title
                       Picasso.get().load(EndPoint.IMAGE_URL_BACKDROP + data!!.backdrop_path).into(header_thumbnail)
                       tv_overview?.text = data!!.overview
                       var type = data!!.genres!!.get(0).genre
                       tv_content_type?.text = type
                       var companies = data!!.production_companies!!.get(0).company_name
                       tv_content_production?.text = companies

                   }
               }
            }

        })
    }


    //mengatur tampilan untuk fullscreen

        //menonaktifkan title pada action bar

        //menangkap semua data yang di ambil dari intent

        //menaruh data yang sudah ditangkap ke widget yang di tentukan

        //mengatur bentuk recycle trailer
}
