package by.bsac.salon.controller;

import by.bsac.salon.dao.connection.ConnectionPool;
import by.bsac.salon.service.ServiceFactory;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.CommandEnum;
import by.bsac.salon.command.Forward;
import by.bsac.salon.dao.mysql.DaoFactory;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        CommandEnum commandEnum = (CommandEnum) request.getAttribute("commandEnum");
        Command command = commandEnum.getCommand();
        LOGGER.debug("Command which would be executed: " + command.getName());
        ServiceFactory factory = getFactory();
        try {
            command.setFactory(factory);
            Forward forward = command.execute(request, response);
            factory.close();

            if (forward != null) {
                String redirectedUri = request.getContextPath() + forward.getPage();
                LOGGER.debug("Request is redirected to URI: " + redirectedUri);
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage = "/jsp/" + commandEnum.getName() + ".jsp";
                LOGGER.debug("Request is forwarded to JSP: " + jspPage);
                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
            }
        } catch (DataBaseException e) {
            LOGGER.error("It is impossible to process request", e);
            request.setAttribute("error", "Ошибка обработки данных");
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }

    }

    private ServiceFactory getFactory() {
        return new ServiceFactory(new DaoFactory());
    }

    @Override
    public void init() {
        try {
            LOGGER.trace("Initializing application.");
            ConnectionPool.getInstance().init();
        } catch (DataBaseException e) {
            LOGGER.error("Can't initialize application.");
            destroy();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        LOGGER.debug("This is a get-method.");
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        LOGGER.debug("This is a post-method.");
        processRequest(req, resp);
    }
}
