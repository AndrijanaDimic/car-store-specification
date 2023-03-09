package rs.raf.sept.andrijana_dimic_rn7820.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.avgust.andrijana_dimic_rn7820.ui.state.AddUserState
import rs.raf.sept.andrijana_dimic_rn7820.databinding.FragmentNotificationsBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.contract.MainContract
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter.SavedCarAdapter
import rs.raf.sept.andrijana_dimic_rn7820.ui.notifications.recyler.SwipeGesture
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.FindUserState
import rs.raf.sept.andrijana_dimic_rn7820.ui.state.SavedCarsState
import rs.raf.sept.andrijana_dimic_rn7820.ui.viewmodel.MainViewModel
import timber.log.Timber

class NotificationsFragment : Fragment(){
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    var  _binding: FragmentNotificationsBinding? = null
    var page = 1
    var limit = 10
    var swiped : Boolean = false

    private val binding get() = _binding!!

    private lateinit var adapter: SavedCarAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
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
        adapter = SavedCarAdapter()
            val swipegesture = object: SwipeGesture(){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    swiped = true
                    when(direction){
                        ItemTouchHelper.LEFT->{
                            val pos = viewHolder.adapterPosition
                            mainViewModel.deleteById(adapter.getItem(pos).id, adapter.getItem(pos).username)
                        }
                        ItemTouchHelper.RIGHT->{
                            val pos = viewHolder.adapterPosition
                            mainViewModel.deleteById(adapter.getItem(pos).id, adapter.getItem(pos).username)
                        }
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipegesture)
            touchHelper.attachToRecyclerView(binding.list)
            binding.list.adapter = adapter

        // getData
        getData(page, limit)

//        binding.nes
    }
    private fun getData(page: Int, limit: Int){

    }

    private fun initObservers(){
        mainViewModel.savedCarsState.observe(viewLifecycleOwner, Observer{
            Timber.e(it.toString())
            renderState(it)
        })
        mainViewModel.addDone.observe(viewLifecycleOwner, Observer{
            Timber.e(it.toString())
            renderAddDoneState(it)
        })

        mainViewModel.findUserState.observe(viewLifecycleOwner, Observer {
            renderFindUserState(it)
        })
        //pokusaj za svakog studenta
        mainViewModel.findActiveUser()
//        mainViewModel.getSavedCarsOrderByDate()
    }

    private fun renderState(savedCarsState: SavedCarsState) {
        when (savedCarsState) {
            is SavedCarsState.Success -> {
                binding.progressbar.visibility = View.GONE
                adapter.submitList(savedCarsState.cars)
            }
            is SavedCarsState.Error ->
                Toast.makeText(context, savedCarsState.message, Toast.LENGTH_SHORT).show()
            is SavedCarsState.DataFetched -> {
                showLoadingState(false)
            }
            is SavedCarsState.Loading -> {
                showLoadingState(true)
            }
        }
    }
    private fun renderFindUserState(findUserState: FindUserState) {
        when (findUserState) {
            is FindUserState.Success -> {
                val username = findUserState.user.username
                mainViewModel.saveAllCars(username)
                mainViewModel.getAllCarsOrderByDate(username)
            }
            is FindUserState.Error ->
                Toast.makeText(context, findUserState.message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun renderAddDoneState(addDone: AddUserState) {
        when (addDone) {
            is AddUserState.Success -> {
                if(swiped) {
                    toastMessage("Automobil je izbrisan iz liste")
                    swiped = false
                }
            }
            is AddUserState.Error ->
                Toast.makeText(context, addDone.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun renderStateAddDone(state: AddUserState) {
        when (state) {
            is AddUserState.Success -> {
                toastMessage("Automobil je sačuvan")
            }
            is AddUserState.Error -> {
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