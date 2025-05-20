package com.aavidsoft.freshersbuddy.tutorials.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.databinding.TutorialsBinding
import com.aavidsoft.freshersbuddy.tutorials.adapter.TutorialsAdapter
import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsRepository
import com.aavidsoft.freshersbuddy.tutorials.viewmodel.TutorialsViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory

class TutorialsFragment : Fragment() {
    private var id: Int = 0
    private lateinit var tutorialsAdapter: TutorialsAdapter
    private lateinit var  tutorialsViewModel: TutorialsViewModel
    private lateinit var  tutorialsFragmentBinding: TutorialsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tutorialsFragmentBinding = TutorialsBinding.inflate(layoutInflater,container,false)

        id = arguments?.getInt("id") ?: 0
        initViews()
        initViewModel()
        initAdapter()
        initListener()
        initObserver()

        tutorialsViewModel.fetchTutorialCategory(id)
        return tutorialsFragmentBinding.root
    }

    private fun initViews(){
        tutorialsFragmentBinding.recyclerSearchTutorial.layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
    private fun initAdapter(){
        tutorialsAdapter = TutorialsAdapter(tutorialsViewModel.tutorials)
        tutorialsFragmentBinding.recyclerSearchTutorial.adapter = tutorialsAdapter
    }
    private fun initListener(){
        tutorialsFragmentBinding.recyclerSearchTutorial.addOnScrollListener(object:
        RecyclerView.OnScrollListener(){
         private var isScrollingUp : Boolean = false
         private  var isScrollingDown: Boolean  = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                isScrollingUp = dy > 0
                isScrollingDown = dy <= -1
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (isScrollingDown){
                    if(!tutorialsViewModel.isFetchingTutorialCategory){
                        if(tutorialsViewModel.hasMoreData){
                            tutorialsViewModel.fetchTutorialCategory(id)
                        }
                    }
                }else if (isScrollingUp && lastVisibleItemPosition == totalItemCount ){
                    if(tutorialsViewModel.hasMoreData){
                        tutorialsViewModel.fetchTutorialCategory(id)
                    }
                }
            }
        }
        )

        tutorialsAdapter.onItemsClickListener = object  : TutorialsAdapter.OnItemsClickListener{
            override fun onItemsClick(
                itemsTutorial: ItemsTutorial,
                position: Int,
                tutorialsAdapter: TutorialsAdapter
            ) {
                showTutorialsDetails(itemsTutorial)
            }
        }
    }

    private fun showTutorialsDetails(itemsTutorial: ItemsTutorial){
        val tutorialsDetailsFragment =  TutorialsDetailsFragment()
        var bundle = Bundle()
        bundle.putSerializable("tutorialId",itemsTutorial.id)
        tutorialsDetailsFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.main,tutorialsDetailsFragment)
            .addToBackStack(null)
            .commit()
    }
    private fun initViewModel(){
        tutorialsViewModel = ViewModelProvider(
            this, ViewModelFactory(
                TutorialsRepository(
                    TutorialsApiService.getInstance()
                )
            )
        )[TutorialsViewModel :: class.java]
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        tutorialsViewModel.tutorialsUpdateAvailableLiveData.observe(viewLifecycleOwner){
            if(it){
                tutorialsAdapter.notifyDataSetChanged()
            }
        }
    }
}