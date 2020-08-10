package no.kristiania.covid19app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_summary.*
import okhttp3.*
import java.io.IOException

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        recyclerView_summary.layoutManager = LinearLayoutManager(this)

        fetchSummary()
    }

    private fun fetchSummary() {
        val url = "https://api.covid19api.com/summary"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()

                val summary = Gson().fromJson(
                    body,
                    Summary::class.java
                )
                // sorts by highest number of confirmed cases, descending
                val sortedSummary = summary.countries.sortedByDescending { it.totalConfirmed }

                runOnUiThread {
                    recyclerView_summary.adapter =
                        SummaryAdapter(
                            sortedSummary
                        )
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute call")
            }

        })
    }
}