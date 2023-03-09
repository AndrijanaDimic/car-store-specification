package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserResponse


sealed class UserState {
    object Loading: UserState()
    object DataFetched: UserState()
    data class Success(val users: UserResponse): UserState()
    data class Error(val message: String): UserState()
}