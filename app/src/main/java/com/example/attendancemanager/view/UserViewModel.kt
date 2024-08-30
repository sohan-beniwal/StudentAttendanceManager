package com.example.attendancemanager.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attendancemanager.Repository.UserRepository
import com.example.attendancemanager.recyleritems.userdata


class UserViewModel : ViewModel() {

    private val repository : UserRepository
    private val _allUsers = MutableLiveData<List<userdata>>()
    val allUsers : LiveData<List<userdata>> = _allUsers


    init {

        repository = UserRepository().getInstance()
        repository.loadUsers(_allUsers)

    }

}