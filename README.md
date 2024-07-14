Task : To Automate the Below Scenario.
Scenario :- All the users of City `FanCode` should have more than half of their todos task completed.
Given User has the todo tasks
And User belongs to the city FanCode
Then User Completed task percentage should be greater than 50%
----------------------------------------------------------------------------------------------------------------------------------------------------------------

Solution:- The solution is developed using Java language and the Framework used is Spring Boot
-- Steps to run the Project
1. You should have Java(i.e JDK) and Maven installed in your machine as a pre-requisite
2. Open the Spring Boot project in a STS or Eclipse IDE
3. Right click on the project folder in Eclipse and "Run As" -> "Maven Install"
4. Once step 3 is completed successfully! Go to the below mentioned file path and right click and run as Spring Boot App/ Java Application
src/main/java/com/shivam/api_automation/TodosApiAutomationApplication.java
5. It will return the User Id along with the PercentageCompletion satisfying the logic mentioned in the above Task.

Output:-
[2m2024-07-14T16:46:06.032+05:30[0;39m [32m INFO[0;39m [35m21096[0;39m [2m---[0;39m [2m[TodosAPIAutomation] [           main][0;39m [2m[0;39m[36mc.s.a.TodosApiAutomationApplication     [0;39m [2m:[0;39m ------------------ Results! ------------------
[2m2024-07-14T16:46:06.033+05:30[0;39m [32m INFO[0;39m [35m21096[0;39m [2m---[0;39m [2m[TodosAPIAutomation] [           main][0;39m [2m[0;39m[36mc.s.a.TodosApiAutomationApplication     [0;39m [2m:[0;39m {5=60.0, 10=60.0}


Note:-
1. You can modify the Latitudes and Longitudesbased on the requirements of a given city and the application will handle as per the mentioned geo location.
2. You can also customize the Completion Percentage of todos as per you preference

API Resource used in this project:-
1. Resources(APIs) :- http://jsonplaceholder.typicode.com/ (/todos and /users)
