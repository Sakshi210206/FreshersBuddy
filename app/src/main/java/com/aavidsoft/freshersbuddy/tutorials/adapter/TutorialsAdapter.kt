package com.aavidsoft.freshersbuddy.tutorials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.databinding.TutorialsBinding
import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial

class TutorialsAdapter(
    private var tutorialsList: ArrayList<ItemsTutorial>
): RecyclerView.Adapter<TutorialsAdapter.TutorialsViewModel>(){

    inner class TutorialsViewModel(view: View):RecyclerView.ViewHolder(view){
        val tutorialsBinding : TutorialsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialsViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_tutorial_fragment,parent,false)
        return TutorialsViewModel(view)
    }

    override fun getItemCount(): Int = tutorialsList.size

    override fun onBindViewHolder(holder: TutorialsViewModel, position: Int) {
        val tutorials = tutorialsList[position]
        holder.tutorialsBinding.tutorialItemObject = tutorials
    }
}