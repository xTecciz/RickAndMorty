package com.teckzi.rickandmorty.presentation.screens.locationscreen

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentLocationBinding
import com.teckzi.rickandmorty.di.Injector
import com.teckzi.rickandmorty.presentation.adapters.LoaderStateAdapter
import com.teckzi.rickandmorty.presentation.adapters.LocationAdapter
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_LOCATION
import com.teckzi.rickandmorty.util.Constants.FILTER_TYPE_ARGUMENT_KEY
import com.teckzi.rickandmorty.util.Constants.LOCATION_TYPE
import com.teckzi.rickandmorty.util.invisible
import com.teckzi.rickandmorty.util.visible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class LocationFragment : Fragment(R.layout.fragment_location), SearchView.OnQueryTextListener,
    SwipyRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var locationAdapter: LocationAdapter
    private val viewModel by viewModels<LocationViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentLocationBinding::bind)


    override fun onAttach(context: Context) {
        Injector.getLocationFragmentComponent().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "Locations"
        }
        setHasOptionsMenu(true)
        initRecyclerView()
        getLocations()
        getFilterResult()
        filterButton()
        binding.swipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun initRecyclerView() {
        locationAdapter = LocationAdapter(requireContext())
        with(binding) {
            locationMainRecyclerView.layoutManager = GridLayoutManager(context, 2)
            locationMainRecyclerView.adapter = locationAdapter.withLoadStateHeaderAndFooter(
                header = LoaderStateAdapter(),
                footer = LoaderStateAdapter()
            )
            locationAdapter.addLoadStateListener { state: CombinedLoadStates ->
                locationMainRecyclerView.isVisible = state.refresh != LoadState.Loading
                locationProgressBar.isVisible = state.refresh == LoadState.Loading
                if (locationAdapter.itemCount < 1) {
                    locationMainRecyclerView.invisible()
                    if (!locationProgressBar.isVisible) {
                        errorMessage.visible()
                        errorImage.visible()
                    } else {
                        errorMessage.invisible()
                        errorImage.invisible()
                    }
                }
            }
        }
    }

    private fun getLocations() {
        lifecycleScope.launch {
            viewModel.getAllLocations.collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }

    private fun filterButton() {
        binding.filterFloatingButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_locationFragment_to_bottomSheet,
                bundleOf(FILTER_TYPE_ARGUMENT_KEY to LOCATION_TYPE)
            )
        }
    }

    private fun getFilterResult() {
        arguments?.getString(FILTER_RETURN_BACK_TO_LOCATION)?.let {
            lifecycleScope.launch(context = Dispatchers.Main) {
                val (type, dimension) = it.split(",")
                searchLocation(type = type, dimension = dimension)
            }
        }
    }

    private fun searchLocation(
        name: String? = null,
        type: String? = null,
        dimension: String? = null
    ) {
        lifecycleScope.launch {
            viewModel.searchLocation(
                name = name,
                type = type,
                dimension = dimension
            )
            viewModel.searchLocation.collectLatest { pagingData ->
                locationAdapter.submitData(pagingData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView
        searchView?.queryHint = "Search location..."
        searchView?.setOnQueryTextListener(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView
        searchView?.isIconified = true
        searchView?.isIconified = true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) searchLocation(name = query)
        return true
    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        getLocations()
        binding.swipeRefreshLayout.isRefreshing = false
    }
}