package abbos2101.movienew.ui.main

import abbos2101.movienew.R
import abbos2101.movienew.adapter.MovieAdapter
import abbos2101.movienew.net.model.DiscoveryResult
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.cancel


class MainActivity : AppCompatActivity(), MainAction {
    private val presenter by lazy { MainPresenter(this) }
    private val adapter by lazy { MovieAdapter(presenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.init()
        presenter.getMovieList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancel()
    }

    override fun onInit() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        main_rv.adapter = adapter
        main_btn_search.setOnClickListener {
            adapter.setClearList()
            if (main_edt_search.text.toString().trim().isNotEmpty())
                presenter.searchMovieList(main_edt_search.text.toString())
            else
                presenter.getMovieList()
        }
    }

    override fun onSetMovieList(newList: ArrayList<DiscoveryResult>) {
        adapter.setMovieList(newList)
    }

    override fun onSetProgressBarVisible(visibility: Int) {
        main_pb.visibility = visibility
    }

    override fun onStartActivity(intent: Intent) {
        startActivity(intent)
    }
}