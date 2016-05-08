
package cput.ac.za.bankingapp.factoryTest;
import junit.framework.Assert;
import cput.ac.za.bankingapp.domain.Statement;
import cput.ac.za.bankingapp.factory.StatementFactory;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Scorpian on 2016-04-03.
 */
public class StatementTest {

	private Statement statement;


    @Before

    public void setUp() throws Exception {

        statement = StatementFactory.getStatement("Summary","Mon");

    }


    @Test
    public void testLogin() throws Exception {

        Assert.assertNotNull(statement);
        Assert.assertEquals(statement.getDetails(), "Summary");
        Assert.assertEquals(statement.getWeekandDay(),"Mon"); //deleteTest


    }


    @Test
    public void testUpdate() throws Exception {
        Statement statementUpdate = new Statement.Builder("eminem")// uses accNo as primary key
                .copy(statement)
				.weekandDay("Tue")
                .build();

        Assert.assertNotNull(statementUpdate);
        Assert.assertEquals(statementUpdate.getWeekandDay(),"Tue");


    }








}
