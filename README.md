# WiFi Security Guardian

This repository contains a production-style Android app for safe WiFi monitoring and risk awareness.

Features:
- Nearby WiFi scanning using Android WiFi APIs
- Security risk scoring for open or weak networks
- Rogue or duplicate SSID detection
- Warden-style alerting for suspicious networks
- Local-only scan history and reporting
- GitHub Actions pipeline to build APKs and release artifacts

Important:
- This app is defensive and informational only.
- It does not perform network exploitation, credential theft, deauthentication, sniffing, or bypass behavior.
- It is intended for safe local network awareness and security education.

Project structure:
- `app/` — Android app module
- `.github/workflows/` — CI/CD automation
- `build.gradle.kts` — project-level Gradle config
- `settings.gradle.kts` — project settings

Quick start:
1. Open the project in Android Studio.
2. Ensure the Android SDK is installed.
3. Sync Gradle.
4. Run the `debug` build or connect a device.

Release build and signing:
1. Create a keystore.
2. Add the following GitHub repository secrets:
   - `KEYSTORE_BASE64`
   - `KEYSTORE_PASSWORD`
   - `KEY_ALIAS`
   - `KEY_PASSWORD`
3. Push a tag like `v1.0.0` or run the workflow manually.

Workflow notes:
- `android-release.yml` builds debug and release artifacts.
- Release APKs are signed only when repository secrets are supplied.
- A tag-triggered release uploads APK artifacts to GitHub Releases.
