package com.example.securejourney

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = BuildConfig.MAP_API_KEY
        Log.d("Test Key", apiKey)

        Log.d("TOBI_TAG", "This is a debug message")

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1001 )


        val navigationBar = findViewById<BottomNavigationView>(R.id.navigationBar)

            navigationBar.setOnItemSelectedListener { menuItem ->

                if (menuItem.itemId == R.id.nav_guard) {
                    inflateFragment(GuardFragment.newInstance());
                } else if (menuItem.itemId == R.id.nav_home) {
                    inflateFragment(HomeFragment())
                } else if (menuItem.itemId == R.id.nav_dashboard) {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1001 )
                        inflateFragment(MapsFragment())
                    }else{
                        inflateFragment(MapsFragment())
                    }

                } else {
                    inflateFragment(ProfileFragment.newInstance())
                }
                true
            }

            navigationBar.selectedItemId = R.id.nav_home;
    }


    private fun inflateFragment(fr: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fr)
        transaction.commit()
    }



}
