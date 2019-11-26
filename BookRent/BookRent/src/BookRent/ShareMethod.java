package BookRent;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.IOException;

public class ShareMethod {
    DatabaseManagement data = new DatabaseManagement();
    ResultSet resultSetBookStore = data.readBookStoreDataBase();
    ResultSet resultSetHistory = data.readHistoryOfUserDataBase();
    ResultSet resultSetCustomer = data.readCustomerDataBase();
    ResultSet resultSetPersonnel = data.readPersonnelDataBase();
    Object[][] bookData;
    Object[][] historyData;
    private int amount;
    private LinkedList<Double> OutstandingBalance = new LinkedList<Double>();
    private LinkedList<Integer> BookBalance = new LinkedList<Integer>();
    public static String username, firstName, lastName;
    public String addressImage;

    public static void setUsername(String username) {
        ShareMethod.username = username;
    }

    public static void setFirstName(String firstName) {
        ShareMethod.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        ShareMethod.lastName = lastName;
    }
    
    public String copiedImage(String addressImage) throws Exception {
        this.addressImage = addressImage;
        FileInputStream in = new FileInputStream(addressImage);
        BufferedInputStream bin = new BufferedInputStream(in);
        FileOutputStream ou = new FileOutputStream("D:\\code\\OOP_Project\\src\\images\\img" + findAmountOfBook() + ".png");
        BufferedOutputStream bou = new BufferedOutputStream(ou);
        int i = 0;
        while (i != -1) {
            i = bin.read();
            bou.write(i);
        }
        bin.close();
        bou.close();
        System.out.println("Copied!");
        return "D:\\\\code\\\\OOP_Project\\\\src\\\\images\\\\img" + findAmountOfBook() + ".png";
    }

