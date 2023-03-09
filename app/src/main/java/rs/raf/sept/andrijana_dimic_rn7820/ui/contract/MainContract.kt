package rs.raf.sept.andrijana_dimic_rn7820.ui.contract

import androidx.lifecycle.LiveData
import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.data.models.*
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.contact.ContactActivity
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.*
import javax.security.auth.login.LoginException

interface MainContract {

    interface ViewModel {
        val userState : LiveData<UserState>
        val addDone: LiveData<AddUserState>
        val logInState: LiveData<LogInState>
        val carState :  LiveData<CarState>
        val sellerState: LiveData<SellerState>
        val savedCarsState: LiveData<SavedCarsState>
        val findUserState: LiveData<FindUserState>
        val registerState: LiveData<RegisterState>
        val contactState :  LiveData<ContactState>
        val insertCarForSavingState:  LiveData<InsertCarForSavingState>

        fun signup(user: User)
        fun insert(userRoom: UserRoom)
        fun loginFromRoom(username: String)
        fun getCars()
        fun saveCar(savedCars: SavedCars)
        fun getSeller(id : Long)
        fun contactus(message: Message)
        fun getSavedCarsOrderByDate()
        fun deleteById(id: Long, username: String)
        fun deleteAndInsertAll(cars : List<SavedCars>)
        fun searchByName(name : String)
        fun searchByColor(color : String)
        fun searchByModel(model : String)
        fun searchByYear(year : String)
        fun setUserActivity(username: String, activity: Boolean)
        fun findActiveUser()
        fun saveAllCars(username: String)
        fun getAllCarsOrderByDate(username: String)
        fun logout()
        fun getCarsPagination(page: Int, limit: Int)
        fun login(user: LogInUser)
    }
}