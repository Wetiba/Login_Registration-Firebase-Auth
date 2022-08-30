package com.example.login_registration_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var EdtEmail:EditText
    private lateinit var EdtPass:EditText
    lateinit var EdtConPaa:EditText
    private lateinit var BtnSignup:Button
    lateinit var TvDirectLogin:TextView
   private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EdtEmail=findViewById(R.id.etSEmailAddress)
        EdtPass=findViewById(R.id.etSPassword)
        EdtConPaa=findViewById(R.id.etSConfPassword)
        BtnSignup=findViewById(R.id.btnSSigned)
        TvDirectLogin=findViewById(R.id.tvRedirectSignUp)
        auth=Firebase.auth

        BtnSignup.setOnClickListener {
            SignUpUser()

        }
        TvDirectLogin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun SignUpUser(){
        val email=EdtEmail.text.toString()
        val pass=EdtPass.text.toString()
        val confirmpass=EdtConPaa.text.toString()
        if (email.isBlank() || pass.isBlank() || confirmpass.isBlank()){
            Toast.makeText(this,"Please Email and password cant be blank",Toast.LENGTH_LONG).show()
            return
        }  else if (pass != confirmpass){
            Toast.makeText(this,"Password do not match",Toast.LENGTH_LONG).show()
            return

        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this,"Signed successfully",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,"Failed to create",Toast.LENGTH_LONG).show()
            }

        }
    }
}