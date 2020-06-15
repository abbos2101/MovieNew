package abbos2101.movienew.ui.content

import abbos2101.movienew.R
import abbos2101.movienew.common.*
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.content_scrolling.*

class ContentActivity : AppCompatActivity(), ContentAction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        setSupportActionBar(findViewById(R.id.toolbar))

        onInit()
    }

    override fun onInit() {
        val scontent_img_url = intent.getStringExtra("$content_img_url")
        val scontent_title = intent.getStringExtra("$content_title")
        val scontent_overview = intent.getStringExtra("$content_overview")
        val scontent_release = intent.getStringExtra("$content_release")
        val scontent_language = intent.getStringExtra("$content_language")
        val scontent_rating = intent.getIntExtra("$content_rating", 0)

        supportActionBar?.setTitle(scontent_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(scontent_img_url)
            .placeholder(R.drawable.ic_movie)
            .error(R.drawable.ic_movie_error)
            .into(content_img)

        content_tv_rating.text = "$scontent_rating%"
        content_tv.text = "$scontent_overview"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return true
    }
}