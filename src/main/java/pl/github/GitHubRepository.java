package pl.github;

import java.util.ArrayList;
import java.util.List;

public class GitHubRepository {

  private String name;
  private List<GitHubUser> contributors = new ArrayList<>();

  public GitHubRepository(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<GitHubUser> getContributors() {
    return contributors;
  }

  public void addContributor(GitHubUser contributor) {
    this.contributors.add(contributor);
  }

}
