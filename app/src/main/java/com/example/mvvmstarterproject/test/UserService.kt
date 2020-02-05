package com.example.mvvmstarterproject.test


import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getListOfUsers(): Response<List<User>>
}