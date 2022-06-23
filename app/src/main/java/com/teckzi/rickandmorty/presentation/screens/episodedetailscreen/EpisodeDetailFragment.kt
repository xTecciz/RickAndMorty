package com.teckzi.rickandmorty.presentation.screens.episodedetailscreen

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
import com.teckzi.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.teckzi.rickandmorty.di.Injector
import com.teckzi.rickandmorty.presentation.adapters.DetailsAdapter
import com.teckzi.rickandmorty.util.Constants.EPISODE_TYPE
import com.teckzi.rickandmorty.util.getEpisodeString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class EpisodeDetailFragment : Fragment(R.layout.fragment_episode_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailsAdapter: DetailsAdapter
    private val viewModel by viewModels<EpisodeDetailViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun onAttach(context: Context) {
        Injector.getEpisodeDetailComponent().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEpisode(args.episodeId)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedEpisode.collectLatest { episode ->
                episode?.let { it ->
                    loadEpisodeData(
                        name = it.name,
                        episode = it.episode,
                        airDate = it.airDate
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

    private fun loadEpisodeData(
        name: String,
        episode: String,
        airDate: String,
    ) {
        with(binding) {
            episodeDetailsName.text = name
            episodeDetailsEpisode.text = episode.getEpisodeString()
            episodeDetailsAirDate.text = airDate
        }
    }

    private fun initRecyclerView(characterList: List<CharacterModel>) {
        detailsAdapter = DetailsAdapter(requireContext(), characterList, EPISODE_TYPE)
        with(binding) {
            characterTitle.text =
                resources.getString(R.string.character_number, characterList.size)
            episodeDetailsRecycler.layoutManager = GridLayoutManager(context, 2)
            episodeDetailsRecycler.adapter = detailsAdapter
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
            findNavController().navigate(R.id.action_episodeDetailFragment_to_characterFragment)
        }
    }
}