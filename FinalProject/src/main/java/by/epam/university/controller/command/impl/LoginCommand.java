package by.epam.university.controller.command.impl;

import by.epam.university.controller.command.Command;
import by.epam.university.controller.constant.SessionConstants;
import by.epam.university.controller.constant.ViewPath;
import by.epam.university.controller.util.PathUtil;
import by.epam.university.model.Role;
import by.epam.university.model.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String RESULT_MESSAGE = "message";
    private static final String SUCCESSFUL = "signInSuccessful";
    private static final String FAILED = "signInFailed";

       @Override
        public void execute(HttpServletRequest request,
                HttpServletResponse response)

        throws IOException {
            String login = request.getParameter(LOGIN_PARAMETER);
            String password = request.getParameter(PASSWORD_PARAMETER);

            StringBuilder viewPath = new StringBuilder();

            try {
                UserService userService
                        = ServiceFactory.getInstance().getUserService();
                User user = userService.logIn(login, password);

                if (user != null) {

                    HttpSession session = request.getSession();
                    session.setAttribute(SessionConstants.USER_ID, user.getId());
                    session.setAttribute(SessionConstants.LOGIN, user.getLogin());
                    session.setAttribute(SessionConstants.ROLE, user.getRole());

                    viewPath.append(defineViewPath(user.getRole(), request));

                } else {
                    URI uriWithParameters
                            = PathUtil.addParametertoRefererPage(
                                    request, RESULT_MESSAGE, FAILED);
                    viewPath.append(uriWithParameters);
                }

            } catch (ValidationException e) {
                LOGGER.warn("Sending invalid data for loginning.", e);
                URI uriWithParam
                        = PathUtil.addParametertoRefererPage(
                                request, RESULT_MESSAGE, FAILED);
                viewPath.append(uriWithParam);
            } catch (ServiceException e) {
                LOGGER.error("Exception during user loginning.", e);
                viewPath.append(
                        request.getContextPath()).append(ViewPath.LOGIN_PAGE);
            }
            response.sendRedirect(viewPath.toString());
        }

        private StringBuilder defineViewPath(Role role, HttpServletRequest request) {
            StringBuilder viewPath = new StringBuilder();
            switch (role) {
                case ADMIN:
                    return viewPath
                            .append(request.getContextPath())
                            .append(ViewPath.ADMIN_MENU);
                case ENTRANT:
                    return viewPath
                            .append(request.getContextPath())
                            .append(ViewPath.ENTRANT_MENU);
                default:
                    URI uriWithParam
                            = PathUtil.addParametertoRefererPage(
                                    request, RESULT_MESSAGE, SUCCESSFUL);
                    return viewPath.append(uriWithParam);
            }
        }
    }