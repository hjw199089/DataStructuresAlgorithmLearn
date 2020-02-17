package BasicLearn.JunitTestExample;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by hjw on 17/5/19.
 */
/*
   运行所有测试
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                PrintTest.class      //,分割所有测试类即可
        }
)
public class TestAll_xxxTest {}


