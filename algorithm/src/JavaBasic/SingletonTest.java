package JavaBasic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/22
 * @Description
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton s = null;
        s = Singleton.getInstance();
        s.print();
        Singleton2 s1 = null;
        s1 = Singleton2.getInstance();
        s1.print();
    }
}
