echo 'run java_level_2 package with new lib'
javac -sourcepath ./java -d build/classes -cp ./lib/commons-lang3.jar src/com/google/drive/Manager.java src/com/google/drive/dictionary/Messages.java
cd lib
jar fx commons-lang3.jar
cp -rf org ../build/classes
cd ../
jar cvfm build/jar/manager.jar resourcess/MANIFEST.MF -C build/classes .
jar tf build/jar/manager.jar
java -jar build/jar/manager.jar

find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f