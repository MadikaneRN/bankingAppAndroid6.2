
package cput.ac.za.bankingapp.factoryTest;

import cput.ac.za.bankingapp.factory.ElectricityFactory;
import cput.ac.za.bankingapp.domain.Electricity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Scorpian on 2016-04-03.
 */
public class ElectricityTest {

    private Electricity electricity;

    @Before
    public void setUp() throws Exception {

        electricity =  ElectricityFactory.getElectricity("m9109987","Eskom",50.0);

    }

    @Test
    public void testElectric() throws Exception {

        Assert.assertEquals(electricity.getMeterNo(),"m9109987");
        Assert.assertEquals(electricity.getSupplierName(),"Eskom");
        Assert.assertEquals(electricity.getAmount(),50.0,0.001);

    }

    @Test
    public void testUpdate() throws Exception {
        Electricity electricityUpdate = new Electricity.Builder(electricity.getMeterNo())
                .copy(electricity)
                .supplierName("Nersa")
                .amount(100)
                .build();
        Assert.assertEquals(electricityUpdate.getMeterNo(),"m9109987");
        Assert.assertEquals(electricityUpdate.getAmount(),100,0.001);
        Assert.assertEquals(electricityUpdate.getSupplierName(),"Nersa");

    }

}
