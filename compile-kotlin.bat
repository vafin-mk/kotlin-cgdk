@echo off

if not exist "%KOTLIN_HOME%/bin/kotlinc.bat" (
    echo Unable to find kotlinc.bat in KOTLIN_HOME ["%KOTLIN_HOME%] > compilation.log
    exit 1
)

if not exist src/main/kotlin/Runner.java (
    echo Unable to find src/main/kotlin/Runner.java > compilation.log
    exit 1
)

if not exist src/main/kotlin/MyStrategy.kt (
    echo Unable to find src/main/kotlin/MyStrategy.kt > compilation.log
    exit 1
)

rd /Q /S classes
md classes

call "%KOTLIN_HOME%/bin/kotlinc.bat" src/main/kotlin/model/*.kt src/main/kotlin/*.kt -d classes/ > compilation.log 2>&1
call "%JAVA_HOME%/bin/javac" -classpath classes/ -d classes/ src/main/kotlin/Runner.java src/main/kotlin/RemoteProcessClient.java >> compilation.log 2>&1

if [ ! -f classes/Runner.class ]
then
    echo Unable to find classes/Runner.class >> compilation.log
    exit 1
fi


jar cvfe "./kotlin-cgdk.jar" Runner -C "./classes" .
