package abbos2101.movienew.adapter

import abbos2101.movienew.R
import abbos2101.movienew.net.model.DiscoveryResult
import abbos2101.movienew.net.model.SearchMovieResult
import abbos2101.movienew.ui.main.MainPresenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val presenter: MainPresenter
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)

        val tv_title: TextView = itemView.findViewById(R.id.item_tv_title)
        val tv_release: TextView = itemView.findViewById(R.id.item_tv_release)
        val tv_rating: TextView = itemView.findViewById(R.id.item_tv_rating)
        val pb_rating: ProgressBar = itemView.findViewById(R.id.item_pb_rating)
        val img: ImageView = itemView.findViewById(R.id.item_img)
        val item: FrameLayout = itemView.findViewById(R.id.item_item)

        fun bindHolder(model: DiscoveryResult, presenter: MainPresenter) {
            tv_title.text = "${model.title}"
            tv_release.text = "${model.release_date}"
            tv_rating.text = "${(model.vote_average * 10).toInt()}%"
            pb_rating.progress = (model.vote_average * 10).toInt()
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${model.poster_path}")
                .placeholder(R.drawable.ic_movie)
                .error(R.drawable.ic_movie_error)
                .into(img)
            item.setOnClickListener {
                presenter.itemClick(model)
            }
        }
    }

    private var list: ArrayList<DiscoveryResult> = arrayListOf()

    fun setMovieList(newList: ArrayList<DiscoveryResult>) {
        this.list = (list + newList) as ArrayList<DiscoveryResult>
        this.notifyDataSetChanged()
    }

    fun setClearList(){
        this.list.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(list.get(position), presenter)
        if (this.list.size - 1 == position)
            presenter.setPageUp()
    }
}