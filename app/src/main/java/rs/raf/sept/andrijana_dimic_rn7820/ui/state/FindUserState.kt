package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserRoom

sealed class FindUserState {
    object Loading: FindUserState()
    object DataFetched: FindUserState()
    data class Success(val user : UserRoom): FindUserState()
    data class Error(val message: String): FindUserState()
}