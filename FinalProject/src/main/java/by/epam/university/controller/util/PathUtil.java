package by.epam.university.controller.util;

import by.epam.university.controller.command.Command;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Defines static methods for working with the path to the view page.
 */
public class PathUtil {

    private static final Logger LOGGER = LogManager.getLogger(Command.class);

    private static final String REFERER_HEADER_NAME = "referer";

    private PathUtil() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static String getRefererPage(HttpServletRequest request) {
        return request.getHeader(REFERER_HEADER_NAME);
    }

    public static URI addParameterToUri(String uri,
                                        String paramName,
                                        String paramValue) {
        try {
            URI uriWithParameter
                    = new URIBuilder(uri).setParameter(paramName, paramValue).build();
            return uriWithParameter;
        } catch (URISyntaxException e) {
            LOGGER.error("Exception during parsing uri string.", e);
            throw new RuntimeException(e);
        }
    }

    public static URI addParametertoRefererPage(
            HttpServletRequest request,
            String paramName,
            String paramValue) {
        return addParameterToUri(getRefererPage(request), paramName, paramValue);
    }

}
