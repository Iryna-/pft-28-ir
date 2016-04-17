package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test

  public void testCommits () throws IOException {
    Github github = new RtGithub("cd4664c180d53716494e9b61d3084a729db6fa81");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Iryna-", "pft-28-iryna.svitlychenko")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
