package com.example.wifiguardian

import android.Manifest
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.wifiguardian.ui.HomeScreen
import com.example.wifiguardian.ui.theme.WiFiGuardianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
        val scanner = WifiScanner(wifiManager)
        val riskScorer = RiskScorer()
        val alertSystem = WardenAlertSystem()

        setContent {
            WiFiGuardianTheme {
                MainApp(scanner, riskScorer, alertSystem)
            }
        }
    }
}

@Composable
private fun MainApp(
    scanner: WifiScanner,
    riskScorer: RiskScorer,
    alertSystem: WardenAlertSystem
) {
    val context = LocalContext.current
    val permissions = arrayOf(
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    LaunchedEffect(Unit) {
        ActivityCompat.requestPermissions(context as ComponentActivity, permissions, 1001)
    }

    HomeScreen(scanner, riskScorer, alertSystem)
}
