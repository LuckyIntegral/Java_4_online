echo 'run simple'
cd ./simple
javac Hello.java
java Hello

cd ../

echo 'run package'
cd ./package
javac ua/com/alevel/Hello.java
java ua.com.alevel.Hello

cd ../

echo 'run separate_package'
cd ./separate_package
javac ua/com/alevel/Hello.java
java ua.com.alevel.Hello

cd ../

echo 'run minimal_proj'
cd ./minimal_proj
javac -sourcepath ./src -d build/classes src/ua/com/alevel/Hello.java
java  -cp build/classes ua.com.alevel.Hello

cd ../

echo 'run med_proj and create first jar'
cd ./med_proj
javac -sourcepath ./src -d build/classes src/ua/com/alevel/Hello.java
jar cvfm build/jar/hello.jar resourcess/MANIFEST.MF -C build/classes .
java -jar build/jar/Hello.jar

cd ../

find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f