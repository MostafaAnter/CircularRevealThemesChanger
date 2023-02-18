package com.anter.circularrevealthemeschanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anter.circularrevealthemeschanger.databinding.FragmentFirstBinding
import com.anter.library_circular_reveal_theme_changer.library_implementation.ThemeChanger

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            ThemeChanger(requireActivity(), it).switchTheme()
        }
        binding.buttonSecond.setOnClickListener {
            ThemeChanger(requireActivity(), it).switchTheme()
        }
        binding.buttonThird.setOnClickListener {
            ThemeChanger(requireActivity(), it).switchTheme()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}