JC =javac

all: 
	mkdir META-INF
	$(JC) src/*.java -d ./
	echo Main-Class: Stocks > META-INF/MANIFEST.MF
	jar cvfm Stocks.jar META-INF/MANIFEST.MF *.class

clean:
	rm -rf META-INF
	rm -rf classes
	rm Stocks.jar
	rm rf *.class
