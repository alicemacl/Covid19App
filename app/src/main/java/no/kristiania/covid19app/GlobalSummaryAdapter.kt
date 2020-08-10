package no.kristiania.covid19app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.global_summary.view.*

class GlobalSummaryAdapter(private val globalSummary: Summary) : RecyclerView.Adapter<CustomGlobalViewHolder>() {
    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomGlobalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val globalCell = layoutInflater.inflate(R.layout.global_summary, parent, false)
        return CustomGlobalViewHolder(globalCell)
    }

    override fun onBindViewHolder(holder: CustomGlobalViewHolder, position: Int) {
        val globalStats = globalSummary.global

        // total confirmed
        holder.globalView.textView_global_new.text = globalStats.newConfirmed.toString()
        holder.globalView.textView_global_total.text = globalStats.totalConfirmed.toString()

        // deaths
        holder.globalView.textView_total_deaths.text = globalStats.totalDeaths.toString()
        holder.globalView.textView_new_deaths.text = globalStats.newDeaths.toString()

        // recovered
        holder.globalView.textView_total_recovered.text = globalStats.totalRecovered.toString()
        holder.globalView.textView_new_recovered.text = globalStats.newRecovered.toString()
    }
}

class CustomGlobalViewHolder(val globalView: View): RecyclerView.ViewHolder(globalView) {

}