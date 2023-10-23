package constant;

public class Constant {
    public static enum Role {
        USER(true),
        ADMIN(false);
        private final boolean role;
        Role(boolean role) {
            this.role = role;
        }

        public boolean getRole() {
            return role;
        }
    }
    public static enum Status {
        ACTIVE(true),
        BLOCK(false);
        private final boolean status;

        private Status(boolean status) {
            this.status = status;
        }

        public boolean getStatus() {
            return status;
        }

    }
}
