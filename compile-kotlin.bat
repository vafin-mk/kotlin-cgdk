@echo off

if not exist "%KOTLIN_HOME%/bin/kotlinc.bat" (
    echo Unable to find kotlinc.bat in KOTLIN_HOME ["%KOTLIN_HOME%] > compilation.log
    exit 1
)

if not exist src/main/kotlin/Runner.kt (
    echo Unable to find src/main/kotlin/Runner.kt > compilation.log
    exit 1
)

if not exist src/main/kotlin/MyStrategy.kt (
    echo Unable to find src/main/kotlin/MyStrategy.kt > compilation.log
    exit 1
)

call "%KOTLIN_HOME%/bin/kotlinc.bat" -d "kotlin-cgdk.jar" src/main/kotlin/*.kt src/main/kotlin/model/*.kt > compilation.log 2>&1
