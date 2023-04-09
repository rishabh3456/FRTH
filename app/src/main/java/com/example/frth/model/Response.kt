package com.example.frth.model

import com.google.gson.annotations.SerializedName

//Class to hold data from api
data class ResponseItem(

	@field:SerializedName("listId")
	val listId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
