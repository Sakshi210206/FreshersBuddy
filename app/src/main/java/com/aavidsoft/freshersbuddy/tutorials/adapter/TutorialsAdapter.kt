package com.aavidsoft.freshersbuddy.tutorials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.databinding.ItemTutorialFragmentBinding
import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial

class TutorialsAdapter(
    private var tutorialsList: ArrayList<ItemsTutorial>
) : RecyclerView.Adapter<TutorialsAdapter.TutorialsViewHolder>() {

    interface OnItemsClickListener {
        fun onItemsClick(
            itemsTutorial: ItemsTutorial,
            position: Int,
            tutorialsAdapter: TutorialsAdapter
        )
    }

    var onItemsClickListener: OnItemsClickListener? = null

    inner class TutorialsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tutorialViewBinding = ItemTutorialFragmentBinding.bind(view)

        init {
            tutorialViewBinding.root.setOnClickListener {
                onItemsClickListener?.onItemsClick(
                    tutorialsList[adapterPosition],
                    adapterPosition,
                    this@TutorialsAdapter
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TutorialsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_tutorial_fragment, parent, false)
        return TutorialsViewHolder(view)
    }

    override fun getItemCount(): Int = tutorialsList.size

    override fun onBindViewHolder(
        holder: TutorialsViewHolder, position: Int
    ) {
        holder.tutorialViewBinding.tutorialItemsObject = tutorialsList[position]
    }
}
