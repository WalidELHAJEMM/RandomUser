package com.wel.randomuser.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wel.randomuser.base.BaseFragment
import com.wel.randomuser.databinding.FragmentUserListBinding
import com.wel.randomuser.extension.observe
import com.wel.randomuser.presentation.utils.DataState
import com.wel.randomuser.presentation.viewmodel.BaseViewModel
import com.wel.randomuser.presentation.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentUserListBinding =
        FragmentUserListBinding.inflate(layoutInflater)

    override val viewModel: UserListViewModel by viewModels()

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
        observe(viewModel.getRandomUsers(), ::onViewStateChange)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewUsers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onViewStateChange(event: DataState) {
        if (event.isRedelivered) return
        when (event) {
            is DataState.Error -> handleErrorMessage(event.error)
            is DataState.Loading -> handleLoading(true)
            is DataState.Success -> {
                handleLoading(false)
                event.data.let {
                    userAdapter.list = it
                }
            }
        }
    }
}
