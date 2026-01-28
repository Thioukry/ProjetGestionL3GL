package util;

    public class ValidationUtil {

        public static void validateUser(String username, String email, String password, String role) {

            if (username == null || username.length() < 4) {
                throw new IllegalArgumentException("Username invalide (min 4 caractères)");
            }

            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Email invalide");
            }

            if (password == null || password.length() < 6) {
                throw new IllegalArgumentException("Mot de passe trop court");
            }

            if (!role.equals("ADMIN") && !role.equals("USER")) {
                throw new IllegalArgumentException("Rôle invalide");
            }
        }


        public static void validateProduct(String libelle, double prix, int quantite) {

            if (libelle == null || libelle.trim().isEmpty()) {
                throw new IllegalArgumentException("Libellé obligatoire");
            }

            if (prix <= 0) {
                throw new IllegalArgumentException("Prix invalide");
            }

            if (quantite < 0) {
                throw new IllegalArgumentException("Quantité invalide");
            }
        }
    }

