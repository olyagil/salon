package by.bsac.salon.filter;

import by.bsac.salon.command.CommandEnum;
import by.bsac.salon.entity.enumeration.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

public class SecurityFilter implements Filter {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession(false);

            CommandEnum commandEnum = (CommandEnum) request.getAttribute(
                    "commandEnum");

            if (!guestCommand(commandEnum)) {
                if (session.getAttribute("role") != null) {
                    Role role = Role.getById((Integer) session.getAttribute("role"));
                    LOGGER.debug("ROLE: " + role);
                    if (userCommand(commandEnum)) {
                        LOGGER.debug("It's a authorized user command " + commandEnum);
                        chain.doFilter(request, response);
                    } else if (role == Role.ADMINISTRATOR
                            && adminCommand(commandEnum)) {
                        LOGGER.debug("It's a admin command " + commandEnum);
                        chain.doFilter(request, response);

                    } else if (role == Role.EMPLOYEE
                            && employeeCommand(commandEnum)) {
                        LOGGER.debug("It's a employee command " + commandEnum);
                        chain.doFilter(request, response);

                    } else if (role == Role.CLIENT
                            && clientCommand(commandEnum)) {
                        LOGGER.debug("It's a client command " + commandEnum);
                        chain.doFilter(request, response);

                    } else {
                        LOGGER.info("Trying to access forbidden recourse: "
                                + commandEnum);
                        httpResponse.sendRedirect(httpRequest.getContextPath() +
                                "/notfound.html");
                    }
                } else {
                    LOGGER.info("Trying to access forbidden recourse: "
                            + commandEnum);
                    session.setAttribute("message_forbidden", "Please, " +
                            "log in to continue this action.");
                    httpResponse.sendRedirect(httpRequest.getContextPath() +
                            "/login.html");
                }
            } else {
                LOGGER.debug("It's a guest command " + commandEnum);
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

    private boolean guestCommand(CommandEnum command) {
        EnumSet<CommandEnum> set = EnumSet.range(CommandEnum.NOTFOUND,
                CommandEnum.MAIN);
        return set.contains(command);
    }

    private boolean userCommand(CommandEnum command) {
        EnumSet<CommandEnum> set = EnumSet.range(CommandEnum.LOGOUT,
                CommandEnum.ACCOUNT_MAIN);
        return set.contains(command);
    }

    private boolean adminCommand(CommandEnum command) {
        EnumSet<CommandEnum> set = EnumSet.range(CommandEnum.CLIENT_LIST,
                CommandEnum.TALON_LIST);
        return set.contains(command);
    }

    private boolean employeeCommand(CommandEnum command) {
        EnumSet<CommandEnum> set = EnumSet.range(CommandEnum.USER_VIEW,
                CommandEnum.TALON_LIST);
        return set.contains(command);
    }

    private boolean clientCommand(CommandEnum command) {
        return EnumSet.range(CommandEnum.TALON_ADD,
                CommandEnum.TALON_LIST).contains(command);
    }

}
