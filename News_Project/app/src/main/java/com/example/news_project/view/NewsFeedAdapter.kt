import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_project.R
import com.example.news_project.model.Image
import com.example.news_project.model.NewsFeedItem
import com.example.news_project.model.NewsItemClickListener
import com.squareup.picasso.Picasso

class NewsFeedAdapter(
    private val newsList: List<NewsFeedItem>,
    private val itemClickListener: NewsItemClickListener
) :
    RecyclerView.Adapter<NewsFeedAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_feed, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount() = newsList.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val imageRecyclerView: RecyclerView = itemView.findViewById(R.id.imageRecyclerView)

        fun bind(news: NewsFeedItem) {
            titleTextView.text = news.title

            val layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            imageRecyclerView.layoutManager = layoutManager

            val adapter = ImageAdapter(news.images ?: emptyList())
            imageRecyclerView.adapter = adapter
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val news = newsList[position]
                    itemClickListener.onNewsItemClick(news)
                }
            }
        }
    }

    inner class ImageAdapter(private val images: List<Image>) :
        RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
            return ImageViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val image = images[position]
            holder.bind(image)
        }

        override fun getItemCount() = images.size

        inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val imageView: ImageView = itemView.findViewById(R.id.imageView)

            fun bind(image: Image) {
                Picasso.get().load(image.href).into(imageView)
            }
        }
    }
}
