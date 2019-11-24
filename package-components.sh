#!/bin/bash
# erzeugt JAR (target/components.jar) für native Komponenten
jar cvf target/components.jar -C target/classes/ de/jsfpraxis/advanced/components/Greeting.class -C target/classes/ de/jsfpraxis/advanced/components/HelloWorld.class 

