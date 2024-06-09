package example.com.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import example.com.entities.User;
import example.com.service.UserService;

@Route("register")
@AnonymousAllowed
public class RegisterationView extends FormLayout {
    private UserService userService = new UserService();
    private BCryptPasswordEncoder passwordEncoder;

    private final TextField email = new TextField("Email");
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final PasswordField password = new PasswordField("Password");
    private final Button registerButton = new Button("Register", event -> register());


    @Autowired
    public RegisterationView(UserService userService, BCryptPasswordEncoder passwordEncoder) {
      this.userService = userService;
      this.passwordEncoder = passwordEncoder;
      add(email, firstName, lastName, password, registerButton);
    }


	private void register() {
    if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
      String hashedPassword = passwordEncoder.encode(password.getValue());
      User user = new User(email.getValue(), firstName.getValue(), lastName.getValue(), hashedPassword);
      userService.register(user);
      getUI().ifPresent(ui -> ui.navigate("/"));
    } else {
      Notification.show("Please enter all fields.");
    }
	}
}

