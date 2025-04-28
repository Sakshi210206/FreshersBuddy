package com.aavidsoft.freshersbuddy.tutorials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.databinding.ItemTutorialBottomSheetBinding
import com.aavidsoft.freshersbuddy.tutorials.models.ItemCategory

class TutorialsCategoryAdapter(private var tutorialCategoryList : ArrayList<ItemCategory>
): RecyclerView.Adapter<TutorialsCategoryAdapter.TutorialCategoryViewHolder>() {

    interface OnItemsClickListener{
        fun onItemsClick(
            tutorialCategory : ItemCategory,
            position: Int,
            tutorialsCategoryAdapter: TutorialsCategoryAdapter
        )
    }

    var onItemsClickListener: OnItemsClickListener? = null

    inner class TutorialCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tutorialCategoryBinding = ItemTutorialBottomSheetBinding.bind(view)

        init {
            tutorialCategoryBinding.root.setOnClickListener{
                onItemsClickListener?.onItemsClick(
                    tutorialCategoryList[adapterPosition],
                    adapterPosition,
                    this@TutorialsCategoryAdapter
                )
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorialsCategoryAdapter.TutorialCategoryViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_tutorial_bottom_sheet,parent,false)
        return TutorialCategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TutorialsCategoryAdapter.TutorialCategoryViewHolder,
        position: Int
    ) {
        holder.tutorialCategoryBinding.tutorialCategoryObject = tutorialCategoryList[position]
    }

    override fun getItemCount(): Int = tutorialCategoryList.size

}