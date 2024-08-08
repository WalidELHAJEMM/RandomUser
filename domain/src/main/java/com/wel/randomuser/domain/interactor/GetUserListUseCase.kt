package com.wel.randomuser.domain.interactor

import com.wel.randomuser.domain.models.User
import com.wel.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetUserListBaseUseCase = BaseUseCase<Unit, Flow<List<User>>>

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) : GetUserListBaseUseCase {

    override suspend operator fun invoke(params: Unit) = userRepository.getUsers()
}
