package rs.raf.sept.andrijana_dimic_rn7820.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import rs.raf.avgust.andrijana_dimic_rn7820.modules.coreModule
import rs.raf.avgust.andrijana_dimic_rn7820.modules.userModule
import rs.raf.sept.andrijana_dimic_rn7820.MainActivity
import rs.raf.sept.andrijana_dimic_rn7820.data.models.LogInUser
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserRoom
import rs.raf.sept.andrijana_dimic_rn7820.databinding.ActivityLogInBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.LogInState
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.UserState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber

class LogInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLogInBinding
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        stopKoin()
        init()
    }
    private fun init() {
        initTimber()
        initKoin()
        initUi()
        initObservers()
    }
    private fun initObservers() {
        mainViewModel.logInState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
    }
    private fun renderState(state: LogInState) {
        when (state) {

            is LogInState.Success -> {

                val passwordFromScreen = binding.password.text.toString()
                val passwordFromRoom = state.user.password

                if(passwordFromScreen.lowercase().equals(passwordFromRoom.lowercase())){

                    mainViewModel.setUserActivity(state.user.username, true)

                    val intent = Intent(this, SplashScreenActivity::class.java)
                    startActivity(intent)

                }else {
                    Toast.makeText(this, "Lozinka nije ispravna", Toast.LENGTH_SHORT).show()
                }
            }
            is LogInState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initKoin() {
        val modules = listOf(
            coreModule,
            userModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@LogInActivity)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }

    }
    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
    private fun initUi() {
       initListeners()
    }
    private fun initListeners(){
        binding.signUp.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.signIn.setOnClickListener() {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if(username.isEmpty()){
                toastMessage("polje 'korisnik' je prazno")
            }
            else if(password.isEmpty()){
                toastMessage("polje 'Lozinka' je prazno")
            }else {

                mainViewModel.loginFromRoom(username)
                mainViewModel.login(LogInUser(username,password,true))
            }
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}