    public int findAmountOfBook() {
        int i=0;
        try {
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                i = resultSetBookStore.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }

    public int findAmountOfPersonnel() {
        int i=0;
        try {
            resultSetPersonnel.beforeFirst();
            while (resultSetPersonnel.next()) {
                i = resultSetPersonnel.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }

    public int findAmountOfCustomer() {
        int i=0;
        try {
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                i = resultSetCustomer.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }

    public int findAmountOfHistory() {
        int i=0;
        try {
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                i = resultSetHistory.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void bookAmount() {
        amount = 0;
        try {
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                amount = resultSetBookStore.getInt(1);
            }
            bookData = new Object[amount][8];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void personnelAmount() {
        try {
            resultSetPersonnel.beforeFirst();
            while (resultSetPersonnel.next()) {
                amount = resultSetPersonnel.getInt(1);
            }
            bookData = new Object[amount][5];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UserAmount() {
        try {
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                amount = resultSetCustomer.getInt(1);
            }
            bookData = new Object[amount][6];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookHistoryAmount() {
        amount = 0;
        try {
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (username.equals(resultSetHistory.getString(2))) {
                    amount++;
                }
            }
            historyData = new Object[amount][8];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookHistoryReturnAmountForStaffAdmin() {
        amount = 0;
        try {
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(8).toUpperCase().equals("RETURNED")) {
                    amount++;
                }
            }
            historyData = new Object[amount][8];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookHistoryRentAmountForStaffAdmin() {
        amount = 0;
        try {
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(8).toUpperCase().equals("RENTED")) {
                    amount++;
                }
            }
            historyData = new Object[amount][8];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getBookStoreData() {
        try {
            System.out.println("come in");
            bookAmount();
            int i = 0;
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                ImageIcon Image = new ImageIcon(resultSetBookStore.getString(7));
                Image newImage = Image.getImage().getScaledInstance(50, 50, 0);
                Icon addIcon = new ImageIcon(newImage);
                bookData[i][0] = resultSetBookStore.getString(1).toUpperCase();
                bookData[i][1] = addIcon;
                bookData[i][2] = resultSetBookStore.getString(4).toUpperCase();
                bookData[i][3] = resultSetBookStore.getString(2).toUpperCase();
                bookData[i][4] = resultSetBookStore.getString(3).toUpperCase();
                bookData[i][5] = resultSetBookStore.getString(6).toUpperCase();
                bookData[i][6] = resultSetBookStore.getString(5).toUpperCase();
                bookData[i][7] = resultSetBookStore.getString(8).toUpperCase();
                System.out.println(bookData[i][1]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookData;
    }

    public Object[][] getPersonnelData() {
        try {
            System.out.println("come in");
            personnelAmount();
            int i = 0;
            resultSetPersonnel.beforeFirst();
            while (resultSetPersonnel.next()) {
                bookData[i][0] = resultSetPersonnel.getString(1).toUpperCase();
                bookData[i][1] = resultSetPersonnel.getString(2).toUpperCase();
                bookData[i][2] = resultSetPersonnel.getString(4).toUpperCase();
                bookData[i][3] = resultSetPersonnel.getString(5).toUpperCase();
                bookData[i][4] = resultSetPersonnel.getString(6).toUpperCase();
                System.out.println(i);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookData;
    }

    public Object[][] getHistoryData() {
        try {
            System.out.println("come in");
            bookHistoryAmount();
            int i = 0;
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                for (Object[] book : bookData) {
                    if (username.equals(resultSetHistory.getString(2))
                            && (resultSetHistory.getString(4).toUpperCase().equals(book[2]))) {
                        historyData[i][0] = i + 1 + "";
                        historyData[i][1] = book[1];
                        historyData[i][2] = book[2];
                        historyData[i][3] = book[3];
                        historyData[i][4] = book[4];
                        historyData[i][5] = book[5];
                        historyData[i][6] = resultSetHistory.getString(5).toUpperCase();
                        historyData[i][7] = resultSetHistory.getString(6).toUpperCase();
                        System.out.println(historyData[i][7]);
                        i++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    public Object[][] getHistoryDataReturnForStaffAdmin() {
        try {
            System.out.println("come in");
            bookHistoryReturnAmountForStaffAdmin();
            int i = 0;
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(8).toUpperCase().equals("RETURNED")) {
                    historyData[i][0] = i + 1 + "";
                    historyData[i][1] = resultSetHistory.getString(2).toUpperCase();
                    historyData[i][2] = resultSetHistory.getString(3).toUpperCase();
                    historyData[i][3] = resultSetHistory.getString(4).toUpperCase();
                    historyData[i][4] = resultSetHistory.getString(5).toUpperCase();
                    historyData[i][5] = resultSetHistory.getString(6).toUpperCase();
                    historyData[i][6] = resultSetHistory.getString(7).toUpperCase();
                    historyData[i][7] = resultSetHistory.getString(8).toUpperCase();
                    System.out.println(historyData[i][7]);
                    i++;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    public Object[][] getHistoryDataRentForStaffAdmin() {
        try {
            System.out.println("come in");
            bookHistoryRentAmountForStaffAdmin();
            int i = 0;
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(8).toUpperCase().equals("RENTED")) {
                    historyData[i][0] = i + 1 + "";
                    historyData[i][1] = resultSetHistory.getString(2).toUpperCase();
                    historyData[i][2] = resultSetHistory.getString(3).toUpperCase();
                    historyData[i][3] = resultSetHistory.getString(4).toUpperCase();
                    historyData[i][4] = resultSetHistory.getString(5).toUpperCase();
                    historyData[i][5] = resultSetHistory.getString(6).toUpperCase();
                    historyData[i][6] = resultSetHistory.getString(7).toUpperCase();
                    historyData[i][7] = resultSetHistory.getString(8).toUpperCase();
                    System.out.println(historyData[i][7]);
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyData;
    }

    public Object[][] getCustomerData() {
        try {
            System.out.println("come in");
            UserAmount();
            int i = 0;
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                bookData[i][0] = resultSetCustomer.getString(1).toUpperCase();
                bookData[i][1] = resultSetCustomer.getString(2).toUpperCase();
                bookData[i][2] = resultSetCustomer.getString(4).toUpperCase();
                bookData[i][3] = resultSetCustomer.getString(5).toUpperCase();
                bookData[i][4] = resultSetCustomer.getString(6).toUpperCase();
                bookData[i][5] = resultSetCustomer.getString(7).toUpperCase();
                System.out.println(bookData[i][1]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookData;
    }

    public void OutstandingBalanceData() {
        LinkedList<Object> listCustomer = new LinkedList<Object>();
        try {
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                for (Object customer : listCustomer) {
                    if (resultSetHistory.getString(2).equals(customer)) {
                        OutstandingBalance.add(OutstandingBalance.get(listCustomer.indexOf(customer))
                                + Double.parseDouble(resultSetHistory.getString(5)));
                        BookBalance.add(BookBalance.get(listCustomer.indexOf(customer)) + 1);
                    } else {
                        listCustomer.add(resultSetHistory.getString(2));
                    }
                }
            }
            historyData = new String[amount][8];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Double> getOutstandingBalance() {
        return OutstandingBalance;
    }

    public void setOutstandingBalance(LinkedList<Double> outstandingBalance) {
        OutstandingBalance = outstandingBalance;
    }

    public LinkedList<Integer> getBookBalance() {
        return BookBalance;
    }

    public void setBookBalance(LinkedList<Integer> bookBalance) {
        BookBalance = bookBalance;
    }
    
}