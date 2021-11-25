package com.example.saferoute.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.saferoute.R
import com.example.saferoute.activity.Register.RegisterPersonalInfo
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.User
import com.example.saferoute.domain.UserLoginInfo
import com.example.saferoute.endpoint.UserEndpoints
import com.example.saferoute.util.NetworkUtils
import com.google.android.material.checkbox.MaterialCheckBox
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun cadastrarUsuaria(v: View) {
        startActivity(Intent(this, RegisterPersonalInfo::class.java))
    }

    fun signIn(v: View) {
        val userData: UserLoginInfo = this.collectUserData(v)
        this.sendRequest(v, userData)
    }

    fun collectUserData(v: View): UserLoginInfo {
        val edtUserLogin: EditText = findViewById(R.id.edt_user_login)
        val edtUserPassword: EditText = findViewById(R.id.edt_user_pwd)

        val userLogin: String = edtUserLogin.text.toString()
        val userPassword: String = edtUserPassword.text.toString()

        return UserLoginInfo(userLogin, userPassword)
    }

    fun sendRequest(v: View, userData: UserLoginInfo) {
        val retrofit = NetworkUtils.buildService(UserEndpoints::class.java, SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)
        retrofit.login(userData).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    throw t
                }
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    validateUser(user)
                }
            }
        )
    }

    fun validateUser(user: User?) {
        if(user != null) {
            this.saveUserData(user)
            this.goHome(user)
        } else {
            val message = "Email e/ou senha inválidos";
            Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
        }
    }

    fun saveUserData(user: User) {
        val database: AppLocalDatabase = AppLocalDatabase.getInstance(applicationContext)
        var checkbox: MaterialCheckBox = findViewById(R.id.checkbox)
        if(checkbox.isChecked) {
            user.saveSession = true
        }
        database.UserDao().saveOne(user)
    }

    fun goHome(user: User){
        val home = Intent(this, Home::class.java)
        home.putExtra("userData", user)
        startActivity(home)
    }
}