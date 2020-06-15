package abbos2101.movienew.net.model

data class MovieVideosModel(
    val id: Int,
    val results: ArrayList<MovieVideosResult>
)

data class MovieVideosResult(
    val id: String,
    val iso_639_1: String,
    val iso_3166_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)