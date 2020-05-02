package com.kai.retailfarm.utility.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import java.util.*

class LocationUtility {
    companion object{
        fun getLocationDetails( context: Context ): Address?{
            if ( ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                val locationManager =
                    context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val providers: List<String> = locationManager.getProviders(true)
                var location: Location? = null
                val geocode = Geocoder(context, Locale.getDefault())

                for (i in providers.size - 1 downTo 0) {
                    location = locationManager.getLastKnownLocation(providers[i])
                    if (location != null) {
                        break
                    }
                }

                val list = geocode.getFromLocation(location!!.latitude, location.longitude, 1)

                return list[0]
            }
            return null
        }
    }
}