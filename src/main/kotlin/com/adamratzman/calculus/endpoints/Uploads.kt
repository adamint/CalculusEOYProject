import com.adamratzman.calculus.Website
import spark.Spark

fun Website.resources() {
    Spark.get("/resources/*") { request, response ->
        val path = request.splat().getOrNull(0)
        uploads.find { path == it.fileName }?.content?.let {
            response.raw().outputStream.write(it)
            response.raw().outputStream.flush()
            response.raw().outputStream.close()
        }?.let { response.raw() } ?: "No file with this path was found"
    }
}

//fun getResourcesForChapter(chapterNumber: Int) =