import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App extends JFrame{

    private JTabbedPane tabs;

    public static void main(String[] args) {
        new App();
    }

    public App(){
        EventQueue.invokeLater(() -> {
            init();
        });
    }



    private void init(){

        setPreferredSize(new Dimension(800, 600));
        setTitle("\"Your\" data is in a hacked database?");
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        tabs = new JTabbedPane();
        tabs.add(HTMLStyle.HTML_BEGIN+HTMLStyle.FIRST_STYLE_BEGIN+"is your email"+HTMLStyle.FIRST_STYLE_END+HTMLStyle.HTML_END, new EmailCheckerTab());
        tabs.add(HTMLStyle.HTML_BEGIN+HTMLStyle.FIRST_STYLE_BEGIN+"is your phone number"+HTMLStyle.FIRST_STYLE_END+HTMLStyle.HTML_END, new PhoneCheckerTab());
        tabs.add(HTMLStyle.HTML_BEGIN+HTMLStyle.FIRST_STYLE_BEGIN+"is your password"+HTMLStyle.FIRST_STYLE_END+HTMLStyle.HTML_END, new PasswordCheckerTab());
        setContentPane(tabs);
        setVisible(true);
    }

    private class EmailCheckerTab extends JPanel{
        public EmailCheckerTab(){
            setBackground(new Color(25, 118, 210));
            setLayout(null);
            JLabel h1 = new JLabel(HTMLStyle.HTML_BEGIN+"<h1 style='text-align:center;'>in a hacked db?</h1>"+HTMLStyle.HTML_END);
            h1.setBounds(250, 30, 400, 40);
            JTextField tf = new JTextField();
            tf.setOpaque(true);
            tf.setBounds(100, 250, 500, 40);
            tf.setText("Example@gmail.com");
            tf.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 18));
            JButton button = new JButton(HTMLStyle.HTML_BEGIN+"<h1>Send</h1>"+HTMLStyle.HTML_END);
            button.setBounds(600, 250, 100, 40);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tf.setText("NOT IMPLEMENTED YET");
                }
            });
            button.setFocusable(false);
            JLabel result = new JLabel();
            result.setText("asdasd");
            result.setFont(new Font("PT Sans Narrow", Font.BOLD, 18));
            result.setVisible(false);
            result.setBounds(100, 300, 500, 40);
            add(h1);
            add(tf);
            add(button);
            add(result);
        }
    }

    private class PhoneCheckerTab extends JPanel{
        public PhoneCheckerTab(){
            setBackground(new Color(25, 118, 210));
            setLayout(null);
            JLabel h1 = new JLabel(HTMLStyle.HTML_BEGIN+"<h1 style='text-align:center;'>in a hacked db?</h1>"+HTMLStyle.HTML_END);
            h1.setBounds(250, 30, 400, 40);
            JTextField tf = new JTextField();
            tf.setOpaque(true);
            tf.setBounds(100, 250, 500, 40);
            tf.setText("06123456789");
            tf.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 18));
            JButton button = new JButton(HTMLStyle.HTML_BEGIN+"<h1>Send</h1>"+HTMLStyle.HTML_END);
            button.setBounds(600, 250, 100, 40);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tf.setText("NOT IMPLEMENTED YET");
                }
            });
            button.setFocusable(false);
            JLabel result = new JLabel();
            result.setText("asdasd");
            result.setFont(new Font("PT Sans Narrow", Font.BOLD, 18));
            result.setVisible(false);
            result.setBounds(100, 300, 500, 40);
            add(h1);
            add(tf);
            add(button);
            add(result);
        }
    }

    private class PasswordCheckerTab extends JPanel{

        private String sha1Pwd;

        public PasswordCheckerTab(){
            setBackground(new Color(25, 118, 210));
            setLayout(null);
            JLabel h1 = new JLabel(HTMLStyle.HTML_BEGIN+"<h1 style='text-align:center;'>in a hacked db?</h1>"+HTMLStyle.HTML_END);
            h1.setBounds(250, 30, 400, 40);
            JTextField tf = new JTextField();
            tf.setOpaque(true);
            tf.setBounds(100, 250, 500, 40);
            tf.setText("Password123");
            tf.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 18));
            JLabel result = new JLabel();
            JButton button = new JButton(HTMLStyle.HTML_BEGIN+"<h1>Send</h1>"+HTMLStyle.HTML_END);
            button.setBounds(600, 250, 100, 40);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        sha1Pwd = sha1(tf.getText());

                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    }
                    String shaPwd = sha1Pwd.substring(0, 5);
                    String remaindHash= sha1Pwd.substring(5);
                    int found = 0;
                    try {
                        found = SendToUrl.passwordEquals(shaPwd, remaindHash);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    result.setText(" This password has been seen "+found+" times before");
                    result.setVisible(true);

                }
            });
            button.setFocusable(false);
            result.setText("asdasd");
            result.setFont(new Font("PT Sans Narrow", Font.BOLD, 18));
            result.setVisible(false);
            result.setBounds(100, 300, 500, 40);
            add(h1);
            add(tf);
            add(button);
            add(result);
        }

        private String sha1(String input) throws NoSuchAlgorithmException {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
    }
}
