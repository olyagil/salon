package by.bsac.beautysalon.command;

import by.bsac.beautysalon.command.account.AccountEditCommand;
import by.bsac.beautysalon.command.account.AccountSaveAvatarCommand;
import by.bsac.beautysalon.command.account.AccountSaveCommand;
import by.bsac.beautysalon.command.admin.*;
import by.bsac.beautysalon.command.common.LanguageCommand;
import by.bsac.beautysalon.command.common.MainCommand;
import by.bsac.beautysalon.command.common.SignUpCommand;
import by.bsac.beautysalon.command.employee.TalonAddCommand;
import by.bsac.beautysalon.command.employee.TalonSaveCommand;
import by.bsac.beautysalon.command.guest.EmployeesCommand;
import by.bsac.beautysalon.command.guest.ServicesCommand;
import by.bsac.beautysalon.command.account.AccountMainCommand;
import by.bsac.beautysalon.command.account.AccountSavePasswordCommand;
import by.bsac.beautysalon.command.common.LoginCommand;
import by.bsac.beautysalon.command.common.LogoutCommand;
import by.bsac.beautysalon.command.common.NotFoundCommand;
import by.bsac.beautysalon.command.employee.TalonDeleteCommand;
import by.bsac.beautysalon.command.employee.TalonEditCommand;
import by.bsac.beautysalon.command.employee.TalonListCommand;

public enum CommandEnum {
    NOTFOUND("notfound", new NotFoundCommand()),

    //GUEST'S COMMANDS
    LOGIN("login", new LoginCommand()),
    SIGNUP("signup", new SignUpCommand()),
    LANGUAGE("language", new LanguageCommand()),

    SERVICES("services", new ServicesCommand()),
    EMPLOYEES("employees", new EmployeesCommand()),
    MAIN("main", new MainCommand()),
    LOGOUT("logout", new LogoutCommand()),

    //ACCOUNT'S COMMANDS
    ACCOUNT_SAVE_AVATAR("account/save/avatar", new AccountSaveAvatarCommand()),
    ACCOUNT_SAVE_INFO("account/save/info", new AccountSaveCommand()),
    ACCOUNT_SAVE_PASSWORD("account/save/password",
            new AccountSavePasswordCommand()),
    ACCOUNT_EDIT_INFO("account/edit/info", new AccountEditCommand()),
    ACCOUNT_EDIT_PASSWORD("account/edit/password", new AccountEditCommand()),
    ACCOUNT_MAIN("account/main", new AccountMainCommand()),

    //ADMIN'S COMMANDS
    CLIENT_LIST("client/list", new ClientListCommand()),

    EMPLOYEE_LIST("employee/list", new EmployeeListCommand()),
    EMPLOYEE_EDIT("employee/edit", new EmployeeEditCommand()),
    EMPLOYEE_SAVE("employee/save", new EmployeeSaveCommand()),
    EMPLOYEE_ADD("employee/add", new EmployeeAddCommand()),

    //SERVICE'S COMMANDS
    SERVICE_LIST("service/list", new ServiceListCommand()),
    SERVICE_EDIT("service/edit", new ServiceEditCommand()),
    SERVICE_SAVE("service/save", new ServiceSaveCommand()),
    SERVICE_DELETE("service/delete", new ServiceDeleteCommand()),

    //ADMIN'S COMMAND
    USER_DELETE("user/delete", new UserDeleteCommand()),
    USER_VIEW("user/view", new UserViewCommand()),

    //COMMANDS WITH TALONS FOR EMPLOYEE

    TALON_EDIT("talon/edit", new TalonEditCommand()),
    TALON_SAVE("talon/save", new TalonSaveCommand()),
    TALON_DELETE("talon/delete", new TalonDeleteCommand()),
    TALON_ADD("talon/add", new TalonAddCommand()),
    TALON_LIST("talon/list", new TalonListCommand());


    private final Command command;
    private final String name;

    CommandEnum(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
