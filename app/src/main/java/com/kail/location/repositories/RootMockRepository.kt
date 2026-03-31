package com.kail.location.repositories

import android.app.Application
import android.content.Intent
import android.os.Build
import com.kail.location.service.ServiceGoRoot
import com.kail.location.service.ServiceGoNoroot
import com.kail.location.views.locationpicker.LocationPickerActivity

class RootMockRepository(private val app: Application) {
    fun startMock(lat: Double, lng: Double, runMode: String) {
        val serviceClass = if (runMode == "root") ServiceGoRoot::class.java else ServiceGoNoroot::class.java
        val extraCoordType = if (runMode == "root") ServiceGoRoot.EXTRA_COORD_TYPE else ServiceGoNoroot.EXTRA_COORD_TYPE
        val intent = Intent(app, serviceClass)
        intent.putExtra(extraCoordType, "BD09")
        intent.putExtra(LocationPickerActivity.LAT_MSG_ID, lat)
        intent.putExtra(LocationPickerActivity.LNG_MSG_ID, lng)
        if (Build.VERSION.SDK_INT >= 26) {
            app.startForegroundService(intent)
        } else {
            app.startService(intent)
        }
    }

    fun stopMock(runMode: String) {
        val serviceClass = if (runMode == "root") ServiceGoRoot::class.java else ServiceGoNoroot::class.java
        val intent = Intent(app, serviceClass)
        app.stopService(intent)
    }
}
