package by.epam.university.controller;

import by.epam.university.controller.command.Command;
import by.epam.university.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Gets the type of command for processing request and executes this command.
     *
     * @param request
     *            an {@link HttpServletRequest} object that contains the request the
     *            client has made of the servlet
     * @param response
     *            an {@link HttpServletResponse} object that contains the response
     *            the servlet sends to the client
     * @throws ServletException
     *             if ServletException has happened during {@code forward()}
     * @throws IOException
     *             if IOException has happened during {@code SendRedirect()} or
     *             {@code forward()}
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.getInstance().getCommand(request);
        command.execute(request, response);
    }
}
