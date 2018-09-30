package by.epam.university.controller.command;

import by.epam.university.controller.command.exception.CommandParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * A factory for creating Commands that process the request and form the
 * response.
 */
public class CommandFactory {

    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    private static final CommandFactory INSTANCE = new CommandFactory();

    private static Map<String, Command> COMMANDS;

    static {
        try {
            COMMANDS = new CommandParser().getCommands();
        } catch (CommandParserException e) {
            LOGGER.fatal(
                    "Fatal error during initializing commands from json file.", e);
            throw new RuntimeException(e);
        }
    }

    /** The default name of the command. */
    private static final String DEFAULT_COMMAND_NAME = "unknownCommand";

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    public Command getCommand(HttpServletRequest request) {
        String commandName = request.getPathInfo();
        Command command = COMMANDS.get(commandName);
        if (command == null) {
            command = COMMANDS.get(DEFAULT_COMMAND_NAME);
        }
        return command;
    }
}
