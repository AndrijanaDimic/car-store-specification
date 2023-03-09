package rs.raf.sept.andrijana_dimic_rn7820.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.sept.andrijana_dimic_rn7820.data.datasources.local.UserDao
import rs.raf.sept.andrijana_dimic_rn7820.data.datasources.remote.UserService
import rs.raf.sept.andrijana_dimic_rn7820.data.models.*
import timber.log.Timber
import java.util.*

class UserRepositoryImpl(
    private val localDataSource: UserDao,
    private val remoteDataSource: UserService
) : UserRepository {
    override fun signup(user: User): Single<UserResponse>{
        return remoteDataSource.signup(user)
    }

    override fun insert(userRoom: UserRoom): Completable {
        val userEntity = UserEntity(
            userRoom.username,
            userRoom.password,
            userRoom.firstName,
            userRoom.lastName,
            userRoom.mobile,
            userRoom.country,
            userRoom.active
        )
        return localDataSource
            .insert(userEntity)
    }

    override fun loginFromRoom(username: String): Single<UserRoom> {

        return localDataSource.login(username).map {
                UserRoom(
                    it.username,
                    it.password,
                    it.firstName,
                    it.lastName,
                    it.mobile,
                    it.country,
                    it.active
                )
        }
    }

    override fun getCars(): Observable<List<CarPresentation>> {
        return remoteDataSource.getCars()
            .map {
                it.cars.map {
                    CarPresentation(
                        it.id,
                        it.car,
                        it.car_model,
                        it.car_color,
                        it.car_model_year,
                        it.car_vin,
                        it.price,
                        it.availability
                    )
                }
            }
    }

    override fun saveCar(savedCars: SavedCars): Completable {
        val carEntity = CarEntity(
            savedCars.id,
            savedCars.username,
            savedCars.car,
            savedCars.car_model,
            savedCars.car_color,
            savedCars.car_model_year,
            savedCars.car_vin,
            savedCars.price,
            savedCars.availability,
            savedCars.date
        )
        return localDataSource.insertCar(carEntity)
    }

    override fun getSeller(id: Long): Single<SellerPresentation> {
        return remoteDataSource.getSeller(id).map {
            SellerPresentation(
                it.sellerResponse.id,
                it.sellerResponse.first_name,
                it.sellerResponse.last_name,
                it.sellerResponse.email,
                it.sellerResponse.phone,
                it.sellerResponse.avatar
            )

        }
    }

    override fun contactus(message: Message): Completable {
        return remoteDataSource.contactus(message)
    }

    override fun getSavedCarsOrderByDate(): Observable<List<SavedCars>> {
       return localDataSource.getSavedCarsOrderByDate().map {
           it.map {
               SavedCars(
                   it.id,
                   it.username,
                   it.car,
                   it.car_model,
                   it.car_color,
                   it.car_model_year,
                   it.car_vin,
                   it.price,
                   it.availability,
                   it.created_date
               )
           }
       }
    }

    override fun deleteById(id: Long, username: String): Completable {
        return localDataSource.deleteById(id,username)
    }

    override fun deleteAndInsertAll(cars: List<SavedCars>) {
        val entities = cars.map {   CarEntity(
            it.id,
            it.username,
            it.car,
            it.car_model,
            it.car_color,
            it.car_model_year,
            it.car_vin,
            it.price,
            it.availability,
            it.date
        ) }
        return localDataSource.deleteAndInsertAll(entities)

    }

    override fun searchByName(name: String) : Observable<List<CarPresentation>>{
       return remoteDataSource.searchByName(name).map {
            it.cars.map {
                CarPresentation(
                    it.id,
                    it.car,
                    it.car_model,
                    it.car_color,
                    it.car_model_year,
                    it.car_vin,
                    it.price,
                    it.availability
                )
            }
       }
    }

    override fun searchByColor(color: String): Observable<List<CarPresentation>> {
        return remoteDataSource.searchByColor(color).map {
            it.cars.map {
                CarPresentation(
                    it.id,
                    it.car,
                    it.car_model,
                    it.car_color,
                    it.car_model_year,
                    it.car_vin,
                    it.price,
                    it.availability
                )
            }
        }
    }

    override fun searchByModel(model: String): Observable<List<CarPresentation>> {
        return remoteDataSource.searchByModel(model).map {
            it.cars.map {
                CarPresentation(
                    it.id,
                    it.car,
                    it.car_model,
                    it.car_color,
                    it.car_model_year,
                    it.car_vin,
                    it.price,
                    it.availability
                )
            }
        }
    }

    override fun searchByYear(year: String): Observable<List<CarPresentation>> {
        return remoteDataSource.searchByYear(year).map {
            it.cars.map {
                CarPresentation(
                    it.id,
                    it.car,
                    it.car_model,
                    it.car_color,
                    it.car_model_year,
                    it.car_vin,
                    it.price,
                    it.availability
                )
            }
        }
    }

    override fun setUserActivity(username: String, activity: Boolean) : Completable {
        return localDataSource.setUserActivity(username, activity)
    }

    override fun findActiveUser(): Single<UserRoom> {
        return localDataSource.findActiveUser().map {
            UserRoom(it.username,
                it.password,
                it.firstName,
                it.lastName,
                it.mobile,
                it.country,
                it.active
            ) }
    }

    override fun saveAllCars(username: String): Completable {
        return localDataSource.saveAllCars(username)
    }

    override fun getAllCarsOrderByDate(username: String): Observable<List<SavedCars>> {
        return localDataSource.getAllCarsOrderByDate(username = username).map {
            it.map {
                SavedCars(
                    it.id,
                    it.username,
                    it.car,
                    it.car_model,
                    it.car_color,
                    it.car_model_year,
                    it.car_vin,
                    it.price,
                    it.availability,
                    it.created_date
                )
            }
        }
    }

    override fun logout(): Completable {
        return localDataSource.logOut()
    }

    override fun getCarsPagination(page: Int, limit : Int): Observable<List<CarPresentation>> {
        return remoteDataSource.getCarsPagination(page, limit)
            .map {
                it.cars.map {
                    CarPresentation(
                        it.id,
                        it.car,
                        it.car_model,
                        it.car_color,
                        it.car_model_year,
                        it.car_vin,
                        it.price,
                        it.availability
                    )
                }
            }
    }

    override fun login(user: LogInUser) : Completable {
       return remoteDataSource.login(user)
    }
}