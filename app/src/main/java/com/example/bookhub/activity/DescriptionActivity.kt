package com.example.bookhub.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.example.bookhub.R
import com.squareup.picasso.Picasso
import org.json.JSONObject

class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName: TextView
    lateinit var txtBookAuthor: TextView
    lateinit var txtBookPrice: TextView
    lateinit var txtBookRating: TextView
    lateinit var txtBookImage: ImageView
    lateinit var txtBookDesc: TextView
    lateinit var btnAddToFav: Button
    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout

    lateinit var toolbar: Toolbar

    var book_id: String? = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        txtBookImage = findViewById(R.id.imgBookImage)
        txtBookDesc = findViewById(R.id.txtxBookDesc)
        btnAddToFav = findViewById(R.id.btnAddToFav)
        progressBar = findViewById(R.id.progressBar)
        progressLayout = findViewById(R.id.progressLayout)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Book Details"

        progressBar.visibility = View.VISIBLE
        progressLayout.visibility = View.VISIBLE

        if(intent != null){
            book_id = intent.getStringExtra("book_id")

        }else{
            finish()
            Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()
        }

        if(book_id == "100"){
            finish()
            Toast.makeText(this,"some error occured",Toast.LENGTH_SHORT).show()
        }

        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"

        val jsonParams = JSONObject()
        jsonParams.put("book_id",book_id)

        val jsonRequest = object : JsonObjectRequest(Request.Method.PUT,url,jsonParams,Response.Listener {
               try{
                   val success = it.getBoolean("success")
                   if(success){
                       val jsonBookObject = it.getJSONObject("book_data")
                       progressLayout.visibility = View.GONE

                       Picasso.get().load(jsonBookObject.getString("image")).error(R.drawable.ic_default_book_image).into(txtBookImage)
                       txtBookName.text = jsonBookObject.getString("name")
                       txtBookAuthor.text = jsonBookObject.getString("author")
                       txtBookPrice.text = jsonBookObject.getString("price")
                       txtBookRating.text = jsonBookObject.getString("rating")
                       txtBookDesc.text = jsonBookObject.getString("description")
                   }else{
                       Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()
                   }
               }catch (e: Exception){
                   Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()
               }

        },Response.ErrorListener {
            Toast.makeText(this,"Volley error has occured : $it",Toast.LENGTH_SHORT).show()
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "984d2f8e9a3e17"
                return headers
            }
        }
        queue.add(jsonRequest)


    }
}