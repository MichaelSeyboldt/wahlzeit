package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.AllPersistenceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllPersistenceTest.class, //list child directory AllTest first, then all testes in current directory
        AccessRightsTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        FliegerTest.class
})
public class AllModdelsTest {
}
