package com.aavidsoft.freshersbuddy.tutorials.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.databinding.TutorialBottomSheetBinding
import com.aavidsoft.freshersbuddy.tutorials.adapter.TutorialsCategoryAdapter
import com.aavidsoft.freshersbuddy.tutorials.models.ItemCategory
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsCategoryRepository
import com.aavidsoft.freshersbuddy.tutorials.viewmodel.TutorialsCategoryViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TutorialsCategoryFragment  : BottomSheetDialogFragment(){
    private lateinit var tutorialsCategoryViewModel: TutorialsCategoryViewModel
    private lateinit var tutorialsCategoryAdapter: TutorialsCategoryAdapter
    private lateinit var tutorialCategoryBinding : TutorialBottomSheetBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         tutorialCategoryBinding = TutorialBottomSheetBinding.inflate(layoutInflater)
        initViews()
        initViewModel()
        initAdapters()
        initListeners()
        initObservers()

        tutorialsCategoryViewModel.fetchTutorialCategory()
        return tutorialCategoryBinding.root
    }

    private fun initViews(){
        tutorialCategoryBinding.tutorialBottomSheetRecyclerview.layoutManager =
            GridLayoutManager(context,3)

    }
    private fun initAdapters(){
        tutorialsCategoryAdapter = TutorialsCategoryAdapter(tutorialsCategoryViewModel.tutorialCategory)
        tutorialCategoryBinding.tutorialBottomSheetRecyclerview.adapter = tutorialsCategoryAdapter
    }
    private fun initListeners(){
        tutorialCategoryBinding.tutorialBottomSheetRecyclerview.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                private var isScrollingUp: Boolean = false
                private var isScrollingDown: Boolean = false
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    isScrollingUp = dy > 0
                    isScrollingDown = dy <= -1
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (isScrollingDown) {
                        if (!tutorialsCategoryViewModel.isFetchingTutorialCategory) {
                            if (tutorialsCategoryViewModel.hasMoreData) {
                                tutorialsCategoryViewModel.fetchTutorialCategory()
                            }
                        }
                    } else if (isScrollingUp && lastVisibleItemPosition == totalItemCount - 1) {
                        if (!tutorialsCategoryViewModel.isFetchingTutorialCategory) {
                            if (tutorialsCategoryViewModel.hasMoreData) {
                                tutorialsCategoryViewModel.fetchTutorialCategory()
                            }
                        }
                    }
                }
            }
        )

//        tutorialsCategoryAdapter.onItemsClickListener = object : TutorialsCategoryAdapter.OnItemsClickListener{
//            override fun onItemsClick(
//                tutorialCategory: ItemCategory,
//                position: Int,
//                tutorialsCategoryAdapter: TutorialsCategoryAdapter
//            ) {
//                showTutorialCategory(tutorialCategory)
//            }
//        }
//    }
//    private fun showTutorialCategory(tutorialCategory: ItemCategory){
//        val tutorialsCategoryFragment = TutorialsCategoryFragment()
//        var bundle = Bundle()
//        bundle.putSerializable("tutorialCategory",tutorialCategory.categoryId)
//        tutorialsCategoryFragment.arguments = bundle
//
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.main,)
//            .addToBackStack(null)
//            .commit()
    }
    private fun initViewModel(){
        tutorialsCategoryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                TutorialsCategoryRepository(
                    TutorialsApiService.getInstance()
                )
            )
        )[TutorialsCategoryViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers(){
        tutorialsCategoryViewModel.tutorialCategoryUpdateAvailableLiveData.observe(viewLifecycleOwner){
            if(it){
                tutorialsCategoryAdapter.notifyDataSetChanged()
            }
        }
    }
}