import entity.Item;
import impl.ItemDaoImpl;

import java.util.List;
import java.util.Scanner;

public class Test1 {

    private final static int B, H;
    private static boolean flag;


    static {
        final Scanner sc = new Scanner(System.in);
        B = sc.nextInt();
        H = sc.nextInt();
        if (B > 0 && H > 0) {
            flag = true;
        } else {
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }
    }

    public static void main(final String[] args) {

        List<Item> items=new ItemDaoImpl().findAll();



        new ItemDaoImpl().update(items.get(0));

        if (flag) {
            int area = B * H;
            System.out.print(area);
        }


    }
}