import by.javaguru.UserDto;
import by.javaguru.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter writer = resp.getWriter()) {
            resp.setContentType("text/html");
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            long userId = 0;

            try {
                userId = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                writer.println(String.format("\"Wrong user id: '%s'.\"", req.getParameter("id")));
                return;
            }

            Optional<UserDto> user = userService.getUser(userId);

            if (user.isPresent()) {
                writer.println(String.format("<p>User with id %d: %s</p>",
                                userId, user.get().getName()));
            } else {
                writer.println(String.format("User with id %d not found", userId));
            }

            writer.println("<a href=\"/users\">Back to users list</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        long userId;

        try {
            userId = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException | NullPointerException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong user id format");
            return;
        }

        String userNewName = req.getParameter("name");

        if (userNewName == null || userNewName.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User name is empty");
            return;
        }

        if ( userService.getUser(userId).isPresent()) {
            userService.changeUserNameById(userId, userNewName);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format("User with id '%d' not found", userId));
        }
    }
}
