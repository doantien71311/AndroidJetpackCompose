package com.example.myapplicationjetpackcompose.services

import android.content.Context


//class DataStoreServices (context: Context) : IDataStoreServies {
//
//
//}

interface IDataStoreServies {

    fun getToken(): String

    fun getBearToken(): String

}

class DataStoreServices () : IDataStoreServies {
    override fun getToken(): String {
        val token : String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3QUQxQTBEMzBDQzcyNkZBODA1MzM0ODYwQkI5OEJDMTUiLCJTcWxfRGF0YWJhc2VfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1VzZXJfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1Bhc3N3b3JkIjoiMjcyNzREMjNDRDc3NDdFMTNENDg4QkM1NERGNEJBQTQ4MDJENThBNDkyQTFDNzFBIiwibV9Vc2VySWQiOiJCOEJBQzZCMDQ5NzA5QjEzNEFCNEI1NEY1N0Q5RTFDMERCMDY4RTkxMDkxQjEwNDciLCJqdGkiOiI3ZTczY2I1Zi00MWJiLTRkMGItYWVjYy1kYTdiZDY0MDNiYmQiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjU5OTIxIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo0MjAwIn0._XrHoKa-a3hQCs-oeeXaNu9NlcfrWstyhenjwOjd50A"
        return token
    }

    override fun getBearToken(): String {
        val bearToken : String = "Bearer "+ this.getToken()
        return bearToken
    }


}