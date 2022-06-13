package com.example.bookhub.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookhub.R
import com.example.bookhub.adapter.DashboardRecylerAdapter
import com.example.bookhub.model.Book
import org.json.JSONException
import org.json.JSONObject
import java.lang.Error


class DashboardFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter : DashboardRecylerAdapter

    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar

//    lateinit var bookInfoList : ArrayList<Book>

    val bookInfoList = arrayListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard,container,false)
        recyclerView = view.findViewById(R.id.recyclerDashboard)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)

        progressLayout.visibility = View.VISIBLE

        layoutManager = LinearLayoutManager(activity)


        val queue = Volley.newRequestQueue(activity as Context)
        val url  = "http://13.235.250.119/v1/book/fetch_books/"

        val jsonObjectRequest = object : JsonObjectRequest(url,Response.Listener<JSONObject> {
            val success = it.getBoolean("success")
            
            try{
                progressLayout.visibility = View.GONE
                if(success){
                    val data = it.getJSONArray("data")
                    for(i in 0 until data.length()){
                        val bookJsonObject = data.getJSONObject(i)
                        val bookObject = Book(
                            bookJsonObject.getString("book_id"),
                            bookJsonObject.getString("name"),
                            bookJsonObject.getString("author"),
                            bookJsonObject.getString("rating"),
                            bookJsonObject.getString("price"),
                            bookJsonObject.getString("image")
                        )
                        bookInfoList.add(bookObject)

                        recyclerAdapter = DashboardRecylerAdapter(activity as Context,bookInfoList)
                        recyclerView.adapter = recyclerAdapter
                        recyclerView.layoutManager = layoutManager
                    }
                }
            }catch (e: JSONException){
                Toast.makeText(context,"Some error has occured",Toast.LENGTH_SHORT).show()
            }
            
        },Response.ErrorListener {
            Toast.makeText(activity as Context,"Volley error has occured",Toast.LENGTH_SHORT).show()
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "984d2f8e9a3e17"
                return headers
            }
        }
        queue.add(jsonObjectRequest)
        return view
    }


}