package com.example.proyecto

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity4 : AppCompatActivity() {

    lateinit var btuno: Button
    lateinit var btdos: Button
    lateinit var bttres: Button
    lateinit var btcuatro: Button
    lateinit var btcinco: Button
    lateinit var btseis: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        btuno = findViewById(R.id.button6)
        btdos = findViewById(R.id.button8)
        bttres = findViewById(R.id.button9)
        btcuatro = findViewById(R.id.button10)
        btcinco = findViewById(R.id.button11)
        btseis = findViewById(R.id.button12)

        btuno.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://elpais.com/escaparate/2023-04-14/plantas-de-interior-resistentes-duraderas-y-muy-faciles-de-cuidar.html")
            startActivity(openURL)
        }

        btdos.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.mundodeportivo.com/uncomo/hogar/articulo/cual-es-la-mejor-hora-para-regar-las-plantas-33542.html")
            startActivity(openURL)
        }

        bttres.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.elcomercio.com/tendencias/exceso-agua-planta-problemas-jardineria.html")
            startActivity(openURL)
        }

        btcuatro.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.ecologiaverde.com/como-hacer-abono-organico-casero-para-plantas-1275.html")
            startActivity(openURL)
        }

        btcinco.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.leroymerlin.es/ideas-y-consejos/consejos/como-germinar-semillas.html#:~:text=Coloca%20las%20semillas%20sobre%20el,hasta%20ver%20crecer%20la%20planta.")
            startActivity(openURL)
        }

        btseis.setOnClickListener{
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.aguafria.es/blog/cuanta-agua-necesitan-las-plantas-de-interior/#:~:text=El%20agua%20es%20esencial%20para,la%20misma%20cantidad%20de%20agua.")
            startActivity(openURL)
        }

    }
}