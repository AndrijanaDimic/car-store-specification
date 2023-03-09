package rs.raf.sept.andrijana_dimic_rn7820.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.MainActivity
import rs.raf.sept.andrijana_dimic_rn7820.data.models.User
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserRoom
import rs.raf.sept.andrijana_dimic_rn7820.databinding.ActivityRegisterBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.RegisterState
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.UserState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        initUi()
        initObservers()
    }
    private fun initUi() {
        initListeners()
    }
    private fun initObservers() {
        mainViewModel.userState.observe(this, Observer {
            Timber.e(it.toString())
            renderStateServer(it)
        })
        mainViewModel.registerState.observe(this, Observer {
            Timber.e(it.toString())
            renderStateRoom(it)
        })

    }

    private fun renderStateServer(state: UserState) {
        when (state) {
            is UserState.Success -> {
                toastMessage(state.users.message)
            }
            is UserState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun renderStateRoom(state: RegisterState) {
        when (state) {
            is RegisterState.Success -> {
                Toast.makeText(this, "uspesna registracija", Toast.LENGTH_SHORT).show()
            }
            is RegisterState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initListeners() {
        binding.register.setOnClickListener() {
            val firstName  = binding.name.text.toString()
            val lastName = binding.lastName.text.toString()
            val country = binding.country.text.toString()
            val mobile = binding.phoneNumber.text.toString()
            val password = binding.password.text.toString()
            val username = binding.username.text.toString()

            if(firstName.isEmpty()){
                toastMessage("polje \"Ime\" je prazno")
            }
            else if(lastName.isEmpty()){
                toastMessage("polje \"Prezime\" je prazno")
            }
            else if(country.isEmpty()){
                toastMessage("polje \"Drzava prebivalista\" je prazno")
            }
            else if(mobile.isEmpty()){
                toastMessage("polje \"Broj telefona\" je prazno")
            }
            else if(password.isEmpty()){
                toastMessage("polje Lozinke je prazno")
            }
            else {
                mainViewModel.signup(User(firstName = username,  lastName = lastName, mobile= mobile, country = country))
                mainViewModel.insert(UserRoom(username = username,password = password, firstName  = firstName,  lastName = lastName, mobile= mobile, country = country, false))
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}