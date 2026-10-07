# Virtual Room Designer UI Tests

This standalone Maven project runs a Selenide end-to-end browser test against the Virtual Room Designer and uses Allure for test reporting. The test creates a uniquely named room, adds and rotates a sofa, removes it, and deletes the room when finished. It also removes the uniquely named room after a failed test so test data is not intentionally left behind.

## Project structure

- `src/main/java/com/roomdesigner/pages` contains page classes.
- `src/test/java/com/roomdesigner/config` contains the shared base test setup.
- `src/test/java/com/roomdesigner` contains the test classes.
- `src/test/java/com/roomdesigner/testdata` contains generated test data.

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
cd "C:\Users\k map\OneDrive\Desktop\selenide-tests"
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

## Allure report

Each test run writes Allure results, including Selenide screenshots and page source attachments, to `target/allure-results`. Generate and open the report with:

```powershell
mvn allure:serve
```

To generate the report without opening it, run `mvn allure:report`; the report is written to `target/site/allure-maven-plugin`.
