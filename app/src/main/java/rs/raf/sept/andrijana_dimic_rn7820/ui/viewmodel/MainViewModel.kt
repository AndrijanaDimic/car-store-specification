package rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.data.models.*
import rs.raf.sept.andrijana_dimic_rn7820.data.repositories.UserRepository
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.*
import timber.log.Timber


class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel(), MainContract.ViewModel{
    private val subscriptions = CompositeDisposable()
    override val userState: MutableLiveData<UserState> = MutableLiveData()
    override val addDone: MutableLiveData<AddUserState> = MutableLiveData()
    override val logInState: MutableLiveData<LogInState> = MutableLiveData()
    override val carState: MutableLiveData<CarState> = MutableLiveData()
    override val sellerState: MutableLiveData<SellerState> = MutableLiveData()
    override val savedCarsState: MutableLiveData<SavedCarsState> = MutableLiveData()
    override val findUserState: MutableLiveData<FindUserState> = MutableLiveData()
    override val registerState: MutableLiveData<RegisterState> = MutableLiveData()
    override val contactState: MutableLiveData<ContactState> = MutableLiveData()
    override val insertCarForSavingState: MutableLiveData<InsertCarForSavingState> = MutableLiveData()


    override fun signup(user: User) {
        val subscription = userRepository
            .signup(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    userState.value = UserState.Success(it)
                },
                {
                    userState.value = UserState.Error("Server: HTTP 400")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun insert(userRoom: UserRoom) {
        val subscription = userRepository
            .insert(userRoom)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                   registerState.value = RegisterState.Success
                },
                {
                    registerState.value = RegisterState.Error("Neuspesno registrovanje")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun loginFromRoom(username: String) {
        val subscription = userRepository
            .loginFromRoom(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    logInState.value = LogInState.Success(it)
                },
                {
                    logInState.value = LogInState.Error("Korinsik sa ovim username-om nije registrovan")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getSeller(id: Long) {
        val subscription = userRepository
            .getSeller(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    sellerState.value = SellerState.Success(it)
                },
                {
                    sellerState.value = SellerState.Error("Greska prilikom pristupa prodavcu")
                    Timber.e(it)
                }
            )
    }

    override fun contactus(message: Message) {
        val subscription = userRepository
            .contactus(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    contactState.value = ContactState.Success
                },
                {
                    contactState.value = ContactState.Error("Server vraca gresku, pokusajte ponovo kasnije")
                    Timber.e(it)
                }
            )
    }

    override fun getSavedCarsOrderByDate() {
        val subscription = userRepository
            .getSavedCarsOrderByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    savedCarsState.value = SavedCarsState.Success(it)
                },
                {
                    savedCarsState.value = SavedCarsState.Error("Greska")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)

    }

    override fun deleteById(id: Long, username: String) {
        val subscription = userRepository
            .deleteById(id, username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    addDone.value = AddUserState.Success
                },
                {
                    addDone.value = AddUserState.Error("Doslo je do greske")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun deleteAndInsertAll(cars: List<SavedCars>) {
        val subscription = userRepository
            .deleteAndInsertAll(cars)
    }

    override fun searchByName(name: String) {
        val subscription = userRepository
            .searchByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Ne postoji automobil pod tim nazivom.")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)

    }

    override fun searchByColor(color: String) {
        val subscription = userRepository
            .searchByColor(color)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Ne postoji automobil sa tom bojom.")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun searchByModel(model: String) {
        val subscription = userRepository
            .searchByModel(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Ne postoji automobil sa tim modelom.")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun searchByYear(year: String) {
        val subscription = userRepository
            .searchByYear(year)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Ne postoji automobil proizveden te godine.")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)

    }

    override fun setUserActivity(username: String, activity: Boolean) {
        val subscription = userRepository
            .setUserActivity(username, activity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
//                    addDone.value = AddUserState.Success
                },
                {
//                    addDone.value = AddUserState.Error("Automobil nije sačuvan, došlo je do greške")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun findActiveUser() {
        val subscription = userRepository
            .findActiveUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    findUserState.value = FindUserState.Success(it)
                },
                {
                    findUserState.value = FindUserState.Error("Ne postoji aktivan user ")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun saveAllCars(username: String) {
        val subscription = userRepository
            .saveAllCars(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
//                    addDone.value = AddUserState.Success
                },
                {
//                    addDone.value = AddUserState.Error("Automobil nije sačuvan, došlo je do greške")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getAllCarsOrderByDate(username: String) {
        val subscription = userRepository
            .getAllCarsOrderByDate(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    savedCarsState.value = SavedCarsState.Success(it)
                },
                {
                    savedCarsState.value = SavedCarsState.Error("Greska")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun logout() {
        val subscription = userRepository
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
//                    savedCarsState.value = SavedCarsState.Success(it)
                },
                {
//                    savedCarsState.value = SavedCarsState.Error("Greska")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getCarsPagination(page:Int, limit:Int) {
        val subscription = userRepository
            .getCarsPagination(page, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Greska")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun login(user: LogInUser) {
        val subscription = userRepository
            .login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    logInState.value = LogInState.Error("Server : Status 400")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getCars() {
        val subscription = userRepository
            .getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Greska")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun saveCar(savedCars: SavedCars) {
        val subscription = userRepository
            .saveCar(savedCars)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                   insertCarForSavingState.value = InsertCarForSavingState.Success
                },
                {
                    insertCarForSavingState.value = InsertCarForSavingState.Error("Automobil nije sačuvan, došlo je do greške")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

}