package BookRent;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import static java.lang.Math.abs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class user extends javax.swing.JFrame {

    DatabaseManagement db = new DatabaseManagement();
    ShareMethod shareMethod = new ShareMethod();
    DefaultTableModel dm;

    //edited---------------------------------------------------------------------------------
    //ตัวแปรมารอรับ
    private double total, price, fine;
    private String id, outBook, var, var1;
    private int returnBook=0;

    //สร้าง ResultSet เก็บไว้ในตัวแปรชื่อ book
    ResultSet book = new DatabaseManagement().readBookStoreDataBase();
    ResultSet history = new DatabaseManagement().readHistoryOfUserDataBase();

    //set วัน/เดือน/ปี ปัจจุบัน
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private String currentDate = df.format(c.getTime());
    
    ImageIcon Image = new ImageIcon("C:\\Users\\User\\Desktop\\BookRent_ProjectOOP\\BookRent\\src\\images\\bg.png");
    Image newImage = Image.getImage().getScaledInstance(150, 220, 0);
    Icon bgPNG = new ImageIcon(newImage);
    //edited---------------------------------------------------------------------------------

    /**
     * Creates new form user
     */
    public user() {
        setUndecorated(true);
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        setShape(new RoundRectangle2D.Double(0, 0, 1200, 740, 50, 50));

        user_welcome_name.setText(ShareMethod.firstName + " " + ShareMethod.lastName);
        user_history_name.setText(ShareMethod.firstName + " " + ShareMethod.lastName);
        try {
            setTableBook();
            setTableHistory();
        } catch (IOException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String active = "user_panel_library";
    private void filterOrder(String query) {
        dm = (DefaultTableModel) user_library_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        user_library_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    private void filterHistory(String query) {
        dm = (DefaultTableModel) user_history_order.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        user_history_order.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    //edited---------------------------------------------------------------------------------
    //เมทเธทบวกวันที่เพิ่มจากวันที่ปัจจุบัน
    public String dateAdd(int day) {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        Date currentDatePlusOne = c.getTime();
        return df.format(currentDatePlusOne);
    }

    //เมทเธทหาความต่างของวันที่
    public int dateDiff(String startDate, String endDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startdate = df.parse(startDate);
            Date enddate = df.parse(endDate);
            long diff = enddate.getTime() - startdate.getTime();
            int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
            return dayDiff;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //edited---------------------------------------------------------------------------------

    public void setTableBook() throws IOException {
        user_library_order.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getBookStoreData(),
                new String[]{"No", "Image", "ID", "BookName", "Author", "Category", "Quantity",
                    "Price"}) {
            Class[] types = new Class[]{java.lang.String.class, javax.swing.Icon.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        user_library_order.setRowHeight(100);

        if (user_library_order.getColumnModel().getColumnCount() > 0) {
            user_library_order.getColumnModel().getColumn(0).setMinWidth(30); //no
            user_library_order.getColumnModel().getColumn(0).setMaxWidth(40);
            user_library_order.getColumnModel().getColumn(1).setMinWidth(90); //image
            user_library_order.getColumnModel().getColumn(1).setMaxWidth(110);
            user_library_order.getColumnModel().getColumn(2).setMinWidth(80); //id
            user_library_order.getColumnModel().getColumn(2).setMaxWidth(100);
            user_library_order.getColumnModel().getColumn(3).setMinWidth(200); //bookname
            user_library_order.getColumnModel().getColumn(3).setMaxWidth(300);
            user_library_order.getColumnModel().getColumn(4).setMinWidth(150); //author
            user_library_order.getColumnModel().getColumn(4).setMaxWidth(300);
            user_library_order.getColumnModel().getColumn(5).setMinWidth(150); //category
            user_library_order.getColumnModel().getColumn(5).setMaxWidth(300);
            user_library_order.getColumnModel().getColumn(6).setMinWidth(40); //Quantity
            user_library_order.getColumnModel().getColumn(6).setMaxWidth(60);
            user_library_order.getColumnModel().getColumn(7).setMinWidth(40); //Price
            user_library_order.getColumnModel().getColumn(7).setMaxWidth(60);
        }
    }

    public void setTableHistory() {
        user_history_order.setModel(new javax.swing.table.DefaultTableModel(shareMethod.getHistoryData(),
                new String[]{"No", "BookID", "BookName", "Author", "Category",
                    "Price", "RentTime", "ReturnTime", "Status"}) {
            Class[] types = new Class[]{java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        user_history_order.setRowHeight(100);

        if (user_history_order.getColumnModel().getColumnCount() > 0) {
            user_history_order.getColumnModel().getColumn(0).setMinWidth(30); //No
            user_history_order.getColumnModel().getColumn(0).setMaxWidth(40);
            user_history_order.getColumnModel().getColumn(1).setMinWidth(80); //BookID
            user_history_order.getColumnModel().getColumn(1).setMaxWidth(100);
            user_history_order.getColumnModel().getColumn(2).setMinWidth(200); //BookName
            user_history_order.getColumnModel().getColumn(2).setMaxWidth(300);
            user_history_order.getColumnModel().getColumn(3).setMinWidth(150); //Author
            user_history_order.getColumnModel().getColumn(3).setMaxWidth(300);
            user_history_order.getColumnModel().getColumn(4).setMinWidth(150); //Category
            user_history_order.getColumnModel().getColumn(4).setMaxWidth(300);
            user_history_order.getColumnModel().getColumn(5).setMinWidth(40); //Price
            user_history_order.getColumnModel().getColumn(5).setMaxWidth(60);
            user_history_order.getColumnModel().getColumn(6).setMinWidth(80); //RentTime
            user_history_order.getColumnModel().getColumn(6).setMaxWidth(90);
            user_history_order.getColumnModel().getColumn(7).setMinWidth(80); //ReturnTime
            user_history_order.getColumnModel().getColumn(7).setMaxWidth(90);
            user_history_order.getColumnModel().getColumn(8).setMinWidth(80); //status
            user_history_order.getColumnModel().getColumn(8).setMaxWidth(90);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        user_main = new javax.swing.JPanel();
        user_sidetab = new javax.swing.JPanel();
        user_tab_welcome = new javax.swing.JPanel();
        user_tab_welcomelogo = new javax.swing.JLabel();
        user_tab_library = new javax.swing.JPanel();
        user_txt_library = new javax.swing.JLabel();
        user_tab_librarylogo = new javax.swing.JLabel();
        user_tab_rent = new javax.swing.JPanel();
        user_tab_rentlogo = new javax.swing.JLabel();
        user_txt_rent = new javax.swing.JLabel();
        user_tab_return = new javax.swing.JPanel();
        user_txt_return = new javax.swing.JLabel();
        user_tab_returnlogo = new javax.swing.JLabel();
        user_tab_history = new javax.swing.JPanel();
        user_txt_history = new javax.swing.JLabel();
        user_tab_historylogo = new javax.swing.JLabel();
        user_tab_empty1 = new javax.swing.JPanel();
        user_tab_empty2 = new javax.swing.JPanel();
        user_tab_logout = new javax.swing.JPanel();
        user_txt_logout = new javax.swing.JLabel();
        user_tab_logoutlogo = new javax.swing.JLabel();
        user_multipanel = new javax.swing.JPanel();
        user_panel_welcome = new javax.swing.JPanel();
        user_welcome_welcome = new javax.swing.JLabel();
        user_welcome_bookrent = new javax.swing.JLabel();
        user_welcome_name = new javax.swing.JLabel();
        user_welcome_logo = new javax.swing.JLabel();
        user_panel_library = new javax.swing.JPanel();
        user_library_search = new javax.swing.JTextField();
        user_library_scroll = new javax.swing.JScrollPane();
        user_library_order = new javax.swing.JTable();
        user_panel_rent = new javax.swing.JPanel();
        user_rent_bookid = new javax.swing.JLabel();
        user_rent_search = new javax.swing.JTextField();
        user_rent_image = new javax.swing.JLabel();
        user_rent_namebook = new javax.swing.JLabel();
        user_rent_type = new javax.swing.JLabel();
        user_rent_daterent = new javax.swing.JLabel();
        user_rent_datereturn = new javax.swing.JLabel();
        user_rent_price = new javax.swing.JLabel();
        user_rent_bookname = new javax.swing.JLabel();
        user_rent_booktype = new javax.swing.JLabel();
        user_rent_bookdaterent = new javax.swing.JLabel();
        user_rent_bookprice = new javax.swing.JLabel();
        user_rent_button = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        user_rent_price1 = new javax.swing.JLabel();
        user_rent_price2 = new javax.swing.JLabel();
        user_rent_bookprice2 = new javax.swing.JLabel();
        user_rent_bookprice3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        user_rent_bookprice4 = new javax.swing.JLabel();
        user_panel_returnbook = new javax.swing.JPanel();
        user_return_bookid = new javax.swing.JLabel();
        user_return_search = new javax.swing.JTextField();
        user_return_image = new javax.swing.JLabel();
        user_return_button = new javax.swing.JButton();
        user_return_namebook = new javax.swing.JLabel();
        user_return_type = new javax.swing.JLabel();
        user_return_daterent = new javax.swing.JLabel();
        user_return_datereturn = new javax.swing.JLabel();
        user_return_bookdatereturn = new javax.swing.JLabel();
        user_return_bookdaterent = new javax.swing.JLabel();
        user_return_booktype = new javax.swing.JLabel();
        user_return_bookname = new javax.swing.JLabel();
        user_return_fine = new javax.swing.JLabel();
        user_return_bookfine = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user_return_booktype1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user_panel_history = new javax.swing.JPanel();
        user_history_title = new javax.swing.JPanel();
        user_history_history = new javax.swing.JLabel();
        user_history_name = new javax.swing.JLabel();
        user_history_search = new javax.swing.JTextField();
        user_history_scroll = new javax.swing.JScrollPane();
        user_history_order = new javax.swing.JTable();
        menubar = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BookRent");
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setResizable(false);

        user_main.setBackground(new java.awt.Color(255, 255, 153));
        user_main.setMaximumSize(new java.awt.Dimension(1200, 700));
        user_main.setMinimumSize(new java.awt.Dimension(1200, 700));
        user_main.setPreferredSize(new java.awt.Dimension(1200, 700));
        user_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_sidetab.setBackground(new java.awt.Color(255, 204, 0));
        user_sidetab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_tab_welcome.setBackground(new java.awt.Color(255, 102, 0));
        user_tab_welcome.setPreferredSize(new java.awt.Dimension(200, 140));
        user_tab_welcome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_tab_welcomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_tab_welcomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user_tab_welcomeMousePressed(evt);
            }
        });
        user_tab_welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_tab_welcomelogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welcome.png"))); // NOI18N
        user_tab_welcome.add(user_tab_welcomelogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 140));

        user_sidetab.add(user_tab_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 140));

        user_tab_library.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_library.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_tab_libraryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_tab_libraryMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user_tab_libraryMousePressed(evt);
            }
        });
        user_tab_library.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_txt_library.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_library.setText("Library");
        user_tab_library.add(user_txt_library, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        user_tab_librarylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_librarylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/library.png"))); // NOI18N
        user_tab_library.add(user_tab_librarylogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        user_sidetab.add(user_tab_library, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, 80));

        user_tab_rent.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_rent.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_rent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_tab_rentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_tab_rentMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user_tab_rentMousePressed(evt);
            }
        });
        user_tab_rent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_tab_rentlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_rentlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rent.png"))); // NOI18N
        user_tab_rent.add(user_tab_rentlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        user_txt_rent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_rent.setText("Rent");
        user_tab_rent.add(user_txt_rent, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        user_sidetab.add(user_tab_rent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, 80));

        user_tab_return.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_return.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_tab_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_tab_returnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user_tab_returnMousePressed(evt);
            }
        });
        user_tab_return.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_txt_return.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_return.setText("Return");
        user_tab_return.add(user_txt_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        user_tab_returnlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_returnlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N
        user_tab_return.add(user_tab_returnlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        user_sidetab.add(user_tab_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, 80));

        user_tab_history.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_history.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_tab_historyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_tab_historyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user_tab_historyMousePressed(evt);
            }
        });
        user_tab_history.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_txt_history.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_history.setText("History");
        user_tab_history.add(user_txt_history, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 80));

        user_tab_historylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_historylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/history.png"))); // NOI18N
        user_tab_history.add(user_tab_historylogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        user_sidetab.add(user_tab_history, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, 80));

        user_tab_empty1.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_empty1.setPreferredSize(new java.awt.Dimension(200, 80));

        javax.swing.GroupLayout user_tab_empty1Layout = new javax.swing.GroupLayout(user_tab_empty1);
        user_tab_empty1.setLayout(user_tab_empty1Layout);
        user_tab_empty1Layout.setHorizontalGroup(
            user_tab_empty1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        user_tab_empty1Layout.setVerticalGroup(
            user_tab_empty1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_empty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, -1, 80));

        user_tab_empty2.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_empty2.setPreferredSize(new java.awt.Dimension(200, 80));

        javax.swing.GroupLayout user_tab_empty2Layout = new javax.swing.GroupLayout(user_tab_empty2);
        user_tab_empty2.setLayout(user_tab_empty2Layout);
        user_tab_empty2Layout.setHorizontalGroup(
            user_tab_empty2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        user_tab_empty2Layout.setVerticalGroup(
            user_tab_empty2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_empty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, -1, 80));

        user_tab_logout.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_logout.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_tab_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_tab_logoutMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                user_tab_logoutMousePressed(evt);
            }
        });
        user_tab_logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_txt_logout.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_logout.setText("Logout");
        user_tab_logout.add(user_txt_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 80));

        user_tab_logoutlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_logoutlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        user_tab_logout.add(user_tab_logoutlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 0, -1, 80));

        user_sidetab.add(user_tab_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 200, 80));

        user_main.add(user_sidetab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, 702));

        user_multipanel.setBackground(new java.awt.Color(255, 255, 153));
        user_multipanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_multipanel.setLayout(new java.awt.CardLayout());

        user_panel_welcome.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_welcome.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_panel_welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_welcome_welcome.setFont(new java.awt.Font("Banaue", 1, 90)); // NOI18N
        user_welcome_welcome.setText("WELCOME");
        user_panel_welcome.add(user_welcome_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 110, 330, -1));

        user_welcome_bookrent.setFont(new java.awt.Font("Banaue", 0, 54)); // NOI18N
        user_welcome_bookrent.setText("BookRent");
        user_panel_welcome.add(user_welcome_bookrent, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 267, -1, -1));

        user_welcome_name.setFont(new java.awt.Font("TH SarabunPSK", 0, 48)); // NOI18N
        user_welcome_name.setText("xxxxxxxxxxxxxxxxxx");
        user_panel_welcome.add(user_welcome_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 422, -1, -1));

        user_welcome_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        user_panel_welcome.add(user_welcome_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 243, -1, -1));

        user_multipanel.add(user_panel_welcome, "card6");

        user_panel_library.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_library.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_panel_library.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_library_search.setFont(new java.awt.Font("Angsana New", 0, 30)); // NOI18N
        user_library_search.setText("Search");
        user_library_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_library_searchMouseClicked(evt);
            }
        });
        user_library_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                user_library_searchKeyReleased(evt);
            }
        });
        user_panel_library.add(user_library_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 29, 948, -1));

        user_library_order.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        user_library_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "ID", "Name", "Category", "Quantity", "Price", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        user_library_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_library_orderMouseClicked(evt);
            }
        });
        user_library_scroll.setViewportView(user_library_order);
        if (user_library_order.getColumnModel().getColumnCount() > 0) {
            user_library_order.getColumnModel().getColumn(0).setMinWidth(50);
            user_library_order.getColumnModel().getColumn(0).setMaxWidth(60);
            user_library_order.getColumnModel().getColumn(1).setMinWidth(150);
            user_library_order.getColumnModel().getColumn(1).setMaxWidth(160);
            user_library_order.getColumnModel().getColumn(2).setMinWidth(150);
            user_library_order.getColumnModel().getColumn(2).setMaxWidth(160);
            user_library_order.getColumnModel().getColumn(5).setMinWidth(80);
            user_library_order.getColumnModel().getColumn(5).setMaxWidth(90);
            user_library_order.getColumnModel().getColumn(6).setMinWidth(80);
            user_library_order.getColumnModel().getColumn(6).setMaxWidth(90);
            user_library_order.getColumnModel().getColumn(7).setMinWidth(100);
            user_library_order.getColumnModel().getColumn(7).setMaxWidth(110);
        }

        user_panel_library.add(user_library_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 84, 948, 591));

        user_multipanel.add(user_panel_library, "card2");

        user_panel_rent.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_rent.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_panel_rent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_rent_bookid.setFont(new java.awt.Font("Angsana New", 1, 84)); // NOI18N
        user_rent_bookid.setText("BOOK ID");
        user_panel_rent.add(user_rent_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 32, -1, -1));

        user_rent_search.setFont(new java.awt.Font("Angsana New", 0, 60)); // NOI18N
        user_rent_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_rent_searchMouseClicked(evt);
            }
        });
        user_rent_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                user_rent_searchKeyReleased(evt);
            }
        });
        user_panel_rent.add(user_rent_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 54, 550, 65));
        user_panel_rent.add(user_rent_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 233, 150, 210));

        user_rent_namebook.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_namebook.setText("Name Book :");
        user_panel_rent.add(user_rent_namebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 210, -1, -1));

        user_rent_type.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_type.setText("Author :");
        user_panel_rent.add(user_rent_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 264, -1, -1));

        user_rent_daterent.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_daterent.setText("Date For Rent :");
        user_panel_rent.add(user_rent_daterent, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 360, -1, -1));

        user_rent_datereturn.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_datereturn.setText("Category :");
        user_panel_rent.add(user_rent_datereturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 312, -1, -1));

        user_rent_price.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_price.setText("Date For Rent :");
        user_panel_rent.add(user_rent_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 408, -1, -1));

        user_rent_bookname.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, -1, -1));

        user_rent_booktype.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_booktype, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 264, -1, -1));

        user_rent_bookdaterent.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_bookdaterent, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 312, -1, -1));

        user_rent_bookprice.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_bookprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(567, 457, -1, -1));

        user_rent_button.setBackground(new java.awt.Color(24, 116, 205));
        user_rent_button.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_rent_button.setText("Rent");
        user_rent_button.setBorder(null);
        user_rent_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        user_rent_button.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        user_rent_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_rent_buttonActionPerformed(evt);
            }
        });
        user_panel_rent.add(user_rent_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 610, 120, 40));

        jComboBox1.setFont(new java.awt.Font("Angsana New", 0, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        user_panel_rent.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(542, 414, 59, -1));

        user_rent_price1.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_price1.setText("Date For Retrun : ");
        user_panel_rent.add(user_rent_price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 457, -1, -1));

        user_rent_price2.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_price2.setText("Price Total :");
        user_panel_rent.add(user_rent_price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 505, -1, -1));

        user_rent_bookprice2.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_bookprice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 510, -1, -1));

        user_rent_bookprice3.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_bookprice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(542, 360, -1, -1));

        jLabel1.setFont(new java.awt.Font("Angsana New", 0, 24)); // NOI18N
        jLabel1.setText("Day");
        user_panel_rent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 417, -1, -1));

        user_rent_bookprice4.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        user_panel_rent.add(user_rent_bookprice4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, -1, -1));

        user_multipanel.add(user_panel_rent, "card3");

        user_panel_returnbook.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_returnbook.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_panel_returnbook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_return_bookid.setFont(new java.awt.Font("Angsana New", 1, 84)); // NOI18N
        user_return_bookid.setText("BOOK ID");
        user_panel_returnbook.add(user_return_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 32, -1, -1));

        user_return_search.setFont(new java.awt.Font("Angsana New", 0, 60)); // NOI18N
        user_return_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_return_searchMouseClicked(evt);
            }
        });
        user_return_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                user_return_searchKeyReleased(evt);
            }
        });
        user_panel_returnbook.add(user_return_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 54, 550, 65));
        user_panel_returnbook.add(user_return_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 233, 150, 210));

        user_return_button.setBackground(new java.awt.Color(24, 116, 205));
        user_return_button.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_return_button.setText("Return");
        user_return_button.setBorder(null);
        user_return_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        user_return_button.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        user_return_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_return_buttonActionPerformed(evt);
            }
        });
        user_panel_returnbook.add(user_return_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 604, 120, 40));

        user_return_namebook.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_namebook.setText("Name :");
        user_panel_returnbook.add(user_return_namebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 210, -1, -1));

        user_return_type.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_type.setText("Author :");
        user_panel_returnbook.add(user_return_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 264, -1, -1));

        user_return_daterent.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_daterent.setText("Date For Rent :");
        user_panel_returnbook.add(user_return_daterent, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 360, -1, -1));

        user_return_datereturn.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_datereturn.setText("Date For Return :");
        user_panel_returnbook.add(user_return_datereturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 414, -1, -1));

        user_return_bookdatereturn.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_panel_returnbook.add(user_return_bookdatereturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 414, -1, -1));

        user_return_bookdaterent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_panel_returnbook.add(user_return_bookdaterent, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 360, -1, -1));

        user_return_booktype.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_panel_returnbook.add(user_return_booktype, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 312, -1, -1));

        user_return_bookname.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_panel_returnbook.add(user_return_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 210, -1, -1));

        user_return_fine.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_fine.setText("Fine  :");
        user_panel_returnbook.add(user_return_fine, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 468, -1, -1));

        user_return_bookfine.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_panel_returnbook.add(user_return_bookfine, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 463, -1, 50));

        jLabel2.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        jLabel2.setText("Category :");
        user_panel_returnbook.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 312, -1, -1));

        user_return_booktype1.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_panel_returnbook.add(user_return_booktype1, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 264, -1, -1));

        jLabel3.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 43));
        user_panel_returnbook.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 70, 40));

        user_multipanel.add(user_panel_returnbook, "card4");

        user_panel_history.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_history.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_panel_history.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_history_title.setBackground(new java.awt.Color(255, 153, 0));

        user_history_history.setFont(new java.awt.Font("Angsana New", 1, 72)); // NOI18N
        user_history_history.setForeground(new java.awt.Color(255, 255, 255));
        user_history_history.setText("History");
        user_history_history.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout user_history_titleLayout = new javax.swing.GroupLayout(user_history_title);
        user_history_title.setLayout(user_history_titleLayout);
        user_history_titleLayout.setHorizontalGroup(
            user_history_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_history_titleLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(user_history_history)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        user_history_titleLayout.setVerticalGroup(
            user_history_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_history_titleLayout.createSequentialGroup()
                .addComponent(user_history_history)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        user_panel_history.add(user_history_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        user_history_name.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_history_name.setText("xxxxxxxxxxxxxxxxxx");
        user_panel_history.add(user_history_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 97, -1, -1));

        user_history_search.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_history_search.setText("Search");
        user_history_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_history_searchMouseClicked(evt);
            }
        });
        user_history_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                user_history_searchKeyReleased(evt);
            }
        });
        user_panel_history.add(user_history_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 156, 948, 40));

        user_history_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "ID", "Name", "Category", "Quantity", "Price", "Rent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        user_history_scroll.setViewportView(user_history_order);
        if (user_history_order.getColumnModel().getColumnCount() > 0) {
            user_history_order.getColumnModel().getColumn(0).setMinWidth(50);
            user_history_order.getColumnModel().getColumn(0).setMaxWidth(60);
            user_history_order.getColumnModel().getColumn(1).setMinWidth(150);
            user_history_order.getColumnModel().getColumn(1).setMaxWidth(160);
            user_history_order.getColumnModel().getColumn(2).setMinWidth(150);
            user_history_order.getColumnModel().getColumn(2).setMaxWidth(160);
            user_history_order.getColumnModel().getColumn(5).setMinWidth(80);
            user_history_order.getColumnModel().getColumn(5).setMaxWidth(90);
            user_history_order.getColumnModel().getColumn(6).setMinWidth(80);
            user_history_order.getColumnModel().getColumn(6).setMaxWidth(90);
            user_history_order.getColumnModel().getColumn(7).setMinWidth(100);
            user_history_order.getColumnModel().getColumn(7).setMaxWidth(110);
        }

        user_panel_history.add(user_history_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 214, 948, 463));

        user_multipanel.add(user_panel_history, "card5");

        user_main.add(user_multipanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, 702));

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

        user_main.add(menubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(user_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_main, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void user_mouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_mouseclicked
        if (evt.getSource() == user_tab_welcome) {
            user_panel_welcome.setVisible(true);
            user_panel_library.setVisible(false);
            user_panel_rent.setVisible(false);
            user_panel_returnbook.setVisible(false);
            user_panel_history.setVisible(false);
        }
        if (evt.getSource() == user_tab_library) {
            user_panel_welcome.setVisible(false);
            user_panel_library.setVisible(true);
            user_panel_rent.setVisible(false);
            user_panel_returnbook.setVisible(false);
            user_panel_history.setVisible(false);
        }
        if (evt.getSource() == user_tab_rent) {
            user_panel_welcome.setVisible(false);
            user_panel_library.setVisible(false);
            user_panel_rent.setVisible(true);
            user_panel_returnbook.setVisible(false);
            user_panel_history.setVisible(false);
        }
        if (evt.getSource() == user_tab_return) {
            user_panel_welcome.setVisible(false);
            user_panel_library.setVisible(false);
            user_panel_rent.setVisible(false);
            user_panel_returnbook.setVisible(true);
            user_panel_history.setVisible(false);
        }
        if (evt.getSource() == user_tab_history) {
            user_panel_welcome.setVisible(false);
            user_panel_library.setVisible(false);
            user_panel_rent.setVisible(false);
            user_panel_returnbook.setVisible(false);
            user_panel_history.setVisible(true);
        }
        if (evt.getSource() == user_tab_logout) {
            new login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_user_mouseclicked

    private void user_library_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_library_searchKeyReleased
        String str = user_library_search.getText();
        filterOrder(str.toUpperCase());
    }//GEN-LAST:event_user_library_searchKeyReleased

    private void user_rent_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_rent_searchKeyReleased
        //edited---------------------------------------------------------------------------------
        //การค้นหาข้อมูลในตารางจาก ResultSet ที่เชื่อม DataBase ไว้ตอนเริ่มโปรแกรม
        //โดยใช้ Event KeyReleased *เมื่อมีการกรอกขอมูลลง JTextField เมทเธทนี้จะทำงาน
        String str = user_rent_search.getText();
        int num = 0;
        try {
            book.beforeFirst(); //ตั้งให้ ResultSet เริ่มที่ตัวแรกใหม่
            while (book.next()) {
                //เมื่อข้อมูลที่กรอกลงไปตรงกับข้อมูลใน ResultSet ที่ดึงมาจาก DataBase
                if (str.equals(book.getString(4))) {
                    outBook = book.getString(5);
                    id = book.getString(1);
                    num = 1;
                    ImageIcon Image = new ImageIcon(book.getString(7));
                    Image newImage = Image.getImage().getScaledInstance(150, 220, 0);
                    Icon addIcon = new ImageIcon(newImage);
                    user_rent_image.setIcon(addIcon);
                    user_rent_bookname.setText(book.getString(2));
                    user_rent_booktype.setText(book.getString(3));
                    user_rent_bookdaterent.setText(book.getString(6));
                    user_rent_bookprice3.setText(currentDate);
                    String dateOut = dateAdd(1);
                    user_rent_bookprice.setText(dateOut);
                    price = Double.parseDouble(book.getString(8));
                    user_rent_bookprice2.setText(Double.toString(price));
                    user_rent_bookprice4.setText("Baht");
                }
            }
            book.beforeFirst(); //ตั้งให้ ResultSet เริ่มที่ตัวแรกใหม่
            if (num == 0) { //เมื่อข้อมูลไม่ตรง
                user_rent_image.setIcon(bgPNG);
                user_rent_bookname.setText("");
                user_rent_booktype.setText("");
                user_rent_bookdaterent.setText("");
                user_rent_bookprice.setText("");
                user_rent_bookprice3.setText("");
                user_rent_bookprice2.setText("");
                user_rent_bookprice4.setText("");
            }
        } catch (Exception e) {
        }
        //edited---------------------------------------------------------------------------------

    }//GEN-LAST:event_user_rent_searchKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        //edited---------------------------------------------------------------------------------
        //เมื่อเลือกว่าจะยืมหนังสือกี่วันที่ jComboBox
        String dateOut = dateAdd(Integer.parseInt(jComboBox1.getSelectedItem().toString())); //คิดความต่างของวันที่โดยใช้ เมทเธท dateAdd
        user_rent_bookprice.setText(dateOut);
        total = Double.parseDouble(jComboBox1.getSelectedItem().toString()) * price; //คิดราคารวมว่ายืมกี่วันราคาทั้งหมดกี่บาท
        user_rent_bookprice2.setText(Double.toString(total));
        user_rent_bookprice4.setText("Baht");
        //edited---------------------------------------------------------------------------------

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void user_rent_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_rent_buttonActionPerformed
        if (outBook.equals("0")){
            JOptionPane.showMessageDialog(null, "Out of books.");
        }else{
            String[] str1 = {ShareMethod.username, user_rent_bookname.getText(), user_rent_search.getText(), user_rent_bookprice2.getText(), 
                        user_rent_bookprice3.getText(), user_rent_bookprice.getText(), "RENTED"};
            db.insertHistoryOfUserDataBase(str1, shareMethod.findAmountOfHistory()+1);
            db.updateAmountBookStoreDataBase(Integer.toString(shareMethod.setBookDataDecrease(user_rent_search.getText())), Integer.parseInt(id));
            history = db.readHistoryOfUserDataBase();
            JOptionPane.showMessageDialog(null, "Rent done.");
            setTableHistory();
            try {
                setTableBook();
            } catch (IOException ex) {
                Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
            }
            user_rent_search.setText("");
            user_rent_bookname.setText("");
            user_rent_booktype.setText("");
            user_rent_bookdaterent.setText("");
            user_rent_bookprice3.setText("");
            user_rent_bookprice.setText("");
            user_rent_bookprice2.setText("");
            user_rent_bookprice4.setText("");
            user_rent_image.setIcon(bgPNG);
            outBook = "0";
        }
    }//GEN-LAST:event_user_rent_buttonActionPerformed

    private void user_return_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_return_searchKeyReleased
        // TODO add your handling code here:
        String str = user_return_search.getText();
        int num = 0;
        try {
            history.beforeFirst();
            while (history.next()) {
                if (history.getString(2).equals(ShareMethod.username) && history.getString(4).equals(str) && history.getString(8).equals("RENTED")) {
                    num = 1;
                    returnBook = 0;
                    user_return_bookname.setText(history.getString(3));
                    book.beforeFirst();
                    while (book.next()) {
                        if (history.getString(4).equals(book.getString(4))) {
                                var1 = book.getString(1);
                                ImageIcon Image = new ImageIcon(book.getString(7));
                                Image newImage = Image.getImage().getScaledInstance(150, 220, 0);
                                Icon addIcon = new ImageIcon(newImage);
                                user_return_image.setIcon(addIcon);
                                user_return_booktype1.setText(book.getString(3));
                                user_return_booktype.setText(book.getString(6));
                                price = Double.parseDouble(book.getString(8));
                        }
                    }
                    book.beforeFirst();
                    var = history.getString(1);
                    fine = Double.parseDouble(history.getString(5));
                    user_return_bookdaterent.setText(history.getString(6));
                    user_return_bookdatereturn.setText(history.getString(7));
                    if (dateDiff(currentDate, history.getString(7)) > 0) {
                        user_return_bookfine.setText("0.0");
                        jLabel3.setText("Baht");
                    } else {
                        double total = (abs(dateDiff(currentDate, history.getString(7))) * 2) * price;
                        user_return_bookfine.setText(Double.toString(total));
                        jLabel3.setText("Baht");
                    }
                }
            }
            history.beforeFirst();
            if (num == 0) {
                returnBook = 1;
                user_return_bookname.setText("");
                user_return_booktype1.setText("");
                user_return_booktype.setText("");
                user_return_bookdaterent.setText("");
                user_return_bookdatereturn.setText("");
                user_return_bookfine.setText("");
                jLabel3.setText("");
                user_return_image.setIcon(bgPNG);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_user_return_searchKeyReleased

    private void user_return_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_return_buttonActionPerformed
        if(returnBook == 1){
            JOptionPane.showMessageDialog(null, "Out of books.");
        }else{
            fine += Double.parseDouble(user_return_bookfine.getText());
            String[] str1 = {ShareMethod.username, user_return_bookname.getText(), user_return_search.getText(), Double.toString(fine),
                user_return_bookdaterent.getText(), user_return_bookdatereturn.getText(), "RETURNED"};
            db.updateAmountBookStoreDataBase(Integer.toString(shareMethod.setBookDataIncrease(user_return_search.getText())), Integer.parseInt(var1));
            db.updateHistoryOfUserDataBase(str1, Integer.parseInt(var));
            JOptionPane.showMessageDialog(null, "Return done.");
            setTableHistory();
            try {
                setTableBook();
            } catch (IOException ex) {
                Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
            }
            user_return_bookname.setText("");
            user_return_booktype1.setText("");
            user_return_booktype.setText("");
            user_return_bookdaterent.setText("");
            user_return_bookdatereturn.setText("");
            user_return_bookfine.setText("");
            user_return_search.setText("");
            jLabel3.setText("");
            user_return_image.setIcon(bgPNG);
            var="";
            var1="";
        }
    }//GEN-LAST:event_user_return_buttonActionPerformed

    private void user_history_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_history_searchKeyReleased

        String str = user_history_search.getText();
        filterHistory(str.toUpperCase());
    }//GEN-LAST:event_user_history_searchKeyReleased

    private void user_library_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_library_searchMouseClicked
        // TODO add your handling code here:
        user_library_search.setText("");
    }//GEN-LAST:event_user_library_searchMouseClicked

    private void user_library_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_library_orderMouseClicked

    }//GEN-LAST:event_user_library_orderMouseClicked

    private void user_history_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_history_searchMouseClicked
        // TODO add your handling code here:
        user_history_search.setText("");
    }//GEN-LAST:event_user_history_searchMouseClicked

    private void user_rent_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_rent_searchMouseClicked
        // TODO add your handling code here:
        book = db.readBookStoreDataBase();
    }//GEN-LAST:event_user_rent_searchMouseClicked

    private void user_return_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_return_searchMouseClicked
        // TODO add your handling code here:
        history = db.readHistoryOfUserDataBase();
    }//GEN-LAST:event_user_return_searchMouseClicked

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

    private void user_tab_welcomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_welcomeMousePressed
        // TODO add your handling code here:
        active = "user_tab_welcome";
        user_tab_welcome.setBackground(new Color(255,102,0));
        user_tab_library.setBackground(new Color(255, 153, 0));
        user_tab_rent.setBackground(new Color(255, 153, 0));
        user_tab_return.setBackground(new Color(255, 153, 0));
        user_tab_history.setBackground(new Color(255, 153, 0));
        user_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_user_tab_welcomeMousePressed

    private void user_tab_libraryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_libraryMousePressed
        // TODO add your handling code here:
        active = "user_tab_library";
        user_tab_welcome.setBackground(new Color(255,102,0));
        user_tab_library.setBackground(new Color(255, 204, 0));
        user_tab_rent.setBackground(new Color(255, 153, 0));
        user_tab_return.setBackground(new Color(255, 153, 0));
        user_tab_history.setBackground(new Color(255, 153, 0));
        user_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_user_tab_libraryMousePressed

    private void user_tab_rentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_rentMousePressed
        // TODO add your handling code here:
        active = "user_tab_rent";
        user_tab_welcome.setBackground(new Color(255,102,0));
        user_tab_library.setBackground(new Color(255, 153, 0));
        user_tab_rent.setBackground(new Color(255, 204, 0));
        user_tab_return.setBackground(new Color(255, 153, 0));
        user_tab_history.setBackground(new Color(255, 153, 0));
        user_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_user_tab_rentMousePressed

    private void user_tab_returnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_returnMousePressed
        // TODO add your handling code here:
        active = "user_tab_return";
        user_tab_welcome.setBackground(new Color(255,102,0));
        user_tab_library.setBackground(new Color(255, 153, 0));
        user_tab_rent.setBackground(new Color(255, 153, 0));
        user_tab_return.setBackground(new Color(255, 204, 0));
        user_tab_history.setBackground(new Color(255, 153, 0));
        user_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_user_tab_returnMousePressed

    private void user_tab_historyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_historyMousePressed
        // TODO add your handling code here:
        active = "user_tab_history";
        user_tab_welcome.setBackground(new Color(255,102,0));
        user_tab_library.setBackground(new Color(255, 153, 0));
        user_tab_rent.setBackground(new Color(255, 153, 0));
        user_tab_return.setBackground(new Color(255, 153, 0));
        user_tab_history.setBackground(new Color(255, 204, 0));
        user_tab_logout.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_user_tab_historyMousePressed

    private void user_tab_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_logoutMousePressed
        // TODO add your handling code here:
        active = "user_tab_logout";
        user_tab_welcome.setBackground(new Color(255,102,0));
        user_tab_library.setBackground(new Color(255, 153, 0));
        user_tab_rent.setBackground(new Color(255, 153, 0));
        user_tab_return.setBackground(new Color(255, 153, 0));
        user_tab_history.setBackground(new Color(255, 153, 0));
        user_tab_logout.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_user_tab_logoutMousePressed

    private void user_tab_welcomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_welcomeMouseEntered
        // TODO add your handling code here:
        user_tab_welcome.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_user_tab_welcomeMouseEntered

    private void user_tab_welcomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_welcomeMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("user_tab_welcome")) ){
                user_tab_welcome.setBackground(new Color(255,102,0));
            }
    }//GEN-LAST:event_user_tab_welcomeMouseExited

    private void user_tab_libraryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_libraryMouseEntered
        // TODO add your handling code here:
        user_tab_library.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_user_tab_libraryMouseEntered

    private void user_tab_libraryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_libraryMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("user_tab_library")) ){
                user_tab_library.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_user_tab_libraryMouseExited

    private void user_tab_rentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_rentMouseEntered
        // TODO add your handling code here:
        user_tab_rent.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_user_tab_rentMouseEntered

    private void user_tab_rentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_rentMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("user_tab_rent")) ){
                user_tab_rent.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_user_tab_rentMouseExited

    private void user_tab_returnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_returnMouseEntered
        // TODO add your handling code here:
        user_tab_return.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_user_tab_returnMouseEntered

    private void user_tab_returnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_returnMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("user_tab_return")) ){
                user_tab_return.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_user_tab_returnMouseExited

    private void user_tab_historyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_historyMouseEntered
        // TODO add your handling code here:
        user_tab_history.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_user_tab_historyMouseEntered

    private void user_tab_historyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_historyMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("user_tab_history")) ){
                user_tab_history.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_user_tab_historyMouseExited

    private void user_tab_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_logoutMouseEntered
        // TODO add your handling code here:
        user_tab_logout.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_user_tab_logoutMouseEntered

    private void user_tab_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tab_logoutMouseExited
        // TODO add your handling code here:
        if( !(active.equalsIgnoreCase("user_tab_logout")) ){
                user_tab_logout.setBackground(new Color(255, 153, 0));
            }
    }//GEN-LAST:event_user_tab_logoutMouseExited

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menubar;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel title;
    private javax.swing.JLabel user_history_history;
    private javax.swing.JLabel user_history_name;
    private javax.swing.JTable user_history_order;
    private javax.swing.JScrollPane user_history_scroll;
    private javax.swing.JTextField user_history_search;
    private javax.swing.JPanel user_history_title;
    private javax.swing.JTable user_library_order;
    private javax.swing.JScrollPane user_library_scroll;
    private javax.swing.JTextField user_library_search;
    private javax.swing.JPanel user_main;
    private javax.swing.JPanel user_multipanel;
    private javax.swing.JPanel user_panel_history;
    private javax.swing.JPanel user_panel_library;
    private javax.swing.JPanel user_panel_rent;
    private javax.swing.JPanel user_panel_returnbook;
    private javax.swing.JPanel user_panel_welcome;
    private javax.swing.JLabel user_rent_bookdaterent;
    private javax.swing.JLabel user_rent_bookid;
    private javax.swing.JLabel user_rent_bookname;
    private javax.swing.JLabel user_rent_bookprice;
    private javax.swing.JLabel user_rent_bookprice2;
    private javax.swing.JLabel user_rent_bookprice3;
    private javax.swing.JLabel user_rent_bookprice4;
    private javax.swing.JLabel user_rent_booktype;
    private javax.swing.JButton user_rent_button;
    private javax.swing.JLabel user_rent_daterent;
    private javax.swing.JLabel user_rent_datereturn;
    private javax.swing.JLabel user_rent_image;
    private javax.swing.JLabel user_rent_namebook;
    private javax.swing.JLabel user_rent_price;
    private javax.swing.JLabel user_rent_price1;
    private javax.swing.JLabel user_rent_price2;
    private javax.swing.JTextField user_rent_search;
    private javax.swing.JLabel user_rent_type;
    private javax.swing.JLabel user_return_bookdaterent;
    private javax.swing.JLabel user_return_bookdatereturn;
    private javax.swing.JLabel user_return_bookfine;
    private javax.swing.JLabel user_return_bookid;
    private javax.swing.JLabel user_return_bookname;
    private javax.swing.JLabel user_return_booktype;
    private javax.swing.JLabel user_return_booktype1;
    private javax.swing.JButton user_return_button;
    private javax.swing.JLabel user_return_daterent;
    private javax.swing.JLabel user_return_datereturn;
    private javax.swing.JLabel user_return_fine;
    private javax.swing.JLabel user_return_image;
    private javax.swing.JLabel user_return_namebook;
    private javax.swing.JTextField user_return_search;
    private javax.swing.JLabel user_return_type;
    private javax.swing.JPanel user_sidetab;
    private javax.swing.JPanel user_tab_empty1;
    private javax.swing.JPanel user_tab_empty2;
    private javax.swing.JPanel user_tab_history;
    private javax.swing.JLabel user_tab_historylogo;
    private javax.swing.JPanel user_tab_library;
    private javax.swing.JLabel user_tab_librarylogo;
    private javax.swing.JPanel user_tab_logout;
    private javax.swing.JLabel user_tab_logoutlogo;
    private javax.swing.JPanel user_tab_rent;
    private javax.swing.JLabel user_tab_rentlogo;
    private javax.swing.JPanel user_tab_return;
    private javax.swing.JLabel user_tab_returnlogo;
    private javax.swing.JPanel user_tab_welcome;
    private javax.swing.JLabel user_tab_welcomelogo;
    private javax.swing.JLabel user_txt_history;
    private javax.swing.JLabel user_txt_library;
    private javax.swing.JLabel user_txt_logout;
    private javax.swing.JLabel user_txt_rent;
    private javax.swing.JLabel user_txt_return;
    private javax.swing.JLabel user_welcome_bookrent;
    private javax.swing.JLabel user_welcome_logo;
    private javax.swing.JLabel user_welcome_name;
    private javax.swing.JLabel user_welcome_welcome;
    // End of variables declaration//GEN-END:variables
}
