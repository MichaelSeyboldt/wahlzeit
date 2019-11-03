package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.AllHandlersTest;
import org.wahlzeit.model.AllModdelsTest;
import org.wahlzeit.services.AllServicesTest;
import org.wahlzeit.utils.AllUtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllHandlersTest.class,
        AllModdelsTest.class,
        AllServicesTest.class,
        AllUtilsTest.class
})
public class AllTest {
}
