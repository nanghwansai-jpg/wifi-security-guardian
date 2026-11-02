package com.example.wifiguardian

import org.junit.Assert.assertEquals
import org.junit.Test

class RiskScorerTest {

    private val scorer = RiskScorer()

    @Test
    fun testOpenNetworkRiskScore() {
        val ap = AccessPointInfo(
            ssid = "Public WiFi",
            bssid = "00:11:22:33:44:55",
            level = -70,
            security = "OPEN",
            frequency = 2412,
            channel = 1
        )
        val (score, riskLevel) = scorer.score(ap)
        assertEquals(45, score)
        assertEquals(RiskLevel.MEDIUM, riskLevel)
    }

    @Test
    fun testHighRiskNetwork() {
        // OPEN (45) + level > -60 (10) + guest/free (5) = 60 -> HIGH
        val ap = AccessPointInfo(
            ssid = "Free-Guest-WiFi",
            bssid = "00:11:22:33:44:56",
            level = -50,
            security = "OPEN",
            frequency = 2437,
            channel = 6
        )
        val (score, riskLevel) = scorer.score(ap)
        assertEquals(60, score)
        assertEquals(RiskLevel.HIGH, riskLevel)
    }

    @Test
    fun testSecureNetworkLowRisk() {
        val ap = AccessPointInfo(
            ssid = "HomeNetwork",
            bssid = "AA:BB:CC:DD:EE:FF",
            level = -75,
            security = "WPA2-PSK-CCMP",
            frequency = 5180,
            channel = 36
        )
        // WPA in security (15) -> score 15 -> LOW
        val (score, riskLevel) = scorer.score(ap)
        assertEquals(15, score)
        assertEquals(RiskLevel.LOW, riskLevel)
    }
}
