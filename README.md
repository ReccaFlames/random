# Random API

Random API returns sum of 2 randomized integers given from [Random.Org](https://www.random.org) and `java.security.SecureRandom`.

## Examples
### /v1/api/random-number/sum
#### Parameters:
* min - lower boundary for random numbers  
* max - upper boundary for random numbers
 
#### Example response:
 ```JSON
{
    "numberFromFirstSource": 1059,
    "numberFromSecondSource": 1926,
    "sumOfNumbers": 2985
}
 ```
numberFromFirstSource - random number from first source
numberFromSecondSource - random number from second source
sumOfNumbers - sum of `numberFromFirstSource` and `numberFromSecondSource`

#### Example request  
`localhost:8080/v1/api/random-number/sum?min=0&max=2000`