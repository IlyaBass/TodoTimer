package com.example.todotimer.repo.todo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity.Companion.ID
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity.Companion.TODO_NAME
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TodoDao {

    @Query("SELECT * FROM $TODO_NAME ORDER BY $ID")
    fun observe(): Flowable<List<TodoDatabaseEntity>>

    @Query("SELECT * FROM $TODO_NAME WHERE id = :todoId")
    fun observeById(todoId: Long): Single<TodoDatabaseEntity>

    @Insert
    fun add(todoItem: TodoDatabaseEntity)

    @Query("DELETE FROM $TODO_NAME WHERE id = :todoId")
    fun delete(todoId: Long)
}
