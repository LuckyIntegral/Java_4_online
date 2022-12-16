echo 'Run all tasks'

echo 'Run task_1'
cd exercise_1
mvn clean package
echo 'Write a string of numbers'
java -jar target/task_1.jar
echo 'Finish task_1'

cd ../

echo'Run task_2'
cd exercise_2
mvn clean package
echo 'Write a string of letters'
java -jar target/task_2.jar
echo 'Finish task_2'

cd ../

exho 'Run task_3'
cd exercise_3
mvn clean package
echo 'Write a number of lessons'
java -jar target/task_3.jar
echo 'Finish task_3'