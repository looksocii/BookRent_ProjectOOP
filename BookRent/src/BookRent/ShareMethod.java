package BookRent;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShareMethod {

    DatabaseManagement data = new DatabaseManagement();
    ResultSet resultSetBookStore = data.readBookStoreDataBase();
    ResultSet resultSetHistory = data.readHistoryOfUserDataBase();
    ResultSet resultSetCustomer = data.readCustomerDataBase();
    ResultSet resultSetPersonnel = data.readPersonnelDataBase();
    Object[][] bookData;
    Object[][] historyData;
    private static String[] password;
    private int amount;
    private LinkedList<Double> OutstandingBalance = new LinkedList<Double>();
    private LinkedList<Integer> BookBalance = new LinkedList<Integer>();
    public static String username, firstName, lastName;
    public String addressImage;
    public static String[] imageAddress;

    public String copiedImage(String addressImage) throws Exception {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String currentDate = df.format(c.getTime());
        this.addressImage = addressImage;
        FileInputStream in = new FileInputStream(addressImage);
        BufferedInputStream bin = new BufferedInputStream(in);
        FileOutputStream ou = new FileOutputStream("C:\\Users\\User\\Desktop\\BookRent_ProjectOOP\\BookRent\\src\\imagecopy\\img" + currentDate + ".png");
        BufferedOutputStream bou = new BufferedOutputStream(ou);
        int i = 0;
        while (i != -1) {
            i = bin.read();
            bou.write(i);
        }
        bin.close();
        bou.close();
        System.out.println("Copied!");
        return "C:\\\\Users\\\\User\\\\Desktop\\\\BookRent_ProjectOOP\\\\BookRent\\\\src\\\\imagecopy\\\\img" + currentDate + ".png";
    }

    public int findAmountOfBook() {
        int i = 0;
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
        int i = 0;
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
        int i = 0;
        try {
            resultSetCustomer = data.readCustomerDataBase();
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
        int i = 0;
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
        resultSetBookStore = data.readBookStoreDataBase();
        amount = 0;
        try {
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                amount = resultSetBookStore.getInt(1);
            }
            bookData = new Object[amount][8];
            imageAddress = new String[amount];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void personnelAmount() {
        try {
            resultSetPersonnel = data.readPersonnelDataBase();
            resultSetPersonnel.beforeFirst();
            while (resultSetPersonnel.next()) {
                amount = resultSetPersonnel.getInt(1);
            }
            bookData = new Object[amount][5];
            password = new String[amount];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UserAmount() {
        try {
            resultSetCustomer = data.readCustomerDataBase();
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                amount = resultSetCustomer.getInt(1);
            }
            bookData = new Object[amount][6];
            password = new String[amount];
            System.out.println(amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookHistoryAmount() {
        amount = 0;
        try {
            resultSetHistory = data.readHistoryOfUserDataBase();
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (username.equals(resultSetHistory.getString(2))) {
                    amount++;
                }
            }
            historyData = new Object[amount][9];
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
            resultSetBookStore = data.readBookStoreDataBase();
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                imageAddress[i] = resultSetBookStore.getString(7);
                ImageIcon Image = new ImageIcon(resultSetBookStore.getString(7));
                Image newImage = Image.getImage().getScaledInstance(80, 100, 0);
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
            resultSetPersonnel = data.readPersonnelDataBase();
            resultSetPersonnel.beforeFirst();
            while (resultSetPersonnel.next()) {
                bookData[i][0] = resultSetPersonnel.getString(1);
                bookData[i][1] = resultSetPersonnel.getString(2);
                bookData[i][2] = resultSetPersonnel.getString(4);
                bookData[i][3] = resultSetPersonnel.getString(5);
                bookData[i][4] = resultSetPersonnel.getString(6);
                password[i] = resultSetPersonnel.getString(3);
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
            resultSetHistory = data.readHistoryOfUserDataBase();
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                for (Object[] book : bookData) {
                    if (username.equals(resultSetHistory.getString(2))
                            && (resultSetHistory.getString(4).equals(book[2]))) {
                        historyData[i][0] = i + 1 + "";
                        historyData[i][1] = book[2];
                        historyData[i][2] = book[3];
                        historyData[i][3] = book[4];
                        historyData[i][4] = book[5];
                        historyData[i][5] = resultSetHistory.getString(5);
                        historyData[i][6] = resultSetHistory.getString(6);
                        historyData[i][7] = resultSetHistory.getString(7);
                        historyData[i][8] = resultSetHistory.getString(8);
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
            resultSetHistory = data.readHistoryOfUserDataBase();
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(8).toUpperCase().equals("RETURNED")) {
                    historyData[i][0] = i + 1 + "";
                    historyData[i][1] = resultSetHistory.getString(2);
                    historyData[i][2] = resultSetHistory.getString(3);
                    historyData[i][3] = resultSetHistory.getString(4);
                    historyData[i][4] = resultSetHistory.getString(5);
                    historyData[i][5] = resultSetHistory.getString(6);
                    historyData[i][6] = resultSetHistory.getString(7);
                    historyData[i][7] = resultSetHistory.getString(8);
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
            resultSetHistory = data.readHistoryOfUserDataBase();
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(8).toUpperCase().equals("RENTED")) {
                    historyData[i][0] = i + 1 + "";
                    historyData[i][1] = resultSetHistory.getString(2);
                    historyData[i][2] = resultSetHistory.getString(3);
                    historyData[i][3] = resultSetHistory.getString(4);
                    historyData[i][4] = resultSetHistory.getString(5);
                    historyData[i][5] = resultSetHistory.getString(6);
                    historyData[i][6] = resultSetHistory.getString(7);
                    historyData[i][7] = resultSetHistory.getString(8);
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
            resultSetCustomer = data.readCustomerDataBase();
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                System.out.println("in method");
                data.updateBeforeupdateBookBalance(Integer.toString(BookBalanceData(resultSetCustomer.getString(2))), Integer.parseInt(resultSetCustomer.getString(1)));
                data.updateBeforeupdateOutstandingBalance(Double.toString(OutstandingBalanceData(resultSetCustomer.getString(2))), Integer.parseInt(resultSetCustomer.getString(1)));
            }   System.out.println("update");
            resultSetCustomer = data.readCustomerDataBase();
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                bookData[i][0] = resultSetCustomer.getString(1);
                bookData[i][1] = resultSetCustomer.getString(2);
                password[i] = resultSetCustomer.getString(3);
                bookData[i][2] = resultSetCustomer.getString(4);
                bookData[i][3] = resultSetCustomer.getString(5);
                bookData[i][4] = resultSetCustomer.getString(6);
                bookData[i][5] = resultSetCustomer.getString(7);
                System.out.println(bookData[i][1]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookData;
    }

    public int setBookDataIncrease(String ID) {
        try {
            resultSetBookStore = data.readBookStoreDataBase();
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                if (ID.equals(resultSetBookStore.getString(4))) {
                    System.out.println(Integer.parseInt(resultSetBookStore.getString(5)) + 1);
                    return Integer.parseInt(resultSetBookStore.getString(5)) + 1;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public int setBookDataDecrease(String ID) {
        try {
            resultSetBookStore = data.readBookStoreDataBase();
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                if (ID.equals(resultSetBookStore.getString(4))) {
                    System.out.println(Integer.parseInt(resultSetBookStore.getString(5)) - 1);
                    return Integer.parseInt(resultSetBookStore.getString(5)) - 1;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public void updateBeforeDelete(int ID) throws IOException {
        resultSetBookStore = data.readBookStoreDataBase();
        try {
            System.out.println("come in");
            resultSetBookStore.beforeFirst();
            while (resultSetBookStore.next()) {
                String insertData = "UPDATE BookStore SET ID = ";
                if (resultSetBookStore.getInt(1) >= ID) {
                    insertData += resultSetBookStore.getInt(1) - 1;
                } else {
                    insertData += resultSetBookStore.getInt(1);
                }
                insertData += " WHERE ID =" + resultSetBookStore.getInt(1) + ";";
                System.out.println(insertData);
                data.DataBase().executeUpdate(insertData);
                System.out.println("updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String insertBackSlash(String adDress) {
        String subText, preText;
        for (int i = 0; adDress.length() > i; i++) {
            if ("\\".equals(adDress.charAt(i))) {
                preText = adDress.substring(0, i) + "\\\\\\";
                subText = adDress.substring(i, -1);
                adDress = preText + subText;
                System.out.println(preText + subText);
                i += 4;
            }
        }
        return adDress;
    }

    public void updateBeforeDeletePersonnel(int ID) throws IOException {
        resultSetPersonnel = data.readPersonnelDataBase();
        try {
            System.out.println("come in");
            resultSetPersonnel.beforeFirst();
            while (resultSetPersonnel.next()) {
                String insertData = "UPDATE Personnel SET ID = ";
                if (resultSetPersonnel.getInt(1) >= ID) {
                    insertData += resultSetPersonnel.getInt(1) - 1;
                } else {
                    insertData += resultSetPersonnel.getInt(1);
                }
                insertData += " WHERE ID =" + resultSetPersonnel.getInt(1) + ";";
                System.out.println(insertData);
                data.DataBase().executeUpdate(insertData);
                System.out.println("updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBeforeDeleteCustomer(int ID) throws IOException {
        try {
            System.out.println("come in");
            resultSetCustomer = data.readCustomerDataBase();
            resultSetCustomer.beforeFirst();
            while (resultSetCustomer.next()) {
                String insertData = "UPDATE Customer SET ID = ";
                if (resultSetCustomer.getInt(1) >= ID) {
                    insertData += resultSetCustomer.getInt(1) - 1;
                } else {
                    insertData += resultSetCustomer.getInt(1);
                }
                insertData += " WHERE ID =" + resultSetCustomer.getInt(1) + ";";
                System.out.println(insertData);
                data.DataBase().executeUpdate(insertData);
                System.out.println("updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double OutstandingBalanceData(String username) {
        Double Balance = 0.0;
        try {
            resultSetHistory = data.readHistoryOfUserDataBase();
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(2).equals(username)) {
                    Balance += Double.parseDouble(resultSetHistory.getString(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Balance;
    }

    public int BookBalanceData(String username) {
        int Balance = 0;
        try {
            resultSetHistory = data.readHistoryOfUserDataBase();
            resultSetHistory.beforeFirst();
            while (resultSetHistory.next()) {
                if (resultSetHistory.getString(2).equals(username)) {
                    Balance++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Balance;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public static String[] getImageAddress() {
        return imageAddress;
    }

    public static void setImageAddress(String[] imageAddress) {
        ShareMethod.imageAddress = imageAddress;
    }
    
        public static void setUsername(String username) {
        ShareMethod.username = username;
    }

    public static void setFirstName(String firstName) {
        ShareMethod.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        ShareMethod.lastName = lastName;
    }
}
