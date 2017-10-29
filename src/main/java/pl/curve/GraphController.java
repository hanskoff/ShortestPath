package pl.curve;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.github.GitHubApi;
import pl.github.GitHubUser;

@RestController
public class GraphController {

  private GitHubApi gitHubApi;

  public GraphController(GitHubApi gitHubApi) {
    this.gitHubApi = gitHubApi;
  }

  @RequestMapping("/distance")
  public Integer greeting(@RequestParam(value="usersrc") String ghUserNameSrc, @RequestParam(value="userdst") String ghUserNameDst) {
    GitHubUser gitHubUser = gitHubApi.findUserRepositories(ghUserNameSrc);
    Graph graph = Graph.createGraph(gitHubUser);
    return graph.searchDistance(ghUserNameSrc, ghUserNameDst);
  }

//   curl "http://localhost:8080/distance?usersrc=jan&userdst=karol"
}
