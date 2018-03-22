import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/21
 * @Description
 */
public class ExecutorTest {
    private static FutureContext<Boolean> context;

    public static class FutureContext<T> {
        private List<Future<T>> futureList = new ArrayList<Future<T>>();
        public void addFuture(Future<T> future) {
            this.futureList.add(future);
        }
        public List<Future<T>> getFutureList() {
            return this.futureList;
        }
    }
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        context = new FutureContext<Boolean>();
        Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                System.out.println("ExecutorTest.call");
                return false;
            }
        });
        context.addFuture(future);
        List<Future<Boolean>> list = context.getFutureList();
        for (Future<Boolean> f : list) {
            if (f.isDone() && !f.isCancelled()) {
                System.out.println("done ~~");
            }
        }

        Class c = Class.forName("java.lang.Integer");
        //获取所有的属性?
        Field[] fs = c.getDeclaredFields();

        //定义可变长的字符串，用来存储属性
        StringBuffer sb = new StringBuffer();
        //通过追加的方法，将每个属性拼接到此字符串中
        //最外边的public定义
        sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");
        //里边的每一个属性
        for(Field field:fs){
            sb.append("\t");//空格
            sb.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等
            sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字
            sb.append(field.getName()+";\n");//属性的名字+回车
        }

        sb.append("}");

    }

//    public static void add() throws Exception{
//        Scanner scanner = new Scanner(System.in);
//        int a = Integer.parseInt(scanner.next());
//        int b = Integer.parseInt(scanner.next());
//        System.out.println(a + b);
//        scanner.close();
//    }

}

