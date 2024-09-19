package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String timezone = req.getParameter("timezone");
        if (timezone == null || timezone.isEmpty()) {
            timezone = "UTC";
        }

        LocalDateTime now;
        try {
            now = LocalDateTime.now(ZoneId.of(timezone));
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid timezone");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Current Time</h1>");
            out.println("<p>" + now.format(formatter) + " " + timezone + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
