package com.otto.beekeeperstocksystem;
import com.otto.beekeeperstocksystem.FactoryTests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Quam on 4/24/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeekeeperFactoryTest.class,
        CategoryFactoryTest.class,
        CustomerFactoryTest.class,
        HarvestFactoryTest.class,
        HiveFactoryTest.class,
        LocationFactoryTest.class,
        OrderFactoryTest.class,
        OrderlineFactoryTest.class,
        PersonFactoryTest.class,
        ProductFactoryTest.class,
        SalesmanFactoryTest.class,
        SubLocationFactoryTest.class,
        SupersFactoryTest.class})
public class TestSuite {
}
