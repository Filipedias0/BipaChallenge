package com.example.bipachallenge.presentation.feature.nodeRankings.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bipachallenge.domain.model.NodeRankingData
import com.example.bipachallenge.domain.useCase.GetNodeRankingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NodeRankingsViewModel(private val getNodeRankingsUseCase: GetNodeRankingsUseCase) : ViewModel() {

    private val _nodeRankings = MutableStateFlow<List<NodeRankingData>>(emptyList())
    val nodeRankings: StateFlow<List<NodeRankingData>> = _nodeRankings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getNodeRankings() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _nodeRankings.value = getNodeRankingsUseCase.invoke()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
