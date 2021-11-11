package io.seohayeon.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_info)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var server = retrofit.create(GetMovieInfo::class.java)

        val id = intent.getIntExtra("id",0)
        var tv_title = findViewById<TextView>(R.id.tv_title)
        var tv_overview = findViewById<TextView>(R.id.tv_overview)
        var iv_poster = findViewById<ImageView>(R.id.iv_movieposter)
        var tv_runtime = findViewById<TextView>(R.id.tv_runtime)
        var tv_release_date = findViewById<TextView>(R.id.tv_release_date)
        var tv_popularity = findViewById<TextView>(R.id.tv_popularity)

        val context = this
        server.getInfo(id,"72638aa6aceca9acad45e840e49e7d6e","ko-KR").enqueue(object:
            Callback<MovieInfoDto> {
            override fun onResponse(call: Call<MovieInfoDto>?, response: Response<MovieInfoDto>?) {
                val movieResponse  = response?.body()
                tv_title.text = movieResponse?.original_title
                tv_overview.text = movieResponse?.overview
                tv_runtime.text = movieResponse?.runtime.toString()+"분"
                tv_release_date.text = "개봉일: "+movieResponse?.release_date
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movieResponse?.poster_path).into(iv_poster)
                tv_popularity.text = "관객수: "+movieResponse?.popularity.toString()
            }

            override fun onFailure(call: Call<MovieInfoDto>?, t: Throwable?) {
                println(t)
            }

        })

    }
}