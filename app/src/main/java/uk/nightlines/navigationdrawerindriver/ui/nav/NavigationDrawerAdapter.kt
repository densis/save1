package uk.nightlines.navigationdrawerindriver.ui.nav

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.holder_drawer_item.view.*
import uk.nightlines.navigationdrawerindriver.R

class NavigationDrawerAdapter(
    private val getItem: (Int) -> NavigationDrawerItem,
    private val itemCount: () -> Int,
    private val itemClicked: (NavigationDrawerItem) -> Unit
) : RecyclerView.Adapter<NavigationDrawerAdapter.NavigationViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NavigationViewHolder =
        NavigationViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.holder_drawer_item,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = itemCount()

    override fun onBindViewHolder(p0: NavigationViewHolder, p1: Int) {
        p0.bind(getItem(p1))
    }

    inner class NavigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: NavigationDrawerItem) = with(itemView) {
            clear()

            Glide.with(context)
                .load(item.imageUrl)
                .into(image)
            navigation_title.text = item.title

            itemView.setOnClickListener {
                itemClicked(item)
            }
        }

        private fun clear() = with(itemView) {
            Glide.with(context).clear(image)
            navigation_title.text = ""
        }
    }
}