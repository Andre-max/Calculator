package com.octagon_technologies.smart_calculator.presentation.ui.calculate

import android.content.Context
import androidx.lifecycle.*
import com.octagon_technologies.smart_calculator.datastore.ThemeDataStore
import com.octagon_technologies.smart_calculator.math.MiniMath
import com.octagon_technologies.smart_calculator.model.MathExpression
import com.octagon_technologies.smart_calculator.model.Theme
import com.octagon_technologies.smart_calculator.repo.MathRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel @Inject constructor(
    private val themeDataStore: ThemeDataStore,
    private val mathRepo: MathRepo
    ) : ViewModel() {

    private val miniMath = MiniMath()

    val theme by lazy { themeDataStore.fetchTheme().asLiveData(Dispatchers.IO) }

    private val _listOfExpressions = MutableLiveData<List<MathExpression>>()
    val listOfExpressions: LiveData<List<MathExpression>> = _listOfExpressions

    private val _formerExpression = MutableLiveData("")
    val formerExpression: LiveData<String> = _formerExpression

    private val _expression = MutableLiveData("")
    val expression: LiveData<String> = _expression

    init {
        fetchMathExpressions()
    }

    fun changeTheme(newTheme: Theme) {
        viewModelScope.launch {
            if (newTheme != theme.value) themeDataStore.saveTheme(newTheme)
        }
    }

    fun updateExpression(key: String) {
        _expression.value += key
    }

    fun evaluate() {
        val calculate = ExpressionBuilder(_expression.value)
            .build()
        calculate.validate()

        val result = calculate.evaluate()
        _formerExpression.value = expression.value
        _expression.value = result.toString()

        saveExpression(formerExpression.value, result)
    }

    private fun saveExpression(expression: String?, result: Double) {
        viewModelScope.launch { mathRepo.saveMathExpression(expression ?: "", result.toString()) }
    }

    fun goBack() { _expression.value?.dropLast(1) }

    fun turnToNegative() =
        miniMath.turnToNegative(expression.value ?: "")

    fun clearExpression() {
        _formerExpression.value = ""
        _expression.value = "0"
    }

    private fun fetchMathExpressions() {
        viewModelScope.launch {
            mathRepo
                .fetchMathExpressions()
                .onEach {
                    Timber.d("listOfMathExpression.size is ${it.size}")
                    _listOfExpressions.value = it
                }
                .launchIn(viewModelScope)
        }
    }
}