package com.example.inventorymanangementsystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.inventorymanangementsystem.databinding.ActivityIms3Binding
import org.json.JSONArray
import java.util.ArrayList

class IMS2Activity : AppCompatActivity() {
    private var selectedZone: String? = null
    private var selectedCircle: String? = null
    private var selectedDivision
            : String? = null
    private var selectedGSS: String? = null

    private var zoneSpinner: Spinner? = null
    private var circleSpinner: Spinner? = null
    private var divisionSpinner //Spinners
            : Spinner? = null
    private var gssSpinner: Spinner? = null
    private var zoneAdapter: ArrayAdapter<CharSequence>? = null
    private var circleAdapter: ArrayAdapter<CharSequence>? = null
    private var divisionAdapter //declare adapters for the spinners
            : ArrayAdapter<CharSequence>? = null
    private var gssAdapter: ArrayAdapter<CharSequence>? = null
    //  val zone = resources.getStringArray(R.array.zone).toList()
    // val circle = resources.getStringArray(R.array.Circle).toList()
    //  val division = resources.getStringArray(R.array.Division).toList()
    // val gss = resources.getStringArray(R.array.GSS).toList()
    //  val circle11 = resources.getStringArray(R.array.patna_circle).toList()
    //  val circle2 = resources.getStringArray(R.array.bhagalpur_circle).toList()
    // val circle3 = resources.getStringArray(R.array.muzzaffarpur_circle).toList()
    // val divsion1 = resources.getStringArray(R.array.patna_east_division).toList()
    // val division2 = resources.getStringArray(R.array.patna_west_division).toList()
    // val division3 = resources.getStringArray(R.array.dehri_on_sone_division).toList()

