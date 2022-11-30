package com.adrian.contacts.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperSQL(c:Context):SQLiteOpenHelper(
        c,DataUserInfo.DATABASE_NAME,null,DataUserInfo.DATABASE_VERSION
),DataUserInfo
{
    /**ContentValues*/
    lateinit var values:ContentValues
    lateinit var db:SQLiteDatabase
    var curs : Cursor? = null
    /**set SqLite Data*/
    override fun onCreate(db: SQLiteDatabase?) {
       db?.execSQL(DataUserInfo.TABLE_SET)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL(DataUserInfo.TABLE_GET)
        onCreate(db)
    }

    /**set UserInfo*/
    override fun listUser(): ArrayList<UserDatas> {
        db = this.readableDatabase
        val storeUserInfo = ArrayList<UserDatas>()
        curs = db.rawQuery(DataUserInfo.USER_SELECT,null)
        if (curs!!.moveToFirst()){
            do {
                val id = curs!!.getString(0)
                val name = curs!!.getString(1)
                val imageUr = curs!!.getString(2)
                val mobN = curs!!.getString(3)
                val age = curs!!.getString(4)
                val email = curs!!.getString(5)
                val more = curs!!.getString(6)
               storeUserInfo.add(UserDatas(id,name,imageUr,mobN,age,email,more))
            } while (curs!!.moveToNext())
        }
        curs!!.requery()
        curs!!.close()
        return  storeUserInfo
    }

    override fun addUserInfo(us: UserDatas) {
        values = ContentValues()
        db = this.readableDatabase
        curs = db.rawQuery(DataUserInfo.USER_SELECT,null)
        values.put(DataUserInfo.COLUMN_NAME,us.name)
        values.put(DataUserInfo.COLUMN_IMAGE,us.imageUr)
        values.put(DataUserInfo.COLUMN_MOBN,us.mobN)
        values.put(DataUserInfo.COLUMN_AGE,us.age)
        values.put(DataUserInfo.COLUMN_EMAIL,us.email)
        values.put(DataUserInfo.COLUMN_MORE,us.more)
        db.insert(DataUserInfo.TABLE_USER,null,values)
        curs!!.requery()

    }

    override fun updateInfo(us: UserDatas): Int {
        values = ContentValues()
        db = this.readableDatabase
        curs = db.rawQuery(DataUserInfo.USER_SELECT,null)
        values.put(DataUserInfo.COLUMN_ID,us.id)
        values.put(DataUserInfo.COLUMN_NAME,us.name)
        values.put(DataUserInfo.COLUMN_IMAGE,us.imageUr)
        values.put(DataUserInfo.COLUMN_MOBN,us.mobN)
        values.put(DataUserInfo.COLUMN_AGE,us.age)
        values.put(DataUserInfo.COLUMN_EMAIL,us.email)
        values.put(DataUserInfo.COLUMN_MORE,us.more)
        val success = db.update(DataUserInfo.TABLE_USER,values,
                "${DataUserInfo.COLUMN_ID}=?", arrayOf(us.id))
        return success
        listUser()
    }

    override fun deleteInfo(id: Int) {
        db = this.writableDatabase
        curs = db.rawQuery(DataUserInfo.USER_SELECT,null)
        db.delete(DataUserInfo.TABLE_USER,
                "${DataUserInfo.COLUMN_ID}=?", arrayOf(id.toString()))
        curs!!.requery()
    }
}