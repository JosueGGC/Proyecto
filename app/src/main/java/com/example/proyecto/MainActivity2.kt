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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        sesion = getSharedPreferences("sesion", 0)


        rvList = findViewById(R.id.rvList)

        rvList.setHasFixedSize(true)
        rvList.itemAnimator = DefaultItemAnimator()
        rvList.layoutManager = LinearLayoutManager(this)

        llenar()

    }


    private fun llenar() {
        val url = Uri.parse(Config.URL+"control")
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
            override fun onClickEdit(posicion: Int) {
            }

            override fun onClickDel(posicion: Int) {
                eliminar(lista[posicion][0]);
            }
        })
    }

    private fun eliminar(id: String?) {
        val url = Uri.parse(Config.URL+"control/"+id)
            .buildUpon()
            .build().toString()

        val peticion = object: JsonObjectRequest(
            Request.Method.DELETE, url, null,
            {
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this, "Error en la peticion", Toast.LENGTH_SHORT).show()
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