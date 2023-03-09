package rs.raf.sept.andrijana_dimic_rn7820.ui.state

import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.data.models.SellerPresentation

sealed class SellerState {
    object Loading: SellerState()
    object DataFetched: SellerState()
    data class Success(val seller: SellerPresentation ): SellerState()
    data class Error(val message: String): SellerState()
}