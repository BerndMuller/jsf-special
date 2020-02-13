#!/bin/bash
# erzeugt JAR (target/components.jar) für native Komponenten
mkdir -p target/META-INF/resources/css
mkdir -p target/META-INF/resources/images
mkdir -p target/META-INF/resources/js
cp src/main/webapp/resources/css/expanding-list.css target/META-INF/resources/css/expanding-list.css
cp src/main/webapp/resources/js/expanding-list.js target/META-INF/resources/js/expanding-list.js
cp src/main/webapp/resources/images/down.svg target/META-INF/resources/images/down.svg
cp src/main/webapp/resources/images/right.svg target/META-INF/resources/images/right.svg
jar cvf target/components.jar \
    -C target/classes/ de/jsfpraxis/special/components/Greeting.class \
    -C target/classes/ de/jsfpraxis/special/components/HelloWorld.class \
    -C target/classes/ de/jsfpraxis/special/components/Tree.class \
    -C target META-INF
