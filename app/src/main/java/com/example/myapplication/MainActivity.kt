package com.example.myapplication

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    var helper: TestOpenHelper? = null
    var db: SQLiteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (helper == null) {
            helper = TestOpenHelper(applicationContext);
        }

        if (db == null) {
            db = helper!!.writableDatabase;
        }
        db?.let { insertData( "aaa", 1000) }
        test("test")
        db?.let { insertData("test",1000) }
    }

    fun test(text: String) {
        var fragment: Fragment? = null

        fragment = if (text == "test") {
            BlankFragment()
        } else {
            BlankFragment2()
        }


//fragmentを管理するためのAPI
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

//FrameLayout内のfragmentの置換（追加）
        transaction.replace(R.id.container, fragment)

//バックスタックに追加
        transaction.addToBackStack(null);

//fragmentを表示
        transaction.commit()
    }

    fun readData(): List<Data> {
        Log.d("debug", "**********Cursor")
        val cursor: Cursor = db!!.query(
            "testdb", arrayOf("company", "stockprice"),
            null,
            null,
            null,
            null,
            null
        )
        val dataList=ArrayList<Data>()
        cursor.moveToFirst()

        for (i in 0 until cursor.count) {
            val data=Data(cursor.getString(0),cursor.getInt(1))
            dataList.add(data)
            cursor.moveToNext()
        }

        // 忘れずに！
        cursor.close()
        return dataList
    }

     fun insertData(com: String, price: Int) {
        val values = ContentValues()
        values.put("company", com)
        values.put("stockprice", price)
         db?.insert("testdb", null, values)
//        db.update()
    }
}