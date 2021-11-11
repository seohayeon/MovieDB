package io.seohayeon.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

///?=&language=ko-KR
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var server = retrofit.create(Service::class.java)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        val context:Context = this



            server.getProfile("72638aa6aceca9acad45e840e49e7d6e","ko-KR").enqueue(object:Callback<MovieResult>{
                override fun onResponse(call: Call<MovieResult>?, response: Response<MovieResult>?) {
                    val movieResponse  = response?.body()
                    movieResponse?.let {
                        recyclerview.adapter = MovieAdapter(context,it.dailyBoxOfficeList)

                    }
                }

                override fun onFailure(call: Call<MovieResult>?, t: Throwable?) {

                }

            })

    }
}