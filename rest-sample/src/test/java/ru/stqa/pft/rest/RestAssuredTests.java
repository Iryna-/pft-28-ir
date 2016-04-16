package ru.stqa.pft.rest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test
  public void testCreateIssue () throws IOException {

    skipIfNotFixed(8);

    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test subject").withDescription("Test description");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(oldIssues, newIssues);

  }

  private Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }


  private int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given()
            .parameter("subject",newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }



  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpen (int issueId) {
    String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");
    String status = issues.get(0).getAsJsonObject().get("state_name").getAsString();
    if (status.equals("Closed")){
      return false;
    }
    return true;
  }
}
