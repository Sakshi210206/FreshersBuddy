package com.aavidsoft.freshersbuddy.articles.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aavidsoft.freshersbuddy.articles.models.Articles

@Dao
interface ArticleDao {

    @Insert
    fun insertArticle(articles: Articles)

    @Delete
    fun deleteArticles(articles: Articles)

    @Update
    fun updateArticles(articles: Articles)

    @Query("Select * from Articles")
    fun getAllArticles(): Cursor

}