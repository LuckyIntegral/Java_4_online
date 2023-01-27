echo 'run hw_8'
echo 'build'
mvn clean package
echo 'run jar'
java -jar target/stream.jar