# Introduction

The application calculates the minimal path for a given whole-number triangle.

As the input it takes lines which describe each row, starting from the topmost single element.
If the input isn't a valid triangle (e.g. wrong number of elements in a row) an error will be produced.

# Commands:
 * sbt run - run the application
 * sbt test - run the unit tests
 * sbt assembly - package the app into a JAR found under the `target/scala-2.13/shortest_path.jar` location
 
# Running the application:

The simplest way is to use the sbt shell and the `sbt run` command.
An alternative is packaging the app into a JAR (the `sbt assembly` command) and executing 
it with `java -jar shortest_path.jar` when executed from the same directory as the JAR 
