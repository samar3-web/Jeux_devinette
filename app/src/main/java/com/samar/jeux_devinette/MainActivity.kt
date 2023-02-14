package com.samar.jeux_devinette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var rand = 0
    var btnTenter: Button? = null
    var nbre: EditText? = null
    var rsltTentative: TextView? = null
    var nbreTente = 0
    var nbreClick = 0
    var hist = ""
    var histTotale = ""
    var txtHistorique: TextView? = null
    var donner: TextView? = null
    var historique: TextView? = null
    var btnNvllePartie: Button? = null
    var rgNiveau: RadioGroup? = null
    var debt: RadioButton? = null
    var exprt: RadioButton? = null
    var cmc: Button? = null
    var scrView: ScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        creerRandom()
        nouvellePartie()
        tenter()
    }

    fun creerRandom() {
        rand = (Math.random() * 1000).toInt()
    }

    fun tenter() {
        btnTenter = findViewById<View>(R.id.btnTenter) as Button
        nbre = findViewById<View>(R.id.nbreTente) as EditText
        rsltTentative = findViewById<View>(R.id.rsltTentative) as TextView
        txtHistorique = findViewById<View>(R.id.txtHistorique) as TextView
        txtHistorique!!.movementMethod = ScrollingMovementMethod()
        btnTenter!!.setOnClickListener {
            if (TextUtils.isEmpty(nbre!!.text.toString())) {
                nbre!!.error = "Veuillez saisir un nombre"
            } else {
                nbreTente = Integer.valueOf(nbre!!.text.toString())
                if (nbreTente > 999) {
                    nbre!!.error = "Veuillez saisir un nombre compris entre 0 et 999"
                } else {
                    showToast("rand = $rand")
                    showToast("tentative = $nbreTente")
                    nbreClick++
                    historique(nbreClick, nbreTente)
                    if (nbreTente > rand) {
                        rsltTentative!!.text = "Plus petit"
                        nbre!!.setText("")
                    } else if (nbreTente < rand) {
                        rsltTentative!!.text = "Plus grand"
                        nbre!!.setText("")
                    } else {
                        rsltTentative!!.text = "Bravo, c'est gagné après $nbreClick tentatives"
                        nbre!!.setText("")
                        nbre!!.isClickable = false
                        btnTenter!!.isClickable = false
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun historique(nbreCl: Int, nombre: Int) {
        hist = "La tentative numero $nbreCl est : $nombre\n"
        histTotale = txtHistorique!!.text.toString() + hist
        txtHistorique!!.text = histTotale
    }

    fun nouvellePartie() {
        btnNvllePartie = findViewById<View>(R.id.nvllePartie) as Button
        btnNvllePartie!!.setOnClickListener { /* finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);

                */
            creerRandom()
            txtHistorique!!.text = ""
            rsltTentative!!.text = ""
            nbre!!.setText("")
            nbreClick = 0
            rgNiveau = findViewById<View>(R.id.rgNiveau) as RadioGroup
            debt = findViewById<View>(R.id.debt) as RadioButton
            exprt = findViewById<View>(R.id.exprt) as RadioButton
            donner = findViewById<View>(R.id.donner) as TextView
            cmc = findViewById<View>(R.id.cmc) as Button
            historique = findViewById<View>(R.id.historique) as TextView
            scrView = findViewById<View>(R.id.scrView) as ScrollView
            rgNiveau!!.visibility = View.VISIBLE
            cmc!!.visibility = View.VISIBLE
            donner!!.visibility = View.GONE
            nbre!!.visibility = View.GONE
            btnTenter!!.visibility = View.GONE
            rsltTentative!!.visibility = View.GONE
            historique!!.visibility = View.GONE
            scrView!!.visibility = View.GONE
            commencerPartie()
            nbre!!.isClickable = true
            btnTenter!!.isClickable = true
        }
    }

    fun commencerPartie() {
        cmc!!.setOnClickListener {
            if (debt!!.isChecked) {
                rgNiveau!!.visibility = View.GONE
                cmc!!.visibility = View.GONE
                donner!!.visibility = View.VISIBLE
                nbre!!.visibility = View.VISIBLE
                btnTenter!!.visibility = View.VISIBLE
                rsltTentative!!.visibility = View.VISIBLE
                historique!!.visibility = View.VISIBLE
                scrView!!.visibility = View.VISIBLE
            } else {
                rgNiveau!!.visibility = View.GONE
                cmc!!.visibility = View.GONE
                donner!!.visibility = View.VISIBLE
                nbre!!.visibility = View.VISIBLE
                btnTenter!!.visibility = View.VISIBLE
                rsltTentative!!.visibility = View.VISIBLE
                historique!!.visibility = View.GONE
                scrView!!.visibility = View.GONE
            }
        }
    }
}