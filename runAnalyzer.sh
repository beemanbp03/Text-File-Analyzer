#! /bin/sh
#! $2 = /analyzer.properties
java -classpath dist/lib/the-wonderful-analyzer-20210724.jar:config analyzer.Driver $1 /analyzer.properties
