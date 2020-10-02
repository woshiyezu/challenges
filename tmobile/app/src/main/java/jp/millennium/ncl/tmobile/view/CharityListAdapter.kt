package jp.millennium.ncl.tmobile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.millennium.ncl.tmobile.R
import jp.millennium.ncl.tmobile.databinding.ItemCharityBinding
import jp.millennium.ncl.tmobile.model.Charity

class CharityListAdapter(val charityList: ArrayList<Charity>) : RecyclerView.Adapter<CharityListAdapter.CharityViewHolder>() {

    fun updateCharityList(newDogsList: List<Charity>) {
        charityList.clear()
        charityList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCharityBinding>(inflater, R.layout.item_charity, parent, false)
        return CharityViewHolder(view)
    }

    override fun getItemCount(): Int = charityList.size


    override fun onBindViewHolder(holder: CharityViewHolder, position: Int) {
        holder.bindData(charityList[position])
    }

    class CharityViewHolder(var view: ItemCharityBinding) : RecyclerView.ViewHolder(view.root) {

        fun bindData(item: Charity) {
            view.charity = item
        }
    }

}