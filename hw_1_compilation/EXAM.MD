## About compilation java ##
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

## About compilation java_level_2  ##
### go to /java_level_2 ###
`echo 'run java_level_2 package with new lib'`\
`javac -sourcepath ./java -d build/classes -cp ./lib/commons-lang3.jar src/com/google/drive/Manager.java src/com/google/drive/dictionary/Messages.java`\
`cd lib`\
`jar fx commons-lang3.jar`\
`cp -rf org ../build/classes`\
`cd ../`\
`jar cvfm build/jar/manager.jar resourcess/MANIFEST.MF -C build/classes .`\
`jar tf build/jar/manager.jar`\
`java -jar build/jar/manager.jar`\
### finally clear all directories ###
`find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f`
