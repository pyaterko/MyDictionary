package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.owl_laugh_at_wasted_time.mydictionary.R
import com.owl_laugh_at_wasted_time.mydictionary.databinding.FragmentDictionaryBinding
import com.owl_laugh_at_wasted_time.mydictionary.databinding.LoadingStateBinding
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.Loading
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.Success
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.viewBinding


class DictionaryFragment : BaseFragment(R.layout.fragment_dictionary) {

    private val binding by viewBinding(FragmentDictionaryBinding::bind)
    private val bindingLoading by viewBinding(LoadingStateBinding::bind)
    private val adapter: DictionaryAdapter by lazy { DictionaryAdapter() }
    private var showFavorite = false
    private lateinit var listWords: List<WordItem>
    private val viewModel by viewModels<DictionaryViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingLoading.tryAgainButtonLoadingState.setOnClickListener {

        }

        viewModel.listWords.observe(viewLifecycleOwner) {
            adapter.wordsItemList = it.toList()
            listWords = it.toList()
        }

        viewModel.liveData.observe(viewLifecycleOwner) { it ->

            when (it) {
                is Loading -> {
                    bindingLoading.progressBarLoadingState.visibility = View.VISIBLE
                }
                is Success -> {
                    bindingLoading.errorContainerLoadingState.visibility = View.INVISIBLE
                    bindingLoading.progressBarLoadingState.visibility = View.INVISIBLE
                    val listWordItem = it.data.map {
                        WordItem(
                            word = it.text!!,
                            translation = it.meanings?.get(0)?.translation?.translation!!
                        )
                    }
                    adapter.wordsItemList = listWordItem.toList()
                }
                is Error -> {
                }
            }
        }

        binding.recyclerViewListNotes.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewListNotes.adapter = adapter
        adapter.onItemClickListener = {
            displayAConfirmationDialog(requireContext(),
                getString(R.string.addfavorite_alert_message),
                actionPB1 = {
                    viewModel.addWordLearn(it)
                }
            )
        }

        setupSwipe(binding.recyclerViewListNotes){
            viewModel.deleteWord(adapter.wordsItemList[it.adapterPosition])
        }

        setSearch()
        binding.imageView.setOnClickListener {
            viewModel.addWordItem(
                WordItem(
                    binding.textViewEnWord.text.toString(),
                    binding.textViewRuWord.text.toString()
                )
            )
        }

    }

    private fun setSearch() {
        binding.searchNote.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (getConnectionType(requireContext()) != 0) {
                    binding.searchNote.onActionViewCollapsed()
                    viewModel.getData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val list = listWords.filter { it.word.contains(newText, true) }
                adapter.wordsItemList = list.toList()
                return true
            }
        })
    }


    private fun getConnectionType(context: Context): Int {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        result = 3
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = 2
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = 1
                    } else if (type == ConnectivityManager.TYPE_VPN) {
                        result = 3
                    }
                }
            }
        }
        return result
    }


    companion object {
        fun newInstance(): DictionaryFragment {
            return DictionaryFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}