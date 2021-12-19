package com.example.networktask

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkService: NetworkService = retrofit.create(NetworkService::class.java)
        networkService.getUser("ykropchik").enqueue(object: Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                TODO("Not yet implemented")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val userInfo: UserInfo? = response.body()

                val userAvatar: ImageView = findViewById(R.id.ivUserAvatar)
                val userLogin: TextView = findViewById(R.id.tvLogin)
                val publicRepos: TextView = findViewById(R.id.tvPublicRepos)

                Glide.with(applicationContext).load(userInfo?.avatarUrl).into(userAvatar)
                userLogin.text = userInfo?.login
                publicRepos.text = "Открытых репозиториев: " + userInfo?.publicRepos.toString()

                userLogin.setOnClickListener(View.OnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(userInfo?.url))
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    applicationContext.startActivity(intent)
                })
            }
        })

        networkService.listRepos("ykropchik").enqueue(object: Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                val repos: List<Repo>? = response.body()

                val recyclerView: RecyclerView = findViewById(R.id.rvList)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = repos?.let { RecyclerViewAdapter(applicationContext, it) }
            }
        })
    }
}
