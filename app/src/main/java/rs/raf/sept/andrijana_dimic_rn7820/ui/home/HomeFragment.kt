package rs.raf.sept.andrijana_dimic_rn7820.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.R
import rs.raf.sept.andrijana_dimic_rn7820.data.models.SavedCars
import rs.raf.sept.andrijana_dimic_rn7820.databinding.FragmentHomeBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.contact.ContactActivity
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter.CarAdapter
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter.SearchCarAdapter
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.CarState
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.InsertCarForSavingState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    var  _binding: FragmentHomeBinding? = null
    var page = 1
    var limit = 1
    var pressed : Boolean = false

    private val binding get() = _binding!!

    private lateinit var adapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
    }
    private fun initRecycler(){
        binding.list.layoutManager = LinearLayoutManager(context)
        adapter = CarAdapter()

        adapter.onClick = { item ->
            pressed = true

            if (item.availability) {

                val alertDialog = AlertDialog.Builder(activity)
                alertDialog.setTitle("")
                val items = arrayOf("Kontaktiraj prodavca", "Sačuvaj")
                val checkedItem = 0
                var clicked = 1
                alertDialog.setSingleChoiceItems(
                    items, checkedItem
                ) { dialog, which ->
                    when (which) {
                        0 -> clicked = 1
                        1 -> clicked = 2
                    }
                }

                alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
                    if(clicked == 1){
                        val intent = Intent(activity, ContactActivity::class.java)
                        val extras = Bundle()
                        extras.putLong(ContactActivity.ID, item.id)
                        intent.putExtras(extras)
                        startActivity(intent)
                    }else
                    if(clicked == 2) {
                        val cal = Calendar.getInstance()
                        val date = cal.time

                        mainViewModel.saveCar(
                            SavedCars(item.id,
                                "",
                            item.car,
                            item.car_model,
                            item.car_color,
                            item.car_model_year,
                            item.car_vin,
                            item.price,
                            item.availability,
                            date
                            )
                        )
//                        mainViewModel.findActiveUser()
                    }
                }


                alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(
                        context,
                        android.R.string.no, Toast.LENGTH_SHORT
                    ).show()
                }
                alertDialog.show()
            }else {
                toastMessage("Automobil trenutno nije dostupan")
            }
        }
        binding.list.adapter = adapter

        //paginacija
//        getData(page, limit)
//
//        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
//                page++
//                binding.progressbar.visibility = View.VISIBLE
//                getData(page,limit)
//            }
//        })
    }
    private fun getData(page: Int, limit: Int){
        if (page > limit) {
            Toast.makeText(context, "That's all the data..", Toast.LENGTH_SHORT).show();
            return;
        }
        Timber.e(page.toString() + limit.toString() + "page and limit ")
        mainViewModel.getCarsPagination(page, limit)
    }

    private fun initObservers(){
        mainViewModel.carState.observe(viewLifecycleOwner, Observer{
            Timber.e(it.toString())
            renderState(it)
        })
        mainViewModel.insertCarForSavingState.observe(viewLifecycleOwner, Observer{
            Timber.e(it.toString())
            renderStateInsertCar(it)
        })
        mainViewModel.getCars()
    }
    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun renderState(carState: CarState) {
        when (carState) {
            is CarState.Success -> {
                binding.progressbar.visibility = View.GONE
                adapter.submitList(carState.cars)
            }
            is CarState.Error ->
                Toast.makeText(context, carState.message, Toast.LENGTH_SHORT).show()
            is CarState.DataFetched -> {
                showLoadingState(false)
            }
            is CarState.Loading -> {
                showLoadingState(true)
            }
        }
    }
    private fun renderStateInsertCar(state: InsertCarForSavingState) {
        when (state) {
            is  InsertCarForSavingState.Success -> {
                if(pressed) {
                    toastMessage("Automobil je sačuvan")
                    pressed = false
                }
            }
            is InsertCarForSavingState.Error -> {
                toastMessage(state.message)
            }
        }
    }
    private fun showLoadingState(loading: Boolean) {
        binding.list.isVisible = !loading
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}