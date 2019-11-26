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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class staff extends javax.swing.JFrame {
    
    
    // edited
        JFileChooser fileChooser = new JFileChooser();
        ShareMethod shareMethod = new ShareMethod();
        DefaultTableModel dm;
        String addressImage;
        private int num1=0, num2=0, num3=0, num4=0, num5=0, num6=0, num7=0;
        DatabaseManagement db = new DatabaseManagement();
        // edited
    
    /**
     * Creates new form staff
     */
    public staff() {
        setUndecorated(true);
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        setShape(new RoundRectangle2D.Double(0,0, 1200,700, 50,50));
        staff_welcome_name.setText(ShareMethod.firstName+" "+ShareMethod.lastName);
            try {
                setTable();
            } catch (IOException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void setTable() throws IOException, SQLException{
        jTable1.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getBookStoreData(),
                                new String[] { "No", "Image", "ID", "BookName", "Author", "Category", "Quantity",
                                                "Price" }) {
                        Class[] types = new Class[] { java.lang.Object.class, javax.swing.Icon.class,
                                        java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                                        java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, };
                        boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
  
                
        staff_rent_order.setModel(new javax.swing.table.DefaultTableModel(
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
        
        staff_return_order.setModel(new javax.swing.table.DefaultTableModel(
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
        
        staff_user_order.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getCustomerData(),
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
    private void filterOrder(String query){
        dm = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void filterRent(String query){
        dm = (DefaultTableModel) staff_rent_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        staff_rent_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void filterReturn(String query){
        dm = (DefaultTableModel) staff_return_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        staff_return_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void filterUser(String query){
        dm = (DefaultTableModel) staff_user_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        staff_user_order.setRowSorter(tr);
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

        staff_main = new javax.swing.JPanel();
        staff_sidetab = new javax.swing.JPanel();
        staff_tab_welcome = new javax.swing.JPanel();
        staff_tab_welcomelogo = new javax.swing.JLabel();
        staff_tab_library = new javax.swing.JPanel();
        staff_txt_library = new javax.swing.JLabel();
        staff_tab_librarylogo = new javax.swing.JLabel();
        staff_tab_rent = new javax.swing.JPanel();
        staff_txt_rent = new javax.swing.JLabel();
        staff_tab_rentlogo = new javax.swing.JLabel();
        staff_tab_return = new javax.swing.JPanel();
        staff_txt_return = new javax.swing.JLabel();
        staff_tab_returnlogo = new javax.swing.JLabel();
        staff_tab_user = new javax.swing.JPanel();
        staff_txt_user = new javax.swing.JLabel();
        staff_tab_userlogo = new javax.swing.JLabel();
        staff_tab_empty1 = new javax.swing.JPanel();
        staff_tab_empty2 = new javax.swing.JPanel();
        staff_tab_logout = new javax.swing.JPanel();
        staff_txt_logout = new javax.swing.JLabel();
        staff_tab_logoutlogo = new javax.swing.JLabel();
        staff_multipanel = new javax.swing.JPanel();
        staff_panel_welcome = new javax.swing.JPanel();
        staff_welcome_welcome = new javax.swing.JLabel();
        staff_welcome_bookrent = new javax.swing.JLabel();
        staff_welcome_name = new javax.swing.JLabel();
        staff_welcome_logo = new javax.swing.JLabel();
        staff_panel_library = new javax.swing.JPanel();
        staff_library_search = new javax.swing.JTextField();
        staff_liblrary_add = new javax.swing.JPanel();
        staff_add_id = new javax.swing.JTextField();
        staff_add_name = new javax.swing.JTextField();
        staff_add_type = new javax.swing.JTextField();
        staff_add_quantity = new javax.swing.JTextField();
        staff_add_price = new javax.swing.JTextField();
        staff_add_buttonimage = new javax.swing.JButton();
        staff_add_image = new javax.swing.JLabel();
        admin_staff_buttondelete = new javax.swing.JButton();
        admin_staff_buttondelete1 = new javax.swing.JButton();
        staff_add_name1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        staff_panel_rent = new javax.swing.JPanel();
        staff_rent_search = new javax.swing.JTextField();
        staff_rent_jscroll = new javax.swing.JScrollPane();
        staff_rent_order = new javax.swing.JTable();
        staff_panel_returnbook = new javax.swing.JPanel();
        staff_rent_search1 = new javax.swing.JTextField();
        staff_return_jscroll = new javax.swing.JScrollPane();
        staff_return_order = new javax.swing.JTable();
        staff_panel_user = new javax.swing.JPanel();
        staff_user_search = new javax.swing.JTextField();
        staff_user_scroll = new javax.swing.JScrollPane();
        staff_user_order = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BookRent");
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setResizable(false);

        staff_main.setBackground(new java.awt.Color(255, 255, 153));
        staff_main.setMaximumSize(new java.awt.Dimension(1200, 700));
        staff_main.setMinimumSize(new java.awt.Dimension(1200, 700));
        staff_main.setPreferredSize(new java.awt.Dimension(1200, 700));

        staff_sidetab.setBackground(new java.awt.Color(255, 204, 0));
        staff_sidetab.setLayout(new javax.swing.BoxLayout(staff_sidetab, javax.swing.BoxLayout.PAGE_AXIS));

        staff_tab_welcome.setBackground(new java.awt.Color(255, 102, 0));
        staff_tab_welcome.setPreferredSize(new java.awt.Dimension(200, 140));
        staff_tab_welcome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
        });

        staff_tab_welcomelogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welcome.png"))); // NOI18N

        javax.swing.GroupLayout staff_tab_welcomeLayout = new javax.swing.GroupLayout(staff_tab_welcome);
        staff_tab_welcome.setLayout(staff_tab_welcomeLayout);
        staff_tab_welcomeLayout.setHorizontalGroup(
            staff_tab_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_tab_welcomeLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(staff_tab_welcomelogo)
                .addGap(60, 60, 60))
        );
        staff_tab_welcomeLayout.setVerticalGroup(
            staff_tab_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_tab_welcomelogo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        staff_sidetab.add(staff_tab_welcome);

        staff_tab_library.setBackground(new java.awt.Color(255, 153, 0));
        staff_tab_library.setPreferredSize(new java.awt.Dimension(200, 80));
        staff_tab_library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseexited(evt);
            }
        });

        staff_txt_library.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_txt_library.setText("Library");

        staff_tab_librarylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_tab_librarylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/library.png"))); // NOI18N

        javax.swing.GroupLayout staff_tab_libraryLayout = new javax.swing.GroupLayout(staff_tab_library);
        staff_tab_library.setLayout(staff_tab_libraryLayout);
        staff_tab_libraryLayout.setHorizontalGroup(
            staff_tab_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_tab_libraryLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(staff_tab_librarylogo)
                .addGap(18, 18, 18)
                .addComponent(staff_txt_library)
                .addGap(23, 23, 23))
        );
        staff_tab_libraryLayout.setVerticalGroup(
            staff_tab_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_txt_library, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_tab_librarylogo, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );

        staff_sidetab.add(staff_tab_library);

        staff_tab_rent.setBackground(new java.awt.Color(255, 153, 0));
        staff_tab_rent.setPreferredSize(new java.awt.Dimension(200, 80));
        staff_tab_rent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseexited(evt);
            }
        });

        staff_txt_rent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_txt_rent.setText("Rent");

        staff_tab_rentlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_tab_rentlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rent.png"))); // NOI18N

        javax.swing.GroupLayout staff_tab_rentLayout = new javax.swing.GroupLayout(staff_tab_rent);
        staff_tab_rent.setLayout(staff_tab_rentLayout);
        staff_tab_rentLayout.setHorizontalGroup(
            staff_tab_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_tab_rentLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(staff_tab_rentlogo)
                .addGap(18, 18, 18)
                .addComponent(staff_txt_rent)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        staff_tab_rentLayout.setVerticalGroup(
            staff_tab_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_txt_rent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_tab_rentlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );

        staff_sidetab.add(staff_tab_rent);

        staff_tab_return.setBackground(new java.awt.Color(255, 153, 0));
        staff_tab_return.setPreferredSize(new java.awt.Dimension(200, 80));
        staff_tab_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseexited(evt);
            }
        });

        staff_txt_return.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_txt_return.setText("Return");

        staff_tab_returnlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_tab_returnlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N

        javax.swing.GroupLayout staff_tab_returnLayout = new javax.swing.GroupLayout(staff_tab_return);
        staff_tab_return.setLayout(staff_tab_returnLayout);
        staff_tab_returnLayout.setHorizontalGroup(
            staff_tab_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_tab_returnLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(staff_tab_returnlogo)
                .addGap(18, 18, 18)
                .addComponent(staff_txt_return)
                .addGap(29, 29, 29))
        );
        staff_tab_returnLayout.setVerticalGroup(
            staff_tab_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_txt_return, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_tab_returnlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );

        staff_sidetab.add(staff_tab_return);

        staff_tab_user.setBackground(new java.awt.Color(255, 153, 0));
        staff_tab_user.setPreferredSize(new java.awt.Dimension(200, 80));
        staff_tab_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseexited(evt);
            }
        });

        staff_txt_user.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_txt_user.setText("User");

        staff_tab_userlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_tab_userlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        javax.swing.GroupLayout staff_tab_userLayout = new javax.swing.GroupLayout(staff_tab_user);
        staff_tab_user.setLayout(staff_tab_userLayout);
        staff_tab_userLayout.setHorizontalGroup(
            staff_tab_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_tab_userLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(staff_tab_userlogo)
                .addGap(18, 18, 18)
                .addComponent(staff_txt_user)
                .addGap(50, 50, 50))
        );
        staff_tab_userLayout.setVerticalGroup(
            staff_tab_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_txt_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_tab_userlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );

        staff_sidetab.add(staff_tab_user);

        staff_tab_empty1.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout staff_tab_empty1Layout = new javax.swing.GroupLayout(staff_tab_empty1);
        staff_tab_empty1.setLayout(staff_tab_empty1Layout);
        staff_tab_empty1Layout.setHorizontalGroup(
            staff_tab_empty1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        staff_tab_empty1Layout.setVerticalGroup(
            staff_tab_empty1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        staff_sidetab.add(staff_tab_empty1);

        staff_tab_empty2.setBackground(new java.awt.Color(255, 153, 0));
        staff_tab_empty2.setPreferredSize(new java.awt.Dimension(200, 80));

        staff_tab_logout.setBackground(new java.awt.Color(255, 153, 0));
        staff_tab_logout.setPreferredSize(new java.awt.Dimension(200, 80));
        staff_tab_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseexited(evt);
            }
        });

        staff_txt_logout.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_txt_logout.setText("Logout");

        staff_tab_logoutlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_tab_logoutlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N

        javax.swing.GroupLayout staff_tab_logoutLayout = new javax.swing.GroupLayout(staff_tab_logout);
        staff_tab_logout.setLayout(staff_tab_logoutLayout);
        staff_tab_logoutLayout.setHorizontalGroup(
            staff_tab_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_tab_logoutLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(staff_tab_logoutlogo)
                .addGap(18, 18, 18)
                .addComponent(staff_txt_logout)
                .addGap(27, 27, 27))
        );
        staff_tab_logoutLayout.setVerticalGroup(
            staff_tab_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_txt_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_tab_logoutlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout staff_tab_empty2Layout = new javax.swing.GroupLayout(staff_tab_empty2);
        staff_tab_empty2.setLayout(staff_tab_empty2Layout);
        staff_tab_empty2Layout.setHorizontalGroup(
            staff_tab_empty2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_tab_empty2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(staff_tab_logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        staff_tab_empty2Layout.setVerticalGroup(
            staff_tab_empty2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_tab_empty2Layout.createSequentialGroup()
                .addComponent(staff_tab_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        staff_sidetab.add(staff_tab_empty2);

        staff_multipanel.setBackground(new java.awt.Color(255, 255, 153));
        staff_multipanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        staff_multipanel.setLayout(new java.awt.CardLayout());

        staff_panel_welcome.setBackground(new java.awt.Color(255, 204, 0));
        staff_panel_welcome.setPreferredSize(new java.awt.Dimension(800, 700));

        staff_welcome_welcome.setFont(new java.awt.Font("Banaue", 1, 90)); // NOI18N
        staff_welcome_welcome.setText("WELCOME");

        staff_welcome_bookrent.setFont(new java.awt.Font("Banaue", 0, 54)); // NOI18N
        staff_welcome_bookrent.setText("BookRent");

        staff_welcome_name.setFont(new java.awt.Font("TH SarabunPSK", 0, 48)); // NOI18N
        staff_welcome_name.setText("xxxxxxxxxxxxxxxxxxxxxxx");

        staff_welcome_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout staff_panel_welcomeLayout = new javax.swing.GroupLayout(staff_panel_welcome);
        staff_panel_welcome.setLayout(staff_panel_welcomeLayout);
        staff_panel_welcomeLayout.setHorizontalGroup(
            staff_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_panel_welcomeLayout.createSequentialGroup()
                .addGroup(staff_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staff_panel_welcomeLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(staff_welcome_welcome))
                    .addGroup(staff_panel_welcomeLayout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addGroup(staff_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(staff_welcome_name)
                            .addGroup(staff_panel_welcomeLayout.createSequentialGroup()
                                .addComponent(staff_welcome_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(staff_welcome_bookrent)))))
                .addGap(16, 16, 16))
        );
        staff_panel_welcomeLayout.setVerticalGroup(
            staff_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_panel_welcomeLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(staff_welcome_welcome)
                .addGap(43, 43, 43)
                .addGroup(staff_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staff_panel_welcomeLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(staff_welcome_bookrent))
                    .addComponent(staff_welcome_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(staff_welcome_name)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        staff_multipanel.add(staff_panel_welcome, "card6");

        staff_panel_library.setBackground(new java.awt.Color(255, 204, 0));
        staff_panel_library.setPreferredSize(new java.awt.Dimension(800, 700));

        staff_library_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        staff_library_search.setText("Search");
        staff_library_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_library_searchMouseClicked(evt);
            }
        });
        staff_library_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                staff_library_searchKeyReleased(evt);
            }
        });

        staff_liblrary_add.setBackground(new java.awt.Color(255, 102, 0));

        staff_add_id.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_id.setText("Book ID");
        staff_add_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_add_idMouseClicked(evt);
            }
        });

        staff_add_name.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_name.setText("Name Book");
        staff_add_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_add_nameMouseClicked(evt);
            }
        });
        staff_add_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staff_add_nameActionPerformed(evt);
            }
        });

        staff_add_type.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_type.setText("Category");
        staff_add_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_add_typeMouseClicked(evt);
            }
        });

        staff_add_quantity.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_quantity.setText("Quantity");
        staff_add_quantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_add_quantityMouseClicked(evt);
            }
        });

        staff_add_price.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_price.setText("Price");
        staff_add_price.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_add_priceMouseClicked(evt);
            }
        });

        staff_add_buttonimage.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_buttonimage.setText("Image");
        staff_add_buttonimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staff_add_buttonimageActionPerformed(evt);
            }
        });

        staff_add_image.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        admin_staff_buttondelete.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_buttondelete.setText("Delete");

        admin_staff_buttondelete1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        admin_staff_buttondelete1.setText("Add");
        admin_staff_buttondelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_staff_buttondelete1ActionPerformed(evt);
            }
        });

        staff_add_name1.setFont(new java.awt.Font("Angsana New", 0, 22)); // NOI18N
        staff_add_name1.setText("Author");
        staff_add_name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_add_name1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout staff_liblrary_addLayout = new javax.swing.GroupLayout(staff_liblrary_add);
        staff_liblrary_add.setLayout(staff_liblrary_addLayout);
        staff_liblrary_addLayout.setHorizontalGroup(
            staff_liblrary_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_liblrary_addLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(staff_add_image, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(staff_add_buttonimage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staff_add_id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staff_add_name, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staff_add_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staff_add_type, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staff_add_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staff_add_price, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(admin_staff_buttondelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(admin_staff_buttondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        staff_liblrary_addLayout.setVerticalGroup(
            staff_liblrary_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_liblrary_addLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(staff_liblrary_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staff_add_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staff_add_buttonimage)
                    .addComponent(staff_add_name)
                    .addComponent(staff_add_name1)
                    .addComponent(staff_add_type)
                    .addComponent(staff_add_quantity)
                    .addComponent(staff_add_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_staff_buttondelete1)
                    .addComponent(admin_staff_buttondelete))
                .addGap(26, 26, 26))
            .addComponent(staff_add_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout staff_panel_libraryLayout = new javax.swing.GroupLayout(staff_panel_library);
        staff_panel_library.setLayout(staff_panel_libraryLayout);
        staff_panel_libraryLayout.setHorizontalGroup(
            staff_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_liblrary_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(staff_panel_libraryLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(staff_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(staff_library_search, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staff_panel_libraryLayout.setVerticalGroup(
            staff_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_panel_libraryLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(staff_library_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(staff_liblrary_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        staff_multipanel.add(staff_panel_library, "card2");

        staff_panel_rent.setBackground(new java.awt.Color(255, 204, 0));

        staff_rent_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        staff_rent_search.setText("Search");
        staff_rent_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_rent_searchMouseClicked(evt);
            }
        });
        staff_rent_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                staff_rent_searchKeyReleased(evt);
            }
        });

        staff_rent_order.setModel(new javax.swing.table.DefaultTableModel(
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
        staff_rent_jscroll.setViewportView(staff_rent_order);
        if (staff_rent_order.getColumnModel().getColumnCount() > 0) {
            staff_rent_order.getColumnModel().getColumn(0).setMinWidth(50);
            staff_rent_order.getColumnModel().getColumn(0).setMaxWidth(60);
            staff_rent_order.getColumnModel().getColumn(1).setMinWidth(100);
            staff_rent_order.getColumnModel().getColumn(1).setMaxWidth(110);
            staff_rent_order.getColumnModel().getColumn(3).setMinWidth(100);
            staff_rent_order.getColumnModel().getColumn(3).setMaxWidth(110);
            staff_rent_order.getColumnModel().getColumn(6).setMinWidth(80);
            staff_rent_order.getColumnModel().getColumn(6).setMaxWidth(90);
            staff_rent_order.getColumnModel().getColumn(7).setMinWidth(80);
            staff_rent_order.getColumnModel().getColumn(7).setMaxWidth(90);
        }

        javax.swing.GroupLayout staff_panel_rentLayout = new javax.swing.GroupLayout(staff_panel_rent);
        staff_panel_rent.setLayout(staff_panel_rentLayout);
        staff_panel_rentLayout.setHorizontalGroup(
            staff_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_panel_rentLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(staff_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(staff_rent_search)
                    .addComponent(staff_rent_jscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        staff_panel_rentLayout.setVerticalGroup(
            staff_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_panel_rentLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(staff_rent_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staff_rent_jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        staff_multipanel.add(staff_panel_rent, "card3");

        staff_panel_returnbook.setBackground(new java.awt.Color(255, 204, 0));
        staff_panel_returnbook.setPreferredSize(new java.awt.Dimension(800, 700));

        staff_rent_search1.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        staff_rent_search1.setText("Search");
        staff_rent_search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_rent_search1MouseClicked(evt);
            }
        });
        staff_rent_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                staff_rent_search1KeyReleased(evt);
            }
        });

        staff_return_order.setModel(new javax.swing.table.DefaultTableModel(
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
        staff_return_jscroll.setViewportView(staff_return_order);
        if (staff_return_order.getColumnModel().getColumnCount() > 0) {
            staff_return_order.getColumnModel().getColumn(0).setMinWidth(50);
            staff_return_order.getColumnModel().getColumn(0).setMaxWidth(60);
            staff_return_order.getColumnModel().getColumn(3).setMinWidth(100);
            staff_return_order.getColumnModel().getColumn(3).setMaxWidth(110);
            staff_return_order.getColumnModel().getColumn(6).setMinWidth(80);
            staff_return_order.getColumnModel().getColumn(6).setMaxWidth(90);
            staff_return_order.getColumnModel().getColumn(7).setMinWidth(80);
            staff_return_order.getColumnModel().getColumn(7).setMaxWidth(90);
        }

        javax.swing.GroupLayout staff_panel_returnbookLayout = new javax.swing.GroupLayout(staff_panel_returnbook);
        staff_panel_returnbook.setLayout(staff_panel_returnbookLayout);
        staff_panel_returnbookLayout.setHorizontalGroup(
            staff_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, staff_panel_returnbookLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(staff_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(staff_rent_search1)
                    .addComponent(staff_return_jscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        staff_panel_returnbookLayout.setVerticalGroup(
            staff_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_panel_returnbookLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(staff_rent_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staff_return_jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        staff_multipanel.add(staff_panel_returnbook, "card4");

        staff_panel_user.setBackground(new java.awt.Color(255, 204, 0));
        staff_panel_user.setPreferredSize(new java.awt.Dimension(1000, 700));

        staff_user_search.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        staff_user_search.setText("Search");
        staff_user_search.setMinimumSize(new java.awt.Dimension(6, 42));
        staff_user_search.setPreferredSize(new java.awt.Dimension(6, 42));
        staff_user_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staff_user_searchMouseClicked(evt);
            }
        });
        staff_user_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                staff_user_searchKeyReleased(evt);
            }
        });

        staff_user_order.setModel(new javax.swing.table.DefaultTableModel(
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
        staff_user_scroll.setViewportView(staff_user_order);
        if (staff_user_order.getColumnModel().getColumnCount() > 0) {
            staff_user_order.getColumnModel().getColumn(0).setMinWidth(50);
            staff_user_order.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        javax.swing.GroupLayout staff_panel_userLayout = new javax.swing.GroupLayout(staff_panel_user);
        staff_panel_user.setLayout(staff_panel_userLayout);
        staff_panel_userLayout.setHorizontalGroup(
            staff_panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_panel_userLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(staff_panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(staff_user_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(staff_user_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        staff_panel_userLayout.setVerticalGroup(
            staff_panel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_panel_userLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(staff_user_search, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staff_user_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        staff_multipanel.add(staff_panel_user, "card5");

        javax.swing.GroupLayout staff_mainLayout = new javax.swing.GroupLayout(staff_main);
        staff_main.setLayout(staff_mainLayout);
        staff_mainLayout.setHorizontalGroup(
            staff_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_mainLayout.createSequentialGroup()
                .addComponent(staff_sidetab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(staff_multipanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        staff_mainLayout.setVerticalGroup(
            staff_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_sidetab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_multipanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staff_main, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseclicked
        if (evt.getSource()== staff_tab_welcome){
            staff_panel_welcome.setVisible(true);
            staff_panel_library.setVisible(false);
            staff_panel_rent.setVisible(false);
            staff_panel_returnbook.setVisible(false);
            staff_panel_user.setVisible(false);
        }
        if (evt.getSource()== staff_tab_library){
            staff_panel_welcome.setVisible(false);
            staff_panel_library.setVisible(true);
            staff_panel_rent.setVisible(false);
            staff_panel_returnbook.setVisible(false);
            staff_panel_user.setVisible(false);
        }
        if (evt.getSource()== staff_tab_rent){
            staff_panel_welcome.setVisible(false);
            staff_panel_library.setVisible(false);
            staff_panel_rent.setVisible(true);
            staff_panel_returnbook.setVisible(false);
            staff_panel_user.setVisible(false);
        }
        if (evt.getSource()== staff_tab_return){
            staff_panel_welcome.setVisible(false);
            staff_panel_library.setVisible(false);
            staff_panel_rent.setVisible(false);
            staff_panel_returnbook.setVisible(true);
            staff_panel_user.setVisible(false);
        }
        if (evt.getSource()== staff_tab_user){
            staff_panel_welcome.setVisible(false);
            staff_panel_library.setVisible(false);
            staff_panel_rent.setVisible(false);
            staff_panel_returnbook.setVisible(false);
            staff_panel_user.setVisible(true);
        }
        if (evt.getSource()== staff_tab_logout){
            new login().setVisible(true);
            this.dispose();
        }
  
    }//GEN-LAST:event_mouseclicked

    private void mouseentered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseentered
        if (evt.getSource()== staff_tab_library){
            staff_tab_library.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource()== staff_tab_rent){
            staff_tab_rent.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource()== staff_tab_return){
            staff_tab_return.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource()== staff_tab_user){
            staff_tab_user.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource()== staff_tab_logout){
            staff_tab_logout.setBackground(new Color(255, 204, 0));
        }
    }//GEN-LAST:event_mouseentered

    private void mouseexited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseexited
        if (evt.getSource()== staff_tab_library){
            staff_tab_library.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource()== staff_tab_rent){
            staff_tab_rent.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource()== staff_tab_return){
            staff_tab_return.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource()== staff_tab_user){
            staff_tab_user.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource()== staff_tab_logout){
            staff_tab_logout.setBackground(new Color(255, 153, 0));
        }
    }//GEN-LAST:event_mouseexited

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2){
            num1=1; num2=1; num3=1; num4=1; num5=1; num6=1; num7=1;
            Object[][] str = shareMethod.getBookStoreData();
            int row = jTable1.getSelectedRow();
            String selectId = jTable1.getValueAt(row, 0).toString();
            for (Object[] objects : str) {
                if(objects[0].equals(selectId)){
                    staff_add_id.setText((String) objects[2]);
                    staff_add_name.setText((String) objects[3]);
                    staff_add_name1.setText((String) objects[4]);
                    staff_add_type.setText((String) objects[5]);
                    staff_add_quantity.setText((String) objects[6]);
                    staff_add_price.setText((String) objects[7]);
                    admin_staff_buttondelete1.setText("Update");
                }
            }
        }else{
            staff_add_id.setText("Book ID");
            staff_add_name.setText("Name Book");
            staff_add_name1.setText("Author");
            staff_add_type.setText("Category");
            staff_add_quantity.setText("Quantity");
            staff_add_price.setText("Price");
            admin_staff_buttondelete1.setText("Add");
            num1=0; num2=0; num3=0; num4=0; num5=0; num6=0; num7=0;
        }
            
    }//GEN-LAST:event_jTable1MouseClicked

    private void staff_add_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_add_idMouseClicked
        if(num1 == 0){
            staff_add_id.setText("");
            num1 = 1;
        }
    }//GEN-LAST:event_staff_add_idMouseClicked

    private void staff_add_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staff_add_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staff_add_nameActionPerformed

    private void staff_add_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_add_nameMouseClicked
        if(num2 == 0){
            staff_add_name.setText("");
            num2 = 1;
        }
    }//GEN-LAST:event_staff_add_nameMouseClicked

    private void staff_add_name1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_add_name1MouseClicked
        if(num3 == 0){
            staff_add_name1.setText("");
            num3 = 1;
        }
    }//GEN-LAST:event_staff_add_name1MouseClicked

    private void staff_add_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_add_typeMouseClicked
        if(num4 == 0){
            staff_add_type.setText("");
            num4 = 1;
        }
    }//GEN-LAST:event_staff_add_typeMouseClicked

    private void staff_add_quantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_add_quantityMouseClicked
        if(num5 == 0){
            staff_add_quantity.setText("");
            num5 = 1;
        }
    }//GEN-LAST:event_staff_add_quantityMouseClicked

    private void staff_add_priceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_add_priceMouseClicked
        if(num6 == 0){
            staff_add_price.setText("");
            num6 = 1;
        }
    }//GEN-LAST:event_staff_add_priceMouseClicked

    private void admin_staff_buttondelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_staff_buttondelete1ActionPerformed
        if (num7==0){
                String[] arrayOfBookStore = {staff_add_name.getText(), staff_add_name1.getText(), staff_add_id.getText(),
                                staff_add_quantity.getText(), staff_add_type.getText(), addressImage,
                                staff_add_price.getText()};
                db.insertBookStoreDataBase(arrayOfBookStore, shareMethod.findAmountOfBook() + 1);
        }
    }//GEN-LAST:event_admin_staff_buttondelete1ActionPerformed

    private void staff_add_buttonimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staff_add_buttonimageActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        // What to do with the file, e.g. display it in a TextArea
                        addressImage = file.getAbsolutePath();
            try {
                this.addressImage = shareMethod.copiedImage(addressImage);
            } catch (Exception ex) {
                Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
            }
                        System.out.println(file.getAbsolutePath());
                } else {
                        System.out.println("File access cancelled by user.");
                }
                ImageIcon Image = new ImageIcon(addressImage);
                Image newImage = Image.getImage().getScaledInstance(80, 90, 0);
                Icon addIcon = new ImageIcon(newImage);
                staff_add_image.setIcon(addIcon);
    }//GEN-LAST:event_staff_add_buttonimageActionPerformed

    private void staff_library_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_library_searchMouseClicked
        // TODO add your handling code here:
        staff_library_search.setText("");
    }//GEN-LAST:event_staff_library_searchMouseClicked

    private void staff_library_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staff_library_searchKeyReleased
        String str = staff_library_search.getText();
        filterOrder(str.toUpperCase());
    }//GEN-LAST:event_staff_library_searchKeyReleased

    private void staff_rent_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_rent_searchMouseClicked
        // TODO add your handling code here:
        staff_rent_search.setText("");
    }//GEN-LAST:event_staff_rent_searchMouseClicked

    private void staff_rent_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staff_rent_searchKeyReleased
        // TODO add your handling code here:
        String str = staff_rent_search.getText();
        filterRent(str.toUpperCase());
    }//GEN-LAST:event_staff_rent_searchKeyReleased

    private void staff_rent_search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_rent_search1MouseClicked
        // TODO add your handling code here:
        staff_rent_search1.setText("");
    }//GEN-LAST:event_staff_rent_search1MouseClicked

    private void staff_rent_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staff_rent_search1KeyReleased
        // TODO add your handling code here:
        String str = staff_rent_search1.getText();
        filterReturn(str.toUpperCase());
    }//GEN-LAST:event_staff_rent_search1KeyReleased

    private void staff_user_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staff_user_searchMouseClicked
        // TODO add your handling code here:
        staff_user_search.setText("");
    }//GEN-LAST:event_staff_user_searchMouseClicked

    private void staff_user_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staff_user_searchKeyReleased
        // TODO add your handling code here:
        String str = staff_user_search.getText();
        filterUser(str.toUpperCase());
    }//GEN-LAST:event_staff_user_searchKeyReleased

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
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin_staff_buttondelete;
    private javax.swing.JButton admin_staff_buttondelete1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton staff_add_buttonimage;
    private javax.swing.JTextField staff_add_id;
    private javax.swing.JLabel staff_add_image;
    private javax.swing.JTextField staff_add_name;
    private javax.swing.JTextField staff_add_name1;
    private javax.swing.JTextField staff_add_price;
    private javax.swing.JTextField staff_add_quantity;
    private javax.swing.JTextField staff_add_type;
    private javax.swing.JPanel staff_liblrary_add;
    private javax.swing.JTextField staff_library_search;
    private javax.swing.JPanel staff_main;
    private javax.swing.JPanel staff_multipanel;
    private javax.swing.JPanel staff_panel_library;
    private javax.swing.JPanel staff_panel_rent;
    private javax.swing.JPanel staff_panel_returnbook;
    private javax.swing.JPanel staff_panel_user;
    private javax.swing.JPanel staff_panel_welcome;
    private javax.swing.JScrollPane staff_rent_jscroll;
    private javax.swing.JTable staff_rent_order;
    private javax.swing.JTextField staff_rent_search;
    private javax.swing.JTextField staff_rent_search1;
    private javax.swing.JScrollPane staff_return_jscroll;
    private javax.swing.JTable staff_return_order;
    private javax.swing.JPanel staff_sidetab;
    private javax.swing.JPanel staff_tab_empty1;
    private javax.swing.JPanel staff_tab_empty2;
    private javax.swing.JPanel staff_tab_library;
    private javax.swing.JLabel staff_tab_librarylogo;
    private javax.swing.JPanel staff_tab_logout;
    private javax.swing.JLabel staff_tab_logoutlogo;
    private javax.swing.JPanel staff_tab_rent;
    private javax.swing.JLabel staff_tab_rentlogo;
    private javax.swing.JPanel staff_tab_return;
    private javax.swing.JLabel staff_tab_returnlogo;
    private javax.swing.JPanel staff_tab_user;
    private javax.swing.JLabel staff_tab_userlogo;
    private javax.swing.JPanel staff_tab_welcome;
    private javax.swing.JLabel staff_tab_welcomelogo;
    private javax.swing.JLabel staff_txt_library;
    private javax.swing.JLabel staff_txt_logout;
    private javax.swing.JLabel staff_txt_rent;
    private javax.swing.JLabel staff_txt_return;
    private javax.swing.JLabel staff_txt_user;
    private javax.swing.JTable staff_user_order;
    private javax.swing.JScrollPane staff_user_scroll;
    private javax.swing.JTextField staff_user_search;
    private javax.swing.JLabel staff_welcome_bookrent;
    private javax.swing.JLabel staff_welcome_logo;
    private javax.swing.JLabel staff_welcome_name;
    private javax.swing.JLabel staff_welcome_welcome;
    // End of variables declaration//GEN-END:variables
}
