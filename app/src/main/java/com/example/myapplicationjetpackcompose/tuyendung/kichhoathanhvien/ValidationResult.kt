package com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien

data class ValidationResult (

    val successful: Boolean,
    val isError: Boolean,
    val errorMessage: String = ""
)
