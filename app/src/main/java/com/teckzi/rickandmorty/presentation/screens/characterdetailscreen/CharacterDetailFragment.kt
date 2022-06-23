package com.teckzi.rickandmorty.presentation.screens.characterdetailscreen

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.teckzi.rickandmorty.di.Injector
import com.teckzi.rickandmorty.presentation.adapters.CharacterDetailsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var episodeAdapter: CharacterDetailsAdapter
    private val viewModel by viewModels<CharacterDetailViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun onAttach(context: Context) {
        Injector.getCharacterDetailComponent().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacter(args.characterId)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedCharacter.collectLatest { character ->
                character?.let { it ->
                    loadCharacterData(
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        gender = it.gender,
                        image = it.image,
                        origin = it.origin,
                        location = it.location
                    )
                }
            }
        }
        getEpisodes()
        navigateToAllEpisodes()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        loadOriginId()
    }

    private fun loadCharacterData(
        name: String,
        status: String,
        species: String,
        gender: String,
        image: String,
        origin: String,
        location: String
    ) {
        with(binding) {
            characterDetailImage.load(image) {
                transformations(CircleCropTransformation())
            }
            backgroundImage.load(image) {
                transformations(BlurTransformation(requireContext(), 6f))
            }
            characterDetailName.text = name
            characterDetailStatus.text = status
            when (status) {
                "Alive" -> characterDetailStatus.setTextColor(Color.parseColor("#2EED0C"))
                "Dead" -> characterDetailStatus.setTextColor(Color.parseColor("#FF0000"))
                "unknown" -> characterDetailStatus.setTextColor(Color.parseColor("#C4FFFFFF"))
            }
            characterDetailGender.text = gender
            characterDetailSpecies.text = species
            characterDetailOrigin.text = origin
            characterDetailLocation.text = location
        }
    }

    private fun initRecyclerView(episodeList: List<EpisodeModel>) {
        episodeAdapter = CharacterDetailsAdapter(requireContext(), episodeList)
        with(binding) {
            episodesTitle.text = resources.getString(R.string.episode_number, episodeList.size)
            characterDetailEpisodes.layoutManager = LinearLayoutManager(requireContext())
            characterDetailEpisodes.adapter = episodeAdapter
        }
    }

    private fun getEpisodes() {
        lifecycleScope.launch {
            viewModel.episodeList.collectLatest {
                initRecyclerView(it as List<EpisodeModel>)
            }
        }
    }

    private fun navigateToAllEpisodes() {
        binding.allEpisodes.setOnClickListener {
            findNavController().navigate(R.id.action_characterDetailFragment_to_episodeFragment)
        }
    }

    private fun loadOriginId() {
        lifecycleScope.launch {
            viewModel.characterOrigin.collectLatest { locationModel ->
                if (locationModel?.id != null) {
                    binding.originButton.setOnClickListener {
                        val action =
                            CharacterDetailFragmentDirections.actionCharacterDetailFragmentToLocationDetailFragment(
                                locationId = locationModel.id
                            )
                        requireView().findNavController().navigate(action)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.characterLocation.collectLatest { locationModel ->
                if (locationModel?.id != null) {
                    binding.locationButton.setOnClickListener {
                        val action =
                            CharacterDetailFragmentDirections.actionCharacterDetailFragmentToLocationDetailFragment(
                                locationId = locationModel.id
                            )
                        requireView().findNavController().navigate(action)
                    }
                }
            }
        }
    }
}