import org.junit.Test;

import java.util.Calendar;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: PACKAGE_NAME
 * @Author: hong
 * @CreateTime: 2024-04-09  14:27
 * @Description: TODO
 * @Version: 1.0
 */

public class test {

    @Test
    public void getMonth() {
//        Calendar calendar = Calendar.getInstance();

//        int month = calendar.get(Calendar.MONTH) + 1;
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        System.out.println(month);
    }
}
