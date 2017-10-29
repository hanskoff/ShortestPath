package pl.github;

import org.springframework.stereotype.Component;

@Component
public class GitHubApiMock implements GitHubApi {

  @Override
  public GitHubUser findUserRepositories(String userName) {
    GitHubUser ghUser1 = new GitHubUser(userName);
    GitHubUser ghUser2 = new GitHubUser("karol");
    GitHubRepository ghRepo = new GitHubRepository("etherum");
    ghUser1.addRepositoryList(ghRepo);
    ghRepo.addContributor(ghUser1);
    ghRepo.addContributor(ghUser2);
    return ghUser1;
  }
}
