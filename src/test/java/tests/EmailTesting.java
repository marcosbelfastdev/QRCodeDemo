package tests;

import core.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Arrays;
import java.util.Properties;

public class EmailTesting {

    DriverManager driverManager;
    public WebDriver browser;

    @Before
    public void setup() {


    }

    @Test
    public void downloadAFile() throws MessagingException {
        Session session = Session.getDefaultInstance(new Properties( ));
        Store store = session.getStore("imaps");
        store.connect("imap.gmx.com", 993, "nextemailtest@gmx.com", "QAChapter@");
        Folder inbox = store.getFolder( "INBOX" );
        inbox.open(Folder.READ_ONLY);

        // Fetch unseen messages from inbox folder
        Message[] messages = inbox.search(
                new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        // Sort messages from recent to oldest
        Arrays.sort( messages, (m1, m2 ) -> {
            try {
                return m2.getSentDate().compareTo( m1.getSentDate() );
            } catch ( MessagingException e ) {
                throw new RuntimeException( e );
            }
        } );

        for ( Message message : messages ) {
            System.out.println(
                    "sendDate: " + message.getSentDate()
                            + " subject:" + message.getSubject() );
        }
    }

    @After
    public void quit() {

    }
}
