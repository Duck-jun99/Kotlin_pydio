package com.kotlinstudy.kotlin_pydio

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseManager {

    private val conn = DriverManager.getConnection("MY_URL", "USER_NAME", "PASSWORD")

    suspend fun fetchData(): List<String> = withContext(Dispatchers.IO) {
        val data = mutableListOf<String>()
        try {
            val stmt = conn.createStatement()
            val query = "SELECT userid FROM member_test"
            val rs = stmt.executeQuery(query)
            while (rs.next()) {
                data.add(rs.getString("users")) // 가져오려는 컬럼의 이름으로 수정
            }
            rs.close()
            stmt.close()
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
        data
    }

    fun closeConnection() {
        try {
            conn.close()
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
}