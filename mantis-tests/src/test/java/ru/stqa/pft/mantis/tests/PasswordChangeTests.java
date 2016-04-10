package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testPasswordChange () throws IOException, MessagingException {
    app.admin().startResetPassword();
    String email = app.admin().getUserEmail();
    System.out.println(email);
    String username = app.admin().getUsername();
    System.out.println(username);
    app.admin().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 30000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    String newPassword = "newpassword";
    app.admin().completeResetPassword(confirmationLink, newPassword);
    assertTrue(app.newSession().login(username, newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