    // private var zone1: ArrayList<String>? = null
    // private var circle1 : ArrayList<String>? = null
    //private var result1: JSONArray? = null
    //private val result2: JSONArray? = null
    //private var result: JSONArray? = null
   // private val getApplicationcontext: Any? = null
    //var db: SpinnerDatabaseHandler = SpinnerDatabaseHandler(getApplicationcontext as Context?)
    private  var btnSubmit: Button? = null
    private var etPassword: EditText? = null
    private var btnReset: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ims2)
        title = "Inventory Management System"
        zoneSpinner =
            findViewById(R.id.spinner_Zone) //Finds a view that was identified by the android:id attribute

        //Country Spinner Initialisation
        zoneAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.zone, R.layout.spinner_layout
        )
        // Specify the layout to use when the list of choices appear
        zoneAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        zoneSpinner!!.adapter = zoneAdapter
        zoneSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                circleSpinner =
                    findViewById(R.id.spinner_Circle) //Finds a view that was identified by the android:id attribute in xml
                selectedZone = zoneSpinner!!.selectedItem.toString()
                val parentID = p0!!.id
                if (parentID == R.id.spinner_Zone) {
                    when (selectedZone) {
                        "Select Your Zone" -> circleAdapter = ArrayAdapter.createFromResource(
                            p0.context,
                            R.array.Circle, R.layout.spinner_layout
                        )
                        "Patna" -> circleAdapter = ArrayAdapter.createFromResource(
                            p0.context,
                            R.array.patna_circle, R.layout.spinner_layout
                        )
                        "Bhagalpur" -> circleAdapter = ArrayAdapter.createFromResource(
                            p0.context,
                            R.array.bhagalpur_circle,
                            R.layout.spinner_layout
                        )
                        "Muzzaffarpur" -> circleAdapter = ArrayAdapter.createFromResource(
                            p0.context,
                            R.array.muzzaffarpur_circle,
                            R.layout.spinner_layout
                        )
                        else -> {}
                    }
                    circleAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    circleSpinner!!.adapter = circleAdapter
                    circleSpinner!!.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            divisionSpinner =
                                findViewById(R.id.spinner_Division)
                            selectedCircle = circleSpinner!!.selectedItem.toString()
                            val parentID = p0!!.id
                            if (parentID == R.id.spinner_Circle) {
                                when (selectedCircle) {
                                    "Select Your Circle" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.Division, R.layout.spinner_layout
                                        )
                                    "Patna(East)" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.patna_east_division, R.layout.spinner_layout
                                        )
                                    "Patna(West)" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.patna_west_division, R.layout.spinner_layout
                                        )
                                    "Dehri On Sone" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.dehri_on_sone_division, R.layout.spinner_layout
                                        )
                                    "Bhojpur" -> divisionAdapter = ArrayAdapter.createFromResource(
                                        p0.context,
                                        R.array.bhojpur_division, R.layout.spinner_layout
                                    )
                                    "Gaya" -> divisionAdapter = ArrayAdapter.createFromResource(
                                        p0.context,
                                        R.array.gaya_division, R.layout.spinner_layout
                                    )
                                    "Biharshariff" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.biharshariff_division, R.layout.spinner_layout
                                        )
                                    "Bhagalpur" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.bhagalpur_division, R.layout.spinner_layout
                                        )
                                    "Begusarai" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.begusarai_division, R.layout.spinner_layout
                                        )
                                    "Purnia" -> divisionAdapter = ArrayAdapter.createFromResource(
                                        p0.context,
                                        R.array.purnia_division, R.layout.spinner_layout
                                    )
                                    "Muzzaffarpur" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.muzzaffarpur_division, R.layout.spinner_layout
                                        )
                                    "Vaishali" -> divisionAdapter = ArrayAdapter.createFromResource(
                                        p0.context,
                                        R.array.vaishali_division, R.layout.spinner_layout
                                    )
                                    "Dardhanga" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.darbhanga_division, R.layout.spinner_layout
                                        )
                                    "Motihari" -> divisionAdapter = ArrayAdapter.createFromResource(
                                        p0.context,
                                        R.array.motihari_division, R.layout.spinner_layout
                                    )
                                    "Chapra" -> divisionAdapter = ArrayAdapter.createFromResource(
                                        p0.context,
                                        R.array.chapra_division, R.layout.spinner_layout
                                    )
                                    "Madhepura" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.madhepura_division, R.layout.spinner_layout
                                        )
                                    "Patna Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.patna_civil_division, R.layout.spinner_layout
                                        )
                                    "Dehri On Sone Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.dehri_on_sone_civil_division,
                                            R.layout.spinner_layout
                                        )
                                    "Gaya Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.gaya_civil_division, R.layout.spinner_layout
                                        )
                                    "Bhagalpur Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.bhagalpur_civil_division,
                                            R.layout.spinner_layout
                                        )
                                    "Purnia Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.purnia_civil_division, R.layout.spinner_layout
                                        )
                                    "Muzzaffarpur Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.muzzaffarpur_civil_division,
                                            R.layout.spinner_layout
                                        )
                                    "Darbhanga Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.darbhanga_civil_division,
                                            R.layout.spinner_layout
                                        )
                                    "Chapra Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.chapra_civil_division, R.layout.spinner_layout
                                        )
                                    "Madhepura Civil" -> divisionAdapter =
                                        ArrayAdapter.createFromResource(
                                            p0.context,
                                            R.array.madhepura_civil_division,
                                            R.layout.spinner_layout
                                        )
                                    else -> {}
                                }
                                divisionAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                divisionSpinner!!.adapter = divisionAdapter
                                divisionSpinner!!.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                            gssSpinner = findViewById(R.id.spinner_GSS)
                                            selectedDivision = divisionSpinner!!.selectedItem
                                                .toString()
                                            val parentID = p0!!.id
                                            if (parentID == R.id.spinner_Division) {
                                                when (selectedDivision) {
                                                    "Select Your Division" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.GSS,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Patna(East)" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.patna_east_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Barh" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.barh_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Patna(West)" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.patna_west_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Patna(Central)" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.patna_central_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Dehri On Sone" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.dehri_on_sone_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Aurangabad" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.aurangabad_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Arrah" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.arrah_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Mohania" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.mohania_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Gaya" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.gaya_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Jehanabad" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.jehanabad_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Biharshariff" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.biharshariff_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Nawada" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.nawada_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Bhagalpur" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.bhagalpur_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Begusarai" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.begusrai_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Munger" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.munger_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Purnia" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.purnia_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Kishanganj" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.kishanganj_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Muzaffarpur" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.muzzaffar_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Sitamarhi" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.sitamarhi_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Hajipur" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.hajipur_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Samastipur" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.samastipur_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Darbhanga" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.darbhanga_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Madhubani" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.madhubani_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Motihari" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.motihari_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Bettiah" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.bettiah_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Chapra" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.chapra_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Gopalganj" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.gopalganj_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Madhepura" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.madhepura_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Birpur" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.birpur_gss,
                                                            R.layout.spinner_layout
                                                        )
                                                    "Patna Civil" -> gssAdapter =
                                                        ArrayAdapter.createFromResource(
                                                            p0.context,
                                                            R.array.patna_civil_division,
                                                            R.layout.spinner_layout
                                                        )
                                                    // "Khagaul Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.khagaul_civil_division,R.layout.spinner_layout)
                                                    //  "Biharshariff Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Dehri On Sone Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Arrah Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Jehanabad Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Bhagalpur Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //  "Begusarai Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Muger Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Purnia Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Kishanganj Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //  "Muzaffarpur Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Sitamarhi Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Hajipur Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Darbhanga Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Samastipur Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Motihari Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Chapra Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Gopalganj Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Madhepura Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Birpur Civil" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //"Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //  "Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    // "Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //  "Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    //  "Patna(East)" -> gssAdapter = ArrayAdapter.createFromResource(parent.applicationContext,R.array.patna_east_gss,R.layout.spinner_layout)
                                                    else -> {}

                                                }
                                                gssAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                                gssSpinner!!.adapter = gssAdapter
                                                gssSpinner!!.onItemSelectedListener =
                                                    object : AdapterView.OnItemSelectedListener {
                                                        override fun onItemSelected(
                                                            p0: AdapterView<*>?,
                                                            p1: View?,
                                                            p2: Int,
                                                            p3: Long
                                                        ) {
                                                            selectedGSS =
                                                                gssSpinner!!.selectedItem.toString()
                                                        }

                                                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                                            TODO("Not yet implemented")
                                                        }

                                                    }

                                            }

                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                            TODO("Not yet implemented")
                                        }

                                    }


                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }


                    }


                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        btnSubmit = findViewById(R.id.btnSubmit)
        btnReset = findViewById(R.id.btnReset)
        btnSubmit?.setOnClickListener {
            val validPassword = etPassword?.text.toString()
            val passWord = "123456789"
           if (zoneSpinner != null && circleSpinner != null && divisionSpinner != null && gssSpinner != null && validPassword == passWord) {
               //db.insertLabel(selectedZone, selectedCircle, selectedDivision, selectedGSS)
               var requestQueue: RequestQueue? = null
               val url = "http://localhost/IMS/ims2.php"
               val stringRequest: StringRequest = object : StringRequest(
                   Method.POST, url,
                   Response.Listener { response: String? ->
                       Toast.makeText(
                           this@IMS2Activity,
                           "Success",
                           Toast.LENGTH_LONG
                       ).show()
                   },
                   Response.ErrorListener { error: VolleyError? ->
                       Toast.makeText(
                           this@IMS2Activity,
                           "Error",
                           Toast.LENGTH_LONG
                       ).show()
                   }) {
                   @Throws(AuthFailureError::class)
                   override fun getParams(): Map<String, String> {
                       val params: MutableMap<String, String> = HashMap()
                       params["Zone"] = selectedZone!!
                       params["Circle"] = selectedCircle!!
                       params["Division"] = selectedDivision!!
                       params["GSS"] = selectedGSS!!
                       return params


                   }

               }
               requestQueue = Volley.newRequestQueue(this@IMS2Activity)
               requestQueue.add(stringRequest)

               Toast.makeText(
                   this@IMS2Activity,
                   "Your information is saved successfully",
                   Toast.LENGTH_SHORT
               ).show()
           }
                val intent = Intent(this@IMS2Activity, IMS3Activity::class.java)
                startActivity(intent)


        }
            btnReset?.setOnClickListener {
             etPassword?.setText(0)
             zoneSpinner?.setSelection(0)
            circleSpinner ?.setSelection(0)
             divisionSpinner?.setSelection(0)
            gssSpinner?.setSelection(0)


             }



    }
}






