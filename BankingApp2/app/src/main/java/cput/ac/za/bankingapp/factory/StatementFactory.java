package cput.ac.za.bankingapp.factory;

import cput.ac.za.bankingapp.domain.Statement;

import java.util.Date;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class StatementFactory {

    public static Statement getStatement(String details, String currentDate)
    {
        Statement myStatement = new Statement.Builder()
                .details(details)
                .weekandDay(currentDate)
                .build();

        return myStatement;
    }

}
