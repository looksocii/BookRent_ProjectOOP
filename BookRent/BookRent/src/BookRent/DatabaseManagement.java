package BookRent;

import java.sql.*;

public class DatabaseManagement {
    private String url = "jdbc:mysql://ihost.it.kmitl.ac.th:3306/it61070139_dbSTD";
    private String sqlReadCustomer = "SELECT * FROM Customer";
    private String sqlReadPersonnel = "SELECT * FROM Personnel";
    private String sqlReadBookStore = "SELECT * FROM BookStore";
    private String sqlReadHistoryOfUser = "SELECT * FROM HistoryOfUser";
    private Connection connect = null;
    private String insertData = null;
    private Connection connecttion = null;
    private Statement dataStatement;
    private int index = 0;
    private String columnBookStore[] = { "ID", "BookName", "Author", "BookID", "Amount", "Category", "ImageAddress", "Price"
           , "" };
    private String columnCustomer[] = { "ID", "Username", "Password", "Firstname", "Lastname", "OutstandingBalance", "BookBalance", "" };
    private String columnPersonnel[] = { "ID", "Username", "Password", "Firstname", "Lastname", "JobPosition", "" };
    private String columnHistoryOfUser[] = { "ID", "User", "NameOfBook", "BookID", "OutstandingBalance",
            "Time", "ReturnTime", "Status", "" };

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
            insertData += ",'" + data + "'";
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
    // // edit

    // public void insertDataBaseImage() {
    // insertData = "INSERT INTO image VAlUES(1,
    // \"LOAD_READ('D:\\\\code\\\\OOP_Project\\\\src\\\\loveicon.png')\");";
    // try {
    // System.out.println(insertData);
    // DataBase().executeUpdate(insertData);
    // System.out.println("updated");
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // // edit

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
            insertData += ",'" + data + "'";
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
            insertData += ",'" + data + "'";
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
            insertData += ",'" + data + "'";
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
        index = 0;
        insertData = "UPDATE BookStore set ";
        for (String data : insertDataToMethod) {
            insertData += columnBookStore[index];
            insertData += " = '" + data + "'";
            if (columnBookStore[index].equals("")) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
            insertData += "where ID = " + ID + ";";
            try {
                System.out.println(insertData);
                DataBase().executeUpdate(insertData);
                System.out.println("updated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        closeConnection();
    }

    public void updateCustomerDataBase(String insertDataToMethod[], int ID) {
        index = 0;
        insertData = "update Customer set ";
        for (String data : insertDataToMethod) {
            insertData += columnCustomer[index];
            insertData += " = '" + data + "'";
            if (columnCustomer[index].equals("")) {
                break;
            } else {
                insertData += ", ";
            }
            index++;
            insertData += "where ID = " + ID + ";";
            try {
                System.out.println(insertData);
                DataBase().executeUpdate(insertData);
                System.out.println("updated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        closeConnection();
    }

    public void updatePersonnelDataBase(String insertDataToMethod[], int ID) {
        index = 0;
        insertData = "UPDATE Personnel SET ";
        for (String data : insertDataToMethod) {
            insertData += columnPersonnel[index];
            insertData += " = '" + data + "'";
            if (columnPersonnel[index].equals("")) {
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
        index = 0;
        insertData = "UPDATE HistoryOfUser SET ";
        for (String data : insertDataToMethod) {
            insertData += columnHistoryOfUser[index];
            insertData += " = '" + data + "'";
            if (columnHistoryOfUser[index].equals("")) {
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

    public void deleteRowBookStoreDataBase(int ID) {
        insertData = "ELETE FROM BookStore WHERE ID = " + ID + ";";
        try {
            DataBase().executeUpdate(insertData);
            System.out.println("deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void deleteRowCustomerDataBase(int ID) {
        insertData = "ELETE FROM Customer WHERE ID = " + ID + ";";
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

    // public void main(String[] args) throws SQLException {
    // ResultSet data = readUserDataBase();
    // while (data.next()) {
    // System.out.print(data.getString(1)+" ");
    // System.out.print(data.getString(2)+" ");
    // System.out.print(data.getString(3)+" ");
    // System.out.print(data.getString(4)+" ");
    // System.out.print(data.getString(5)+" ");
    // System.out.print(data.getString(6)+" ");
    // System.out.println(data.getString(7));
    // }
    // }

    // public void aRead() {
    // try {
    // Connection c = ConnectDataBase();
    // ResultSet rs = c.createStatement().executeQuery(sql_read_book);
    // while (rs.next()) {
    // System.out.println("data" + rs.getString(1));
    // System.out.println("data" + rs.getString(2));
    // }
    // rs.close();
    // c.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}