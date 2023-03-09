package rs.raf.sept.andrijana_dimic_rn7820.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarEntity
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserEntity
import java.util.*

@Dao
abstract class UserDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(user: UserEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(cars: List<CarEntity>): Completable

    @Query ("SELECT * FROM users WHERE username == :username")
    abstract fun login (username : String): Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCar(carEntity: CarEntity): Completable

    @Query ("SELECT * FROM cars ORDER BY created_date asc")
    abstract fun getSavedCarsOrderByDate() : Observable<List<CarEntity>>

    @Query("DELETE FROM cars WHERE id == :id AND username == :username")
    abstract fun deleteById(id: Long, username: String) : Completable


    @Query("DELETE FROM cars")
    abstract fun deleteAll()

    @Query("UPDATE users SET active = :active WHERE username == :username")
    abstract fun setUserActivity(username: String, active:Boolean) : Completable

    @Query("UPDATE users SET active = 0 ")
    abstract fun logOut() : Completable

    @Query("SELECT * from  users WHERE active = 1")
    abstract fun findActiveUser() :  Single<UserEntity>


    @Query("UPDATE cars SET username = :username WHERE username = ''")
    abstract fun saveAllCars(username: String) : Completable

    @Query("SELECT * FROM cars WHERE username = :username ORDER BY created_date asc")
    abstract fun getAllCarsOrderByDate(username: String) : Observable<List<CarEntity>>
    @Transaction
    open fun deleteAndInsertAll(cars: List<CarEntity>) {
        deleteAll()
        insertAll(cars).blockingAwait()
    }

}