package com.kai.retailfarm.utility.validation

import android.util.Patterns
import java.util.regex.Pattern

class ValidationUtility {
    companion object{
        fun emailValidation( email: String ):Boolean{
            if( Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
                return true
            }
            return false
        }

        fun passwordValidation( password: String ):Boolean{
            val pattern = Pattern.compile("[a-zA-Z0-9!@#$]{7,24}" )
            if( pattern.matcher(password).matches() ) {
                return true
            }
            return false
        }

        fun mobileValidation( mobile: String ):Boolean{
            if( Patterns.PHONE.matcher(mobile).matches() ) {
                return true
            }
            return false
        }

        fun cityValidation( city: String ):Boolean{
            val pattern = Pattern.compile("^[a-zA-Z]{2,}$" )
            if( pattern.matcher(city).matches() ) {
                return true
            }
            return false
        }

        fun postalValidation( postal: String ):Boolean{
            val pattern = Pattern.compile("^[1-9][0-9]{5}$" )
            if( pattern.matcher(postal).matches() ) {
                return true
            }
            return false
        }

        fun stateValidation( state: String ):Boolean{
            val pattern = Pattern.compile("^[a-zA-Z]{2,}$" )
            if( pattern.matcher(state).matches() ) {
                return true
            }
            return false
        }

        fun nameValidation( name: String ):Boolean{
//            val pattern = Pattern.compile("^[a-zA-Z]$" )
//            if( pattern.matcher(name).matches() ) {
//                return true
//            }
//            return false
            return true
        }

        fun priceValidation( price: String ):Boolean{
            val pattern = Pattern.compile("^[0-9]+$")
            if( pattern.matcher(price).matches() )
            {
                return true
            }
            return false
        }

        fun unitValidation( unit: String ):Boolean{
            val pattern = Pattern.compile("^[0-9]+$")
            if( pattern.matcher(unit).matches() )
            {
                return true
            }
            return false
        }
    }
}

