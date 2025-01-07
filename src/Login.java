import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

public class Login extends JFrame{
    private JPanel panel;
    private JTextField userField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private JLabel emailLabel;
    private JTextField emailField;
    private JCheckBox checkBox;

    private Pattern emailRegexPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public Login() {
        setSize(750, 750);
        setContentPane(panel);
        setVisible(true);
        loginButton.setEnabled(false);

        loginButton.addActionListener(_ -> {
            if(new String(passwordField.getPassword()).equals("CorrectPassword3")){
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Successful Login!");
            } else{
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Incorrect Password!");
            }
        });

        userField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(userField.getText().length() <= 5){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Your username should be more than 5 characters");
                    userField.requestFocus();
                } else{
                    messageLabel.setText(" ");
                }
            }
        });

        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(emailRegexPattern.matcher(emailField.getText()).matches()){
                    messageLabel.setText(" ");
                } else{
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Your email is not valid!");
                    emailField.requestFocus();
                }
            }
        });

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(passwordField.getPassword());

                if(password.length() <= 8){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Your password must be more than 8 characters!");
                    passwordField.requestFocus();
                } else if(password.toLowerCase().equals(password)){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Your password must contain at least one capital letter!");
                    passwordField.requestFocus();
                } else if(!containsNumber(passwordField.getPassword())){
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Your password must contain at least one number!");
                    passwordField.requestFocus();
                }
            }
        });

        checkBox.addItemListener(_ -> loginButton.setEnabled(checkBox.isSelected()));
    }

    private boolean containsNumber(char[] a){
        for(char b : a){
            if(b >= '1' && b <= '9')
                return true;
        }

        return false;
    }
}
