public class ChainOfResponsibilityExample {
        public abstract static class AuthenticationProcessor {
            public AuthenticationProcessor nextProcessor;

            protected String token;
            protected String username;
            protected String password;

            public AuthenticationProcessor(String token, String username, String password) {
                this.token = token;
                this.username = username;
                this.password = password;
            }

            public abstract boolean isAuthorized();
        }

        public static class TokenAuthenticationProcessor extends AuthenticationProcessor{

            public TokenAuthenticationProcessor(String token, String username, String password){
                super(token, username, password);
            }

            @Override
            public boolean isAuthorized() {
                if (token.equals("token")){
                    System.out.println("Authentication completed (Token)");
                    return true;
                } else if (nextProcessor != null){
                    return nextProcessor.isAuthorized();
                }
                System.out.println("Authentication failed");
                return false;
            }
        }

        public static class PasswordAuthenticationProcessor extends AuthenticationProcessor{

            public PasswordAuthenticationProcessor(String token, String username, String password){
                super(token, username, password);
            }

            @Override
            public boolean isAuthorized() {
                if (password.equals("password") && username.equals("username")){
                    System.out.println("Authentication completed (Username/Password)");
                    return true;
                } else if (nextProcessor != null){
                    return nextProcessor.isAuthorized();
                }
                System.out.println("Authentication failed");
                return false;
            }
        }

    public static void main(String[] args) {
        System.out.print("The correct token - ");
        AuthenticationProcessor authenticationProcessor1 = new TokenAuthenticationProcessor("token", "asername", "password");
        authenticationProcessor1.nextProcessor = new PasswordAuthenticationProcessor("token", "asername", "password");
        authenticationProcessor1.isAuthorized();

        System.out.print("The correct username and password - ");
        AuthenticationProcessor authenticationProcessor2 = new TokenAuthenticationProcessor("taken", "username", "password");
        authenticationProcessor2.nextProcessor = new PasswordAuthenticationProcessor("taken", "username", "password");
        authenticationProcessor2.isAuthorized();

        System.out.print("All uncorrected - ");
        AuthenticationProcessor authenticationProcessor3 = new TokenAuthenticationProcessor("taken", "asername", "password");
        authenticationProcessor3.nextProcessor = new PasswordAuthenticationProcessor("taken", "asername", "password");
        authenticationProcessor3.isAuthorized();
    }

}

