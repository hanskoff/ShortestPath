package pl.curve;

import pl.github.GitHubUser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

public class Graph {

  private HashMap<String, User> userLookup = new HashMap<>();

  public static class User {
    private String name;
    LinkedList<User> adjacent = new LinkedList<>();

    private User(String name) {
      this.name = name;
    }

  }

  public void addEdge(String source, String destination) {
    User srcUser = ofNullable(userLookup.get(source))
      .orElseGet(getUserSupplier(source));
    User destUser = ofNullable(userLookup.get(destination))
      .orElseGet(getUserSupplier(destination));

    addEdge(srcUser, destUser);
  }

  public Integer searchDistance(String sourceName, String destinationName) {
    return searchDistance(getUser(sourceName), getUser(destinationName));
  }

  public static Graph createGraph(GitHubUser ghUser) {
    Graph g = new Graph();
    addUserContributorsToGraph(ghUser, g);

    ghUser.getRepositories()
      .forEach(repo -> repo.getContributors()
        .forEach(usr -> {
          if (!ghUser.equals(usr)) {
            addUserContributorsToGraph(usr, g);
          }
        }));
    return g;
  }

  private static void addUserContributorsToGraph(GitHubUser ghUser, Graph graph) {
    User rootUser = graph.getUser(ghUser.getName());
    ghUser.getRepositories()
      .forEach(repository -> repository.getContributors()
        .forEach(user -> graph.addEdge(rootUser, graph.getUser(user.getName()))));
  }

  //BFS
  private Integer searchDistance(User source, User destination) {
    HashSet<User> visitedSet = new HashSet<>();
    HashMap<User, Integer> distance = new HashMap<>();
    LinkedList<User> queue = new LinkedList<>();

    visitedSet.add(source);
    distance.put(source, 0);
    queue.add(source);

    while (queue.size() != 0) {
      source = queue.poll();

      if (source.equals(destination)) {
        return distance.get(source);
      }

      LinkedList<User> adjacent = source.adjacent;

      while (!adjacent.isEmpty()) {
        User n = adjacent.poll();
        if (!visitedSet.contains(n)) {
          distance.put(n, distance.get(source) + 1);
          visitedSet.add(n);
          queue.add(n);
        }
      }

    }
    return -1;
  }

  private User getUser(String name) {
    return ofNullable(userLookup.get(name))
      .orElseGet(getUserSupplier(name));
  }

  private Supplier<User> getUserSupplier(String userName) {
    return () -> {
      User nd = new User(userName);
      userLookup.put(userName, nd);
      return nd;
    };
  }

  private void addEdge(User source, User destination) {
    source.adjacent.add(destination);
  }
}
