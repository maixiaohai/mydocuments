package JavaBasic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/13
 * @Description 只在getInstace方法前加修饰符synchronized可以处理并发问题，但是性能不好，下为使用同步代码块，且检查了两次null情况。
 * 这种写法被称为“双重检查锁”，顾名思义，就是在getSingleton()方法中，进行两次null检查。看似多此一举，但实际上却极大提升了并发度，
 * 进而提升了性能。为什么可以提高并发度呢？就像上文说的，在单例中new的情况非常少，绝大多数都是可以并行的读操作。
 * 因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了。
 */
public class Singleton3 {
    private static volatile Singleton3 instance;
    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
    private Singleton3() {};
}
