package com.wel.randomuser.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.wel.randomuser.base.BaseAdapter
import com.wel.randomuser.databinding.ItemUserListBinding
import com.wel.randomuser.domain.models.User
import javax.inject.Inject

class UserAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<User>() {

    private val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.firstName == newItem.firstName
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<User> {
        override fun bind(item: User) {
            binding.apply {
                lastName.text = item.lastName
                firstName.text = item.firstName
                glide.load(item.smallPicture).into(imageViewCharacter)
                email.text = item.email
            }
        }
    }
}
