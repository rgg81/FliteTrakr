# Building executable JAR

*This project requires java 17.*


`$ ./mvnw clean package`

# Run

`$ java -jar target/AdidasAssigment.jar example.txt`

## Instructions

We need to pass a text file as parameter. The file must have the following structure:   
First line has the data:   
Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27,LHR-NUE-23   
Following lines are commands or questions to be made. There are 4 commands:   
PRICE_CONNECTION PATH    
CHEAPEST_CONNECTION SOURCE-DESTINATION   
SEARCH_STOPS SOURCE-DESTINATION THRESHOLD [MAX|MIN|EQUAL]
SEARCH_PRICES SOURCE-DESTINATION THRESHOLD    


## Input example

Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27,LHR-NUE-23    
PRICE_CONNECTION NUE-FRA-LHR    
CHEAPEST_CONNECTION NUE-AMS    
CHEAPEST_CONNECTION AMS-FRA    
SEARCH_STOPS NUE-FRA 3 MAX    
SEARCH_PRICES NUE-LHR 170     






