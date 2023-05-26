package com.example.myapplicationjetpackcompose.services

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HttpClientService {

    fun <T>getData(call: Call<T>, callback: Callback<T>) {

        call
            .enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>?) {
               // super.onResponse(call, response)
                if (call.isCanceled) return
                callback.onResponse(call, response)
            }


            override fun onFailure(call: Call<T>?, t: Throwable?) {
                //super.onFailure(call, t)
                callback.onFailure(call, t)
            }
        })

    }



}

class RetrofitCallback<T>(private val mCallback: Callback<T>) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        mCallback.onResponse(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {

        mCallback.onFailure(call, t)
    }

    companion object {
        private const val TAG = "RetrofitCallback"
    }
}


//class CustomCallback<T>(call: Call<T>) : Callback<T> {
//
//
////    private val TAG = "CustomCallback"
////    private val mApiService: ApiService = ConnectionsRequest.getApiService()
////    private val config: Config = Config.getInstance()
////
////    init {
////        call = call
////    }
////
////    override fun onResponse(main_call: Call<T>, response: Response<T>) {
////        //Check if the token is still valid
////        if (Gson().toJson(response.body()).contains("needrefreshtoken")) {
////            Log.i(TAG, "Generate new token")
////            main_call.cancel()
////            RetrofitCallback.enqueue(
////                mApiService.getToken(config.refreshtoken()),
////                object : Callback<TokenModel?> {
////                    override fun onResponse(
////                        call: Call<TokenModel?>,
////                        response: Response<TokenModel?>
////                    ) {
////                        config.token = response.body().getToken()
////                        Log.i(TAG, "New token generated and saved")
////                        retryMainRequest()
////                    }
////
////                    override fun onFailure(call: Call<TokenModel?>, t: Throwable) {}
////                })
////        }
////    }
////
////    override fun onFailure(call: Call<T>, t: Throwable) {
////        Log.e(TAG, t.toString())
////    }
////
////    private fun retryMainRequest() {
////        Log.i(TAG, "Retry request")
////        call.clone().enqueue(this)
////    }
//}

//class DefaultCallback<T>(callback: Callback<T>) : Callback<T?> {
//    private val callback: Callback<T?>
//
//    init {
//        this.callback = callback
//    }
//
//    override fun onResponse(call: Call<T?>, response: Response<T?>) {
//        if (response.body() == null) {
//            callback.onFailure(call, NullPointerException("Empty response"))
//            Log.e(TAG, "Response code is: " + response.code())
//        } else {
//            callback.onResponse(call, response)
//        }
//    }
//
//    override fun onFailure(call: Call<T?>, t: Throwable) {
//        Log.e(TAG, t.toString())
//        callback.onFailure(call, t)
//    }
//
//    companion object {
//        private const val TAG = "LOGO_SendReport"
//    }
//}

//
//interface IHttpClientService {
//
//
//}