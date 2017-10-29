package pl.curve;

import org.junit.Test;
import pl.github.GitHubRepository;
import pl.github.GitHubUser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class GraphTest {

  @Test
  public void shouldReturnOneForSimpleCase() throws Exception {
    //given
    GitHubUser ghUser1 = new GitHubUser("jan");
    GitHubUser ghUser2 = new GitHubUser("karol");
    GitHubRepository ghRepo = new GitHubRepository("etherum");
    ghUser1.addRepositoryList(ghRepo);
    ghRepo.addContributor(ghUser1);
    ghRepo.addContributor(ghUser2);

    Graph objectUnderTest = Graph.createGraph(ghUser1);

    //when
    int result = objectUnderTest.searchDistance("jan", "karol");

    //then
    assertThat(result, is(1));
  }

  @Test
  public void shouldReturnTwoForDeeperLevelDistance() throws Exception {
    //given
    GitHubUser ghUser1 = new GitHubUser("jan");
    GitHubUser ghUser2 = new GitHubUser("karol");
    GitHubUser ghUser3 = new GitHubUser("waldek");
    GitHubRepository ghRepo = new GitHubRepository("etherum");
    GitHubRepository ghRepo2 = new GitHubRepository("monero");
    ghUser1.addRepositoryList(ghRepo);
    ghUser2.addRepositoryList(ghRepo2);
    ghRepo.addContributor(ghUser1);
    ghRepo.addContributor(ghUser2);
    ghRepo2.addContributor(ghUser3);

    Graph objectUnderTest = Graph.createGraph(ghUser1);

    //when
    int result = objectUnderTest.searchDistance("jan", "waldek");

    //then
    assertThat(result, is(2));
  }

  @Test
  public void shouldNotFindAnyDistance() throws Exception {
    //given
    GitHubUser ghUser1 = new GitHubUser("jan");
    GitHubUser ghUser2 = new GitHubUser("karol");
    GitHubUser ghUser3 = new GitHubUser("waldek");
    GitHubRepository ghRepo = new GitHubRepository("etherum");
    GitHubRepository ghRepo2 = new GitHubRepository("monero");
    ghUser1.addRepositoryList(ghRepo);
    ghUser2.addRepositoryList(ghRepo2);
    ghRepo.addContributor(ghUser1);
    ghRepo.addContributor(ghUser2);

    Graph objectUnderTest = Graph.createGraph(ghUser1);

    //when
    int result = objectUnderTest.searchDistance("jan", "waldek");

    //then
    assertThat(result, is(-1));
  }

  @Test
  public void shouldReturnOneForSimpleCaseForUserWith2Repos() throws Exception {
    //given
    GitHubUser ghUser1 = new GitHubUser("jan");
    GitHubUser ghUser2 = new GitHubUser("karol");
    GitHubUser ghUser3 = new GitHubUser("waldek");
    GitHubRepository ghRepo = new GitHubRepository("etherum");
    GitHubRepository ghRepo1 = new GitHubRepository("lisk");
    ghUser1.addRepositoryList(ghRepo);
    ghUser1.addRepositoryList(ghRepo1);
    ghUser3.addRepositoryList(ghRepo1);
    ghRepo.addContributor(ghUser1);
    ghRepo.addContributor(ghUser2);
    ghRepo1.addContributor(ghUser3);

    Graph objectUnderTest = Graph.createGraph(ghUser1);

    //when
    int result = objectUnderTest.searchDistance("jan", "waldek");

    //then
    assertThat(result, is(1));
  }
}