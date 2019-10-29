import java.io.PrintWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class ProductServer {
    static String dbfile = "C:\\Users\\austi\\OneDrive\\Desktop\\SQL Databases\\store2.db";

    public static void main(String[] args) {

        int port = 1000;

        if (args.length > 0) {
            System.out.println("Running arguments: ");
            for (String arg : args)
                System.out.println(arg);
            port = Integer.parseInt(args[0]);
            dbfile = args[1];
        }

        try {
            ServerSocket server = new ServerSocket(port);

            System.out.println("Server is listening at port = " + port);

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
                Scanner in = new Scanner(pipe.getInputStream());

                String command = in.nextLine();
                if (command.equals("GET PRODUCT")) {
                    String str = in.nextLine();
                    System.out.println("GET product with id = " + str);
                    int productID = Integer.parseInt(str);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Products WHERE ProductID = " + productID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            out.println(rs.getString("Name")); // send back product name!
                            out.println(rs.getDouble("Price")); // send back product price!
                            out.println(rs.getDouble("Quantity")); // send back product quantity!
                        }
                        else
                            out.println("null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals("PUT PRODUCT")) {
                    String id = in.nextLine();  // read all information from client
                    String name = in.nextLine();
                    String price = in.nextLine();
                    String quantity = in.nextLine();

                    System.out.println("PUT command with ProductID = " + id);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Products WHERE ProductID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            rs.close();
                            stmt.execute("DELETE FROM Products WHERE ProductID = " + id);
                        }

                        sql = "INSERT INTO Products VALUES (" + id + ",\"" + name + "\","
                                + price + "," + quantity + ")";
                        System.out.println("SQL for PUT: " + sql);
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                }
                if (command.equals("GET CUSTOMER")) {
                    String str = in.nextLine();
                    System.out.println("GET customer with id = " + str);
                    int customerID = Integer.parseInt(str);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Customers WHERE CustomerID = " + customerID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            out.println(rs.getString("Name")); // send back product name!
                            out.println(rs.getString("Phone")); // send back product price!
                            out.println(rs.getString("Address")); // send back product quantity!
                        }
                        else
                            out.println("null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals("PUT CUSTOMER")) {
                    String id = in.nextLine();  // read all information from client
                    String name = in.nextLine();
                    String phone = in.nextLine();
                    String address = in.nextLine();
                    System.out.println("phone: " + phone + " address: " + address);

                    System.out.println("PUT command with CustomerID = " + id);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Customers WHERE CustomerID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            rs.close();
                            stmt.execute("DELETE FROM Customers WHERE CustomerID = " + id);
                        }

                        sql = "INSERT INTO Customers VALUES (" + id + ",\"" + name + "\","
                                + "\"" + phone + "\"," + "\"" + address + "\")";
                        System.out.println("SQL for PUT: " + sql);
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                }

//---------------PURCHASE UPDATE----------------

                if (command.equals("GET PURCHASE")) {
                    String str = in.nextLine();
                    System.out.println("GET purchase with id = " + str);
                    int customerID = Integer.parseInt(str);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Purchases WHERE PurchaseID = " + customerID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            out.println(rs.getInt("CustomerID"));
                            out.println(rs.getInt("ProductID"));
                            out.println(rs.getDouble("Price"));
                            out.println(rs.getDouble("Quantity"));
                            out.println(rs.getDouble("Cost"));
                            out.println(rs.getDouble("Tax"));
                            out.println(rs.getDouble("Total"));
                            out.println(rs.getString("Date"));
                        }
                        else
                            out.println("null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals("PUT PURCHASE")) {
                    String id = in.nextLine();  // read all information from client
                    String cid = in.nextLine();
                    String pid = in.nextLine();
                    String price = in.nextLine();
                    String quant = in.nextLine();
                    String cost = in.nextLine();
                    String tax = in.nextLine();
                    String total = in.nextLine();
                    String date = in.nextLine();

                    //System.out.println("phone: " + phone + " address: " + address);

                    System.out.println("PUT command with PurchaseID = " + id);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Purchases WHERE PurchaseID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            rs.close();
                            stmt.execute("DELETE FROM Purchases WHERE PurchaseID = " + id);
                        }

                        sql = "INSERT INTO Customers VALUES (" + id + "," + cid + "," + pid + ","
                                + price + "," + quant + "," + cost + "," + tax + "," + total + ",\""
                                + date + "\")";
                        System.out.println("SQL for PUT: " + sql);
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                }

                else {
                    out.println(0); // logout unsuccessful!
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}