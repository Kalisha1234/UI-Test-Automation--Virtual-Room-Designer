# Virtual Room Designer UI Tests

This standalone Maven project runs a Selenide end-to-end browser test against the Virtual Room Designer. The test creates a uniquely named room, adds and rotates a sofa, removes it, and deletes the room when finished. It also attempts to remove the uniquely named room after a failed test so test data is not intentionally left behind.

## Prerequisites

- Java 17 or newer
- Maven 3.8 or newer
- Google Chrome installed (or another Selenium-supported browser selected with `selenide.browser`)
- The backend running at `http://localhost:8080`
- The frontend running at `http://localhost:5173` (Vite) or `http://localhost:3000` (Docker Compose)

The frontend and backend must both be reachable because this test exercises the UI, and the frontend sends API requests directly to `http://localhost:8080/api`.

## Run

From PowerShell, run:

```powershell
cd "C:\Users\k map\OneDrive\Desktop\Virtual Room Designer\selenide-tests"
mvn test
```

The test opens Chrome in headed mode by default. Run it headlessly if preferred:

```powershell
mvn test "-Dselenide.headless=true"
```

Use the production Docker frontend instead of Vite:

```powershell
mvn test "-Dselenide.baseUrl=http://localhost:3000"
```

Other browser and timing settings can be overridden with `-Dselenide.browser=firefox`, `-Dselenide.browserSize=1440x1000`, and `-Dselenide.timeout=15000`.

Selenide uses Selenium Manager to locate or resolve the matching browser driver on the first run. Browser-driver downloads may therefore require internet access.
