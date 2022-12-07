## About compilation ##
### go to /java/first_hello_world ###
`javac Hello.java`\
`java Hello`
### go to java/second_hello_world ###
`javac ua/com/alevel/Hello.java`\
`java ua.com.alevel.Hello`
### go to java/third_hello_world ###
`javac ua/com/alevel/Hello.java`\
`java ua.com.alevel.Hello`
### go to java/medium_hello_world ###
`javac -sourcepath ./src -d build/classes src/ua/com/alevel/Hello.java`\
`java  -cp build/classes ua.com.alevel.Hello`
### go to java/hard_hello_world ###
`javac -sourcepath ./src -d build/classes src/ua/com/alevel/Hello.java`\
`jar cvfm build/jar/hello.jar resourcess/MANIFEST.MF -C build/classes .`\
`java -jar build/jar/Hello.jar`
### finally clear all directories ###
`find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f`