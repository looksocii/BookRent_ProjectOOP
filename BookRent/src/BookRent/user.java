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
    private double total, price;
    private String id, outBook;

    //สร้าง ResultSet เก็บไว้ในตัวแปรชื่อ book
    ResultSet book = new DatabaseManagement().readBookStoreDataBase();
    ResultSet history = new DatabaseManagement().readHistoryOfUserDataBase();

    //set วัน/เดือน/ปี ปัจจุบัน
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private String currentDate = df.format(c.getTime());
    
    ImageIcon Image = new ImageIcon("C:\\Users\\Puntakarn\\Desktop\\Full\\BookRent\\src\\images\\bg.png");
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
        setShape(new RoundRectangle2D.Double(0, 0, 1200, 700, 50, 50));

        user_welcome_name.setText(ShareMethod.firstName + " " + ShareMethod.lastName);
        user_history_name.setText(ShareMethod.firstName + " " + ShareMethod.lastName);
        try {
            setTableBook();
            setTableHistory();
        } catch (IOException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            user_library_order.getColumnModel().getColumn(0).setMinWidth(30);
            user_library_order.getColumnModel().getColumn(0).setMaxWidth(40);
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
        user_panel_history = new javax.swing.JPanel();
        user_history_title = new javax.swing.JPanel();
        user_history_history = new javax.swing.JLabel();
        user_history_name = new javax.swing.JLabel();
        user_history_search = new javax.swing.JTextField();
        user_history_scroll = new javax.swing.JScrollPane();
        user_history_order = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BookRent");
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setResizable(false);

        user_main.setBackground(new java.awt.Color(255, 255, 153));
        user_main.setMaximumSize(new java.awt.Dimension(1200, 700));
        user_main.setMinimumSize(new java.awt.Dimension(1200, 700));
        user_main.setPreferredSize(new java.awt.Dimension(1200, 700));

        user_sidetab.setBackground(new java.awt.Color(255, 204, 0));
        user_sidetab.setLayout(new javax.swing.BoxLayout(user_sidetab, javax.swing.BoxLayout.PAGE_AXIS));

        user_tab_welcome.setBackground(new java.awt.Color(255, 102, 0));
        user_tab_welcome.setPreferredSize(new java.awt.Dimension(200, 140));
        user_tab_welcome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
        });

        user_tab_welcomelogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welcome.png"))); // NOI18N

        javax.swing.GroupLayout user_tab_welcomeLayout = new javax.swing.GroupLayout(user_tab_welcome);
        user_tab_welcome.setLayout(user_tab_welcomeLayout);
        user_tab_welcomeLayout.setHorizontalGroup(
            user_tab_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_tab_welcomeLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(user_tab_welcomelogo)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        user_tab_welcomeLayout.setVerticalGroup(
            user_tab_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_tab_welcomelogo, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_welcome);

        user_tab_library.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_library.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_mouseexited(evt);
            }
        });

        user_txt_library.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_library.setText("Library");

        user_tab_librarylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_librarylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/library.png"))); // NOI18N

        javax.swing.GroupLayout user_tab_libraryLayout = new javax.swing.GroupLayout(user_tab_library);
        user_tab_library.setLayout(user_tab_libraryLayout);
        user_tab_libraryLayout.setHorizontalGroup(
            user_tab_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_tab_libraryLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(user_tab_librarylogo)
                .addGap(18, 18, 18)
                .addComponent(user_txt_library)
                .addGap(23, 23, 23))
        );
        user_tab_libraryLayout.setVerticalGroup(
            user_tab_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_txt_library, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(user_tab_librarylogo, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_library);

        user_tab_rent.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_rent.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_rent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_mouseexited(evt);
            }
        });

        user_tab_rentlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_rentlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rent.png"))); // NOI18N

        user_txt_rent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_rent.setText("Rent");

        javax.swing.GroupLayout user_tab_rentLayout = new javax.swing.GroupLayout(user_tab_rent);
        user_tab_rent.setLayout(user_tab_rentLayout);
        user_tab_rentLayout.setHorizontalGroup(
            user_tab_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_tab_rentLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(user_tab_rentlogo)
                .addGap(18, 18, 18)
                .addComponent(user_txt_rent)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        user_tab_rentLayout.setVerticalGroup(
            user_tab_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_tab_rentlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(user_txt_rent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_rent);

        user_tab_return.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_return.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_mouseexited(evt);
            }
        });

        user_txt_return.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_return.setText("Return");

        user_tab_returnlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_returnlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N

        javax.swing.GroupLayout user_tab_returnLayout = new javax.swing.GroupLayout(user_tab_return);
        user_tab_return.setLayout(user_tab_returnLayout);
        user_tab_returnLayout.setHorizontalGroup(
            user_tab_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_tab_returnLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(user_tab_returnlogo)
                .addGap(18, 18, 18)
                .addComponent(user_txt_return)
                .addGap(29, 29, 29))
        );
        user_tab_returnLayout.setVerticalGroup(
            user_tab_returnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_txt_return, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(user_tab_returnlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_return);

        user_tab_history.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_history.setPreferredSize(new java.awt.Dimension(200, 80));
        user_tab_history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_mouseexited(evt);
            }
        });

        user_txt_history.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_history.setText("History");

        user_tab_historylogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_historylogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/history.png"))); // NOI18N

        javax.swing.GroupLayout user_tab_historyLayout = new javax.swing.GroupLayout(user_tab_history);
        user_tab_history.setLayout(user_tab_historyLayout);
        user_tab_historyLayout.setHorizontalGroup(
            user_tab_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_tab_historyLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(user_tab_historylogo)
                .addGap(18, 18, 18)
                .addComponent(user_txt_history)
                .addGap(24, 24, 24))
        );
        user_tab_historyLayout.setVerticalGroup(
            user_tab_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_txt_history, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(user_tab_historylogo, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_history);

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
            .addGap(0, 91, Short.MAX_VALUE)
        );

        user_sidetab.add(user_tab_empty1);

        user_tab_empty2.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_empty2.setPreferredSize(new java.awt.Dimension(200, 80));

        user_tab_logout.setBackground(new java.awt.Color(255, 153, 0));
        user_tab_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_mouseentered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_mouseexited(evt);
            }
        });

        user_txt_logout.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_txt_logout.setText("Logout");

        user_tab_logoutlogo.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_tab_logoutlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N

        javax.swing.GroupLayout user_tab_logoutLayout = new javax.swing.GroupLayout(user_tab_logout);
        user_tab_logout.setLayout(user_tab_logoutLayout);
        user_tab_logoutLayout.setHorizontalGroup(
            user_tab_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_tab_logoutLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(user_tab_logoutlogo)
                .addGap(18, 18, 18)
                .addComponent(user_txt_logout)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        user_tab_logoutLayout.setVerticalGroup(
            user_tab_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_txt_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(user_tab_logoutlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout user_tab_empty2Layout = new javax.swing.GroupLayout(user_tab_empty2);
        user_tab_empty2.setLayout(user_tab_empty2Layout);
        user_tab_empty2Layout.setHorizontalGroup(
            user_tab_empty2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_tab_empty2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(user_tab_logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        user_tab_empty2Layout.setVerticalGroup(
            user_tab_empty2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_tab_empty2Layout.createSequentialGroup()
                .addComponent(user_tab_logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        user_sidetab.add(user_tab_empty2);

        user_multipanel.setBackground(new java.awt.Color(255, 255, 153));
        user_multipanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        user_multipanel.setLayout(new java.awt.CardLayout());

        user_panel_welcome.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_welcome.setPreferredSize(new java.awt.Dimension(1000, 700));

        user_welcome_welcome.setFont(new java.awt.Font("Banaue", 1, 90)); // NOI18N
        user_welcome_welcome.setText("WELCOME");

        user_welcome_bookrent.setFont(new java.awt.Font("Banaue", 0, 54)); // NOI18N
        user_welcome_bookrent.setText("BookRent");

        user_welcome_name.setFont(new java.awt.Font("TH SarabunPSK", 0, 48)); // NOI18N
        user_welcome_name.setText("xxxxxxxxxxxxxxxxxx");

        user_welcome_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout user_panel_welcomeLayout = new javax.swing.GroupLayout(user_panel_welcome);
        user_panel_welcome.setLayout(user_panel_welcomeLayout);
        user_panel_welcomeLayout.setHorizontalGroup(
            user_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_welcomeLayout.createSequentialGroup()
                .addGroup(user_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_panel_welcomeLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(user_welcome_welcome))
                    .addGroup(user_panel_welcomeLayout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(user_welcome_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(user_welcome_bookrent))
                    .addGroup(user_panel_welcomeLayout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(user_welcome_name)))
                .addGap(322, 322, 322))
        );
        user_panel_welcomeLayout.setVerticalGroup(
            user_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_welcomeLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(user_welcome_welcome)
                .addGap(43, 43, 43)
                .addGroup(user_panel_welcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_panel_welcomeLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(user_welcome_bookrent))
                    .addComponent(user_welcome_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(user_welcome_name)
                .addContainerGap())
        );

        user_multipanel.add(user_panel_welcome, "card6");

        user_panel_library.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_library.setPreferredSize(new java.awt.Dimension(1000, 700));

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

        user_library_order.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        user_library_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "ID", "Name", "Type", "Quantity", "Price", "Status"
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

        javax.swing.GroupLayout user_panel_libraryLayout = new javax.swing.GroupLayout(user_panel_library);
        user_panel_library.setLayout(user_panel_libraryLayout);
        user_panel_libraryLayout.setHorizontalGroup(
            user_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_panel_libraryLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(user_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(user_library_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(user_library_search))
                .addGap(26, 26, 26))
        );
        user_panel_libraryLayout.setVerticalGroup(
            user_panel_libraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_panel_libraryLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(user_library_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(user_library_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        user_multipanel.add(user_panel_library, "card2");

        user_panel_rent.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_rent.setPreferredSize(new java.awt.Dimension(1000, 700));

        user_rent_bookid.setFont(new java.awt.Font("Angsana New", 1, 84)); // NOI18N
        user_rent_bookid.setText("BOOK ID");

        user_rent_search.setFont(new java.awt.Font("Angsana New", 0, 60)); // NOI18N
        user_rent_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                user_rent_searchKeyReleased(evt);
            }
        });

        user_rent_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        user_rent_namebook.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_namebook.setText("Name Book :");

        user_rent_type.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_type.setText("Author :");

        user_rent_daterent.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_daterent.setText("Date For Rent :");

        user_rent_datereturn.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_datereturn.setText("Category :");

        user_rent_price.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_price.setText("Date For Rent :");

        user_rent_bookname.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N

        user_rent_booktype.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N

        user_rent_bookdaterent.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N

        user_rent_bookprice.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N

        user_rent_button.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_rent_button.setText("Rent");
        user_rent_button.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        user_rent_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_rent_buttonActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Angsana New", 0, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        user_rent_price1.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_price1.setText("Date For Retrun : ");

        user_rent_price2.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_rent_price2.setText("Price Total :");

        user_rent_bookprice2.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N

        user_rent_bookprice3.setFont(new java.awt.Font("Angsana New", 0, 32)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Angsana New", 0, 24)); // NOI18N
        jLabel1.setText("Day");

        javax.swing.GroupLayout user_panel_rentLayout = new javax.swing.GroupLayout(user_panel_rent);
        user_panel_rent.setLayout(user_panel_rentLayout);
        user_panel_rentLayout.setHorizontalGroup(
            user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_rentLayout.createSequentialGroup()
                .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_panel_rentLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(user_rent_image, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_datereturn)
                                .addGap(18, 18, 18)
                                .addComponent(user_rent_bookdaterent))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_namebook)
                                .addGap(18, 18, 18)
                                .addComponent(user_rent_bookname))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(user_rent_button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_type)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user_rent_booktype))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_price2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(user_rent_bookprice2))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_daterent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(user_rent_bookprice3))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_price)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addGroup(user_panel_rentLayout.createSequentialGroup()
                                .addComponent(user_rent_price1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user_rent_bookprice))))
                    .addGroup(user_panel_rentLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(user_rent_bookid)
                        .addGap(40, 40, 40)
                        .addComponent(user_rent_search, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        user_panel_rentLayout.setVerticalGroup(
            user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_rentLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_rent_bookid)
                    .addComponent(user_rent_search, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_panel_rentLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(user_rent_image, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(user_panel_rentLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_rent_namebook)
                            .addComponent(user_rent_bookname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_rent_type)
                            .addComponent(user_rent_booktype))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_rent_datereturn)
                            .addComponent(user_rent_bookdaterent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_rent_daterent)
                            .addComponent(user_rent_bookprice3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_rent_price)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(8, 8, 8)
                .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_rent_price1)
                    .addComponent(user_rent_bookprice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(user_panel_rentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_rent_price2)
                    .addComponent(user_rent_bookprice2))
                .addGap(41, 41, 41)
                .addComponent(user_rent_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        user_multipanel.add(user_panel_rent, "card3");

        user_panel_returnbook.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_returnbook.setPreferredSize(new java.awt.Dimension(1000, 700));

        user_return_bookid.setFont(new java.awt.Font("Angsana New", 1, 84)); // NOI18N
        user_return_bookid.setText("BOOK ID");

        user_return_search.setFont(new java.awt.Font("Angsana New", 0, 60)); // NOI18N
        user_return_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                user_return_searchKeyReleased(evt);
            }
        });

        user_return_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        user_return_button.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N
        user_return_button.setText("Return");
        user_return_button.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        user_return_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_return_buttonActionPerformed(evt);
            }
        });

        user_return_namebook.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_namebook.setText("Name :");

        user_return_type.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_type.setText("Type :");

        user_return_daterent.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_daterent.setText("Date For Rent :");

        user_return_datereturn.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_datereturn.setText("Date For Return :");

        user_return_bookdatereturn.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N

        user_return_bookdaterent.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N

        user_return_booktype.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N

        user_return_bookname.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N

        user_return_fine.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_return_fine.setText("Fine  :");

        user_return_bookfine.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        jLabel2.setText("Category :");

        user_return_booktype1.setFont(new java.awt.Font("Angsana New", 0, 36)); // NOI18N

        javax.swing.GroupLayout user_panel_returnbookLayout = new javax.swing.GroupLayout(user_panel_returnbook);
        user_panel_returnbook.setLayout(user_panel_returnbookLayout);
        user_panel_returnbookLayout.setHorizontalGroup(
            user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(user_return_image, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                                .addComponent(user_return_daterent)
                                .addGap(18, 18, 18)
                                .addComponent(user_return_bookdaterent))
                            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                                .addComponent(user_return_datereturn)
                                .addGap(18, 18, 18)
                                .addComponent(user_return_bookdatereturn))
                            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                                .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(user_return_namebook)
                                    .addComponent(user_return_type))
                                .addGap(18, 18, 18)
                                .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(user_return_bookname)
                                    .addComponent(user_return_booktype1)))
                            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                                .addComponent(user_return_fine)
                                .addGap(18, 18, 18)
                                .addComponent(user_return_bookfine))
                            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(user_return_button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(user_return_booktype))))
                    .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(user_return_bookid)
                        .addGap(40, 40, 40)
                        .addComponent(user_return_search, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        user_panel_returnbookLayout.setVerticalGroup(
            user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_return_bookid)
                            .addComponent(user_return_search, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_return_namebook)
                            .addComponent(user_return_bookname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_return_type)
                            .addComponent(user_return_booktype1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(user_return_booktype))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_return_daterent)
                            .addComponent(user_return_bookdaterent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_return_datereturn)
                            .addComponent(user_return_bookdatereturn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(user_panel_returnbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user_return_fine)
                            .addComponent(user_return_bookfine)))
                    .addGroup(user_panel_returnbookLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(user_return_image, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(user_return_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        user_multipanel.add(user_panel_returnbook, "card4");

        user_panel_history.setBackground(new java.awt.Color(255, 204, 0));
        user_panel_history.setPreferredSize(new java.awt.Dimension(1000, 700));

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

        user_history_name.setFont(new java.awt.Font("Angsana New", 1, 36)); // NOI18N
        user_history_name.setText("xxxxxxxxxxxxxxxxxx");

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

        user_history_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Image", "ID", "Name", "Type", "Quantity", "Price", "Rent"
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

        javax.swing.GroupLayout user_panel_historyLayout = new javax.swing.GroupLayout(user_panel_history);
        user_panel_history.setLayout(user_panel_historyLayout);
        user_panel_historyLayout.setHorizontalGroup(
            user_panel_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_historyLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(user_panel_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(user_history_name)
                    .addComponent(user_history_search)
                    .addComponent(user_history_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addComponent(user_history_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        user_panel_historyLayout.setVerticalGroup(
            user_panel_historyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panel_historyLayout.createSequentialGroup()
                .addComponent(user_history_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user_history_name)
                .addGap(18, 18, 18)
                .addComponent(user_history_search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(user_history_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        user_multipanel.add(user_panel_history, "card5");

        javax.swing.GroupLayout user_mainLayout = new javax.swing.GroupLayout(user_main);
        user_main.setLayout(user_mainLayout);
        user_mainLayout.setHorizontalGroup(
            user_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_mainLayout.createSequentialGroup()
                .addComponent(user_sidetab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(user_multipanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE))
        );
        user_mainLayout.setVerticalGroup(
            user_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user_sidetab, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(user_multipanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
            .addComponent(user_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void user_mouseentered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_mouseentered
        if (evt.getSource() == user_tab_library) {
            user_tab_library.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource() == user_tab_rent) {
            user_tab_rent.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource() == user_tab_return) {
            user_tab_return.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource() == user_tab_history) {
            user_tab_history.setBackground(new Color(255, 204, 0));
        }
        if (evt.getSource() == user_tab_logout) {
            user_tab_logout.setBackground(new Color(255, 204, 0));
        }
    }//GEN-LAST:event_user_mouseentered

    private void user_mouseexited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_mouseexited
        if (evt.getSource() == user_tab_library) {
            user_tab_library.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource() == user_tab_rent) {
            user_tab_rent.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource() == user_tab_return) {
            user_tab_return.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource() == user_tab_history) {
            user_tab_history.setBackground(new Color(255, 153, 0));
        }
        if (evt.getSource() == user_tab_logout) {
            user_tab_logout.setBackground(new Color(255, 153, 0));
        }
    }//GEN-LAST:event_user_mouseexited

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
                    user_rent_bookprice2.setText(Double.toString(price) + " Baht");
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
        user_rent_bookprice2.setText(Double.toString(total) + " Baht");
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
            user_rent_image.setIcon(bgPNG);
        }
    }//GEN-LAST:event_user_rent_buttonActionPerformed

    private void user_return_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_return_searchKeyReleased
        // TODO add your handling code here:
        String str = user_return_search.getText();
        int num = 0;
        try {
            history.beforeFirst();
            while (history.next()) {
                if (history.getString(2).equals(ShareMethod.username) && history.getString(4).equals(str) && history.getString(8).equals("rented")) {
                    num = 1;
                    user_return_bookname.setText(history.getString(3));
                    book.beforeFirst();
                    while (book.next()) {
                        if (history.getString(4).equals(book.getString(4))) {
                            user_return_booktype1.setText(book.getString(3));
                            user_return_booktype.setText(book.getString(6));
                            price = Double.parseDouble(book.getString(8));
                        }
                    }
                    book.beforeFirst();
                    user_return_bookdaterent.setText(history.getString(6));
                    user_return_bookdatereturn.setText(history.getString(7));
                    if (dateDiff(currentDate, history.getString(7)) > 0) {
                        user_return_bookfine.setText("0.0 Baht");
                    } else {
                        double total = (abs(dateDiff(currentDate, history.getString(7))) * 2) * price;
                        user_return_bookfine.setText(Double.toString(total) + " Baht");
                    }
                }
            }
            history.beforeFirst();
            if (num == 0) {
                user_return_bookname.setText("");
                user_return_booktype1.setText("");
                user_return_booktype.setText("");
                user_return_bookdaterent.setText("");
                user_return_bookdatereturn.setText("");
                user_return_bookfine.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_user_return_searchKeyReleased

    private void user_return_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_return_buttonActionPerformed
        JOptionPane.showMessageDialog(null, "Return done.");
        user_return_bookname.setText("");
        user_return_booktype1.setText("");
        user_return_booktype.setText("");
        user_return_bookdaterent.setText("");
        user_return_bookdatereturn.setText("");
        user_return_bookfine.setText("");
        user_return_search.setText("");
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
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
