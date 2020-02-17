package BasicLearn.JunitTestExample;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by hjw on 17/5/19.
 */
@RunWith(Parameterized.class)
public class PrintTest {
    String  expected = null;// 期望输出
    String ctype = null;
    /*
    测试参数数组
    一组测试样例: {"expected","ctype",  "appversion",  "UtmCamp"}
    */

    @Parameterized.Parameters
    public static Collection<Object[]> t(){
        return Arrays.asList(new Object[][]{
                {"hello","hell0o"} //( String  expected,String ctype)
                }
        );
    }
    public PrintTest( String  expected,String ctype) {
        this.expected = expected;
        this.ctype = ctype;
    }

    @org.junit.Test
    public void testOutput(){
        // subject
        Assert.assertEquals(expected,(new Print()).helloPrint(ctype));
    }

}
