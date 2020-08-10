package no.kristiania.covid19app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_details.view.*

class SingleCountryAdapter(
    private val countryDetails: Array<CountryDetails>,
    private val newConfirmed: String?,
    private val newDeaths: String?,
    private val newRecovered: String?
): RecyclerView.Adapter<CustomSingleViewHolder>() {

    override fun getItemCount(): Int {
        return 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomSingleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val singleCell = layoutInflater.inflate(R.layout.country_details, parent, false)
        return CustomSingleViewHolder(
            singleCell
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomSingleViewHolder, position: Int) {

        val latest = countryDetails.size
        val countryDetail = countryDetails[latest -1]

        holder.singleView.textView_single_country.text = countryDetail.country

        holder.singleView.textView_total_confirmed.text = countryDetail.confirmed.toString()
        holder.singleView.textView_total_recovered.text = countryDetail.recovered.toString()
        holder.singleView.textView_total_deaths.text = countryDetail.deaths.toString()

        holder.singleView.textView_new_recovered.text = "+ $newRecovered"
        holder.singleView.textView_new_deaths.text = "+ $newDeaths"
        holder.singleView.textView_new_confirmed.text = "+ $newConfirmed"
    }
}

class CustomSingleViewHolder(val singleView: View): RecyclerView.ViewHolder(singleView) {

}