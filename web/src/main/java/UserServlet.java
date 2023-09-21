import by.javaguru.User;
import by.javaguru.UserDto;
import by.javaguru.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        userService.changeUserNameById(5, "Trofim"); // 2.3 - Изменить имя пользователя по его Id
        Map<Long, User> allUsers = userService.getAllUsers(); // 2.1 - Получить список всех пользователей

        long key = 12;
        Optional<UserDto> user = userService.getUser(key); // 2.2 Вернуть информацию о пользователе по его Id
        String userInfo = user.map(u -> u.getName()).orElse("User not found");

        writer.println("<html>");
        writer.println("    <body>");
        writer.println("        <table border=\"2\">");
        writer.println("            <tr>");
        writer.println("                <th>ID</th>");
        writer.println("                <th>User name</th>");
        writer.println("            </tr>");

        for (var entry : allUsers.values()) {
            writer.println("            <tr>");
            writer.println(String.format("                <td>%s</td>", entry.getId()));
            writer.println(String.format("                <td>%s</td>", entry.getName()));
            writer.println("            </tr>");
        }

        writer.println("        </table>");
        writer.println(String.format("        <p>ID: '%d. User info: %s</p>", key, userInfo));
        writer.println("    </body>");
        writer.println("</html>");
        writer.close();
    }
}
