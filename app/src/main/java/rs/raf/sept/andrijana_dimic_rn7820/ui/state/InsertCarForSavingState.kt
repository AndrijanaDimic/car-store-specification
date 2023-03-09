package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation

sealed class InsertCarForSavingState {
    object Success: InsertCarForSavingState()
    data class Error(val message: String): InsertCarForSavingState()
}