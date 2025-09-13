public class ChainOfResponsibilityExample {


    public static class AuthenticationProvider {
        protected String token;
        protected String username;
        protected String password;


        public AuthenticationProvider(String token, String username, String password) {
            this.token = token;
            this.username = username;
            this.password = password;
        }


        public String getToken() {
            return token;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public abstract static class AuthenticationProcessor {
        public AuthenticationProcessor nextProcessor;

        public AuthenticationProcessor(AuthenticationProcessor next) {
            this.nextProcessor = next;
        }

        public abstract boolean isAuthorized(AuthenticationProvider provider);
    }

    public static class TokenAuthenticationProcessor extends AuthenticationProcessor {

        public TokenAuthenticationProcessor(AuthenticationProcessor next) {
            super(next);
        }

        @Override
        public boolean isAuthorized(AuthenticationProvider provider) {
            if (provider.getToken().equals("token")) {
                System.out.println("Authentication completed (Token)");
                return true;
            } else if (nextProcessor != null) {
                return nextProcessor.isAuthorized(provider);
            }
            System.out.println("Authentication failed");
            return false;
        }
    }

    public static class PasswordAuthenticationProcessor extends AuthenticationProcessor {

        public PasswordAuthenticationProcessor(AuthenticationProcessor next) {
            super(next);
        }

        @Override
        public boolean isAuthorized(AuthenticationProvider provider) {
            if (provider.getPassword().equals("password") && provider.getUsername().equals("username")) {
                System.out.println("Authentication completed (Username/Password)");
                return true;
            } else if (nextProcessor != null) {
                return nextProcessor.isAuthorized(provider);
            }
            System.out.println("Authentication failed");
            return false;
        }
    }

    public static void main(String[] args) {
        AuthenticationProvider providerCorrectToken = new AuthenticationProvider("token", "asername", "password");
        AuthenticationProvider providerUncorrectedToken = new AuthenticationProvider("taken", "username", "password");
        AuthenticationProvider providerAllUncorrected = new AuthenticationProvider("taken", "asername", "password");

        AuthenticationProcessor processor = new TokenAuthenticationProcessor(new PasswordAuthenticationProcessor(null));
        processor.isAuthorized(providerCorrectToken);
        processor.isAuthorized(providerUncorrectedToken);
        processor.isAuthorized(providerAllUncorrected);
    }

}

