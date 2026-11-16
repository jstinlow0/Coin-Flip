# Coin Flip – project handoff

Context for continuing this project in a new Claude session.

## What exists
- **Repo:** `jstinlow0/Coin-Flip`, work branch `ccr-a8f96cff-wybbiq` (all work is here, nothing merged to a default branch yet).
- **Android home-screen widget** (`android/`, Java, minSdk 26): tap the coin → short spin animation → HEADS or TAILS (SecureRandom 50/50).
  - `CoinWidget.java` – widget logic. `MainActivity.java` – app screen with "Add widget to home screen" button.
  - Coin faces: `res/drawable-nodpi/coin_heads.png` (Memento Mori, gold skull) and `coin_tails.png` (Amor Fati, bronze flame), cropped from the user's photos. Spin frame: `res/drawable/coin_edge.xml`.
  - Signed with the committed `app/coinflip.keystore` (password `coinflip`) so updates install over old versions. Current version: 1.1 (versionCode 2) – bump it on each change.
- **Build/delivery:** `.github/workflows/android.yml` builds on every push touching `android/` and replaces the GitHub Release `widget-latest` with `CoinFlip.apk`.
  Phone install link: https://github.com/jstinlow0/Coin-Flip/releases/download/widget-latest/CoinFlip.apk
- **Web version** (`index.html`, `manifest.json`, `sw.js`, `icons/`): installable PWA with the same two coin faces.

## Status
Working; the user installed the widget on Android and liked it. No open bugs.

## About the user
- Prefers brief, direct answers for non-business topics; goal-oriented, wants the bigger picture (ask *why* if unclear). Runs a card cleaning business.
- **Core values: Memento Mori** (remember you will die – don't waste today) and **Amor Fati** (love your fate – use whatever happens as fuel). The coin represents these.
- Switching to PC because the `/ponytail` skill is installed there, not on the phone.
