package com.aavidsoft.freshersbuddy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aavidsoft.freshersbuddy.articles.allarticles.fragments.ArticlesFragment
import com.aavidsoft.freshersbuddy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, ArticlesFragment())
            .commit()

    }
}