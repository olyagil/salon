package by.bsac.salon.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    public static CommandEnum create(String command) {
        CommandEnum commandEnum;
        try {
            commandEnum = CommandEnum.valueOf(command
                    .toUpperCase()
                    .replaceAll("/", "_"));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Such command doesn't exist: " + command);
            commandEnum = CommandEnum.NOTFOUND;
        }
        return commandEnum;
    }
}
