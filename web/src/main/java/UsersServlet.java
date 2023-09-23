import by.javaguru.User;
import by.javaguru.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static UserService userService = UserService.genInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = resp.getWriter();

        Map<Long, User> allUsers = userService.getAllUsers();

        writer.println("<html>");
        writer.println("    <body>");
        writer.println("        <h1>Users list from database:</h2>");
        writer.println("        <table border=\"2\">");
        writer.println("            <tr>");
        writer.println("                <th>ID</th>");
        writer.println("                <th>User name</th>");
        writer.println("            </tr>");

        for (var entry : allUsers.values()) {
            writer.println("            <tr>");
            writer.println(String.format("                <td>" +
                    "<a href =\"/user?id=%d\">%d</a></td>", entry.getId(), entry.getId()));
            writer.println(String.format("                <td>%s</td>", entry.getName()));
            writer.println("            </tr>");
        }

        writer.println("        </table>");
        writer.println("    </body>");
        writer.println("</html>");
        writer.close();
    }
}
