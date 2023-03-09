package rs.raf.avgust.andrijana_dimic_rn7820.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.sept.andrijana_dimic_rn7820.data.datasources.local.UserDataBase
import rs.raf.sept.andrijana_dimic_rn7820.data.datasources.remote.UserService
import rs.raf.sept.andrijana_dimic_rn7820.data.repositories.UserRepository
import rs.raf.sept.andrijana_dimic_rn7820.data.repositories.UserRepositoryImpl
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel

val userModule = module {
    viewModel { MainViewModel(userRepository = get()) }

    single<UserRepository> { UserRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<UserDataBase>().getUserDao() }

    single<UserService> { create(retrofit = get()) }
}