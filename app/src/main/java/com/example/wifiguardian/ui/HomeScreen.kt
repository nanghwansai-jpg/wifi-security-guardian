package com.example.wifiguardian.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wifiguardian.AccessPointInfo
import com.example.wifiguardian.RiskScorer
import com.example.wifiguardian.WardenAlertSystem
import com.example.wifiguardian.WifiScanner

@Composable
fun HomeScreen(
    scanner: WifiScanner,
    riskScorer: RiskScorer,
    alertSystem: WardenAlertSystem
) {
    var accessPoints by remember { mutableStateOf(emptyList<AccessPointInfo>()) }
    var alerts by remember { mutableStateOf(emptyList<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "WiFi Security Guardian",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
                accessPoints = scanner.scanNearbyNetworks()
                alerts = alertSystem.evaluate(accessPoints)
            }) {
                Text("Scan")
            }
        }

        if (alerts.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Warden Alerts",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    alerts.forEach { alert ->
                        Text("• $alert")
                    }
                }
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(accessPoints) { accessPoint ->
                val (score, riskLevel) = riskScorer.score(accessPoint)
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = accessPoint.ssid, style = MaterialTheme.typography.titleMedium)
                        Text(text = "BSSID: ${accessPoint.bssid}")
                        Text(text = "Signal: ${accessPoint.level} dBm")
                        Text(text = "Security: ${accessPoint.security}")
                        Text(text = "Channel: ${accessPoint.channel}")
                        Text(text = "Risk: $score (${riskLevel.name})")
                    }
                }
            }
        }
    }
}
