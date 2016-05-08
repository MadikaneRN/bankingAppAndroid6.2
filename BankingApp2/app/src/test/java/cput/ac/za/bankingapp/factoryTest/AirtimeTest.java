package cput.ac.za.bankingapp.factoryTest;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import cput.ac.za.bankingapp.domain.Airtime;
import cput.ac.za.bankingapp.factory.AirtimeFactory;


/**
 * Created by Scorpian on 2016-04-03.
 */
public class AirtimeTest {


    private Airtime myAritime;

    @Before
    public void setUp() throws Exception {
        myAritime = AirtimeFactory.getAirtime("0825915433","MadikaneRn","Vodacom");

    }

    @Test
    public void testAirtime() throws Exception {
        Assert.assertEquals(myAritime.getCellphoneNo(),"0825915433");
        Assert.assertEquals(myAritime.getServiceProvider(),"Vodacom");

    }


    public void testUpdate() throws Exception
    {
        Airtime airtimeUpdate = new Airtime.Builder("0825915433")// uses accNo as primary key
                .copy(myAritime)
                .beneficiary("Mgayi")
                .serviceProvider("CellC")
                .build();

        Assert.assertEquals(airtimeUpdate.getCellphoneNo(),"0825915433");
        Assert.assertEquals(airtimeUpdate.getServiceProvider(),"CellC");
        Assert.assertEquals(airtimeUpdate.getBeneficiary(),"Mgayi");
    }


}








