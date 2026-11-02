package com.example.wifiguardian

class RiskScorer {
    fun score(ap: AccessPointInfo): Pair<Int, RiskLevel> {
        var score = 0

        if (ap.security.isBlank() || ap.security.equals("OPEN", true)) score += 45
        if (ap.security.contains("WEP", ignoreCase = true)) score += 30
        if (ap.security.contains("WPA", ignoreCase = true)) score += 15
        if (ap.level > -60) score += 10
        if (ap.ssid.contains("guest", ignoreCase = true) || ap.ssid.contains("free", ignoreCase = true)) {
            score += 5
        }

        val level = when {
            score >= 80 -> RiskLevel.CRITICAL
            score >= 60 -> RiskLevel.HIGH
            score >= 35 -> RiskLevel.MEDIUM
            else -> RiskLevel.LOW
        }

        return score.coerceIn(0, 100) to level
    }
}
