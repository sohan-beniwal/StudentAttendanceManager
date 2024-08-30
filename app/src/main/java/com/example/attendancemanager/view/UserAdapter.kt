package com.example.attendancemanager.view
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancemanager.R
import com.example.attendancemanager.recyleritems.userdata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private val userList = ArrayList<userdata>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]
        holder.SubjectName.text = currentitem.subject
        holder.Present.text = currentitem.present
        holder.TotalClasses.text = currentitem.totalclasses
        holder.presentbtn.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            val clickedCard = userList[adapterPosition]
            val clickedSubjectName = clickedCard.subject
            handleButtonClick(clickedSubjectName)
        }
        holder.absentbtn.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            val clickedCard = userList[adapterPosition]
            val clickedSubjectName = clickedCard.subject
            handleButtonClickAbsent(clickedSubjectName)
        }
    }

    private fun handleButtonClickAbsent(clickedSubjectName: String?) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null && clickedSubjectName != null) {
            // Reference to the specific subject's node in the Firebase Realtime Database
            val databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userId/$clickedSubjectName")

            // Retrieve the current "present" value from the database
            databaseReference.child("totalclasses").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val currentTotalValue = dataSnapshot.getValue(String::class.java)

                    if (currentTotalValue != null) {
                        // Assuming you want to increment "present" by 1, you can modify this logic as needed
                        val updatedTotalValue = (currentTotalValue.toInt() + 1).toString()

                        // Update the "present" value in the database
                        databaseReference.child("totalclasses").setValue(updatedTotalValue)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener {
                            }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle database error
                }
            })
        }
    }

    private fun handleButtonClick(clickedSubjectName: String?) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null && clickedSubjectName != null) {
            // Reference to the specific subject's node in the Firebase Realtime Database
            val databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userId/$clickedSubjectName")

            // Retrieve the current "present" value from the database
            databaseReference.child("present").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val currentPresentValue = dataSnapshot.getValue(String::class.java)

                    if (currentPresentValue != null) {
                        // Assuming you want to increment "present" by 1, you can modify this logic as needed
                        val updatedPresentValue = (currentPresentValue.toInt() + 1).toString()

                        // Update the "present" value in the database
                        databaseReference.child("present").setValue(updatedPresentValue)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener {
                            }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle database error
                }
            })
        }
        val databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userId/$clickedSubjectName")

        // Retrieve the current "present" value from the database
        databaseReference.child("totalclasses").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentTotalValue = dataSnapshot.getValue(String::class.java)

                if (currentTotalValue != null) {
                    // Assuming you want to increment "present" by 1, you can modify this logic as needed
                    val updatedTotalValue = (currentTotalValue.toInt() + 1).toString()

                    // Update the "present" value in the database
                    databaseReference.child("totalclasses").setValue(updatedTotalValue)
                        .addOnSuccessListener {
                        }
                        .addOnFailureListener {
                        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<userdata>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var presentbtn : Button = itemView.findViewById(R.id.presentbtn)
        val SubjectName : TextView = itemView.findViewById(R.id.subjectname)
        val Present : TextView = itemView.findViewById(R.id.name1)
        val TotalClasses : TextView = itemView.findViewById(R.id.attendanceratio)
        val absentbtn : Button = itemView.findViewById(R.id.absentbtn)
        val progress : ProgressBar = itemView.findViewById(R.id.progressbar)
    }

}