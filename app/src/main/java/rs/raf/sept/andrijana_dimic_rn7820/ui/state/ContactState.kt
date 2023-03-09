package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState

sealed class ContactState {
    object Success: ContactState()
    data class Error(val message: String): ContactState()
}