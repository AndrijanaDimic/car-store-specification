package rs.raf.sept.andrijana_dimic_rn7820.data.repositories


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.sept.andrijana_dimic_rn7820.data.models.*
import java.util.*

interface UserRepository {

    fun signup(user : User): Single <UserResponse>
    fun insert(userRoom: UserRoom): Completable
    fun loginFromRoom(username: String): Single <UserRoom>
    fun getCars(): Observable<List<CarPresentation>>
    fun saveCar(savedCars: SavedCars): Completable
    fun getSeller(id: Long): Single <SellerPresentation>
    fun contactus(message: Message): Completable
    fun getSavedCarsOrderByDate(): Observable<List<SavedCars>>
    fun deleteById(id : Long, username: String): Completable
    fun deleteAndInsertAll(cars : List<SavedCars>)
    fun searchByName(name : String) : Observable<List<CarPresentation>>
    fun searchByColor(color : String): Observable<List<CarPresentation>>
    fun searchByModel(model : String): Observable<List<CarPresentation>>
    fun searchByYear(year : String): Observable<List<CarPresentation>>
//    fun insertSavedCarsIdForUser(userSavedCarsRoom: UserSavedCarsRoom): Completable
    fun setUserActivity(username : String, activity: Boolean) : Completable
    fun findActiveUser() : Single<UserRoom>
    fun saveAllCars(username: String) : Completable
    fun getAllCarsOrderByDate(username: String): Observable<List<SavedCars>>
    fun logout():Completable
    fun getCarsPagination(page: Int, limit: Int): Observable<List<CarPresentation>>
    fun login(user: LogInUser): Completable
}