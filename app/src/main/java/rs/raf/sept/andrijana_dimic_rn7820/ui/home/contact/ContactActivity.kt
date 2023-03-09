package rs.raf.sept.andrijana_dimic_rn7820.ui.home.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.databinding.ActivityContactBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.ContactState
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.SellerState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber

class ContactActivity : AppCompatActivity() {

    companion object EditActivity {
        @JvmStatic
        val ID = "id"
    }
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        initUi()
        initObservers()
    }
    private fun initObservers() {
        mainViewModel.sellerState.observe(this,   Observer{
            Timber.e(it.toString())
            renderSellerState(it)
        })
        mainViewModel.contactState.observe(this,   Observer{
            Timber.e(it.toString())
            renderContactState(it)
        })

    }
    private fun renderSellerState(state: SellerState) {
        when (state) {
            is SellerState.Success -> {
                binding.firstName.setText(state.seller.first_name)
                binding.lastName.setText(state.seller.last_name)
                binding.email.setText(state.seller.email)
                binding.phone.setText(state.seller.phone)
                val url = state.seller.avatar
                Picasso.get().load(url).into(binding.imageView)
            }
            is SellerState.Error -> {
                binding.send.isEnabled = true
                toastMessage(state.message)
            }
        }
    }
    private fun renderContactState(state: ContactState) {
        when (state) {
            is ContactState.Success -> {
                toastMessage("Poruka je poslata")
            }
            is ContactState.Error -> {
                toastMessage(state.message)
            }
        }
    }
    private fun initUi() {
        val intent = intent
        if (intent != null) {
            val id = intent.getLongExtra(ID, 0)
            mainViewModel.getSeller(id)
        }
        binding.send.setOnClickListener(){
            val first_name= binding.firstName.text.toString()
            val last_name = binding.lastName.text.toString()
            val message = binding.message.text.toString()
            val phone = binding.phoneNumber.text.toString()

            if(first_name.isEmpty()){
                toastMessage("Polje ime je prazno")
            }else if (last_name.isEmpty()){
                toastMessage("Polje Prezime je prazno")
            }else if (phone.isEmpty()){
                toastMessage("Polje broj telefona je prazno")
            }else {

                mainViewModel.contactus(
                    rs.raf.sept.andrijana_dimic_rn7820.data.models.Message(
                        first_name,
                        last_name,
                        message,
                        phone
                    )
                )
            }
            }
    }
    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
