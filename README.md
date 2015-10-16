# Hotel

Project to implement a HTTP service which does the following :
1. Search for hotels based on the city name
2. Sort the results according to ascending or descending order.
3. Rate Limit API requests based on in-memory representation of RateLimit

URL :
http://localhost:8080/api/hotel
http://localhost:8080/api/hotel/city/Bangkok
http://localhost:8080/api/hotel/city?city=Bangkok&sort=asc
