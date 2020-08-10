package no.kristiania.covid19app

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_summary.view.*
import kotlinx.android.synthetic.main.country_row.view.*

class SummaryAdapter(private val summary: List<Countries>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return summary.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val singleCountryCell = layoutInflater.inflate(R.layout.country_row, parent, false)
        return CustomViewHolder(singleCountryCell)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val country = summary[position]

        holder.view.textView_country.text = country.country
        holder.view.textView_total_confirmed.text = country.totalConfirmed.toString()
        holder.view.textView_new_confirmed.text = "+" + country.newConfirmed.toString()

        val flagImageUrl = "https://www.countryflags.io/${country.countryCode}/shiny/32.png"
        val flagThumbnail = holder.view.imageView_country_flag
        Picasso.get().load(flagImageUrl).into(flagThumbnail)

        holder.countries = country
        holder.newConfirmed = country.newConfirmed.toString()
        holder.newDeaths = country.newDeaths.toString()
        holder.newRecovered = country.newRecovered.toString()
    }
}

class CustomViewHolder(
    val view: View,
    var countries: Countries? = null,
    var newConfirmed: String? = null,
    var newDeaths: String? = null,
    var newRecovered: String? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val COUNTRY_NAME_KEY = "COUNTRY_TITLE"
        val COUNTRY_CODE_KEY = "COUNTRY_CODE"

        val COUNTRY_NEW_CASES_KEY = "COUNTRY_NEW_CASES"
        val COUNTRY_NEW_RECOVERED_KEY = "COUNTRY_NEW_RECOVERED"
        val COUNTRY_NEW_DEATHS_KEY = "COUNTRY_NEW_DEATHS"
    }
    init {
        view.setOnClickListener {

            val intent = Intent(view.context, SingleCountryActivity::class.java)

            intent.putExtra(COUNTRY_NAME_KEY, countries?.country)
            intent.putExtra(COUNTRY_CODE_KEY, countries?.countryCode)

            intent.putExtra(COUNTRY_NEW_CASES_KEY, newConfirmed)
            intent.putExtra(COUNTRY_NEW_DEATHS_KEY, newDeaths)
            intent.putExtra(COUNTRY_NEW_RECOVERED_KEY, newRecovered)

            view.context.startActivity(intent)
        }
    }
}