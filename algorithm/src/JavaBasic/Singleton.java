package JavaBasic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/22
 * @Description 恶汉式，加载时直接创建实例化对象，没有线程安全问题
 */
public class Singleton {
    private final static Singleton INSTANCE = new Singleton();
    public static Singleton getInstance() {
        return INSTANCE;
    }
    private Singleton() {}

    public void print() {
        System.out.println("恶汉式Singleton ");
    }
}
