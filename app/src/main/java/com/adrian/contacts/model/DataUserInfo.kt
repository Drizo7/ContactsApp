package com.adrian.contacts.model

interface DataUserInfo {

    fun listUser():ArrayList<UserDatas>
    fun addUserInfo(us:UserDatas)
    fun updateInfo(us:UserDatas): Int
    fun deleteInfo(id:Int)


    companion object{
        const val   DATABASE_NAME = "UserDatas"
        const val DATABASE_VERSION = 1
        const val TABLE_USER = "UserDatas"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "UserName"
        const val COLUMN_MOBN = "UserMobN"
        const val COLUMN_IMAGE = "UserImage"
        const val COLUMN_AGE = "UserAge"
        const val COLUMN_EMAIL = "UserEmail"
        const val COLUMN_MORE = "UserMore"

        /**select Data*/
        const val USER_SELECT  = "SELECT * FROM $TABLE_USER"

        /**CREATE TABLE*/
        const val TABLE_GET = "DROP TABLE EXISTS $TABLE_USER"

        /**set Table*/
        const val TABLE_SET = "CREATE TABLE $TABLE_USER" +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT," +
                "$COLUMN_IMAGE TEXT,$COLUMN_MOBN TEXT,$COLUMN_AGE TEXT," +
                "$COLUMN_EMAIL TEXT,$COLUMN_MORE TEXT)"

    }



}