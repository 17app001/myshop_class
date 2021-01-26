import entity.Item;
import impl.ItemDaoImpl;
import util.Tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemViewer implements ActionListener {
    private JPanel bottomPane;
    private JButton addBtn;
    private JButton viewBtn;
    private JTextField nameText;
    private JTextField dateText;
    private JTextArea infoText;
    private JTextField priceText;
    private JTextField qtyText;
    private JPanel mainPanel;

    //產生建構式（方便存取私有元件變數)

    public ItemViewer(){
        JFrame frame = new JFrame("ItemViewer");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //實作按鈕監聽功能（this==>本身類別需要實作該介面方法）
        addBtn.addActionListener(this);

    }

    public static void main(String[] args) {
        new ItemViewer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addBtn){

            //開始取得每個欄位資料
            try {
                String name = nameText.getText();
                float price = Float.parseFloat(priceText.getText());
                int qty = Integer.parseInt(qtyText.getText());

                //自動取得今天日期
                String date = dateText.getText();
                if(date.equals("")){
                    date= Tools.getToday();
                }

                String info = infoText.getText();
                //測試輸出
                //System.out.println(name + " " + price + " " + qty + " " + date + " " + info);
                //寫入資料庫
                Item item = new Item(name,price,qty,Tools.strToDate(date),info);
                new ItemDaoImpl().insert(item);

                System.out.println("寫入資料庫成功:"+item);

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    }
}
