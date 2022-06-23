package com.teckzi.rickandmorty.presentation.common.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentBottomSheetBinding
import com.teckzi.rickandmorty.util.Constants.CHARACTER_TYPE
import com.teckzi.rickandmorty.util.Constants.EPISODE_TYPE
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_CHARACTER
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_EPISODE
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_LOCATION
import com.teckzi.rickandmorty.util.Constants.FILTER_TYPE_ARGUMENT_KEY
import com.teckzi.rickandmorty.util.Constants.LOCATION_TYPE
import com.teckzi.rickandmorty.util.gone
import com.teckzi.rickandmorty.util.setStringToSeason
import com.teckzi.rickandmorty.util.visible

class BottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var sheetKey = "character"
    private var characterStatusChip: String? = ""
    private var characterGenderChip: String? = ""
    private var characterSpeciesText: String? = ""
    private var characterTypeText: String? = ""
    private var locationType: String? = ""
    private var locationDimension: String? = ""
    private var season: String? = ""
    private var episode: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val args = arguments?.getString(FILTER_TYPE_ARGUMENT_KEY)
        sheetKey = args.toString()
        when (sheetKey) {
            CHARACTER_TYPE -> {
                characterFilter()
            }
            LOCATION_TYPE -> {
                locationFilter()
            }
            EPISODE_TYPE -> {
                episodeFilter()
            }
        }
        binding.buttonApplyBottomSheet.setOnClickListener {
            when (sheetKey) {
                CHARACTER_TYPE -> {
                    findNavController().navigate(
                        R.id.action_bottomSheet_to_characterFragment,
                        bundleOf(FILTER_RETURN_BACK_TO_CHARACTER to "$characterStatusChip,$characterSpeciesText,$characterTypeText,$characterGenderChip")
                    )
                }
                LOCATION_TYPE -> {
                    findNavController().navigate(
                        R.id.action_bottomSheet_to_locationFragment,
                        bundleOf(FILTER_RETURN_BACK_TO_LOCATION to "$locationType,$locationDimension")
                    )
                }
                EPISODE_TYPE -> {
                    findNavController().navigate(
                        R.id.action_bottomSheet_to_episodeFragment,
                        bundleOf(FILTER_RETURN_BACK_TO_EPISODE to "$season$episode")
                    )
                }
            }
        }
        return binding.root
    }

    private fun characterFilter() {
        binding.chipGroupStatus.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val text = chip.text.toString().lowercase()
            characterStatusChip = if (text != "") text else null
        }

        binding.chipGroupGender.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val text = chip.text.toString().lowercase()
            characterGenderChip = if (text != "") text else null
        }

        binding.editTextSpecies.doAfterTextChanged {
            val text = it.toString()
            characterSpeciesText = if (text != "") text else null
        }

        binding.editTextType.doAfterTextChanged {
            val text = it.toString()
            characterTypeText = if (text != "") text else null
        }
    }

    private fun locationFilter() {
        locationFilterViews()
        binding.textViewSpecies.text = resources.getString(R.string.type)
        binding.editTextSpecies.apply {
            hint = "Type"
            doAfterTextChanged {
                locationType = it.toString()
            }
        }
        binding.textViewType.text =
            resources.getString(R.string.dimension)
        binding.editTextType.apply {
            hint = "Dimension"
            doAfterTextChanged {
                locationDimension = it.toString()
            }
        }
    }

    private fun episodeFilter() {
        episodeFilterViews()
        val seasonAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.season_list)
        )
        seasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSeason.adapter = seasonAdapter

        binding.spinnerSeason.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                season = when (parent?.getItemAtPosition(position)) {
                    "All seasons" -> ""
                    else -> "${parent?.getItemAtPosition(position)}".setStringToSeason()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val episodeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.episode_list)
        )

        episodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEpisode.adapter = episodeAdapter
        binding.spinnerEpisode.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    episode = when (parent?.getItemAtPosition(position)) {
                        "All episodes" -> ""
                        else -> "${parent?.getItemAtPosition(position)}".setStringToSeason()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun locationFilterViews() {
        binding.textViewStatus.gone()
        binding.scrollViewStatus.gone()
        binding.textViewGender.gone()
        binding.scrollViewGender.gone()
    }

    private fun episodeFilterViews() {
        locationFilterViews()
        binding.editTextSpecies.gone()
        binding.textViewSpecies.gone()
        binding.textViewType.gone()
        binding.editTextType.gone()
        binding.textViewEpisode.visible()
        binding.spinnerSeason.visible()
        binding.spinnerEpisode.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}