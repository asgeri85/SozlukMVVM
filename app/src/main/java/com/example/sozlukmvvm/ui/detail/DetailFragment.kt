package com.example.sozlukmvvm.ui.detail

import androidx.navigation.fragment.navArgs
import com.example.sozlukmvvm.base.BaseFragment
import com.example.sozlukmvvm.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding::inflate
) {

    private val args:DetailFragmentArgs by navArgs()
    override val viewModel: DetailViewModel by lazy { DetailViewModel() }

    override fun onCreateFinished() {
        binding.word=args.word
    }

    override fun observeEvents() {

    }

}