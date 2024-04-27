package Filters;

import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class CheckAdmin implements Filter {

    private FilterConfig filterConfig = null;

    public CheckAdmin() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("user_id") != null) {

            DBConnectionManager cob = new DBConnectionManager();
            Connection connection = cob.getDBConnection();
            PreparedStatement statement = null;
            String role = null;

            try {
                statement = connection.prepareStatement("select role from user where user_id = ?");
                statement.setString(1, (String) session.getAttribute("user_id"));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    role = resultSet.getString("role");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        cob.closeDBConnection();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (role != null) {
                if (!role.equals("1")) {
                    String ipAddress = request.getRemoteAddr();
                    String userId = (String) session.getAttribute("user_id");
                    String resource = httpRequest.getRequestURI();
                    System.out.println("Unauthorized access attempt detected: User id : [ "+userId+" ] from IP : [ "+ipAddress+" ] attempted to access resource [ "+resource+" ]. Access denied.");
                    String loginURL = httpRequest.getContextPath() + "/sign_in_page.jsp";
                    if (!httpRequest.getRequestURI().equals(loginURL)) {
                        httpResponse.sendRedirect(loginURL);
                    }
                } else {
                    chain.doFilter(request, response);
                }
            }

        } else {
            System.out.println("Not logged");
            String loginURL = httpRequest.getContextPath() + "/sign_in_page.jsp";
            if (!httpRequest.getRequestURI().equals(loginURL)) {
                httpResponse.sendRedirect(loginURL);
            }
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CheckAdmin()");
        }
        StringBuilder sb = new StringBuilder("CheckAdmin(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
