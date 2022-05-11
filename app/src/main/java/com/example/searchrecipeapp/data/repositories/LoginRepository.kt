package com.example.searchrecipeapp.data.repositories

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.searchrecipeapp.data.OperationResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.core.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val application: Application) {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val userLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    suspend fun register(email: String, password: String) {
        withContext(Dispatchers.IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    object : OnCompleteListener<AuthResult> {
                        override fun onComplete(task: Task<AuthResult>) {
                            if (task.isSuccessful) {
                                userLiveData.postValue(firebaseAuth.currentUser)
                            } else {
                                Toast.makeText(
                                    application,
                                    task.exception!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
        }
    }

    suspend fun login(email: String, password: String){
        withContext(Dispatchers.IO) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    object : OnCompleteListener<AuthResult> {
                        override fun onComplete(task: Task<AuthResult>) {
                            if (task.isSuccessful) {
                                userLiveData.postValue(firebaseAuth.currentUser)
                            } else {
                                Toast.makeText(
                                    application,
                                    task.exception!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
        }
    }
}