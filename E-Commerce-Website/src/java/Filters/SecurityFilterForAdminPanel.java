package Filters;

import java.io.IOException;
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
public class SecurityFilterForAdminPanel implements Filter {

    private FilterConfig filterConfig = null;

    public SecurityFilterForAdminPanel() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String[] adminResourceRegister = {
            "add_product_page.jsp",
            "view_all_products_page.jsp",
            "add_product_page.jsp",
            "view_product_page.jsp",
            "orders_page.jsp",
            "order_page.jsp",
            "",
            "ProductDetailsRetrieveServlet",
            "ReadProductServlet",
            "UpdateProductServlet",
            "CreateProductServlet",
            "DeleteProductServlet",
            "ReadOrderServlet",
            "UpdateOrderServlet",
            "",
        };
        
        
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        int lastIndexOfSlash = uri.lastIndexOf('/');

        if (lastIndexOfSlash != -1) {
            String resource = uri.substring(lastIndexOfSlash + 1);
            System.out.println(resource);
            boolean isAdminRegisteredResource = false;
            for (String registeredResource : adminResourceRegister) {
                if (registeredResource.equals(resource)) {
                    isAdminRegisteredResource = true;
                }
            }
            if (isAdminRegisteredResource) {
                if (session != null && session.getAttribute("role") != null) {
                    if (session.getAttribute("role").equals("1")) {
                        chain.doFilter(request, response);
                    } else {
                        String ipAddress = request.getRemoteAddr();
                        String userId = (String) session.getAttribute("user_id");
                        String requestedResource = httpRequest.getRequestURI();
                        System.out.println("Unauthorized access attempt detected: User id : [ " + userId + " ] from IP : [ " + ipAddress + " ] attempted to access resource [ " + requestedResource + " ]. Access denied.");
                        String loginURL = httpRequest.getContextPath() + "/sign_in_page.jsp";
                        if (!httpRequest.getRequestURI().equals(loginURL)) {
                            httpResponse.sendRedirect(loginURL);
                        }
                    }
                } else {
                    String loginURL = httpRequest.getContextPath() + "/sign_in_page.jsp";
                    if (!httpRequest.getRequestURI().equals(loginURL)) {
                        httpResponse.sendRedirect(loginURL);
                    }
                }
            } else {
                chain.doFilter(request, response);
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
