# TripAdvisor API
----

| EndPoint | Params | Description |
| ------ | ------ | ------ |
| queryOne | ***season***:String | all cities where the best season is ***season*** |
| queryTwo | ***category***:String | all cities of category ***category*** |
| queryThree | ***category***:String | all cities that contains activities of category ***category*** |
| queryFour | ***category***:String | all activities of category ***category*** with their ranking sorted |
| queryFive | ***category***:String,\<br/>***rank***:int | all activities of category ***category*** with their ranking equals to ***rank*** |

| querySix | ***category***:String, ***maxPrice***:int(optional), ***duration***:int(optional) | all activities of category ***category*** that is open in daylight, has optional ***maxPrice*** and ***duration*** parameter for query 17 and 18 and 19. |
| querySeven | ***category***:String | all activities of category ***category*** that are open at night |
| queryEight | ***category***:String | all activities of category ***category*** along with their prices |
| queryNine | ***category***:String | all activities of category ***category*** along with their prices sorted |
| queryTen | ***category***:String,<br/> ***city***:String | all activities of category ***category*** in city ***city*** |
| queryEleven |  | same as ***queryEight*** |
| queryTwelve | ***category***:String, ***match***:String | all activities of category ***category*** that has the string ***match*** in its name |
| queryThirteen | ***category***:String, ***maxPrice***:int | all activities of category ***category*** that have their price less than ***maxPrice*** |
| queryFourteen | ***category***:String, ***user***:String | all activities of category ***category*** that matches as an interest to user ***user*** |
| queryFifteen | ***category***:String | all activities with category ***category*** |
<!-- done ^ -->