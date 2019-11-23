# Currency converter

## Project details
* Source code: LF, UTF-8
* Code align IntelliJ default (CTRL-ALT-L)
* Java 11
* Spock unit tests
* Rates are hardcoded
* Gradle wrapper used
* Generic number format expected, like 200.00 (no regional settings)
* During build warning is shown regarding Spock+Groovy libraries used but it is safe to ignore it

## Usage
Command line parameters can be passed directly to gradle wrapper:
```
gradlew run --args="EUR/DKK 1.00"
```

