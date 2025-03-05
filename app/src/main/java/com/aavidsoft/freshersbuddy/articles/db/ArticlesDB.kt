package com.aavidsoft.freshersbuddy.articles.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aavidsoft.freshersbuddy.articles.models.Articles

@Database(entities = [Articles::class], version = 1)
abstract class ArticlesDB : RoomDatabase(){

    abstract fun getArticleDao():ArticleDao
}