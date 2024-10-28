import org.openjdk.jol.info.ClassLayout;

public class ClassLayOutPrint {

//    int num;

    public static void main(String[] args) {
        ClassLayOutPrint lock = new ClassLayOutPrint();
        System.out.println(lock);
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

}
