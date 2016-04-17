package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

public class GithubTests {

  @Test

  public void testCommits (){
    Github github = new RtGithub("ff8ee3952c23a47ca7a3bdab74e71e11c779613c");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Iryna-", "pft-28-iryna.svitlychenko")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(commit);
    }
  }
}
