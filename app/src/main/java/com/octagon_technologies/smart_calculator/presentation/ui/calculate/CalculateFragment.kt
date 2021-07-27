package com.octagon_technologies.smart_calculator.presentation.ui.calculate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.octagon_technologies.smart_calculator.R
import com.octagon_technologies.smart_calculator.databinding.FragmentCalculateBinding
import com.octagon_technologies.smart_calculator.presentation.ui.calculate.each_history.EachHistoryGroup
import com.octagon_technologies.smart_calculator.presentation.views.KeyView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CalculateFragment : Fragment(R.layout.fragment_calculate) {

    private lateinit var binding: FragmentCalculateBinding
    private lateinit var bottomSheet: BottomSheetBehavior<View>

    private val viewModel by viewModels<CalculateViewModel>()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculateBinding.bind(view)

        initFragment()
        initRecyclerView()
        initLiveData()

        setUpKeypad(view)
        setUpSwipeListener()
    }

    private fun initFragment() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.theme = viewModel.theme
    }

    private fun initRecyclerView() {
        binding.historyRecyclerview.also { history ->
            history.adapter = groupAdapter
            history.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initLiveData() {
        viewModel.listOfExpressions.observe(viewLifecycleOwner) { listOfMathExpression ->
            lifecycleScope.launch(Dispatchers.IO) {
                val listOfHistoryGroup = listOfMathExpression.map { mathExpression ->
                    EachHistoryGroup(viewModel.theme, viewLifecycleOwner, mathExpression)
                }
                withContext(Dispatchers.Main) {
                    groupAdapter.addAll(listOfHistoryGroup)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpSwipeListener() {
        bottomSheet = BottomSheetBehavior.from(binding.keypad)
        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                val isCollapsed = newState == BottomSheetBehavior.STATE_COLLAPSED
                binding.bottomTab.visibility = if (isCollapsed) View.VISIBLE else View.GONE
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun setUpKeypad(view: View) {
        val listOfKeys = listOf(
            R.id.key_divide,
            R.id.key_7, R.id.key_8, R.id.key_9, R.id.key_multiply,
            R.id.key_4, R.id.key_5, R.id.key_6, R.id.key_minus,
            R.id.key_1, R.id.key_2, R.id.key_3, R.id.key_add,
            R.id.key_0, R.id.key_00, R.id.key_decimal
        )
        listOfKeys

        setUpTopKeys()

        listOfKeys.forEach { layoutId ->
            val keyView: KeyView = view.findViewById(layoutId)
            keyView.setOnClickListener { viewModel.updateExpression(keyView.key) }
        }

        binding.keyEqual.setOnClickListener { viewModel.evaluate() }
    }

    private fun setUpTopKeys() {
        binding.keyC.setOnClickListener { viewModel.clearExpression() }
        binding.keyNegative.setOnClickListener { viewModel.turnToNegative() }
        binding.keyGoBack.setOnClickListener { viewModel.goBack() }
    }

}