package com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien

import android.util.Patterns

class ValidateEmail {

    fun execute (email:String): ValidationResult {

        if (email.isBlank()) {

            return ValidationResult(
                successful = false,
                errorMessage = "Email không được trống"
            )

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            return ValidationResult (
                successful = false,
                errorMessage = "Email không đúng định dạng"

            )

        }

        return ValidationResult(

            successful = true,
            errorMessage = ""
        )

    }


}