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
        private AuthenticationProcessor nextProcessor;

        public AuthenticationProcessor(AuthenticationProcessor next) {
            this.nextProcessor = next;
        }

        public boolean isAuthorized(AuthenticationProvider provider) {
            if (isValidated(provider)) {
                System.out.println(getMassage());
                return true;
            } else if (nextProcessor != null) {
                return nextProcessor.isAuthorized(provider);
            }
            System.out.println("Authentication failed");
            return false;
        }

        public abstract boolean isValidated(AuthenticationProvider provider);

        public abstract String getMassage();
    }

    public static class TokenAuthenticationProcessor extends AuthenticationProcessor {

        public TokenAuthenticationProcessor(AuthenticationProcessor next) {
            super(next);
        }

        @Override
        public boolean isValidated(AuthenticationProvider provider) {
            return provider.getToken().equals("token");
        }

        @Override
        public String getMassage() {
            return "Authentication completed (Token)";
        }
    }

    public static class PasswordAuthenticationProcessor extends AuthenticationProcessor {

        public PasswordAuthenticationProcessor(AuthenticationProcessor next) {
            super(next);
        }

        @Override
        public boolean isValidated(AuthenticationProvider provider) {
            return provider.getPassword().equals("password") && provider.getUsername().equals("username");
        }

        @Override
        public String getMassage() {
            return "Authentication completed (Username/Password)";
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

