package by.bsac.salon.filter;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.CommandEnum;
import by.bsac.salon.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ActionFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();

            LOGGER.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length() + 1;
            int endAction = uri.indexOf('.');
            String commandName;
            if (endAction >= 0) {
                commandName = uri.substring(beginAction, endAction);
            } else {
                commandName = uri.substring(beginAction);
            }
            CommandEnum commandEnum = CommandFactory.create(commandName);//
            Command command = commandEnum.getCommand();
            LOGGER.debug("Command name: " + commandName + " with class: " + command);

            command.setName(commandName);
            httpRequest.setAttribute("commandEnum", commandEnum);

            chain.doFilter(request, response);

        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
