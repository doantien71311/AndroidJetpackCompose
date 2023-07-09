package com.example.myapplicationjetpackcompose.services


//import com.google.gson.GsonBuilder
//import com.google.gson.JsonDeserializationContext
//import com.google.gson.JsonDeserializer
//import com.google.gson.JsonElement
//import com.google.gson.JsonParseException

import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID


object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}





object RetrofitService {




    private val retrofit by lazy {

//        val gson = GsonBuilder()
//            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//            .create()

//        val gson = GsonBuilder().registerTypeAdapter(
//            LocalDateTime::class.java,
//            JsonDeserializer { json, type, jsonDeserializationContext ->
//                val instant = Instant.ofEpochMilli(json.asJsonPrimitive.asLong)
//                LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
//            })
//            .create()



        val client = OkHttpClient()


        var json = kotlinx.serialization.json.Json {
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true

        }



        Retrofit.Builder()
            .baseUrl(url_api.httpAdress)

            //.addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create(gson))
            //.addConverterFactory(MoshiConverterFactory.create())
           // .client(client)

            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    }



    val IRetrofitService by lazy {

        retrofit.create(com.example.myapplicationjetpackcompose.services.IRetrofitService::class.java)
    }
}