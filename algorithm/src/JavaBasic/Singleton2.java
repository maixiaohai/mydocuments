package JavaBasic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/22
 * @Description 懒汉式，调用时才实例化对象，但有严重线程安全问题
 */
public class Singleton2 {
    private static Singleton2 instance;
    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
    private Singleton2() {};
    public void print() {
        System.out.println("懒汉式 Singleton");
    }
}
