package view;

import entity.Item;
import impl.ItemDaoImpl;
import util.Tools;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/***
 * 商店頁面
 */
public class ItemPage {

    ItemDaoImpl itemDao;
    List<Item> items;

    public ItemPage() {
        itemDao = new ItemDaoImpl();
    }

    public void mainPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Tools.getToday());
        while (true) {
            System.out.println("=======================");
            System.out.println("1.產品資訊");
            System.out.println("2.新增產品");
            System.out.println("3.購買產品");
            System.out.println("4.離開");
            System.out.println("請輸入選擇:");
            int choice = scanner.nextInt();
            if (choice == 4) {
                break;
            }

            switch (choice) {
                case 1:
                    findAll();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    update();
                    break;
            }

            Tools.pressAnyKey();
        }
    }

    //更新資料
    public void update() {
        //依照編號呈現
        items = itemDao.findAll();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + "\t" + items.get(i).getItem());
        }

        if (items.size() <= 0) {
            System.out.println("目前無商品...");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================");
        System.out.println("請輸入購買商品編號:");
        int id = scanner.nextInt();

        if (id >= 0 && id <= items.size()) {
            itemDao.update(items.get(id));
        }
    }

    public void insert() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=======================");
            System.out.println("商品名稱(-1:exit):");
            String name = scanner.nextLine();
            if (name.equals("-1")) {
                break;
            }

            try {
                System.out.println("商品價格:");
                float price = scanner.nextFloat();
                System.out.println("商品數量:");
                int qty = scanner.nextInt();
                scanner.nextLine();
                System.out.println("請輸入日期(2020-1-1):");
                String date = scanner.nextLine();
                if (date.equals("")) {
                    date = Tools.getToday();
                    System.out.println(date);
                }
                System.out.println("請輸入備註");
                String info = scanner.nextLine();


                itemDao.insert(new Item(name, price, qty, Tools.strToDate(date), info));
            } catch (InputMismatchException e) {
                System.out.println("資料輸入錯誤!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void findAll() {
        items = itemDao.findAll();

        for (Item item : items) {
            System.out.println(item);
        }
    }
}



