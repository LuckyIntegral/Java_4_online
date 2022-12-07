echo 'run first_hello_world package'
cd ./first_hello_world
javac Hello.java
java Hello

cd ../

echo 'run second_hello_world'
cd ./second_hello_world
javac ua/com/alevel/Hello.java
java ua.com.alevel.Hello

cd ../

echo 'run third_hello_world'
cd ./third_hello_world
javac ua/com/alevel/Hello.java
java ua.com.alevel.Hello

cd ../

echo 'run medium_hello_world'
cd ./medium_hello_world
javac -sourcepath ./src -d build/classes src/ua/com/alevel/Hello.java
java  -cp build/classes ua.com.alevel.Hello

cd ../

echo 'run hard_hello_world and create first jar'
cd ./hard_hello_world
javac -sourcepath ./src -d build/classes src/ua/com/alevel/Hello.java
jar cvfm build/jar/hello.jar resourcess/MANIFEST.MF -C build/classes .
java -jar build/jar/Hello.jar

cd ../

find . -name "*.class" -type f -print0 | xargs -0 /bin/rm -f