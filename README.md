### Challenge

Please create REST API with one endpoint which will find the shortest contribution path
between two GitHub users.


### Solution

I've created a java implementation with help of spring framework (for building REST api and dependency injection).
Spring logic is in GraphController class.

To find a shortest contribution path between 2 GitHub users I decided to create a graph and traverse it with BFS incrementing distance value to each node.
Solution is in Graph class.

There are unit tests in class GraphTest.

### Running

It's required to build project with:
  
    ./gradlew build
    
I recommend to import project to IDE. (I use intelij Idea IDE).

To start REST server on port 8080, man has to run main method from Application class.
After that it is possible to call:

    curl "http://localhost:8080/distance?usersrc=jan&userdst=karol"
    
Where `usersrc` (source) contains a name of first user and `userdst` (destination) is a detstination user.

Connection to github API is skipped and data is being mocked in GitHubApiMock class.