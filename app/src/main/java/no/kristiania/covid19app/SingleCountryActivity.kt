package no.kristiania.covid19app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_single_country.*
import okhttp3.*
import java.io.IOException
import java.time.LocalDateTime

class SingleCountryActivity : AppCompatActivity() {

    companion object {
        val COUNTRY_NAME_KEY = "COUNTRY_TITLE"
        val COUNTRY_CODE_KEY = "COUNTRY_CODE"

        val COUNTRY_NEW_CASES_KEY = "COUNTRY_NEW_CASES"
        val COUNTRY_NEW_RECOVERED_KEY = "COUNTRY_NEW_RECOVERED"
        val COUNTRY_NEW_DEATHS_KEY = "COUNTRY_NEW_DEATHS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_country)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.COUNTRY_NAME_KEY)
        supportActionBar?.title = "Summary for $navBarTitle"

        val newConfirmed = intent.getStringExtra(CustomViewHolder.COUNTRY_NEW_CASES_KEY)
        val newDeaths = intent.getStringExtra(CustomViewHolder.COUNTRY_NEW_DEATHS_KEY)
        val newRecovered = intent.getStringExtra(CustomViewHolder.COUNTRY_NEW_RECOVERED_KEY)

        val intent = Intent(this, SingleCountryAdapter::class.java)

        intent.putExtra(COUNTRY_NEW_CASES_KEY, newConfirmed)
        intent.putExtra(COUNTRY_NEW_DEATHS_KEY, newDeaths)
        intent.putExtra(COUNTRY_NEW_RECOVERED_KEY, newRecovered)

        recyclerView_single.layoutManager = LinearLayoutManager(this)

        fetchDetails(newConfirmed, newDeaths, newRecovered)
    }

    private fun fetchDetails(newConfirmed: String?, newDeaths: String?, newRecovered: String?) {
        val countryId = intent.getStringExtra(CustomViewHolder.COUNTRY_NAME_KEY)
        val countryDetailUrl = "https://api.covid19api.com/country/$countryId"

        val request = Request.Builder().url(countryDetailUrl).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()

                val countryDetails = Gson().fromJson(
                    body,
                    Array<CountryDetails>::class.java
                )

                runOnUiThread {
                    recyclerView_single.adapter =
                        SingleCountryAdapter(
                            countryDetails,
                            newConfirmed,
                            newDeaths,
                            newRecovered
                        )
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute call")
            }
        })
    }
}