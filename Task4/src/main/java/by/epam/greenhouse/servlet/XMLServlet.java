package by.epam.greenhouse.servlet;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.parser.Builder;
import by.epam.greenhouse.parser.dom.DOMBuilder;
import by.epam.greenhouse.parser.sax.SAXBuilder;
import by.epam.greenhouse.parser.stax.StAXBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Servlet class using xml-file.
 */
@WebServlet(name = "XMLServlet", urlPatterns = {"/XMLServlet"})
public class XMLServlet extends HttpServlet {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(XMLServlet.class);

    /**
     * Max threshold size for disk file item.
     */
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    /**
     * Max upload file size.
     */
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    /**
     * Constructor for this servlet.
     */
    public XMLServlet() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    protected void doPost(final HttpServletRequest request, final
    HttpServletResponse response)
            throws ServletException {
        processRequest(request, response);
    }

    /**
     * {@inheritDoc}
     */
    protected void doGet() {
        System.out.println("HUY");
    }

    /**
     * Gets xml file and outputs table based on parsed data.
     *
     * @param request is http request to server, containing uploaded file and
     *                 parser type.
     * @param response is response to client.
     * @throws ServletException is servlet exception.
     */
    private void processRequest(final HttpServletRequest request, final
    HttpServletResponse response)
            throws ServletException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            List fileList = upload.parseRequest(request);
            FileItem fileItem = (FileItem) fileList.get(0);
            FileItem selectItem = (FileItem) fileList.get(1);
            InputStream inputStream = fileItem.getInputStream();
            String parserType = selectItem.getString();

            Builder flowerBuilder = getBuilder(parserType);
            flowerBuilder.buildSet(inputStream);

            Set<Flower> flowers = flowerBuilder.getEntities();
            request.setAttribute("flowers", flowers);
            request.getRequestDispatcher("/result.jsp").forward(request,
                    response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Gets device builder by string.
     *
     * @param parserType input parser type
     * @return device builder object.
     * @throws Exception exception.
     */
    private Builder getBuilder(final String parserType) throws
            Exception {
        switch (parserType) {
            case "dom":
                LOGGER.info("DOM parser is chosen!");
                return new DOMBuilder();
            case "sax":
                LOGGER.info("SAX parser is chosen!");
                return new SAXBuilder();
            case "stax":
                LOGGER.info("StAX parser is chosen!");
                return new StAXBuilder();
            default:
                throw new Exception("Some exeption occurred!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void destroy() {
        super.destroy();
    }
}
