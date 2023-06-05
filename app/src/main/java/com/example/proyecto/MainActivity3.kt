package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class MainActivity3 : AppCompatActivity() {
    lateinit var etHoraEdit : EditText
    lateinit var etHumedadEdit : EditText
    lateinit var etTemperaturaEdit : EditText
    lateinit var bSaveEdit : Button
    lateinit var bCancelEdit : Button
    lateinit var sesion: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        etHoraEdit = findViewById(R.id.editTextTextPersonName3)
        etHumedadEdit = findViewById(R.id.editTextTextPersonName4)
        etTemperaturaEdit = findViewById(R.id.editTextTextPersonName5)
        bSaveEdit = findViewById(R.id.button4)
        bCancelEdit = findViewById(R.id.button5)
        sesion = getSharedPreferences("sesion",0)

        bCancelEdit.setOnClickListener { startActivity(Intent(this, MainActivity2::class.java)) }
        bSaveEdit.setOnClickListener { guardar("1") }
    }

    private fun guardar(id: String?) {
        val url = Uri.parse(Config.URL+"control/"+id)
            .buildUpon()
            .build().toString()
        val datos: JSONObject = JSONObject()
        datos.put("h_Registro", etHoraEdit.text.toString())
        datos.put("tm_Riego", etHumedadEdit.text.toString())
        datos.put("tm_Ambiente", etTemperaturaEdit.text.toString())
        val peticion = object: JsonObjectRequest(
            Request.Method.PUT, url, datos,
            {
                err ->Toast.makeText(this, "Error en la peticion: "+err, Toast.LENGTH_SHORT).show()
                println(datos)
            },
            {
                Toast.makeText(this, "Registro  modificado", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity2::class.java))
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val header: MutableMap<String, String> = HashMap()
                header["Authorization"] = "Bearer " + sesion.getString("token", "")
                return header
            }
        }

        MySingleton.getInstance(applicationContext).addToRequestQueue(peticion)
    }
}
