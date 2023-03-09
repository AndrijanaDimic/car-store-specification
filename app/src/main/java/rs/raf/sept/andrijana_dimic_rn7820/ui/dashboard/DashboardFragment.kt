package rs.raf.sept.andrijana_dimic_rn7820.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.sept.andrijana_dimic_rn7820.R
import rs.raf.sept.andrijana_dimic_rn7820.databinding.FragmentDashboardBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter.SearchCarAdapter
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.CarState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    var  _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchCarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
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
        initListener()
        initRecycler()
    }
    private fun initListener(){
        binding.search.setOnClickListener() {
            if (binding.name.isChecked) {
                mainViewModel.searchByName(binding.editText.text.toString())
            }else if (binding.carColor.isChecked){
                mainViewModel.searchByColor(binding.editText.text.toString())
            }else if(binding.carModel.isChecked){
                mainViewModel.searchByModel(binding.editText.text.toString())
            }else if(binding.carModelYear.isChecked){
                mainViewModel.searchByYear(binding.editText.text.toString())
            }
        }
    }
    private fun initRecycler(){
        binding.list.layoutManager = LinearLayoutManager(context)
        adapter = SearchCarAdapter()
        binding.list.adapter = adapter
    }

    private fun initObservers(){
        mainViewModel.carState.observe(viewLifecycleOwner, Observer{
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.getCars()
    }

    private fun renderState(carState: CarState) {
        when (carState) {
            is CarState.Success -> {
                adapter.submitList(carState.cars)
            }
            is CarState.Error ->
                Toast.makeText(context, carState.message, Toast.LENGTH_SHORT).show()
            }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}