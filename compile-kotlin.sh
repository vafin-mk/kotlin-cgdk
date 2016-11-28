if [ ! -f $KOTLIN_HOME/bin/kotlinc ]
then
    echo Unable to find kotlinc in KOTLIN_HOME [$KOTLIN_HOME] > compilation.log
    exit 1
fi

if [ ! -f src/main/kotlin/Runner.java ]
then
    echo Unable to find src/main/kotlin/Runner.java > compilation.log
    exit 1
fi

if [ ! -f src/main/kotlin/MyStrategy.kt ]
then
    echo Unable to find src/main/kotlin/MyStrategy.kt > compilation.log
    exit 1
fi

$KOTLIN_HOME/bin/kotlinc src/main/kotlin/model/*.kt src/main/kotlin/*.kt -d classes/ > compilation.log 2>&1
$JAVA_HOME/bin/javac -classpath classes/ -d classes/ src/main/kotlin/Runner.java src/main/kotlin/RemoteProcessClient.java >> compilation.log 2>&1

if [ ! -f classes/Runner.class ]
then
    echo Unable to find classes/Runner.class >> compilation.log
    exit 1
fi

jar cf "./kotlin-cgdk.jar" -C "./classes" . >> compilation.log 2>&1
