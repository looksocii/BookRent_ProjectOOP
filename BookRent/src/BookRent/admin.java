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
    /**
     * Creates new form admin
     */
    public admin() {
        setUndecorated(true);
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        setShape(new RoundRectangle2D.Double(0,0, 1200,700, 50,50));
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
    }
    
    public void setTableRent(){     
        admin_rent_order.setModel(new javax.swing.table.DefaultTableModel(
                                shareMethod.getHistoryDataRentForStaffAdmin(), new String[] { "No", "Username",
                                                "BookName", "ID", "Price", "Time", "ReturnTime", "status" }) {
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
    }
    
    public void setTableReturn(){        
        admin_return_order.setModel(new javax.swing.table.DefaultTableModel(
                                shareMethod.getHistoryDataReturnForStaffAdmin(), new String[] { "No", "Username",
                                                "BookName", "ID", "Price", "Time", "ReturnTime", "status" }) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BookRent");
        setResizable(false);

        admin_main.setBackground(new java.awt.Color(255, 255, 153));
        admin_main.setMaximumSize(new java.awt.Dimension(1200, 700));
        admin_main.setMinimumSize(new java.awt.Dimension(1200, 700));
        admin_main.setPreferredSize(new java.awt.Dimension(1200, 700));

        admin_sidetab.setBackground(new java.awt.Color(255, 204, 0));
        admin_sidetab.setLayout(new javax.swing.BoxLayout(admin_sidetab, javax.swing.BoxLayout.PAGE_AXIS));

        admin_tab_welcome.setBackground(new java.awt.Color(255, 102, 0));
        admin_tab_welcome.setPreferredSize(new java.awt.Dimension(0, 140));
        admin_tab_welcome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
        });

        admin_tab_welcomelogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welcome.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_welcomeLayout = new javax.swing.GroupLayout(admin_tab_welcome);
        admin_tab_welcome.setLayout(admin_tab_welcomeLayout);
        admin_tab_welcomeLayout.setHorizontalGroup(
            admin_tab_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_tab_welcomeLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(admin_tab_welcomelogo)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        admin_tab_welcomeLayout.setVerticalGroup(
            admin_tab_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_tab_welcomelogo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_welcome);

        admin_tab_library.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_library.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_mouseexited(evt);
            }
        });

        admin_txt_library.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_library.setText("Library");

        admin_tab_librarylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_librarylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/library.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_libraryLayout = new javax.swing.GroupLayout(admin_tab_library);
        admin_tab_library.setLayout(admin_tab_libraryLayout);
        admin_tab_libraryLayout.setHorizontalGroup(
            admin_tab_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_tab_libraryLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(admin_tab_librarylogo)
                .addGap(18, 18, 18)
                .addComponent(admin_txt_library)
                .addGap(23, 23, 23))
        );
        admin_tab_libraryLayout.setVerticalGroup(
            admin_tab_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_tab_librarylogo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
            .addComponent(admin_txt_library, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_library);

        admin_tab_rent.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_rent.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_rent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_mouseexited(evt);
            }
        });

        admin_txt_rent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_rent.setText("Rent");

        admin_tab_rentlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_rentlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rent.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_rentLayout = new javax.swing.GroupLayout(admin_tab_rent);
        admin_tab_rent.setLayout(admin_tab_rentLayout);
        admin_tab_rentLayout.setHorizontalGroup(
            admin_tab_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_tab_rentLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(admin_tab_rentlogo)
                .addGap(18, 18, 18)
                .addComponent(admin_txt_rent)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        admin_tab_rentLayout.setVerticalGroup(
            admin_tab_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_txt_rent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(admin_tab_rentlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_rent);

        admin_tab_return.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_return.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_mouseexited(evt);
            }
        });

        admin_txt_return.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_return.setText("Return");

        admin_tab_returnlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_returnlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_returnLayout = new javax.swing.GroupLayout(admin_tab_return);
        admin_tab_return.setLayout(admin_tab_returnLayout);
        admin_tab_returnLayout.setHorizontalGroup(
            admin_tab_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_tab_returnLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(admin_tab_returnlogo)
                .addGap(18, 18, 18)
                .addComponent(admin_txt_return)
                .addGap(29, 29, 29))
        );
        admin_tab_returnLayout.setVerticalGroup(
            admin_tab_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_txt_return, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(admin_tab_returnlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_return);

        admin_tab_user.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_user.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_mouseexited(evt);
            }
        });

        admin_txt_user.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_user.setText("User");

        admin_tab_userlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_userlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_userLayout = new javax.swing.GroupLayout(admin_tab_user);
        admin_tab_user.setLayout(admin_tab_userLayout);
        admin_tab_userLayout.setHorizontalGroup(
            admin_tab_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_tab_userLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(admin_tab_userlogo)
                .addGap(18, 18, 18)
                .addComponent(admin_txt_user)
                .addGap(50, 50, 50))
        );
        admin_tab_userLayout.setVerticalGroup(
            admin_tab_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_txt_user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(admin_tab_userlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_user);

        admin_tab_staff.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_staff.setPreferredSize(new java.awt.Dimension(200, 80));
        admin_tab_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_mouseexited(evt);
            }
        });

        admin_txt_staff.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_staff.setText("Staff");

        admin_tab_stafflogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_stafflogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staff.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_staffLayout = new javax.swing.GroupLayout(admin_tab_staff);
        admin_tab_staff.setLayout(admin_tab_staffLayout);
        admin_tab_staffLayout.setHorizontalGroup(
            admin_tab_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_tab_staffLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(admin_tab_stafflogo)
                .addGap(18, 18, 18)
                .addComponent(admin_txt_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        admin_tab_staffLayout.setVerticalGroup(
            admin_tab_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_txt_staff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(admin_tab_stafflogo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_staff);

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

        admin_sidetab.add(admin_tab_empty1);

        admin_tab_logout.setBackground(new java.awt.Color(255, 153, 0));
        admin_tab_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admin_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admin_mouseexited(evt);
            }
        });

        admin_txt_logout.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_txt_logout.setText("Logout");

        admin_tab_logoutlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        admin_tab_logoutlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N

        javax.swing.GroupLayout admin_tab_logoutLayout = new javax.swing.GroupLayout(admin_tab_logout);
        admin_tab_logout.setLayout(admin_tab_logoutLayout);
        admin_tab_logoutLayout.setHorizontalGroup(
            admin_tab_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_tab_logoutLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(admin_tab_logoutlogo)
                .addGap(18, 18, 18)
                .addComponent(admin_txt_logout)
                .addGap(25, 25, 25))
        );
        admin_tab_logoutLayout.setVerticalGroup(
            admin_tab_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_txt_logout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(admin_tab_logoutlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        admin_sidetab.add(admin_tab_logout);

        admin_multipanel.setBackground(new java.awt.Color(255, 255, 153));
        admin_multipanel.setLayout(new java.awt.CardLayout());

        admin_panel_welcome.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_welcome.setPreferredSize(new java.awt.Dimension(800, 700));

        admin_welcome_welcome.setFont(new java.awt.Font("Banaue", 1, 90)); // NOI18N
        admin_welcome_welcome.setText("WELCOME");

        admin_welcome_bookrent.setFont(new java.awt.Font("Banaue", 0, 54)); // NOI18N
        admin_welcome_bookrent.setText("BookRent");

        admin_welcome_name.setFont(new java.awt.Font("TH SarabunPSK", 0, 48)); // NOI18N
        admin_welcome_name.setText("xxxxxxxxxxxxxxxxxxxxx");

        admin_welcome_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout admin_panel_welcomeLayout = new javax.swing.GroupLayout(admin_panel_welcome);
        admin_panel_welcome.setLayout(admin_panel_welcomeLayout);
        admin_panel_welcomeLayout.setHorizontalGroup(
            admin_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_panel_welcomeLayout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(admin_welcome_welcome)
                .addGap(337, 337, 337))
            .addGroup(admin_panel_welcomeLayout.createSequentialGroup()
                .addGroup(admin_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(admin_panel_welcomeLayout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(admin_welcome_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(admin_welcome_bookrent))
                    .addGroup(admin_panel_welcomeLayout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(admin_welcome_name)))
                .addContainerGap())
        );
        admin_panel_welcomeLayout.setVerticalGroup(
            admin_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panel_welcomeLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(admin_welcome_welcome)
                .addGap(43, 43, 43)
                .addGroup(admin_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(admin_panel_welcomeLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(admin_welcome_bookrent))
                    .addComponent(admin_welcome_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(admin_welcome_name)
                .addContainerGap())
        );

        admin_multipanel.add(admin_panel_welcome, "card6");

        admin_panel_library.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_library.setPreferredSize(new java.awt.Dimension(800, 700));

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

        admin_liblrary_add.setBackground(new java.awt.Color(255, 102, 0));

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

        admin_add_name.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_name.setText("Author");
        admin_add_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_nameMouseClicked(evt);
            }
        });

        admin_add_type.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_type.setText("Category");
        admin_add_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_typeMouseClicked(evt);
            }
        });

        admin_add_quantity.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_quantity.setText("Quantity");
        admin_add_quantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_quantityMouseClicked(evt);
            }
        });

        admin_add_price.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_price.setText("Price");
        admin_add_price.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_priceMouseClicked(evt);
            }
        });

        admin_add_buttonadd.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_buttonadd.setText("Add");
        admin_add_buttonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_buttonaddActionPerformed(evt);
            }
        });

        admin_add_buttonimage.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_buttonimage.setText("Image");
        admin_add_buttonimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_buttonimageActionPerformed(evt);
            }
        });

        admin_add_image.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        admin_add_buttondelete.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_buttondelete.setText("Delete");
        admin_add_buttondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_add_buttondeleteActionPerformed(evt);
            }
        });

        admin_add_name1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_add_name1.setText("Name Book");
        admin_add_name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_add_name1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout admin_liblrary_addLayout = new javax.swing.GroupLayout(admin_liblrary_add);
        admin_liblrary_add.setLayout(admin_liblrary_addLayout);
        admin_liblrary_addLayout.setHorizontalGroup(
            admin_liblrary_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_liblrary_addLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(admin_add_image, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_buttonimage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_name, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_type, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_price, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_add_buttonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(admin_add_buttondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        admin_liblrary_addLayout.setVerticalGroup(
            admin_liblrary_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_liblrary_addLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(admin_liblrary_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admin_add_quantity)
                    .addComponent(admin_add_type)
                    .addComponent(admin_add_name)
                    .addComponent(admin_add_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_add_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_add_buttonadd)
                    .addComponent(admin_add_buttonimage)
                    .addComponent(admin_add_buttondelete)
                    .addComponent(admin_add_name1))
                .addGap(26, 26, 26))
            .addComponent(admin_add_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout admin_panel_libraryLayout = new javax.swing.GroupLayout(admin_panel_library);
        admin_panel_library.setLayout(admin_panel_libraryLayout);
        admin_panel_libraryLayout.setHorizontalGroup(
            admin_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_liblrary_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(admin_panel_libraryLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(admin_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(admin_library_search))
                .addContainerGap())
        );
        admin_panel_libraryLayout.setVerticalGroup(
            admin_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_panel_libraryLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(admin_library_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(admin_liblrary_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        admin_multipanel.add(admin_panel_library, "card2");

        admin_panel_rent.setBackground(new java.awt.Color(255, 204, 0));

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

        javax.swing.GroupLayout admin_panel_rentLayout = new javax.swing.GroupLayout(admin_panel_rent);
        admin_panel_rent.setLayout(admin_panel_rentLayout);
        admin_panel_rentLayout.setHorizontalGroup(
            admin_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_panel_rentLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(admin_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(admin_rent_search)
                    .addComponent(admin_rent_jscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        admin_panel_rentLayout.setVerticalGroup(
            admin_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panel_rentLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(admin_rent_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(admin_rent_jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        admin_multipanel.add(admin_panel_rent, "card3");

        admin_panel_returnbook.setBackground(new java.awt.Color(255, 204, 0));
        admin_panel_returnbook.setPreferredSize(new java.awt.Dimension(800, 700));

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

        javax.swing.GroupLayout admin_panel_returnbookLayout = new javax.swing.GroupLayout(admin_panel_returnbook);
        admin_panel_returnbook.setLayout(admin_panel_returnbookLayout);
        admin_panel_returnbookLayout.setHorizontalGroup(
            admin_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panel_returnbookLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(admin_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(admin_rent_search1)
                    .addComponent(admin_return_jscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        admin_panel_returnbookLayout.setVerticalGroup(
            admin_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panel_returnbookLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(admin_rent_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(admin_return_jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        admin_multipanel.add(admin_panel_returnbook, "card4");

        admin_panel_user.setBackground(new java.awt.Color(255, 204, 0));

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

        admin_staff_add1.setBackground(new java.awt.Color(255, 102, 0));
        admin_staff_add1.setPreferredSize(new java.awt.Dimension(1000, 85));

        admin_user_addusername.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_user_addusername.setText("Username");
        admin_user_addusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addusernameMouseClicked(evt);
            }
        });

        admin_user_addpassword.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        admin_user_addpassword.setText("xxxxxxxxxxx");
        admin_user_addpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addpasswordMouseClicked(evt);
            }
        });

        admin_user_addfirstname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_user_addfirstname.setText("Firstname");
        admin_user_addfirstname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addfirstnameMouseClicked(evt);
            }
        });

        admin_staff_addbuttonadd1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addbuttonadd1.setText("ADD");
        admin_staff_addbuttonadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_addbuttonadd1ActionPerformed(evt);
            }
        });

        admin_staff_buttondelete1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_buttondelete1.setText("Delete");
        admin_staff_buttondelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_buttondelete1ActionPerformed(evt);
            }
        });

        admin_user_addlastname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_user_addlastname.setText("Lastname");
        admin_user_addlastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_user_addlastnameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout admin_staff_add1Layout = new javax.swing.GroupLayout(admin_staff_add1);
        admin_staff_add1.setLayout(admin_staff_add1Layout);
        admin_staff_add1Layout.setHorizontalGroup(
            admin_staff_add1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_staff_add1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(admin_user_addusername, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_user_addpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_user_addfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_user_addlastname, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_addbuttonadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_buttondelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        admin_staff_add1Layout.setVerticalGroup(
            admin_staff_add1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_staff_add1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(admin_staff_add1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admin_user_addusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_user_addpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_user_addfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_user_addlastname)
                    .addComponent(admin_staff_addbuttonadd1)
                    .addComponent(admin_staff_buttondelete1))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout admin_panel_userLayout = new javax.swing.GroupLayout(admin_panel_user);
        admin_panel_user.setLayout(admin_panel_userLayout);
        admin_panel_userLayout.setHorizontalGroup(
            admin_panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panel_userLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(admin_panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(admin_user_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(admin_user_search))
                .addGap(26, 26, 26))
            .addComponent(admin_staff_add1, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
        );
        admin_panel_userLayout.setVerticalGroup(
            admin_panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panel_userLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(admin_user_search, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(admin_user_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(admin_staff_add1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        admin_multipanel.add(admin_panel_user, "card5");

        admin_panel_staff.setBackground(new java.awt.Color(255, 204, 0));

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

        admin_staff_add.setBackground(new java.awt.Color(255, 102, 0));
        admin_staff_add.setPreferredSize(new java.awt.Dimension(1000, 85));

        admin_staff_addusername.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addusername.setText("Username");
        admin_staff_addusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addusernameMouseClicked(evt);
            }
        });

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

        admin_staff_addfirstname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addfirstname.setText("Firstname");
        admin_staff_addfirstname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addfirstnameMouseClicked(evt);
            }
        });

        admin_staff_addlastname.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addlastname.setText("JobPosition");
        admin_staff_addlastname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addlastnameMouseClicked(evt);
            }
        });

        admin_staff_addbuttonadd.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addbuttonadd.setText("ADD");
        admin_staff_addbuttonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_addbuttonaddActionPerformed(evt);
            }
        });

        admin_staff_buttondelete.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_buttondelete.setText("Delete");
        admin_staff_buttondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_buttondeleteActionPerformed(evt);
            }
        });

        admin_staff_addlastname1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_addlastname1.setText("Lastname");
        admin_staff_addlastname1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_staff_addlastname1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout admin_staff_addLayout = new javax.swing.GroupLayout(admin_staff_add);
        admin_staff_add.setLayout(admin_staff_addLayout);
        admin_staff_addLayout.setHorizontalGroup(
            admin_staff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_staff_addLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(admin_staff_addusername, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_addpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_addfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_addlastname1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_addlastname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_addbuttonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin_staff_buttondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        admin_staff_addLayout.setVerticalGroup(
            admin_staff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_staff_addLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(admin_staff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admin_staff_addusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_staff_addpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_staff_addfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_staff_addlastname1)
                    .addComponent(admin_staff_addlastname)
                    .addComponent(admin_staff_addbuttonadd)
                    .addComponent(admin_staff_buttondelete))
                .addGap(20, 20, 20))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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

        javax.swing.GroupLayout admin_panel_staffLayout = new javax.swing.GroupLayout(admin_panel_staff);
        admin_panel_staff.setLayout(admin_panel_staffLayout);
        admin_panel_staffLayout.setHorizontalGroup(
            admin_panel_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_staff_add, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
            .addGroup(admin_panel_staffLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(admin_panel_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(admin_staff_search, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        admin_panel_staffLayout.setVerticalGroup(
            admin_panel_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_panel_staffLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(admin_staff_search, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(admin_staff_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        admin_multipanel.add(admin_panel_staff, "card7");

        javax.swing.GroupLayout admin_mainLayout = new javax.swing.GroupLayout(admin_main);
        admin_main.setLayout(admin_mainLayout);
        admin_mainLayout.setHorizontalGroup(
            admin_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_mainLayout.createSequentialGroup()
                .addComponent(admin_sidetab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(admin_multipanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE))
        );
        admin_mainLayout.setVerticalGroup(
            admin_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin_sidetab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(admin_multipanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

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
            .addComponent(admin_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void admin_mouseentered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_mouseentered
        if (evt.getSource()== admin_tab_library){
            admin_tab_library.setBackground(new Color(255,204,0));
        }
        if (evt.getSource()== admin_tab_rent){
            admin_tab_rent.setBackground(new Color(255,204,0));
        }
        if (evt.getSource()== admin_tab_return){
            admin_tab_return.setBackground(new Color(255,204,0));
        }
        if (evt.getSource()== admin_tab_user){
            admin_tab_user.setBackground(new Color(255,204,0));
        }
        if (evt.getSource()== admin_tab_staff){
            admin_tab_staff.setBackground(new Color(255,204,0));
        }
        if (evt.getSource()== admin_tab_logout){
            admin_tab_logout.setBackground(new Color(255,204,0));
        }
    }//GEN-LAST:event_admin_mouseentered

    private void admin_mouseexited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_mouseexited
        if (evt.getSource()== admin_tab_library){
            admin_tab_library.setBackground(new Color(255,153,0));
        }
        if (evt.getSource()== admin_tab_rent){
            admin_tab_rent.setBackground(new Color(255,153,0));
        }
        if (evt.getSource()== admin_tab_return){
            admin_tab_return.setBackground(new Color(255,153,0));
        }
        if (evt.getSource()== admin_tab_user){
            admin_tab_user.setBackground(new Color(255,153,0));
        }
        if (evt.getSource()== admin_tab_staff){
            admin_tab_staff.setBackground(new Color(255,153,0));
        }
        if (evt.getSource()== admin_tab_logout){
            admin_tab_logout.setBackground(new Color(255,153,0));
        }
    }//GEN-LAST:event_admin_mouseexited

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
            admin_add_id.setText("");
            admin_add_name1.setText("");
            admin_add_name.setText("");
            admin_add_type.setText("");
            admin_add_quantity.setText("");
            admin_add_price.setText("");
            admin_add_buttonadd.setText("Add");
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
            admin_add_id.setText("");
            admin_add_name1.setText("");
            admin_add_name.setText("");
            admin_add_type.setText("");
            admin_add_quantity.setText("");
            admin_add_price.setText("");
            admin_add_buttonadd.setText("Add");
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
            admin_add_id.setText("");
            admin_add_name1.setText("");
            admin_add_name.setText("");
            admin_add_type.setText("");
            admin_add_quantity.setText("");
            admin_add_price.setText("");
            admin_add_buttonadd.setText("Add");
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
            admin_add_id.setText("");
            admin_add_name1.setText("");
            admin_add_name.setText("");
            admin_add_type.setText("");
            admin_add_quantity.setText("");
            admin_add_price.setText("");
            admin_add_buttonadd.setText("Add");
            num1=0; num2=0; num3=0; num4=0; num5=0; num6=0; num7=0; id=0;
        }else{
            JOptionPane.showMessageDialog(null, "You have not selected.");
        }
        notImg1=0;
        notImg2=0;
    }//GEN-LAST:event_admin_add_buttondeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
            }
        });
    }

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
