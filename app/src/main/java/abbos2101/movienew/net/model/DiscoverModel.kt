package abbos2101.movienew.net.model

data class DiscoverModel(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: ArrayList<DiscoveryResult>
)

data class DiscoveryResult(
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val poster_path: String,
    val id: Int,
    val adult: Boolean,
    val original_language: String,
    val original_title: String,
    val title: String,
    val vote_average: Float,
    val overview: String,
    val release_date: String
)