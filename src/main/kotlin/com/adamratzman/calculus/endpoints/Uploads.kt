import spark.Response
import spark.Spark.get

fun aws() {
    get("/aws/*") { request, response ->
        response.redirectToAwsFile("/${request.splat()[0]}")
    }
}
fun Response.redirectToAwsFile(path: String) {
    redirect("http://s3.amazonaws.com/calculus-eoy$path")
}