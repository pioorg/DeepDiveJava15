# DeepDiveJava15

This is a demo project to show some new features of Java™ 15.
It uses `--enable-preview` and Maven, but you don't need to have Maven installed.

To run this project, you need to have Java™ 15 installed. You can use https://sdkman.io/ and/or https://openjdk.java.net/, https://adoptopenjdk.net/.

If you'd like to run the example app, one of the options is this:

    $ MAVEN_OPTS="--enable-preview" ./mvnw exec:java -pl goodies

(If it doesn't work, you might have Java 15 not installed.)

## jpackage demo
Understand: https://openjdk.java.net/jeps/343

    $ ./mvnw package 
    $ cp records/target/records-1.0-SNAPSHOT.jar records/target/lib/
    $ jpackage --name DDJ15RECORDS --input records/target/lib --main-jar records-1.0-SNAPSHOT.jar --dest records/target