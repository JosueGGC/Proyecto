package com.example.proyecto

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {

    lateinit var rvList: RecyclerView

    lateinit var sesion: SharedPreferences

    lateinit var control: Button
    lateinit var preguntas: Button
    lateinit var bRefresh: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        sesion = getSharedPreferences("sesion", 0)


        control = findViewById(R.id.button2)
        preguntas = findViewById(R.id.button3)
        bRefresh = findViewById(R.id.button7)
        rvList = findViewById(R.id.rvList)

        rvList.setHasFixedSize(true)
        rvList.itemAnimator = DefaultItemAnimator()
        rvList.layoutManager = LinearLayoutManager(this)

        llenar()
        control.setOnClickListener { startActivity(Intent(this, MainActivity3::class.java)) }
        preguntas.setOnClickListener { startActivity(Intent(this, MainActivity4::class.java)) }
        bRefresh.setOnClickListener { llenar() }

    }


    private fun llenar() {
        val url = Uri.parse(Config.URL+"registro")
            .buildUpon()
            .build().toString()
        val peticion = object: JsonArrayRequest(Method.GET, url, null,
            {
                    res -> llenarRespuesta(res)
            },
            {
                Toast.makeText(this, "Error en la peticion", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val header: MutableMap<String, String> = HashMap()
                header["Authorization"] = "Bearer " + sesion.getString("token", "")
                return header
            }
        }
        MySingleton.getInstance(applicationContext).addToRequestQueue(peticion)
    }

    private fun llenarRespuesta(res: JSONArray) {
        val lista = Array(res.length()){ arrayOfNulls<String>(9) }
        for(i in 0 until res.length()){
            Log.d("ARRAY", res.getJSONObject(i).getString("id_Registro"))
            lista[i][0] = res.getJSONObject(i).getString("id_Registro")
            lista[i][1] = res.getJSONObject(i).getString("id_Usuario")
            lista[i][2] = res.getJSONObject(i).getString("sensor_Uno")
            lista[i][3] = res.getJSONObject(i).getString("valor_Uno")
            lista[i][4] = res.getJSONObject(i).getString("op_Uno")
            lista[i][5] = res.getJSONObject(i).getString("sensor_Dos")
            lista[i][6] = res.getJSONObject(i).getString("valor_Dos")
            lista[i][7] = res.getJSONObject(i).getString("op_Dos")
            lista[i][8] = res.getJSONObject(i).getString("fec_hora")
        }
        rvList.adapter = MyAdapter(lista, object : MyListener{
            override fun onClickDel(posicion: Int) {
                eliminar(lista[posicion][0]);
            }
        })
    }

    private fun eliminar(id: String?) {
        val url = Uri.parse(Config.URL+"registro/"+id)
            .buildUpon()
            .build().toString()

        val peticion = object: JsonObjectRequest(
            Request.Method.DELETE, url, null,
            {
                Toast.makeText(this, "Error en la peticion", Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show()
                llenar()
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