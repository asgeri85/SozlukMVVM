package com.example.sozlukmvvm.ui.home

import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sozlukmvvm.R
import com.example.sozlukmvvm.adapters.WordAdapter
import com.example.sozlukmvvm.base.BaseFragment
import com.example.sozlukmvvm.databinding.FragmentHomeBinding
import com.example.sozlukmvvm.model.Kelimeler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate
),SearchView.OnQueryTextListener {

    private val wordAdapter:WordAdapter= WordAdapter()
    override val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreateFinished() {
        setHasOptionsMenu(true)
    }

    override fun observeEvents() {
        with(viewModel){
            wordData.observe(viewLifecycleOwner){
                it?.let {
                    it.kelimeler?.let { it1 -> setRecycler(it1) }
                }
            }

            loading.observe(viewLifecycleOwner){
                if (it){
                    binding.progressBar.visibility=View.VISIBLE
                    binding.rvHome.visibility=View.GONE
                }else{
                    binding.progressBar.visibility=View.GONE
                    binding.rvHome.visibility=View.VISIBLE
                }
            }

            error.observe(viewLifecycleOwner){
                it?.let {
                    Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setRecycler(list:List<Kelimeler>){
        wordAdapter.updateList(list)
        with(binding.rvHome){
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=wordAdapter
        }
        wordAdapter.onClick={
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bat_menu,menu)

        val item=menu.findItem(R.id.action_search)
        val searchView=item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.getSearchWord(it)
            return true
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.getSearchWord(it)
            return true
        }
        return false
    }

}