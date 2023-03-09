package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.data.models.SavedCars

sealed class SavedCarsState {
    object Loading: SavedCarsState()
    object DataFetched: SavedCarsState()
    data class Success(val cars: List<SavedCars>): SavedCarsState()
    data class Error(val message: String): SavedCarsState()
}