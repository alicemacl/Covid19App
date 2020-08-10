package no.kristiania.covid19app

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_global_summary.*
import kotlinx.android.synthetic.main.activity_summary.*
import okhttp3.*
import java.io.IOException

class GlobalSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_summary)

        recyclerView_global_stats.layoutManager = LinearLayoutManager(this)

        supportActionBar?.title = "Global summary"

        fetchGlobal()
    }
    private fun fetchGlobal() {
        val url = "https://api.covid19api.com/summary"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()

                val globalSummary = Gson().fromJson(
                    body,
                    Summary::class.java
                )
                println(globalSummary)

                runOnUiThread {
                    recyclerView_global_stats.adapter =
                        GlobalSummaryAdapter(
                            globalSummary
                        )
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute call")
            }

        })
    }
}