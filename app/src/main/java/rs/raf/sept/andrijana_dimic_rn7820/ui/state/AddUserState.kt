package rs.raf.avgust.andrijana_dimic_rn7820.ui.state

sealed class AddUserState {
    object Success: AddUserState()
    data class Error(val message: String): AddUserState()
}