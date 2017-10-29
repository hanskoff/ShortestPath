package pl.github;

import org.springframework.stereotype.Component;

@Component
public interface GitHubApi {

  GitHubUser findUserRepositories(String userName);
}
