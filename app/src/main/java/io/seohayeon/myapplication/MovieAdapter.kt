package io.seohayeon.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MovieAdapter(private val context: Context, private val dataSet: List<MovieDto>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_poster: ImageView
        var item_id: Int?

        init {
            iv_poster = view.findViewById(R.id.iv_poster)
            item_id = 0
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_item, viewGroup, false)



        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+dataSet[position].backdrop_path).into(viewHolder.iv_poster)
        viewHolder.item_id = dataSet[position].id
        val intent = Intent(viewHolder.itemView.context, MovieActivity::class.java)
        intent.putExtra("id",viewHolder.item_id)
        viewHolder.itemView.setOnClickListener {
            ContextCompat.startActivity(viewHolder.itemView.context,intent,null)
            println(viewHolder.item_id)
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
