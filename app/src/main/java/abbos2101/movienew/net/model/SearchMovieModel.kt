package abbos2101.movienew.net.model

data class SearchMovieModel(
    val results: ArrayList<SearchMovieResult>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)

data class SearchMovieResult(
    val id: Int,
    val popularity: Float,
    val video: Boolean,
    val vote_count: Int,
    val vote_average: Float,
    val title: String,
    val release_date: String,
    val original_language: String,
    val original_title: String,
    val adult: Boolean,
    val overview: String,
    val poster_path: String
)