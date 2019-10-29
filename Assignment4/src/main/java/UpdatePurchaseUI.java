import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UpdatePurchaseUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load Purchase");
    public JButton btnSave = new JButton("Save Purchase");


    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);

    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");

    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");

    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");

    public UpdatePurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update Purchase Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("PurchaseID "));
        line1.add(txtPurchaseID);
        line1.add(labDate);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("CustomerID "));
        line2.add(txtCustomerID);
        line2.add(labCustomerName);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("ProductID "));
        line3.add(txtProductID);
        line3.add(labProductName);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Quantity "));
        line4.add(txtQuantity);
        line4.add(labPrice);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(labCost);
        line5.add(labTax);
        line5.add(labTotalCost);
        view.getContentPane().add(line5);

    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();

            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            // do client/server

            try {
                Socket link = new Socket("localhost", 1000);
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                output.println("GET PURCHASE");
                output.println(purchase.mProductID);

                purchase.mCustomerID = input.nextInt();
                txtCustomerID.setText(Integer.toString(purchase.mCustomerID));

                purchase.mProductID = input.nextInt();
                txtProductID.setText(Integer.toString(purchase.mProductID));

                purchase.mPrice = input.nextDouble();
                labPrice.setText(Double.toString(purchase.mPrice));

                purchase.mQuantity = input.nextDouble();
                txtQuantity.setText(Double.toString(purchase.mQuantity));

                purchase.mCost = input.nextDouble();
                labCost.setText(Double.toString(purchase.mCost));

                purchase.mTax = input.nextDouble();
                labTax.setText(Double.toString(purchase.mTax));

                purchase.mTotal = input.nextDouble();
                labTotalCost.setText(Double.toString(purchase.mTotal));

                purchase.mDate = input.nextLine();
                labDate.setText(purchase.mDate);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            String cid = txtCustomerID.getText();
            purchase.mCustomerID = Integer.parseInt(cid);

            String pid = txtProductID.getText();
            purchase.mProductID = Integer.parseInt(pid);

            String price = labPrice.getText();
            purchase.mPrice = Double.parseDouble(price);

            String quant = txtQuantity.getText();
            purchase.mQuantity = Double.parseDouble(quant);

            String cost = labCost.getText();
            purchase.mCost = Double.parseDouble(cost);

            String tax = labTax.getText();
            purchase.mTax = Double.parseDouble(tax);

            String total = labTotalCost.getText();
            purchase.mTotal = Double.parseDouble(total);

            String date = labDate.getText();
            purchase.mDate = date;

            // all product infor is ready! Send to Server!

            try {
                Socket link = new Socket("localhost", 1000);
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                output.println("PUT PURCHASE");
                output.println(purchase.mPurchaseID);
                output.println(purchase.mCustomerID);
                output.println(purchase.mProductID);
                output.println(purchase.mPrice);
                output.println(purchase.mQuantity);
                output.println(purchase.mCost);
                output.println(purchase.mTax);
                output.println(purchase.mTotal);
                output.println(purchase.mDate);
                JOptionPane.showMessageDialog(null, "Purchase Information Updated Successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
