package com.example.wifiguardian

data class AccessPointInfo(
    val ssid: String,
    val bssid: String,
    val level: Int,
    val security: String,
    val frequency: Int,
    val channel: Int,
    val timestamp: Long = System.currentTimeMillis()
)

enum class RiskLevel {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}
