package com.example.frth

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.frth.api.APIBuilder
import com.example.frth.databinding.ActivityMainBinding
import com.example.frth.model.ResponseItem
import retrofit2.Call
import retrofit2.Callback

//Class to process and render data (view+view-model)
class MainActivity : AppCompatActivity() {

    //binding to the activity_main.xml file to affect its text input fields.
    private var binding:ActivityMainBinding?=null

    //Initialization functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        getItems()
    }

    //function to transform api data to required string
    fun displayItems(itemsData: List<ResponseItem?>?):String{

        val filteredData=itemsData?.filter { !(it?.name.isNullOrBlank()) }?.groupBy { it?.listId }
        val displayString = StringBuilder()

        if (filteredData != null) {

            val itemList=filteredData.entries.sortedBy { it.key }

            for (entry in itemList){

                displayString.append("\n\n".plus("ListID: ").plus(entry.value[0]?.listId?.toString()).plus("\n\n\n"))
                val entryList=entry.value.sortedBy { it?.name?.substring(5)?.toInt() }

                for(item in entryList){

                    displayString.append("ID: ".plus(item?.id.toString()).plus("    Name: ").plus(item?.name))
                    displayString.append("\n")
                }
            }
        }
        return if (displayString.isNotEmpty()) displayString.toString() else "Could not get data."
    }

    //function to perform the get request call and update xml text field.
    private fun getItems() {
        val caller = APIBuilder.apiCallingService().getItemData()
        caller.enqueue(object : Callback<List<ResponseItem>> {

            //In case of success
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: retrofit2.Response<List<ResponseItem>>
            ) {
                val providedData = response.body()

                if (!response.isSuccessful || providedData == null) {
                    println("Could not get data.")
                    return
                }
                binding?.txtID?.text= displayItems(providedData)
            }

            //In case of failure
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<ResponseItem>>, trace: Throwable) {
                binding?.txtID?.text="Error"
                println( if (trace.message.isNullOrBlank() or trace.message.isNullOrEmpty()) "Error:"
                else trace.message)
                trace.printStackTrace()
            }
        })
    }
}