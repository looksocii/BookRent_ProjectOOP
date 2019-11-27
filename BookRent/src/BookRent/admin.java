package BookRent;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class admin extends javax.swing.JFrame {

    JFileChooser fileChooser = new JFileChooser();
    ShareMethod shareMethod = new ShareMethod();
    DatabaseManagement db = new DatabaseManagement();
    DefaultTableModel dm;
    String addressImage1, addressImage2;
    private int num1=0, num2=0, num3=0, num4=0, num5=0, num6=0, num7=0, id=0, notImg1=0, notImg2=0;
    private int var1=0, var2=0, var3=0, var4=0;
    private int staff1=0, staff2=0, staff3=0, staff4=0, staff5=0, staff6=0;
    private int user1=0, user2=0, user3=0, user4=0, user5=0, user6=0;
    String addressImage;
    
    ImageIcon Image = new ImageIcon("C:\\Users\\User\\Desktop\\BookRent_ProjectOOP\\BookRent\\src\\images\\bg.png");
    Image newImage = Image.getImage().getScaledInstance(150, 220, 0);
    Icon bgPNG = new ImageIcon(newImage);
    /**
     * Creates new form admin
     */
    public admin() {
        setUndecorated(true);
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        setShape(new RoundRectangle2D.Double(0,0, 1200,740, 50,50));
        admin_welcome_name.setText("Admin");
        try {
            setTableBook();
            setTableRent();
            setTableReturn();
            setTableUser();
            setTableStaff();
        } catch (IOException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String active = "admin_panel_library";
    public void setTableBook() throws IOException{
        admin_library_order.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getBookStoreData(),
                                new String[] { "No", "Image", "ID", "BookName", "Author", "Category", "Quantity",
                                                "Price" }) {
                        Class[] types = new Class[] { java.lang.String.class, javax.swing.Icon.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class, };
                        boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                admin_library_order.setRowHeight(100);
                    if (admin_library_order.getColumnModel().getColumnCount() > 0) {
                    admin_library_order.getColumnModel().getColumn(0).setMinWidth(30); //no
                    admin_library_order.getColumnModel().getColumn(0).setMaxWidth(40);
                    admin_library_order.getColumnModel().getColumn(1).setMinWidth(90); //image
                    admin_library_order.getColumnModel().getColumn(1).setMaxWidth(110);
                    admin_library_order.getColumnModel().getColumn(2).setMinWidth(80); //id
                    admin_library_order.getColumnModel().getColumn(2).setMaxWidth(100);
                    admin_library_order.getColumnModel().getColumn(3).setMinWidth(200); //bookname
                    admin_library_order.getColumnModel().getColumn(3).setMaxWidth(300);
                    admin_library_order.getColumnModel().getColumn(4).setMinWidth(150); //author
                    admin_library_order.getColumnModel().getColumn(4).setMaxWidth(300);
                    admin_library_order.getColumnModel().getColumn(5).setMinWidth(150); //Category
                    admin_library_order.getColumnModel().getColumn(5).setMaxWidth(300);
                    admin_library_order.getColumnModel().getColumn(6).setMinWidth(40); //Quantity
                    admin_library_order.getColumnModel().getColumn(6).setMaxWidth(60);
                    admin_library_order.getColumnModel().getColumn(7).setMinWidth(40); //Price
                    admin_library_order.getColumnModel().getColumn(7).setMaxWidth(60);
            }
    }
    
    public void setTableRent(){     
        admin_rent_order.setModel(new javax.swing.table.DefaultTableModel(
                                shareMethod.getHistoryDataRentForStaffAdmin(), new String[] { "No", "Username",
                                                "BookName", "ID", "Price", "RentTime", "ReturnTime", "status" }) {
                        Class[] types = new Class[] { java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false,
                                        false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                admin_rent_order.setRowHeight(50);
                    if (admin_rent_order.getColumnModel().getColumnCount() > 0) {
                        admin_rent_order.getColumnModel().getColumn(0).setMinWidth(30); //no
                        admin_rent_order.getColumnModel().getColumn(0).setMaxWidth(40); 
                        admin_rent_order.getColumnModel().getColumn(1).setMinWidth(150); //username
                        admin_rent_order.getColumnModel().getColumn(1).setMaxWidth(300); 
                        admin_rent_order.getColumnModel().getColumn(2).setMinWidth(200); //bookname
                        admin_rent_order.getColumnModel().getColumn(2).setMaxWidth(300);
                        admin_rent_order.getColumnModel().getColumn(3).setMinWidth(80); //id
                        admin_rent_order.getColumnModel().getColumn(3).setMaxWidth(100);
                        admin_rent_order.getColumnModel().getColumn(4).setMinWidth(40); //price
                        admin_rent_order.getColumnModel().getColumn(4).setMaxWidth(60);
                        admin_rent_order.getColumnModel().getColumn(5).setMinWidth(100); //time
                        admin_rent_order.getColumnModel().getColumn(5).setMaxWidth(110);
                        admin_rent_order.getColumnModel().getColumn(6).setMinWidth(100); //RentTime
                        admin_rent_order.getColumnModel().getColumn(6).setMaxWidth(110);
                        admin_rent_order.getColumnModel().getColumn(7).setMinWidth(100); //status
                        admin_rent_order.getColumnModel().getColumn(7).setMaxWidth(110);
            }
    }
    
    public void setTableReturn(){        
        admin_return_order.setModel(new javax.swing.table.DefaultTableModel(
                                shareMethod.getHistoryDataReturnForStaffAdmin(), new String[] { "No", "Username",
                                                "BookName", "ID", "Price", "RentTime", "ReturnTime", "status" }) {
                        Class[] types = new Class[] { java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false,
                                        false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                admin_return_order.setRowHeight(50);
                if (admin_return_order.getColumnModel().getColumnCount() > 0) {
                    admin_return_order.getColumnModel().getColumn(0).setMinWidth(30); //no
                    admin_return_order.getColumnModel().getColumn(0).setMaxWidth(40); 
                    admin_return_order.getColumnModel().getColumn(1).setMinWidth(150); //username
                    admin_return_order.getColumnModel().getColumn(1).setMaxWidth(300); 
                    admin_return_order.getColumnModel().getColumn(2).setMinWidth(200); //bookname
                    admin_return_order.getColumnModel().getColumn(2).setMaxWidth(300);
                    admin_return_order.getColumnModel().getColumn(3).setMinWidth(80); //id
                    admin_return_order.getColumnModel().getColumn(3).setMaxWidth(100);
                    admin_return_order.getColumnModel().getColumn(4).setMinWidth(40); //price
                    admin_return_order.getColumnModel().getColumn(4).setMaxWidth(60);
                    admin_return_order.getColumnModel().getColumn(5).setMinWidth(100); //time
                    admin_return_order.getColumnModel().getColumn(5).setMaxWidth(110);
                    admin_return_order.getColumnModel().getColumn(6).setMinWidth(100); //ReturnTime
                    admin_return_order.getColumnModel().getColumn(6).setMaxWidth(110);
                    admin_return_order.getColumnModel().getColumn(7).setMinWidth(100); //status
                    admin_return_order.getColumnModel().getColumn(7).setMaxWidth(110);
            }
    }
    
    public void setTableUser(){
        admin_user_order.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getCustomerData(),
                                new String[] { "No", "Username", "Firstname", "Lastname", "OutstandingBalance",
                                                "BookBalance" }) {
                        Class[] types = new Class[] { java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                admin_user_order.setRowHeight(50);
                    if (admin_user_order.getColumnModel().getColumnCount() > 0) {
                        admin_user_order.getColumnModel().getColumn(0).setMinWidth(30); //no
                        admin_user_order.getColumnModel().getColumn(0).setMaxWidth(40);
                        admin_user_order.getColumnModel().getColumn(1).setMinWidth(150); //username
                        admin_user_order.getColumnModel().getColumn(1).setMaxWidth(300);
                        admin_user_order.getColumnModel().getColumn(2).setMinWidth(150); //firstname
                        admin_user_order.getColumnModel().getColumn(2).setMaxWidth(300);
                        admin_user_order.getColumnModel().getColumn(3).setMinWidth(150); //lastname
                        admin_user_order.getColumnModel().getColumn(3).setMaxWidth(300);
                        admin_user_order.getColumnModel().getColumn(4).setMinWidth(80); //OutstandingBalance
                        admin_user_order.getColumnModel().getColumn(4).setMaxWidth(90);
                        admin_user_order.getColumnModel().getColumn(5).setMinWidth(80); //BookBalance
                        admin_user_order.getColumnModel().getColumn(5).setMaxWidth(90);
            }  
    }
    
    public void setTableStaff(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getPersonnelData(),
                                new String[] { "No", "Username", "Firstname", "Lastname", "JobPosition" }) {
                        Class[] types = new Class[] { java.lang.String.class, java.lang.String.class,
                                        java.lang.String.class, java.lang.String.class, java.lang.String.class };
                        boolean[] canEdit = new boolean[] { false, false, false, false, false};

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                jTable1.setRowHeight(50);
                    if (jTable1.getColumnModel().getColumnCount() > 0) {
                        jTable1.getColumnModel().getColumn(0).setMinWidth(30); //no
                        jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
                        jTable1.getColumnModel().getColumn(1).setMinWidth(150); //username
                        jTable1.getColumnModel().getColumn(1).setMaxWidth(300);
                        jTable1.getColumnModel().getColumn(2).setMinWidth(150); //firstname
                        jTable1.getColumnModel().getColumn(2).setMaxWidth(300);
                        jTable1.getColumnModel().getColumn(3).setMinWidth(150); //lastname
                        jTable1.getColumnModel().getColumn(3).setMaxWidth(300);
                        jTable1.getColumnModel().getColumn(4).setMinWidth(80); //job
                        jTable1.getColumnModel().getColumn(4).setMaxWidth(90);
                    }
    }
    
    private void filterOrder(String query){
        dm = (DefaultTableModel) admin_library_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        admin_library_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void filterRent(String query){
        dm = (DefaultTableModel) admin_rent_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        admin_rent_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void filterReturn(String query){
        dm = (DefaultTableModel) admin_return_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        admin_return_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void filterUser(String query){
        dm = (DefaultTableModel) admin_user_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        admin_user_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    private void filterStaff(String query){
        dm = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        admin_main = new javax.swing.JPanel();
        admin_sidetab = new javax.swing.JPanel();
        admin_tab_welcome = new javax.swing.JPanel();
        admin_tab_welcomelogo = new javax.swing.JLabel();
        admin_tab_library = new javax.swing.JPanel();
        admin_txt_library = new javax.swing.JLabel();
        admin_tab_librarylogo = new javax.swing.JLabel();
        admin_tab_rent = new javax.swing.JPanel();
        admin_txt_rent = new javax.swing.JLabel();
        admin_tab_rentlogo = new javax.swing.JLabel();
        admin_tab_return = new javax.swing.JPanel();
        admin_txt_return = new javax.swing.JLabel();
        admin_tab_returnlogo = new javax.swing.JLabel();
        admin_tab_user = new javax.swing.JPanel();
        admin_txt_user = new javax.swing.JLabel();
        admin_tab_userlogo = new javax.swing.JLabel();
        admin_tab_staff = new javax.swing.JPanel();
        admin_txt_staff = new javax.swing.JLabel();
        admin_tab_stafflogo = new javax.swing.JLabel();
        admin_tab_empty1 = new javax.swing.JPanel();
        admin_tab_logout = new javax.swing.JPanel();
        admin_txt_logout = new javax.swing.JLabel();
        admin_tab_logoutlogo = new javax.swing.JLabel();
        admin_multipanel = new javax.swing.JPanel();
        admin_panel_welcome = new javax.swing.JPanel();
        admin_welcome_welcome = new javax.swing.JLabel();
        admin_welcome_bookrent = new javax.swing.JLabel();
        admin_welcome_name = new javax.swing.JLabel();
        admin_welcome_logo = new javax.swing.JLabel();
        admin_panel_library = new javax.swing.JPanel();
        admin_library_search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        admin_library_order = new javax.swing.JTable();
        admin_liblrary_add = new javax.swing.JPanel();
        admin_add_id = new javax.swing.JTextField();
        admin_add_name = new javax.swing.JTextField();
        admin_add_type = new javax.swing.JTextField();
        admin_add_quantity = new javax.swing.JTextField();
        admin_add_price = new javax.swing.JTextField();
        admin_add_buttonadd = new javax.swing.JButton();
        admin_add_buttonimage = new javax.swing.JButton();
        admin_add_image = new javax.swing.JLabel();
        admin_add_buttondelete = new javax.swing.JButton();
        admin_add_name1 = new javax.swing.JTextField();
        admin_panel_rent = new javax.swing.JPanel();
        admin_rent_search = new javax.swing.JTextField();
        admin_rent_jscroll = new javax.swing.JScrollPane();
        admin_rent_order = new javax.swing.JTable();
        admin_panel_returnbook = new javax.swing.JPanel();
        admin_rent_search1 = new javax.swing.JTextField();
        admin_return_jscroll = new javax.swing.JScrollPane();
        admin_return_order = new javax.swing.JTable();
        admin_panel_user = new javax.swing.JPanel();
        admin_user_search = new javax.swing.JTextField();
        admin_user_scroll = new javax.swing.JScrollPane();
        admin_user_order = new javax.swing.JTable();
        admin_staff_add1 = new javax.swing.JPanel();
        admin_user_addusername = new javax.swing.JTextField();
        admin_user_addpassword = new javax.swing.JPasswordField();
        admin_user_addfirstname = new javax.swing.JTextField();
        admin_staff_addbuttonadd1 = new javax.swing.JButton();
        admin_staff_buttondelete1 = new javax.swing.JButton();
        admin_user_addlastname = new javax.swing.JTextField();
        admin_panel_staff = new javax.swing.JPanel();
        admin_staff_search = new javax.swing.JTextField();
        admin_staff_add = new javax.swing.JPanel();
        admin_staff_addusername = new javax.swing.JTextField();
        admin_staff_addpassword = new javax.swing.JPasswordField();
        admin_staff_addfirstname = new javax.swing.JTextField();
        admin_staff_addlastname = new javax.swing.JTextField();
        admin_staff_addbuttonadd = new javax.swing.JButton();
        admin_staff_buttondelete = new javax.swing.JButton();
        admin_staff_addlastname1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        menubar = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BookRent");
        setResizable(false);

        admin_main.setBackground(new java.awt.Color(255, 255, 153));
        admin_main.setMaximumSize(new java.awt.Dimension(1200, 700));
        admin_main.setMinimumSize(new java.awt.Dimension(1200, 700));
        admin_main.setPreferredSize(new java.awt.Dimension(1200, 700));
        admin_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_sidetab.setBackground(new java.awt.Color(255, 204, 0));
        admin_sidetab.setPreferredSize(new java.awt.Dimension(200, 700));
        admin_sidetab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_tab_welcome.setBackground(new java.awt.Color(255, 102, 0));
        admin_tab_welcome.setPreferredSize(new java.awt.Dimension(0, 140));
        admin_tab_welcome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_welcomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_welcomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_welcomeMousePressed(evt);
            }
        });
        admin_tab_welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_tab_welcomelogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welcome.png"))); // NOI18N
        admin_tab_welcome.add(admin_tab_welcomelogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 140));

        admin_sidetab.add(admin_tab_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, -1));

        admin_tab_library.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_library.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_libraryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_libraryMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_libraryMousePressed(evt);
            }
        });
        admin_tab_library.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_txt_library.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_library.setText("Library");
        admin_tab_library.add(admin_txt_library, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        admin_tab_librarylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_librarylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/library.png"))); // NOI18N
        admin_tab_library.add(admin_tab_librarylogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        admin_sidetab.add(admin_tab_library, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        admin_tab_rent.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_rent.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_rent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_rentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_rentMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_rentMousePressed(evt);
            }
        });
        admin_tab_rent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_txt_rent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_rent.setText("Rent");
        admin_tab_rent.add(admin_txt_rent, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        admin_tab_rentlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_rentlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rent.png"))); // NOI18N
        admin_tab_rent.add(admin_tab_rentlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        admin_sidetab.add(admin_tab_rent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        admin_tab_return.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_return.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_returnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_returnMousePressed(evt);
            }
        });
        admin_tab_return.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_txt_return.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_return.setText("Return");
        admin_tab_return.add(admin_txt_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        admin_tab_returnlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_returnlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N
        admin_tab_return.add(admin_tab_returnlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        admin_sidetab.add(admin_tab_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        admin_tab_user.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_user.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_userMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_userMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_userMousePressed(evt);
            }
        });
        admin_tab_user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_txt_user.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_user.setText("User");
        admin_tab_user.add(admin_txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        admin_tab_userlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_userlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        admin_tab_user.add(admin_tab_userlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        admin_sidetab.add(admin_tab_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));

        admin_tab_staff.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_staff.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_staffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_staffMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_staffMousePressed(evt);
            }
        });
        admin_tab_staff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_txt_staff.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_staff.setText("Staff");
        admin_tab_staff.add(admin_txt_staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, 55, 80));

        admin_tab_stafflogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_stafflogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staff.png"))); // NOI18N
        admin_tab_staff.add(admin_tab_stafflogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        admin_sidetab.add(admin_tab_staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, -1, -1));

        admin_tab_empty1.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_empty1.setPreferredSize(new java.awt.Dimension(200, 80));

        javax.swing.GroupLayout admin_tab_empty1Layout = new javax.swing.GroupLayout(admin_tab_empty1);
        admin_tab_empty1.setLayout(admin_tab_empty1Layout);
        admin_tab_empty1Layout.setHorizontalGroup(
            admin_tab_empty1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        admin_tab_empty1Layout.setVerticalGroup(
            admin_tab_empty1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_empty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, -1, -1));

        admin_tab_logout.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_tab_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_tab_logoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_tab_logoutMousePressed(evt);
            }
        });
        admin_tab_logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_txt_logout.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_logout.setText("Logout");
        admin_tab_logout.add(admin_txt_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        admin_tab_logoutlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_logoutlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        admin_tab_logout.add(admin_tab_logoutlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        admin_sidetab.add(admin_tab_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 200, -1));

        admin_main.add(admin_sidetab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        admin_multipanel.setBackground(new java.awt.Color(255, 255, 153));
        admin_multipanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        admin_multipanel.setLayout(new java.awt.CardLayout());

        admin_panel_welcome.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_welcome.setMinimumSize(new java.awt.Dimension(1000, 700));
        admin_panel_welcome.setPreferredSize(new java.awt.Dimension(800, 700));
        admin_panel_welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_welcome_welcome.setFont(new java.awt.Font("Banaue", 1, 90)); // NOI18N
        admin_welcome_welcome.setText("WELCOME");
        admin_panel_welcome.add(admin_welcome_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 110, 334, -1));

        admin_welcome_bookrent.setFont(new java.awt.Font("Banaue", 0, 54)); // NOI18N
        admin_welcome_bookrent.setText("BookRent");
        admin_panel_welcome.add(admin_welcome_bookrent, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 267, -1, -1));

        admin_welcome_name.setFont(new java.awt.Font("TH SarabunPSK", 0, 48)); // NOI18N
        admin_welcome_name.setText("xxxxxxxxxxxxxxxxxxxxx");
        admin_panel_welcome.add(admin_welcome_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 405, -1, -1));

        admin_welcome_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        admin_panel_welcome.add(admin_welcome_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 243, -1, -1));

        admin_multipanel.add(admin_panel_welcome, "card6");

        admin_panel_library.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_library.setMinimumSize(new java.awt.Dimension(1000, 700));
        admin_panel_library.setPreferredSize(new java.awt.Dimension(1000, 700));
        admin_panel_library.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_library_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        admin_library_search.setText("Search");
        admin_library_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_library_searchMouseClicked(evt);
            }
        });
        admin_library_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                admin_library_searchKeyReleased(evt);
            }
        });
        admin_panel_library.add(admin_library_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 29, 948, -1));

        admin_library_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "ID", "Name", "Type", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        admin_library_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_library_orderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(admin_library_order);
        if (admin_library_order.getColumnModel().getColumnCount() > 0) {
            admin_library_order.getColumnModel().getColumn(0).setMinWidth(50);
            admin_library_order.getColumnModel().getColumn(0).setMaxWidth(60);
            admin_library_order.getColumnModel().getColumn(1).setMinWidth(180);
            admin_library_order.getColumnModel().getColumn(1).setMaxWidth(190);
            admin_library_order.getColumnModel().getColumn(2).setMinWidth(150);
            admin_library_order.getColumnModel().getColumn(2).setMaxWidth(160);
            admin_library_order.getColumnModel().getColumn(5).setMinWidth(80);
            admin_library_order.getColumnModel().getColumn(5).setMaxWidth(90);
        }

        admin_panel_library.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 84, 948, 477));

        admin_liblrary_add.setBackground(new java.awt.Color(255, 102, 0));
        admin_liblrary_add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_add_id.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_id.setText("Book ID");
        admin_add_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_idMouseClicked(evt);
            }
        });
        admin_add_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_idActionPerformed(evt);
            }
        });
        admin_liblrary_add.add(admin_add_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 25, 80, -1));

        admin_add_name.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_name.setText("Author");
        admin_add_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_nameMouseClicked(evt);
            }
        });
        admin_liblrary_add.add(admin_add_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 25, 128, -1));

        admin_add_type.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_type.setText("Category");
        admin_add_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_typeMouseClicked(evt);
            }
        });
        admin_liblrary_add.add(admin_add_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 25, 112, -1));

        admin_add_quantity.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_quantity.setText("Quantity");
        admin_add_quantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_quantityMouseClicked(evt);
            }
        });
        admin_liblrary_add.add(admin_add_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 25, 70, -1));

        admin_add_price.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_price.setText("Price");
        admin_add_price.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_priceMouseClicked(evt);
            }
        });
        admin_liblrary_add.add(admin_add_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(762, 25, 63, -1));

        admin_add_buttonadd.setBackground(new java.awt.Color(24, 116, 205));
        admin_add_buttonadd.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_buttonadd.setText("Add");
        admin_add_buttonadd.setBorder(null);
        admin_add_buttonadd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admin_add_buttonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_buttonaddActionPerformed(evt);
            }
        });
        admin_liblrary_add.add(admin_add_buttonadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 24, 77, 35));

        admin_add_buttonimage.setBackground(new java.awt.Color(24, 116, 205));
        admin_add_buttonimage.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_buttonimage.setText("Image");
        admin_add_buttonimage.setBorder(null);
        admin_add_buttonimage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admin_add_buttonimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_buttonimageActionPerformed(evt);
            }
        });
        admin_liblrary_add.add(admin_add_buttonimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, 70, 35));

        admin_add_image.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_liblrary_add.add(admin_add_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, 85, 85));

        admin_add_buttondelete.setBackground(new java.awt.Color(24, 116, 205));
        admin_add_buttondelete.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_buttondelete.setText("Delete");
        admin_add_buttondelete.setBorder(null);
        admin_add_buttondelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admin_add_buttondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_buttondeleteActionPerformed(evt);
            }
        });
        admin_liblrary_add.add(admin_add_buttondelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(921, 24, 70, 35));

        admin_add_name1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_name1.setText("Name Book");
        admin_add_name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_name1MouseClicked(evt);
            }
        });
        admin_liblrary_add.add(admin_add_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 25, 130, -1));

        admin_panel_library.add(admin_liblrary_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 1000, -1));

        admin_multipanel.add(admin_panel_library, "card2");

        admin_panel_rent.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_rent.setMinimumSize(new java.awt.Dimension(1000, 700));
        admin_panel_rent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_rent_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        admin_rent_search.setText("Search");
        admin_rent_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_rent_searchMouseClicked(evt);
            }
        });
        admin_rent_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                admin_rent_searchKeyReleased(evt);
            }
        });
        admin_panel_rent.add(admin_rent_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 29, 948, -1));

        admin_rent_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "User", "ID", "Name", "Type", "Quantity", "Price", "Rent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        admin_rent_jscroll.setViewportView(admin_rent_order);
        if (admin_rent_order.getColumnModel().getColumnCount() > 0) {
            admin_rent_order.getColumnModel().getColumn(0).setMinWidth(50);
            admin_rent_order.getColumnModel().getColumn(0).setMaxWidth(60);
            admin_rent_order.getColumnModel().getColumn(5).setHeaderValue("Type");
            admin_rent_order.getColumnModel().getColumn(6).setMinWidth(60);
            admin_rent_order.getColumnModel().getColumn(6).setMaxWidth(70);
            admin_rent_order.getColumnModel().getColumn(6).setHeaderValue("Quantity");
            admin_rent_order.getColumnModel().getColumn(7).setMinWidth(90);
            admin_rent_order.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        admin_panel_rent.add(admin_rent_jscroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 84, 948, 591));

        admin_multipanel.add(admin_panel_rent, "card3");

        admin_panel_returnbook.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_returnbook.setMinimumSize(new java.awt.Dimension(1000, 700));
        admin_panel_returnbook.setPreferredSize(new java.awt.Dimension(800, 700));
        admin_panel_returnbook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_rent_search1.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        admin_rent_search1.setText("Search");
        admin_rent_search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_rent_search1MouseClicked(evt);
            }
        });
        admin_rent_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                admin_rent_search1KeyReleased(evt);
            }
        });
        admin_panel_returnbook.add(admin_rent_search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 29, 948, -1));

        admin_return_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "User", "ID", "Name", "Type", "Quantity", "Price", "Return"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        admin_return_jscroll.setViewportView(admin_return_order);
        if (admin_return_order.getColumnModel().getColumnCount() > 0) {
            admin_return_order.getColumnModel().getColumn(0).setMinWidth(50);
            admin_return_order.getColumnModel().getColumn(0).setMaxWidth(60);
            admin_return_order.getColumnModel().getColumn(5).setHeaderValue("Type");
            admin_return_order.getColumnModel().getColumn(6).setMinWidth(60);
            admin_return_order.getColumnModel().getColumn(6).setMaxWidth(70);
            admin_return_order.getColumnModel().getColumn(6).setHeaderValue("Quantity");
            admin_return_order.getColumnModel().getColumn(7).setMinWidth(90);
            admin_return_order.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        admin_panel_returnbook.add(admin_return_jscroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 84, 948, 591));

        admin_multipanel.add(admin_panel_returnbook, "card4");

        admin_panel_user.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_user.setMinimumSize(new java.awt.Dimension(1000, 700));
        admin_panel_user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_user_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        admin_user_search.setText("Search");
        admin_user_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_searchMouseClicked(evt);
            }
        });
        admin_user_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                admin_user_searchKeyReleased(evt);
            }
        });
        admin_panel_user.add(admin_user_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 29, 948, -1));

        admin_user_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Username", "Firstname", "Lastname", "History"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        admin_user_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_orderMouseClicked(evt);
            }
        });
        admin_user_scroll.setViewportView(admin_user_order);
        if (admin_user_order.getColumnModel().getColumnCount() > 0) {
            admin_user_order.getColumnModel().getColumn(0).setMinWidth(50);
            admin_user_order.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        admin_panel_user.add(admin_user_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 84, 948, 471));

        admin_staff_add1.setBackground(new java.awt.Color(255, 102, 0));
        admin_staff_add1.setPreferredSize(new java.awt.Dimension(1000, 85));
        admin_staff_add1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_user_addusername.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_user_addusername.setText("Username");
        admin_user_addusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addusernameMouseClicked(evt);
            }
        });
        admin_staff_add1.add(admin_user_addusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 31, 178, -1));

        admin_user_addpassword.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        admin_user_addpassword.setText("xxxxxxxxxxx");
        admin_user_addpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addpasswordMouseClicked(evt);
            }
        });
        admin_staff_add1.add(admin_user_addpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 30, 216, -1));

        admin_user_addfirstname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_user_addfirstname.setText("Firstname");
        admin_user_addfirstname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addfirstnameMouseClicked(evt);
            }
        });
        admin_staff_add1.add(admin_user_addfirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 31, 183, -1));

        admin_staff_addbuttonadd1.setBackground(new java.awt.Color(24, 116, 205));
        admin_staff_addbuttonadd1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addbuttonadd1.setText("Add");
        admin_staff_addbuttonadd1.setBorder(null);
        admin_staff_addbuttonadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_addbuttonadd1ActionPerformed(evt);
            }
        });
        admin_staff_add1.add(admin_staff_addbuttonadd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 30, 80, 35));

        admin_staff_buttondelete1.setBackground(new java.awt.Color(24, 116, 205));
        admin_staff_buttondelete1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_buttondelete1.setText("Delete");
        admin_staff_buttondelete1.setBorder(null);
        admin_staff_buttondelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_buttondelete1ActionPerformed(evt);
            }
        });
        admin_staff_add1.add(admin_staff_buttondelete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(898, 30, 80, 35));

        admin_user_addlastname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_user_addlastname.setText("Lastname");
        admin_user_addlastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addlastnameMouseClicked(evt);
            }
        });
        admin_staff_add1.add(admin_user_addlastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 31, 179, -1));

        admin_panel_user.add(admin_staff_add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 582, 1010, -1));

        admin_multipanel.add(admin_panel_user, "card5");

        admin_panel_staff.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_staff.setMinimumSize(new java.awt.Dimension(1000, 700));
        admin_panel_staff.setPreferredSize(new java.awt.Dimension(1000, 700));
        admin_panel_staff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_staff_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        admin_staff_search.setText("Search");
        admin_staff_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_searchMouseClicked(evt);
            }
        });
        admin_staff_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                admin_staff_searchKeyReleased(evt);
            }
        });
        admin_panel_staff.add(admin_staff_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 29, 948, -1));

        admin_staff_add.setBackground(new java.awt.Color(255, 102, 0));
        admin_staff_add.setPreferredSize(new java.awt.Dimension(1000, 85));
        admin_staff_add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin_staff_addusername.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addusername.setText("Username");
        admin_staff_addusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addusernameMouseClicked(evt);
            }
        });
        admin_staff_add.add(admin_staff_addusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 31, 150, -1));

        admin_staff_addpassword.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        admin_staff_addpassword.setText("Password00");
        admin_staff_addpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addpasswordMouseClicked(evt);
            }
        });
        admin_staff_addpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_addpasswordActionPerformed(evt);
            }
        });
        admin_staff_add.add(admin_staff_addpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 30, 158, -1));

        admin_staff_addfirstname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addfirstname.setText("Firstname");
        admin_staff_addfirstname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addfirstnameMouseClicked(evt);
            }
        });
        admin_staff_add.add(admin_staff_addfirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 31, 150, -1));

        admin_staff_addlastname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addlastname.setText("JobPosition");
        admin_staff_addlastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addlastnameMouseClicked(evt);
            }
        });
        admin_staff_add.add(admin_staff_addlastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 31, 150, -1));

        admin_staff_addbuttonadd.setBackground(new java.awt.Color(24, 116, 205));
        admin_staff_addbuttonadd.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addbuttonadd.setText("Add");
        admin_staff_addbuttonadd.setBorder(null);
        admin_staff_addbuttonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_addbuttonaddActionPerformed(evt);
            }
        });
        admin_staff_add.add(admin_staff_addbuttonadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(814, 30, 80, 35));

        admin_staff_buttondelete.setBackground(new java.awt.Color(24, 116, 205));
        admin_staff_buttondelete.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_buttondelete.setText("Delete");
        admin_staff_buttondelete.setBorder(null);
        admin_staff_buttondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_buttondeleteActionPerformed(evt);
            }
        });
        admin_staff_add.add(admin_staff_buttondelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(901, 30, 80, 35));

        admin_staff_addlastname1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addlastname1.setText("Lastname");
        admin_staff_addlastname1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addlastname1MouseClicked(evt);
            }
        });
        admin_staff_add.add(admin_staff_addlastname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 31, 150, -1));

        admin_panel_staff.add(admin_staff_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 577, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        admin_panel_staff.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 89, 948, 458));

        admin_multipanel.add(admin_panel_staff, "card7");

        admin_main.add(admin_multipanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        menubar.setBackground(new java.awt.Color(0, 0, 153));
        menubar.setPreferredSize(new java.awt.Dimension(1200, 40));
        menubar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                menubarMouseDragged(evt);
            }
        });
        menubar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menubarMousePressed(evt);
            }
        });
        menubar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closebuttonclose(evt);
            }
        });
        menubar.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, -1, 38));

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        menubar.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1117, 0, -1, 38));

        title.setFont(new java.awt.Font("Coolsville", 0, 16)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("BOOKRENT");
        menubar.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 0, -1, 40));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoheader.png"))); // NOI18N
        menubar.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        admin_main.add(menubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(admin_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_main, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void admin_mouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_mouseclicked
        if (evt.getSource()== admin_tab_welcome){
            admin_panel_welcome.setVisible(true);
            admin_panel_library.setVisible(false);
            admin_panel_rent.setVisible(false);
            admin_panel_returnbook.setVisible(false);
            admin_panel_user.setVisible(false);
            admin_panel_staff.setVisible(false);
        }
        if (evt.getSource()== admin_tab_library){
            admin_panel_welcome.setVisible(false);
            admin_panel_library.setVisible(true);
            admin_panel_rent.setVisible(false);
            admin_panel_returnbook.setVisible(false);
            admin_panel_user.setVisible(false);
            admin_panel_staff.setVisible(false);
        }
        if (evt.getSource()== admin_tab_rent){
            admin_panel_welcome.setVisible(false);
            admin_panel_library.setVisible(false);
            admin_panel_rent.setVisible(true);
            admin_panel_returnbook.setVisible(false);
            admin_panel_user.setVisible(false);
            admin_panel_staff.setVisible(false);
        }
        if (evt.getSource()== admin_tab_return){
            admin_panel_welcome.setVisible(false);
            admin_panel_library.setVisible(false);
            admin_panel_rent.setVisible(false);
            admin_panel_returnbook.setVisible(true);
            admin_panel_user.setVisible(false);
            admin_panel_staff.setVisible(false);
        }
        if (evt.getSource()== admin_tab_user){
            admin_panel_welcome.setVisible(false);
            admin_panel_library.setVisible(false);
            admin_panel_rent.setVisible(false);
            admin_panel_returnbook.setVisible(false);
            admin_panel_user.setVisible(true);
            admin_panel_staff.setVisible(false);
        }
        if (evt.getSource()== admin_tab_staff){
            admin_panel_welcome.setVisible(false);
            admin_panel_library.setVisible(false);
            admin_panel_rent.setVisible(false);
            admin_panel_returnbook.setVisible(false);
            admin_panel_user.setVisible(false);
            admin_panel_staff.setVisible(true);
        }
        if (evt.getSource()== admin_tab_logout){
            new login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_admin_mouseclicked

    private void admin_add_buttonimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_add_buttonimageActionPerformed
        if (num7==0){
            int returnVal = fileChooser.showOpenDialog(this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            // What to do with the file, e.g. display it in a TextArea
                            addressImage1 = file.getAbsolutePath();
                try {
                    this.addressImage1 = shareMethod.copiedImage(addressImage1);
                } catch (Exception ex) {
                    Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
                }
                            System.out.println(file.getAbsolutePath());
                    } else {
                            System.out.println("File access cancelled by user.");
                    }
                    ImageIcon Image = new ImageIcon(addressImage1);
                    Image newImage = Image.getImage().getScaledInstance(80, 90, 0);
                    Icon addIcon = new ImageIcon(newImage);
                    admin_add_image.setIcon(addIcon);
        }else{
            int returnVal = fileChooser.showOpenDialog(this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            // What to do with the file, e.g. display it in a TextArea
                            addressImage2 = file.getAbsolutePath();
                try {
                    this.addressImage2 = shareMethod.copiedImage(addressImage2);
                } catch (Exception ex) {
                    Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
                }
                            System.out.println(file.getAbsolutePath());
                    } else {
                            System.out.println("File access cancelled by user.");
                    }
                    ImageIcon Image = new ImageIcon(addressImage2);
                    Image newImage = Image.getImage().getScaledInstance(80, 90, 0);
                    Icon addIcon = new ImageIcon(newImage);
                    admin_add_image.setIcon(addIcon);
        }
        notImg2 = 1;
    }//GEN-LAST:event_admin_add_buttonimageActionPerformed

    private void admin_library_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_library_searchMouseClicked
        // TODO add your handling code here:
        admin_library_search.setText("");
    }//GEN-LAST:event_admin_library_searchMouseClicked

    private void admin_library_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_library_searchKeyReleased
        // TODO add your handling code here:
        String str = admin_library_search.getText();
        filterOrder(str.toUpperCase());
    }//GEN-LAST:event_admin_library_searchKeyReleased

    private void admin_rent_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_rent_searchMouseClicked
        // TODO add your handling code here:
        admin_rent_search.setText("");
    }//GEN-LAST:event_admin_rent_searchMouseClicked

    private void admin_rent_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_rent_searchKeyReleased
        // TODO add your handling code here:
        String str = admin_rent_search.getText();
        filterRent(str.toUpperCase());
    }//GEN-LAST:event_admin_rent_searchKeyReleased

    private void admin_rent_search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_rent_search1MouseClicked
        // TODO add your handling code here:
        admin_rent_search1.setText("");
    }//GEN-LAST:event_admin_rent_search1MouseClicked

    private void admin_rent_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_rent_search1KeyReleased
        // TODO add your handling code here:
        String str = admin_rent_search1.getText();
        filterReturn(str.toUpperCase());
    }//GEN-LAST:event_admin_rent_search1KeyReleased

    private void admin_user_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_user_searchMouseClicked
        // TODO add your handling code here:
        admin_user_search.setText("");
    }//GEN-LAST:event_admin_user_searchMouseClicked

    private void admin_user_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_user_searchKeyReleased
        // TODO add your handling code here:
        String str = admin_user_search.getText();
        filterUser(str);
    }//GEN-LAST:event_admin_user_searchKeyReleased

    private void admin_staff_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_staff_searchMouseClicked
        // TODO add your handling code here:
        admin_staff_search.setText("");
    }//GEN-LAST:event_admin_staff_searchMouseClicked

    private void admin_staff_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_staff_searchKeyReleased
        // TODO add your handling code here:
        String str = admin_staff_search.getText();
        filterStaff(str);
    }//GEN-LAST:event_admin_staff_searchKeyReleased

    private void admin_add_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_add_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_add_idActionPerformed

    private void admin_add_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_add_idMouseClicked
        // TODO add your handling code here:
        if(num1 == 0){
            admin_add_id.setText("");
            num1 = 1;
        }
    }//GEN-LAST:event_admin_add_idMouseClicked

    private void admin_add_name1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_add_name1MouseClicked
        // TODO add your handling code here:
        if(num2 == 0){
            admin_add_name1.setText("");
            num2 = 1;
        }
    }//GEN-LAST:event_admin_add_name1MouseClicked

    private void admin_add_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_add_nameMouseClicked
        // TODO add your handling code here:
        if(num3 == 0){
            admin_add_name.setText("");
            num3 = 1;
        }
    }//GEN-LAST:event_admin_add_nameMouseClicked

    private void admin_add_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_add_typeMouseClicked
        // TODO add your handling code here:
        if(num4 == 0){
            admin_add_type.setText("");
            num4 = 1;
        }
    }//GEN-LAST:event_admin_add_typeMouseClicked

    private void admin_add_quantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_add_quantityMouseClicked
        // TODO add your handling code here:
        if(num5 == 0){
            admin_add_quantity.setText("");
            num5 = 1;
        }
    }//GEN-LAST:event_admin_add_quantityMouseClicked

    private void admin_add_priceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_add_priceMouseClicked
        // TODO add your handling code here:
        if(num6 == 0){
            admin_add_price.setText("");
            num6 = 1;
        }
    }//GEN-LAST:event_admin_add_priceMouseClicked

    private void admin_add_buttonaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_add_buttonaddActionPerformed

        String[] arrayOfBookStore1 = {admin_add_name1.getText(), admin_add_name.getText(), admin_add_id.getText(),
                                admin_add_quantity.getText(), admin_add_type.getText(), addressImage1,
                                admin_add_price.getText()};
        String[] arrayOfBookStore2 = {admin_add_name1.getText(), admin_add_name.getText(), admin_add_id.getText(),
                                admin_add_quantity.getText(), admin_add_type.getText(), addressImage2,
                                admin_add_price.getText()};
        int var = 0;
        if (notImg1 == 1 && notImg2 == 0){
            var = 1;
        }
        if (num7==0){
            db.insertBookStoreDataBase(arrayOfBookStore1, shareMethod.findAmountOfBook() + 1);
            JOptionPane.showMessageDialog(null, "Add Completed.");
            try {
                setTableBook();
            } catch (IOException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            admin_add_id.setText("Book ID");
            admin_add_name1.setText("Name Book");
            admin_add_name.setText("Author");
            admin_add_type.setText("Category");
            admin_add_quantity.setText("Quantity");
            admin_add_price.setText("Price");
            admin_add_buttonadd.setText("Add");
            admin_add_image.setIcon(bgPNG);
            num1=0; num2=0; num3=0; num4=0; num5=0; num6=0; num7=0;
        }else{
            if (var == 1){
                String[] arrayOfBookStore3 = {admin_add_name1.getText(), admin_add_name.getText(), admin_add_id.getText(),
                                admin_add_quantity.getText(), admin_add_type.getText(), admin_add_price.getText()};
                db.updateBookStoreDataBaseNoImage(arrayOfBookStore3, id);
            }else{
                db.updateBookStoreDataBase(arrayOfBookStore2, id);
            }
            JOptionPane.showMessageDialog(null, "Update Completed.");
            try {
                setTableBook();
            } catch (IOException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                setTableBook();
            } catch (IOException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
            admin_add_id.setText("Book ID");
            admin_add_name1.setText("Name Book");
            admin_add_name.setText("Author");
            admin_add_type.setText("Category");
            admin_add_quantity.setText("Quantity");
            admin_add_price.setText("Price");
            admin_add_buttonadd.setText("Add");
            admin_add_image.setIcon(bgPNG);
            num1=0; num2=0; num3=0; num4=0; num5=0; num6=0; num7=0;
        }
        notImg1=0;
        notImg2=0;
    }//GEN-LAST:event_admin_add_buttonaddActionPerformed

    private void admin_library_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_library_orderMouseClicked
        Object[][] str = shareMethod.getBookStoreData();
        int row = admin_library_order.getSelectedRow();
        String selectId = admin_library_order.getValueAt(row, 0).toString();
        if (evt.getClickCount() == 2){
            num1=1; num2=1; num3=1; num4=1; num5=1; num6=1; num7=1;
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    id = Integer.parseInt((String) objects[0]);
                    admin_add_image.setIcon((Icon) objects[1]);
                    addressImage2 = shareMethod.getImageAddress()[row];
                    admin_add_id.setText((String) objects[2]);
                    admin_add_name1.setText((String) objects[3]);
                    admin_add_name.setText((String) objects[4]);
                    admin_add_type.setText((String) objects[5]);
                    admin_add_quantity.setText((String) objects[6]);
                    admin_add_price.setText((String) objects[7]);
                    admin_add_buttonadd.setText("Update");
                }
            }
        }else{
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    id = Integer.parseInt((String) objects[0]);
                    System.out.println(id);
                }
            }
            admin_add_id.setText("Book ID");
            admin_add_name1.setText("Name Book");
            admin_add_name.setText("Author");
            admin_add_type.setText("Category");
            admin_add_quantity.setText("Quantity");
            admin_add_price.setText("Price");
            admin_add_buttonadd.setText("Add");
            admin_add_image.setIcon(bgPNG);
            num1=0; num2=0; num3=0; num4=0; num5=0; num6=0; num7=0;
        }
        notImg1 = 1;
    }//GEN-LAST:event_admin_library_orderMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        Object[][] str = shareMethod.getPersonnelData();
        int row = jTable1.getSelectedRow();
        String selectId = jTable1.getValueAt(row, 0).toString();
        if (evt.getClickCount() == 2){
            staff1=1; staff2=1; staff3=1; staff4=1; staff5=1; staff6=1;
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    id = Integer.parseInt((String) objects[0]);
                    admin_staff_addusername.setText((String) objects[1]);
                    admin_staff_addfirstname.setText((String) objects[2]);
                    admin_staff_addlastname1.setText((String) objects[3]);
                    admin_staff_addlastname.setText((String) objects[4]);
                    admin_staff_addpassword.setText(shareMethod.getPassword()[id-1]);
                    admin_staff_addbuttonadd.setText("Update");
                }
            }
        }else{
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    id = Integer.parseInt((String) objects[0]);
                    System.out.println(id);
                }
            }
            admin_staff_addusername.setText("Username");
            admin_staff_addfirstname.setText("Firstname");
            admin_staff_addlastname1.setText("Lastname");
            admin_staff_addpassword.setText("xxxxxxxxxx");
            admin_staff_addlastname.setText("JobPosition");
            admin_staff_addbuttonadd.setText("Add");
            staff1=0; staff2=0; staff3=0; staff4=0; staff5=0; staff6=0;
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void admin_staff_addusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_staff_addusernameMouseClicked
        // TODO add your handling code here:
        if(staff1 == 0){
            admin_staff_addusername.setText("");
            staff1 = 1;
        }
    }//GEN-LAST:event_admin_staff_addusernameMouseClicked

    private void admin_staff_addpasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_staff_addpasswordMouseClicked
        // TODO add your handling code here:
        if(staff2 == 0){
            admin_staff_addpassword.setText("");
            staff2 = 1;
        }
    }//GEN-LAST:event_admin_staff_addpasswordMouseClicked

    private void admin_staff_addfirstnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_staff_addfirstnameMouseClicked
        // TODO add your handling code here:
        if(staff3 == 0){
            admin_staff_addfirstname.setText("");
            staff3 = 1;
        }
    }//GEN-LAST:event_admin_staff_addfirstnameMouseClicked

    private void admin_staff_addlastname1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_staff_addlastname1MouseClicked
        // TODO add your handling code here:
        if(staff4 == 0){
            admin_staff_addlastname1.setText("");
            staff4 = 1;
        }
    }//GEN-LAST:event_admin_staff_addlastname1MouseClicked

    private void admin_staff_addlastnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_staff_addlastnameMouseClicked
        // TODO add your handling code here:
        if(staff5 == 0){
            admin_staff_addlastname.setText("");
            staff5 = 1;
        }
    }//GEN-LAST:event_admin_staff_addlastnameMouseClicked

    private void admin_staff_addbuttonaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_staff_addbuttonaddActionPerformed
        // TODO add your handling code here:
        String[] arrayOfStaff = {admin_staff_addusername.getText(), admin_staff_addpassword.getText(), admin_staff_addfirstname.getText(),
                                admin_staff_addlastname1.getText(), admin_staff_addlastname.getText()};
        if (staff6==0){
            db.insertPersonnelDataBase(arrayOfStaff, shareMethod.findAmountOfPersonnel()+1);
            JOptionPane.showMessageDialog(null, "Add Completed.");
            setTableStaff();
            admin_staff_addusername.setText("Username");
            admin_staff_addfirstname.setText("Firstname");
            admin_staff_addlastname1.setText("Lastname");
            admin_staff_addpassword.setText("xxxxxxxxxx");
            admin_staff_addlastname.setText("JobPosition");
            admin_staff_addbuttonadd.setText("Add");
            staff1=0; staff2=0; staff3=0; staff4=0; staff5=0; staff6=0;
        }else{
            db.updatePersonnelDataBase(arrayOfStaff, id);
            JOptionPane.showMessageDialog(null, "Update Completed.");
            setTableStaff();
            admin_staff_addusername.setText("Username");
            admin_staff_addfirstname.setText("Firstname");
            admin_staff_addlastname1.setText("Lastname");
            admin_staff_addpassword.setText("xxxxxxxxxx");
            admin_staff_addlastname.setText("JobPosition");
            admin_staff_addbuttonadd.setText("Add");
            staff1=0; staff2=0; staff3=0; staff4=0; staff5=0; staff6=0;
        }
    }//GEN-LAST:event_admin_staff_addbuttonaddActionPerformed

    private void admin_staff_buttondeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_staff_buttondeleteActionPerformed
        if (id != 0){
            db.deleteRowPersonnelDataBase(id);
            try {
                shareMethod.updateBeforeDeletePersonnel(id);
            } catch (IOException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Delete Done.");
            setTableStaff();
            admin_staff_addusername.setText("Username");
            admin_staff_addfirstname.setText("Firstname");
            admin_staff_addlastname1.setText("Lastname");
            admin_staff_addpassword.setText("xxxxxxxxx");
            admin_staff_addlastname.setText("JobPosition");
            admin_staff_addbuttonadd.setText("Add");
            staff1=0; staff2=0; staff3=0; staff4=0; staff5=0; staff6=0;
        }else{
            JOptionPane.showMessageDialog(null, "You have not selected.");
        }
    }//GEN-LAST:event_admin_staff_buttondeleteActionPerformed

    private void admin_user_addusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_user_addusernameMouseClicked
        // TODO add your handling code here:
        if(user1 == 0){
            admin_user_addusername.setText("");
            user1 = 1;
        }
    }//GEN-LAST:event_admin_user_addusernameMouseClicked

    private void admin_user_addpasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_user_addpasswordMouseClicked
        // TODO add your handling code here:
        if(user2 == 0){
            admin_user_addpassword.setText("");
            user2 = 1;
        }
    }//GEN-LAST:event_admin_user_addpasswordMouseClicked

    private void admin_user_addfirstnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_user_addfirstnameMouseClicked
        // TODO add your handling code here:
        if(user3 == 0){
            admin_user_addfirstname.setText("");
            user3 = 1;
        }
    }//GEN-LAST:event_admin_user_addfirstnameMouseClicked

    private void admin_staff_addbuttonadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_staff_addbuttonadd1ActionPerformed
        // TODO add your handling code here:
        String[] arrayOfStaff = {admin_user_addusername.getText(), admin_user_addpassword.getText(), admin_user_addfirstname.getText(),
                                admin_user_addlastname.getText(), "0", "0"};
        if (user6==0){
            db.insertCustomerDataBase(arrayOfStaff, shareMethod.findAmountOfCustomer()+1);
            JOptionPane.showMessageDialog(null, "Add Completed.");
            setTableUser();
            admin_user_addusername.setText("Username");
            admin_user_addfirstname.setText("Firstname");
            admin_user_addlastname.setText("Lastname");
            admin_user_addpassword.setText("xxxxxxxxxx");
            admin_staff_addbuttonadd1.setText("Add");
            user1=0; user2=0; user3=0; user4=0; user5=0; user6=0;
        }else{
            db.updateCustomerDataBase(arrayOfStaff, id);
            JOptionPane.showMessageDialog(null, "Update Completed.");
            setTableUser();
            admin_user_addusername.setText("Username");
            admin_user_addfirstname.setText("Firstname");
            admin_user_addlastname.setText("Lastname");
            admin_user_addpassword.setText("xxxxxxxxxx");
            admin_staff_addbuttonadd1.setText("Add");
            user1=0; user2=0; user3=0; user4=0; user5=0; user6=0;
        }
    }//GEN-LAST:event_admin_staff_addbuttonadd1ActionPerformed

    private void admin_staff_buttondelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_staff_buttondelete1ActionPerformed
        // TODO add your handling code here:
        if (id != 0){
            db.deleteRowCustomerDataBase(id);
            try {
                shareMethod.updateBeforeDeleteCustomer(id);
            } catch (IOException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Delete Done.");
            setTableUser();
            admin_user_addusername.setText("Username");
            admin_user_addfirstname.setText("Firstname");
            admin_user_addlastname.setText("Lastname");
            admin_user_addpassword.setText("xxxxxxxxxx");
            admin_staff_addbuttonadd1.setText("Add");
            user1=0; user2=0; user3=0; user4=0; user5=0; user6=0;
        }else{
            JOptionPane.showMessageDialog(null, "You have not selected.");
        }
    }//GEN-LAST:event_admin_staff_buttondelete1ActionPerformed

    private void admin_user_addlastnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_user_addlastnameMouseClicked
        // TODO add your handling code here:
        if(user4 == 0){
            admin_user_addlastname.setText("");
            user4 = 1;
        }
    }//GEN-LAST:event_admin_user_addlastnameMouseClicked

    private void admin_staff_addpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_staff_addpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_staff_addpasswordActionPerformed

    private void admin_user_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_user_orderMouseClicked
        // TODO add your handling code here:
        Object[][] str = shareMethod.getCustomerData();
        int row = admin_user_order.getSelectedRow();
        String selectId = admin_user_order.getValueAt(row, 0).toString();
        if (evt.getClickCount() == 2){
            user1=1; user2=1; user3=1; user4=1; user5=1; user6=1;
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    id = Integer.parseInt((String) objects[0]);
                    admin_user_addusername.setText((String) objects[1]);
                    admin_user_addpassword.setText(shareMethod.getPassword()[id-1]);
                    admin_user_addfirstname.setText((String) objects[2]);
                    admin_user_addlastname.setText((String) objects[3]);
                    admin_staff_addbuttonadd1.setText("Update");
                }
            }
        }else{
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    id = Integer.parseInt((String) objects[0]);
                    System.out.println(id);
                }
            }
            admin_user_addusername.setText("Username");
            admin_user_addpassword.setText("xxxxxxxxxxx");
            admin_user_addfirstname.setText("Firstname");
            admin_user_addlastname.setText("Lastname");
            admin_staff_addbuttonadd1.setText("Add");
            user1=0; user2=0; user3=0; user4=0; user5=0; user6=0;
        }
    }//GEN-LAST:event_admin_user_orderMouseClicked

    private void admin_add_buttondeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_add_buttondeleteActionPerformed
        // TODO add your handling code here:
        if (id != 0){
            db.deleteRowBookStoreDataBase(id);
            try {
                shareMethod.updateBeforeDelete(id);
            } catch (IOException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Delete Done.");
            try {
                setTableBook();
            } catch (IOException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
            admin_add_id.setText("Book ID");
            admin_add_name1.setText("Name Book");
            admin_add_name.setText("Author");
            admin_add_type.setText("Category");
            admin_add_quantity.setText("Quantity");
            admin_add_price.setText("Price");
            admin_add_buttonadd.setText("Add");
            admin_add_image.setIcon(bgPNG);
            num1=0; num2=0; num3=0; num4=0; num5=0; num6=0; num7=0; id=0;
        }else{
            JOptionPane.showMessageDialog(null, "You have not selected.");
        }
        notImg1=0;
        notImg2=0;
    }//GEN-LAST:event_admin_add_buttondeleteActionPerformed

    private void closebuttonclose(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closebuttonclose
        dispose();
    }//GEN-LAST:event_closebuttonclose

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        // TODO add your handling code here:
        //this.setState(menubar.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked
    int x, y;
    private void menubarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubarMouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_menubarMouseDragged

    private void menubarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubarMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_menubarMousePressed

    private void admin_tab_welcomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_welcomeMouseEntered
        // TODO add your handling code here:
        admin_tab_welcome.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_admin_tab_welcomeMouseEntered

    private void admin_tab_welcomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_welcomeMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_welcome")) ){
                admin_tab_welcome.setBackground(new Color(255,102,0));
            }
    }//GEN-LAST:event_admin_tab_welcomeMouseExited

    private void admin_tab_libraryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_libraryMouseEntered
        // TODO add your handling code here:
        admin_tab_library.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_admin_tab_libraryMouseEntered

    private void admin_tab_libraryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_libraryMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_library")) ){
                admin_tab_library.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_admin_tab_libraryMouseExited

    private void admin_tab_rentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_rentMouseEntered
        // TODO add your handling code here:
        admin_tab_rent.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_admin_tab_rentMouseEntered

    private void admin_tab_rentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_rentMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_rent")) ){
                admin_tab_rent.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_admin_tab_rentMouseExited

    private void admin_tab_returnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_returnMouseEntered
        // TODO add your handling code here:
        admin_tab_return.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_admin_tab_returnMouseEntered

    private void admin_tab_returnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_returnMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_return")) ){
                admin_tab_return.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_admin_tab_returnMouseExited

    private void admin_tab_userMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_userMouseEntered
        // TODO add your handling code here:
        admin_tab_user.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_admin_tab_userMouseEntered

    private void admin_tab_userMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_userMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_user")) ){
                admin_tab_user.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_admin_tab_userMouseExited

    private void admin_tab_staffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_staffMouseEntered
        // TODO add your handling code here:
        admin_tab_staff.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_admin_tab_staffMouseEntered

    private void admin_tab_staffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_staffMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_staff")) ){
                admin_tab_staff.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_admin_tab_staffMouseExited

    private void admin_tab_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_logoutMouseEntered
        // TODO add your handling code here:
        admin_tab_logout.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_admin_tab_logoutMouseEntered

    private void admin_tab_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_logoutMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("admin_tab_logout")) ){
                admin_tab_logout.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_admin_tab_logoutMouseExited

    private void admin_tab_welcomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_welcomeMousePressed
        // TODO add your handling code here:
        active = "admin_tab_welcome";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 153, 0));
        admin_tab_rent.setBackground(new Color(255, 153, 0));
        admin_tab_return.setBackground(new Color(255, 153, 0));
        admin_tab_user.setBackground(new Color(255, 153, 0));
        admin_tab_staff.setBackground(new Color(255, 153, 0));
        admin_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_admin_tab_welcomeMousePressed

    private void admin_tab_libraryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_libraryMousePressed
        // TODO add your handling code here:
        active = "admin_tab_library";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 204, 0));
        admin_tab_rent.setBackground(new Color(255, 153, 0));
        admin_tab_return.setBackground(new Color(255, 153, 0));
        admin_tab_user.setBackground(new Color(255, 153, 0));
        admin_tab_staff.setBackground(new Color(255, 153, 0));
        admin_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_admin_tab_libraryMousePressed

    private void admin_tab_rentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_rentMousePressed
        // TODO add your handling code here:
        active = "admin_tab_rent";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 153, 0));
        admin_tab_rent.setBackground(new Color(255, 204, 0));
        admin_tab_return.setBackground(new Color(255, 153, 0));
        admin_tab_user.setBackground(new Color(255, 153, 0));
        admin_tab_staff.setBackground(new Color(255, 153, 0));
        admin_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_admin_tab_rentMousePressed

    private void admin_tab_returnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_returnMousePressed
        // TODO add your handling code here:
        active = "admin_tab_return";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 153, 0));
        admin_tab_rent.setBackground(new Color(255, 153, 0));
        admin_tab_return.setBackground(new Color(255, 204, 0));
        admin_tab_user.setBackground(new Color(255, 153, 0));
        admin_tab_staff.setBackground(new Color(255, 153, 0));
        admin_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_admin_tab_returnMousePressed

    private void admin_tab_userMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_userMousePressed
        // TODO add your handling code here:
        active = "admin_tab_user";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 153, 0));
        admin_tab_rent.setBackground(new Color(255, 153, 0));
        admin_tab_return.setBackground(new Color(255, 153, 0));
        admin_tab_user.setBackground(new Color(255, 204, 0));
        admin_tab_staff.setBackground(new Color(255, 153, 0));
        admin_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_admin_tab_userMousePressed

    private void admin_tab_staffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_staffMousePressed
        // TODO add your handling code here:
        active = "admin_tab_staff";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 153, 0));
        admin_tab_rent.setBackground(new Color(255, 153, 0));
        admin_tab_return.setBackground(new Color(255, 153, 0));
        admin_tab_user.setBackground(new Color(255, 153, 0));
        admin_tab_staff.setBackground(new Color(255, 204, 0));
        admin_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_admin_tab_staffMousePressed

    private void admin_tab_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tab_logoutMousePressed
        // TODO add your handling code here:
        active = "admin_tab_logout";
        admin_tab_welcome.setBackground(new Color(255,102,0));
        admin_tab_library.setBackground(new Color(255, 153, 0));
        admin_tab_rent.setBackground(new Color(255, 153, 0));
        admin_tab_return.setBackground(new Color(255, 153, 0));
        admin_tab_user.setBackground(new Color(255, 153, 0));
        admin_tab_staff.setBackground(new Color(255, 153, 0));
        admin_tab_logout.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_admin_tab_logoutMousePressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin_add_buttonadd;
    private javax.swing.JButton admin_add_buttondelete;
    private javax.swing.JButton admin_add_buttonimage;
    private javax.swing.JTextField admin_add_id;
    private javax.swing.JLabel admin_add_image;
    private javax.swing.JTextField admin_add_name;
    private javax.swing.JTextField admin_add_name1;
    private javax.swing.JTextField admin_add_price;
    private javax.swing.JTextField admin_add_quantity;
    private javax.swing.JTextField admin_add_type;
    private javax.swing.JPanel admin_liblrary_add;
    private javax.swing.JTable admin_library_order;
    private javax.swing.JTextField admin_library_search;
    private javax.swing.JPanel admin_main;
    private javax.swing.JPanel admin_multipanel;
    private javax.swing.JPanel admin_panel_library;
    private javax.swing.JPanel admin_panel_rent;
    private javax.swing.JPanel admin_panel_returnbook;
    private javax.swing.JPanel admin_panel_staff;
    private javax.swing.JPanel admin_panel_user;
    private javax.swing.JPanel admin_panel_welcome;
    private javax.swing.JScrollPane admin_rent_jscroll;
    private javax.swing.JTable admin_rent_order;
    private javax.swing.JTextField admin_rent_search;
    private javax.swing.JTextField admin_rent_search1;
    private javax.swing.JScrollPane admin_return_jscroll;
    private javax.swing.JTable admin_return_order;
    private javax.swing.JPanel admin_sidetab;
    private javax.swing.JPanel admin_staff_add;
    private javax.swing.JPanel admin_staff_add1;
    private javax.swing.JButton admin_staff_addbuttonadd;
    private javax.swing.JButton admin_staff_addbuttonadd1;
    private javax.swing.JTextField admin_staff_addfirstname;
    private javax.swing.JTextField admin_staff_addlastname;
    private javax.swing.JTextField admin_staff_addlastname1;
    private javax.swing.JPasswordField admin_staff_addpassword;
    private javax.swing.JTextField admin_staff_addusername;
    private javax.swing.JButton admin_staff_buttondelete;
    private javax.swing.JButton admin_staff_buttondelete1;
    private javax.swing.JTextField admin_staff_search;
    private javax.swing.JPanel admin_tab_empty1;
    private javax.swing.JPanel admin_tab_library;
    private javax.swing.JLabel admin_tab_librarylogo;
    private javax.swing.JPanel admin_tab_logout;
    private javax.swing.JLabel admin_tab_logoutlogo;
    private javax.swing.JPanel admin_tab_rent;
    private javax.swing.JLabel admin_tab_rentlogo;
    private javax.swing.JPanel admin_tab_return;
    private javax.swing.JLabel admin_tab_returnlogo;
    private javax.swing.JPanel admin_tab_staff;
    private javax.swing.JLabel admin_tab_stafflogo;
    private javax.swing.JPanel admin_tab_user;
    private javax.swing.JLabel admin_tab_userlogo;
    private javax.swing.JPanel admin_tab_welcome;
    private javax.swing.JLabel admin_tab_welcomelogo;
    private javax.swing.JLabel admin_txt_library;
    private javax.swing.JLabel admin_txt_logout;
    private javax.swing.JLabel admin_txt_rent;
    private javax.swing.JLabel admin_txt_return;
    private javax.swing.JLabel admin_txt_staff;
    private javax.swing.JLabel admin_txt_user;
    private javax.swing.JTextField admin_user_addfirstname;
    private javax.swing.JTextField admin_user_addlastname;
    private javax.swing.JPasswordField admin_user_addpassword;
    private javax.swing.JTextField admin_user_addusername;
    private javax.swing.JTable admin_user_order;
    private javax.swing.JScrollPane admin_user_scroll;
    private javax.swing.JTextField admin_user_search;
    private javax.swing.JLabel admin_welcome_bookrent;
    private javax.swing.JLabel admin_welcome_logo;
    private javax.swing.JLabel admin_welcome_name;
    private javax.swing.JLabel admin_welcome_welcome;
    private javax.swing.JLabel close;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menubar;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
