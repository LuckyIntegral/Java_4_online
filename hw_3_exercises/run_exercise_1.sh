echo 'run exercise_1'
# shellcheck disable=SC2164
cd exercise_1
mvn clean package
java -jar target/exercise1.jar
echo 'finish'