package com.example.frth

import com.example.frth.api.APIBuilder
import com.example.frth.model.ResponseItem
import org.junit.Test
import retrofit2.Call


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class APIUnitTest {
    @Test
    fun queryCorrectnessTest() {

        val testAPI = APIBuilder.apiCallingService()
        val call: Call<List<ResponseItem>> = testAPI.getItemData()

        //url is correct?
        assert(call.request().url.toString()=="https://fetch-hiring.s3.amazonaws.com/hiring.json")

        //get method?
        assert(call.request().method=="GET")

        //ok response?
        assert(call.execute().code()==200)
    }


}
