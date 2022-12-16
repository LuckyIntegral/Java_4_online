echo 'run all tasks'

echo 'run task_1'
cd exercise_1
mvn clean package
java -jar target/task_1.jar
echo 'finish task_1'

cd ../

echo'run task_2'
cd exercise_2
mvn clean package
java -jar target/task_2.jar
echo 'finish task_2'

cd ../