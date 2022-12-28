echo 'run hw-5'
echo 'build:'
mvn clean package
echo 'run jar'
java -jar target/crud_test.jar