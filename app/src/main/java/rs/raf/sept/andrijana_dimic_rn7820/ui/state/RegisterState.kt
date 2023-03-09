package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserRoom

sealed class RegisterState {
    object Success: RegisterState()
    data class Error(val message: String): RegisterState()
}