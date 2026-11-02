package com.example.wifiguardian

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WardenAlertSystemTest {

    private val alertSystem = WardenAlertSystem()

    @Test
    fun testAlertsOnOpenNetwork() {
        val apList = listOf(
            AccessPointInfo("TestOpen", "00:11:22:33:44:55", -50, "OPEN", 2412, 1)
        )
        val alerts = alertSystem.evaluate(apList)
        assertTrue(alerts.any { it.contains("Open WiFi network detected nearby") })
    }

    @Test
    fun testAlertsOnDuplicateSsid() {
        val apList = listOf(
            AccessPointInfo("CafeWiFi", "00:11:22:33:44:01", -50, "WPA2", 2412, 1),
            AccessPointInfo("CafeWiFi", "00:11:22:33:44:02", -70, "WPA2", 2437, 6)
        )
        val alerts = alertSystem.evaluate(apList)
        assertTrue(alerts.any { it.contains("Duplicate SSID detected") })
    }

    @Test
    fun testAlertsOnWeakSecurity() {
        val apList = listOf(
            AccessPointInfo("OldRouter", "00:11:22:33:44:03", -60, "WEP", 2412, 1)
        )
        val alerts = alertSystem.evaluate(apList)
        assertTrue(alerts.any { it.contains("Weak security protocol detected") })
    }

    @Test
    fun testNoAlertsForSafeSingleNetwork() {
        val apList = listOf(
            AccessPointInfo("SecureOffice", "00:11:22:33:44:04", -65, "WPA2-PSK", 5180, 36)
        )
        val alerts = alertSystem.evaluate(apList)
        assertEquals(0, alerts.size)
    }
}
