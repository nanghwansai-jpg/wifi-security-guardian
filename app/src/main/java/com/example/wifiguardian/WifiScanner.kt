package com.example.wifiguardian

import android.net.wifi.WifiManager

class WifiScanner(private val wifiManager: WifiManager) {
    fun scanNearbyNetworks(): List<AccessPointInfo> {
        val results = wifiManager.scanResults
        return results.map { scan ->
            AccessPointInfo(
                ssid = scan.SSID.ifBlank { "Hidden" },
                bssid = scan.BSSID.ifBlank { "Unknown" },
                level = scan.level,
                security = scan.capabilities,
                frequency = scan.frequency,
                channel = channelFromFrequency(scan.frequency)
            )
        }
    }

    private fun channelFromFrequency(freq: Int): Int {
        return when {
            freq in 2412..2472 -> ((freq - 2407) / 5)
            freq in 5170..5825 -> ((freq - 5000) / 5)
            else -> 0
        }
    }
}
