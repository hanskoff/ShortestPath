package pl.github;

import java.util.ArrayList;
import java.util.List;

public class GitHubUser {
  private String name;
  private List<GitHubRepository> repositoryList = new ArrayList<>();

  public GitHubUser(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<GitHubRepository> getRepositories() {
    return repositoryList;
  }

  public void addRepositoryList(GitHubRepository repository) {
    this.repositoryList.add(repository);
  }

}
