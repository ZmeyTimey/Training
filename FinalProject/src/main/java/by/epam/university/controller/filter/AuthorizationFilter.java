package by.epam.university.controller.filter;

import by.epam.university.controller.constant.ViewPath;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.impl.MySqlDAOFactory;
import by.epam.university.model.Role;
import by.epam.university.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Authorization filter.
 */
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        try {

            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final UserDAO userDAO = new MySqlDAOFactory()
                    .getNonTransactionalDAOManager().getUserDAO();

            final String login = httpRequest.getParameter("login");
            final String password = httpRequest.getParameter("password");

            final HttpSession session = httpRequest.getSession();

            boolean isSessionNull = nonNull(session);
//            boolean isLoginNull = nonNull(session.getAttribute("login"));
//            boolean isPasswordNull = nonNull(session.getAttribute("password"));

            boolean isLoginNull = nonNull(login);
            boolean isPasswordNull = nonNull(password);

            boolean isDataEntered
                    = isSessionNull
                    && isLoginNull
                    && isPasswordNull;
            boolean isUserExists = userDAO.isUserExists(login, password);

            if (isDataEntered) {

                User user = userDAO.getUser(login, password);
                final Role role
                        = user.getRole();

                moveToMenu(httpRequest, httpResponse, role);

            } else if (isUserExists) {

                User user = userDAO.getUser(login, password);
                final Role role
                        = user.getRole();

                httpRequest.getSession().setAttribute("password", password);
                httpRequest.getSession().setAttribute("login", login);
                httpRequest.getSession().setAttribute("role", role);

                moveToMenu(httpRequest, httpResponse, role);

            } else {

                moveToMenu(httpRequest, httpResponse, Role.UNKNOWN);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest request,
                            final HttpServletResponse response,
                            final Role role)
            throws ServletException, IOException {

        if (role.equals(Role.ADMIN)) {

            request.getRequestDispatcher(
                    ViewPath.ADMIN_MENU).forward(request, response);

        } else if (role.equals(Role.ENTRANT)) {

            request.getRequestDispatcher(
                    ViewPath.ENTRANT_MENU).forward(request, response);

        } else {

            request.getRequestDispatcher(
                    ViewPath.LOGIN_PAGE).forward(request, response);
        }
    }
}
