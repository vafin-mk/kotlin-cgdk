if [ ! -f $KOTLIN_HOME/bin/kotlinc ]
then
    echo Unable to find kotlinc in KOTLIN_HOME [$KOTLIN_HOME] > compilation.log
    exit 1
fi

if [ ! -f src/main/kotlin/Runner.kt ]
then
    echo Unable to find src/main/kotlin/Runner.kt > compilation.log
    exit 1
fi

if [ ! -f src/main/kotlin/MyStrategy.kt ]
then
    echo Unable to find src/main/kotlin/MyStrategy.kt > compilation.log
    exit 1
fi

$KOTLIN_HOME/bin/kotlinc -d "kotlin-cgdk.jar" src/main/kotlin/*.kt src/main/kotlin/model/*.kt > compilation.log 2>&1
