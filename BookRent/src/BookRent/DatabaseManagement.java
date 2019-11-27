package BookRent;

import java.sql.*;

public class DatabaseManagement {

    private String url = "jdbc:mysql://ihost.it.kmitl.ac.th:3306/it61070139_dbSTD?seUnicode=true&characterEncoding=UTF-8";
    private String sqlReadCustomer = "SELECT * FROM Customer";
    private String sqlReadPersonnel = "SELECT * FROM Personnel";
    private String sqlReadBookStore = "SELECT * FROM BookStore";
    private String sqlReadHistoryOfUser = "SELECT * FROM HistoryOfUser";
    private Connection connect = null;
    private String insertData = null;
    private Connection connecttion = null;
    private Statement dataStatement;
    private int index = 0;
    private String columnBookStore[] = {"ID", "BookName", "Author", "BookID", "Amount", "Category", "ImageAddress", "Price"};
    private String columnBookStoreNoImage[] = {"ID", "BookName", "Author", "BookID", "Amount", "Category", "Price"};
    private String columnCustomer[] = {"ID", "Username", "Password", "Firstname", "Lastname", "OutstandingBalance", "BookBalance"};
    private String columnPersonnel[] = {"ID", "Username", "Password", "Firstname", "Lastname", "JobPosition"};
    private String columnHistoryOfUser[] = {"ID", "User", "NameOfBook", "BookID", "OutstandingBalance",
        "Time", "ReturnTime", "Status"};

    public Connection ConnectDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, "it61070139_test", "999999999");
            System.out.println("Conneted");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Statement DataBase() {
        try {
            connecttion = ConnectDataBase();
            dataStatement = connecttion.createStatement();
            return dataStatement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet readBookStoreDataBase() {
        try {
            return DataBase().executeQuery(sqlReadBookStore);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet readPersonnelDataBase() {
        try {
            return DataBase().executeQuery(sqlReadPersonnel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet readHistoryOfUserDataBase() {
        try {
            return DataBase().executeQuery(sqlReadHistoryOfUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet readCustomerDataBase() {
        try {
            return DataBase().executeQuery(sqlReadCustomer);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            ConnectDataBase().close();
            DataBase().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBookStoreDataBase(String insertDataToMethod[], int ID) {
        insertData = "INSERT INTO BookStore (" + columnBookStore[0];
        for (String column : columnBookStore) {
            if (column.equals("ID")) {
                continue;
            } else if (column.equals("")) {
                break;
            } else {
                insertData += "," + column;
            }
        }
        insertData += ") VALUES(" + ID;
        for (String data : insertDataToMethod) {
            insertData += ",\"" + data + "\"";
        }
        insertData += ");";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }
    public void insertPersonnelDataBase(String insertDataToMethod[], int ID) {
        insertData = "INSERT INTO Personnel (" + columnPersonnel[0];
        for (String column : columnPersonnel) {
            if (column.equals("ID")) {
                continue;
            } else if (column.equals("")) {
                break;
            } else {
                insertData += "," + column;
            }
        }
        insertData += ") VALUES(" + ID;
        for (String data : insertDataToMethod) {
            insertData += ",\"" + data + "\"";
        }
        insertData += ");";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void insertCustomerDataBase(String insertDataToMethod[], int ID) {
        insertData = "INSERT INTO Customer (" + columnCustomer[0];
        for (String column : columnCustomer) {
            if (column.equals("ID")) {
                continue;
            } else if (column.equals("")) {
                break;
            } else {
                insertData += "," + column;
            }
        }
        insertData += ") VALUES(" + ID;
        for (String data : insertDataToMethod) {
            insertData += ",\"" + data + "\"";
        }
        insertData += ");";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void insertHistoryOfUserDataBase(String insertDataToMethod[], int ID) {
        insertData = "INSERT INTO HistoryOfUser (" + columnHistoryOfUser[0];
        for (String column : columnHistoryOfUser) {
            if (column.equals("ID")) {
                continue;
            } else if (column.equals("")) {
                break;
            } else {
                insertData += "," + column;
            }
        }
        insertData += ") VALUES(" + ID;
        for (String data : insertDataToMethod) {
            insertData += ",\"" + data + "\"";
        }
        insertData += ");";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void updateBookStoreDataBase(String insertDataToMethod[], int ID) {
        index = 1;
        insertData = "UPDATE BookStore SET ";
        for (String data : insertDataToMethod) {
            insertData += columnBookStore[index];
            insertData += " = \"" + data + "\"";
            if (index == 7) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
        }
        insertData += " WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void updateBookStoreDataBaseNoImage(String insertDataToMethod[], int ID) {
        index = 1;
        insertData = "UPDATE BookStore SET ";
        for (String data : insertDataToMethod) {
            insertData += columnBookStoreNoImage[index];
            insertData += " = \"" + data + "\"";
            if (index == 6) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
        }
        insertData += " WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void updateCustomerDataBase(String insertDataToMethod[], int ID) {
        index = 1;
        insertData = "UPDATE Customer SET ";
        for (String data : insertDataToMethod) {
            insertData += columnCustomer[index];
            insertData += " = \"" + data + "\"";
            if (index == 6) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
        }
        insertData += "WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void updatePersonnelDataBase(String insertDataToMethod[], int ID) {
        index = 1;
        insertData = "UPDATE Personnel SET ";
        for (String data : insertDataToMethod) {
            insertData += columnPersonnel[index];
            insertData += " = \"" + data + "\"";
            if (index == 5) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
        }
        insertData += "WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void updateHistoryOfUserDataBase(String insertDataToMethod[], int ID) {
        index = 1;
        insertData = "UPDATE HistoryOfUser SET ";
        for (String data : insertDataToMethod) {
            insertData += columnHistoryOfUser[index];
            insertData += " = \"" + data + "\"";
            if (index == 7) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
        }
        insertData += " WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }
        
    public void deleteRowBookStoreDataBase(int ID) {
        insertData = "DELETE FROM BookStore WHERE ID = " + ID + ";";
        try {
            DataBase().executeUpdate(insertData);
            System.out.println("deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void deleteRowCustomerDataBase(int ID) {
        insertData = "DELETE FROM Customer WHERE ID = " + ID + ";";
        try {
            DataBase().executeUpdate(insertData);
            System.out.println("deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void deleteRowPersonnelDataBase(int ID) {
        insertData = "DELETE FROM Personnel WHERE ID = " + ID + ";";
        try {
            DataBase().executeUpdate(insertData);
            System.out.println("deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void deleteRowHistoryOfUserDataBase(int ID) {
        insertData = "DELETE FROM HistoryOfUser WHERE ID = " + ID + ";";
        try {
            DataBase().executeUpdate(insertData);
            System.out.println("deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void updateAmountBookStoreDataBase(String string, int ID) {
        String insertData;
        insertData = "UPDATE BookStore SET  Amount = \"" + string;
        insertData += "\" WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateBeforeupdateBookBalance(String string, int ID) {
        String insertData;
        insertData = "UPDATE Customer SET  BookBalance = \"" + string;
        insertData += "\" WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBeforeupdateOutstandingBalance(String string, int ID) {
        String insertData;
        insertData = "UPDATE Customer SET  OutstandingBalance = \"" + string;
        insertData += "\" WHERE ID = " + ID + ";";
        try {
            System.out.println(insertData);
            DataBase().executeUpdate(insertData);
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

