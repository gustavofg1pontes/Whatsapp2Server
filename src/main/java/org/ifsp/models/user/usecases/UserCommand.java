package org.ifsp.models.user.usecases;

public record UserCommand(
        String command
) {
    public static final String CREATE_USER = "create_user";
    public static final String SEND_MESSAGE = "send_message";
    // caso queira adicionar mais comandos;
}
