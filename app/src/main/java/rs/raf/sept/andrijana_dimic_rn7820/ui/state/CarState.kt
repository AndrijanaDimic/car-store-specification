package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserRoom

sealed class CarState {
    object Loading: CarState()
    object DataFetched: CarState()
    data class Success(val cars: List<CarPresentation>): CarState()
    data class Error(val message: String): CarState()
}