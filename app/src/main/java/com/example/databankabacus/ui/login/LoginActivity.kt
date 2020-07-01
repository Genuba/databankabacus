package com.example.databankabacus.ui.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.databankabacus.R
import com.example.databankabacus.data.model.AutenticarUsuario
import com.example.databankabacus.data.model.Login
import com.example.databankabacus.data.model.RespuestaAutenticarUsuario
import com.example.databankabacus.data.model.Token
import com.example.databankabacus.interfaces.JsonPlaceHolderApi
import com.example.databankabacus.utils.ApiUtils
import com.example.databankabacus.utils.SqliteUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi
    private lateinit var sqliteUsuario: SqliteUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loading = findViewById<ProgressBar>(R.id.loading)

        sqliteUsuario = SqliteUsuario(this@LoginActivity)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://apidatabank.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected) {
            val username = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)

            val cursor = sqliteUsuario.Usuario
            if (cursor.moveToFirst()) {
                if (cursor.getString(2) == username.text.toString() && cursor.getString(3) == password.text.toString()){
                    Toast.makeText(this@LoginActivity, "Bienvenido " + cursor.getString(2), Toast.LENGTH_LONG).show();
                    //val intent = Intent(this@LoginActivity, OtrraActividad::class.java)
                    //startActivity(intent)
                }else {
                    Toast.makeText(this@LoginActivity, "Usuario o password erroneos ", Toast.LENGTH_LONG).show();
                }
            }
        }else{
            getTokenLogin();
        }
    }

    fun getTokenLogin() {
        var token: String = ""
        val parameters = Login()
        parameters.email = "AdminInventarios"
        parameters.password = "CapoRCapo#234"
        val call: Call<Token> = jsonPlaceHolderApi.postLogin(parameters)

        call.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>?, t: Throwable?) {
                Log.v("retrofit", "call failed get token")
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                var postResponse = response.body()
                if (postResponse != null) {
                    sqliteUsuario.updateToken(response.body()?.token)
                }
            }
        })
    }

    fun autenticarUsuario(view: View) {

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        if(username.text.toString() != "" && password.text.toString() != null){
            var autenticarUsuario = AutenticarUsuario()
            autenticarUsuario.setUsuario(username.text.toString())
            autenticarUsuario.setClave(password.text.toString())
            val call = ApiUtils.getAPIService(this).postAutenticarUsuario(autenticarUsuario)

            call?.enqueue(object : Callback<RespuestaAutenticarUsuario> {
                override fun onFailure(call: Call<RespuestaAutenticarUsuario>, t: Throwable) {
                    Log.v("retrofit", "ingreso erroneo")
                }

                override fun onResponse(call: Call<RespuestaAutenticarUsuario>, response: Response<RespuestaAutenticarUsuario>) {
                    if(response.body()?.result?.getValor() != 0){
                        Toast.makeText(this@LoginActivity, response.body()?.result?.mensaje, Toast.LENGTH_LONG).show();
                    }else{
                        sqliteUsuario.updateUsuario(response.body()?.idUsuario,response.body()?.usuario,response.body()?.clave)
                        val cursor = sqliteUsuario.Usuario

                        if (cursor.moveToFirst()) {
                            Toast.makeText(this@LoginActivity, "Bienvenido " + cursor.getString(2), Toast.LENGTH_LONG).show();
                        }
                    }
                    //val intent = Intent(this@LoginActivity, OtrraActividad::class.java)
                    //startActivity(intent)
                }
            })

        }else {
            Toast.makeText(this, "Debe ingresar ambos campos", Toast.LENGTH_LONG).show()
        }

    }
}
