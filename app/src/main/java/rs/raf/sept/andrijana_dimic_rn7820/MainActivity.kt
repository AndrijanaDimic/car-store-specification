package rs.raf.sept.andrijana_dimic_rn7820

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.avgust.andrijana_dimic_rn7820.modules.coreModule
import rs.raf.avgust.andrijana_dimic_rn7820.modules.userModule
import rs.raf.sept.andrijana_dimic_rn7820.databinding.ActivityMainBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.start.LogInActivity
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.FindUserState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        init()
    }

    private fun init(){
        initUi()
        initObservers()
    }
    private fun initUi(){

    }
    private fun initObservers(){
        mainViewModel.findUserState.observe(this, Observer {
            renderFindUserState(it)
        })
    }
    private fun renderFindUserState(findUserState: FindUserState) {
        when (findUserState) {
            is FindUserState.Success -> {
                val username = findUserState.user.username
                mainViewModel.saveAllCars(username)
            }
            is FindUserState.Error ->
                Toast.makeText(this, findUserState.message, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        Timber.e(menu.getItem(0).itemId.toString())
        menu.getItem(0).itemId
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout -> {
                mainViewModel.logout()
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}