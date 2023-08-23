package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String fullName;
    private String email;
    private String password;

    // Конструктор с двумя аргументами
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Конструктор без аргументов (для использования Lombok @NoArgsConstructor)
    public User() {
    }
}

