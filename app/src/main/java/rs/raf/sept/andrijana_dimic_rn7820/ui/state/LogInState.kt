package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserRoom

sealed class LogInState {
    object Loading: LogInState()
    object DataFetched: LogInState()
    data class Success(val user: UserRoom): LogInState()
    data class Error(val message: String): LogInState()
}