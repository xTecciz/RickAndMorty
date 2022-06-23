package com.teckzi.rickandmorty.presentation.screens.locationdetailscreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.domain.model.CharacterModel
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentLocationDetailBinding
import com.teckzi.rickandmorty.di.Injector
import com.teckzi.rickandmorty.presentation.adapters.DetailsAdapter
import com.teckzi.rickandmorty.util.Constants.LOCATION_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class LocationDetailFragment : Fragment(R.layout.fragment_location_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var charactersAdapter: DetailsAdapter
    private val viewModel by viewModels<LocationDetailViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentLocationDetailBinding::bind)
    private val args by navArgs<LocationDetailFragmentArgs>()

    override fun onAttach(context: Context) {
        Injector.getLocationDetailComponent().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocation(args.locationId)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedLocation.collectLatest { location ->
                location?.let { it ->
                    loadLocationData(
                        name = it.name,
                        type = it.type,
                        dimension = it.dimension
                    )
                }
            }
        }
        getCharacters()
        navigateToAllCharacters()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadLocationData(
        name: String,
        type: String,
        dimension: String,
    ) {
        with(binding) {
            locationDetailsName.text = name
            locationDetailsType.text = type
            locationDetailsDimension.text = dimension
        }
    }

    private fun initRecyclerView(characterList: List<CharacterModel>) {
        charactersAdapter = DetailsAdapter(requireContext(), characterList, LOCATION_TYPE)
        with(binding) {
            characterTitle.text =
                resources.getString(R.string.character_number, characterList.size)
            locationDetailsRecycler.layoutManager = GridLayoutManager(context, 2)
            locationDetailsRecycler.adapter = charactersAdapter
        }
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            viewModel.characterList.collectLatest {
                initRecyclerView(it as List<CharacterModel>)
            }
        }
    }

    private fun navigateToAllCharacters() {
        binding.allCharacters.setOnClickListener {
            findNavController().navigate(R.id.action_locationDetailFragment_to_characterFragment)
        }
    }
}