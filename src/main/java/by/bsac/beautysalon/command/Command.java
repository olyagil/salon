package by.bsac.beautysalon.command;

import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    private String name;
    protected ServiceFactory serviceFactory;

    public void setFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public abstract Forward execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws DataBaseException;

}
