package filter;

public enum Message {
    WELCOME("Welcome to the Unix-ish command line.\n"),
    NEWCOMMAND("> "),
    GOODBYE("Thank you for using the Unix-ish command line. Goodbye!\n"),
    FILE_NOT_FOUND("At least one of the files in the command [%s] was not found.\n"),
    //System.out.print(Message.FILE_NOT_FOUND.with_parameter(command));
    DIRECTORY_NOT_FOUND("The directory specified by the command [%s] was not found.\n"),
    //System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(command));
    COMMAND_NOT_FOUND("The command [%s] was not recognized.\n"),
    //System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(command));
    REQUIRES_INPUT("The command [%s] requires input.\n"),
    //System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
    CANNOT_HAVE_OUTPUT("The command [%s] cannot have an output.\n"),
    //System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(command));
    REQUIRES_PARAMETER("The command [%s] requires parameter(s).\n"),
    //System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
    INVALID_PARAMETER("The parameter for command [%s] is invalid.\n"),
    //System.out.print(Message.INVALID_PARAMETER.with_parameter(command));
    CANNOT_HAVE_INPUT("The command [%s] cannot have an input.\n")
    //System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
    ;
    private final String message;
    
    private Message(String message){
        this.message=message;
    }
    public String toString(){
        return this.message;
    }
    public String with_parameter(String parameter){
        if(this==WELCOME || this==NEWCOMMAND || this == GOODBYE){
            return this.toString();
        }
        return String.format(this.message, parameter.trim());
    }
    
}
