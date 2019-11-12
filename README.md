# Currency converter

## Project details
* Source code: LF, UTF-8
* Code align IntelliJ default (CTRL-ALT-L)
* Java 11 (sorry if you dont have it on local machine)
* Rates are hardcoded (as you said)
* Gradle wrapper used
* Generic number format expected, like 200.00 (no regional settings)
* During build warning is shown regarding Spock+Groovy libraries used buts is safe to ignore it


##How to run
You can pass command line parameters directly to gradle wrapper: 
```
gradlew run --args="EUR/DKK 1.00"
```

