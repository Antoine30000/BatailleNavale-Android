package com.example.bataillenavale

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class GridAdapter(private val context: Context, private val gridSize: Int) : BaseAdapter() {
    private val gridItems = Array(gridSize * gridSize) { 0 }
    private val hiddenShips = Array(gridSize * gridSize) { 0 }

    override fun getCount(): Int = gridItems.size

    override fun getItem(position: Int): Any = gridItems[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView = convertView as ImageView? ?: ImageView(context).apply {
            layoutParams = ViewGroup.LayoutParams(85, 85)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        imageView.setImageResource(gridItems[position])
        return imageView
    }

    fun setItem(position: Int, resource: Int) {
        gridItems[position] = resource
        hiddenShips[position] = resource
        notifyDataSetChanged()
    }

    fun hideShips() {
        for (i in hiddenShips.indices) {
            if (hiddenShips[i] == R.drawable.boat) {
                gridItems[i] = 0 // or some other drawable representing hidden state
            }
        }
        notifyDataSetChanged()
    }
}
