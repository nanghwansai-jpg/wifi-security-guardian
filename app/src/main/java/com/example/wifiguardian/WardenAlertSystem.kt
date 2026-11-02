package com.example.wifiguardian

class WardenAlertSystem {
    fun evaluate(apList: List<AccessPointInfo>): List<String> {
        val alerts = mutableListOf<String>()

        val openNetworks = apList.filter {
            it.security.isBlank() || it.security.equals("OPEN", true)
        }
        if (openNetworks.isNotEmpty()) {
            alerts.add("Open WiFi network detected nearby.")
        }

        val duplicateNames = apList.groupBy { it.ssid }.filter { it.value.size > 1 }
        if (duplicateNames.isNotEmpty()) {
            alerts.add("Duplicate SSID detected; possible rogue access point.")
        }

        if (apList.any { it.security.contains("WEP", true) }) {
            alerts.add("Weak security protocol detected.")
        }

        return alerts
    }
}
