package com.example.attendancemanager.Repository

import androidx.lifecycle.MutableLiveData
import com.example.attendancemanager.recyleritems.userdata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserRepository {
    var userid = FirebaseAuth.getInstance().currentUser!!.uid

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userid)

    @Volatile private var INSTANCE : UserRepository ?= null

    fun getInstance() : UserRepository{
        return INSTANCE ?: synchronized(this){

            val instance = UserRepository()
            INSTANCE = instance
            instance
        }


    }


    fun loadUsers(userList : MutableLiveData<List<userdata>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<userdata> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(userdata::class.java)!!

                    }

                    userList.postValue(_userList)

                }catch (e : Exception){


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }

}