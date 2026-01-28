
    import dao.ProductDao;
    import dao.UserDao;
    import model.Product;
    import model.User;
    import service.AuthService;
    import util.PasswordUtil;

    import java.util.Scanner;


    public class Main {

        static Scanner scanner = new Scanner(System.in);
        static UserDao userDao = new UserDao();
        static ProductDao productDAO = new ProductDao();

        public static void main(String[] args) throws Exception {

            while (true) {
                System.out.println("=== CONNEXION ===");
                System.out.print("Username : ");
                String username = scanner.nextLine();

                System.out.print("Mot de passe : ");
                String password = scanner.nextLine();
                String hashedPassword = PasswordUtil.hashPassword(password);

                User user = AuthService.login(username, hashedPassword);

                if (user == null) {
                    System.out.println("Identifiants incorrects");
                    continue;
                }

                if (user.getRole().equals("ADMIN")) {
                    menuAdmin();
                } else {
                    menuUser(user);
                }
            }
        }

        static void menuAdmin() throws Exception {
            int choix;
            do {
                System.out.println("\n=== MENU ADMIN ===");
                System.out.println("1. Ajouter utilisateur");
                System.out.println("2. Lister utilisateurs");
                System.out.println("3. Supprimer utilisateur");
                System.out.println("0. Déconnexion");

                choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1 -> {
                        User u = new User();
                        System.out.print("Username : ");
                        u.setUsername(scanner.nextLine());
                        System.out.print("Password : ");
                        u.setPassword(scanner.nextLine());
                        System.out.print("Role (ADMIN/USER) : ");
                        u.setRole(scanner.nextLine());
                        UserDao.addUser(u);
                    }
                    case 2 -> {
                        for (User u : UserDao.getAllUsers()) {
                            System.out.println(u.getId() + " - " + u.getUsername() + " (" + u.getRole() + ")");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID utilisateur : ");
                        int id = Integer.parseInt(scanner.nextLine());
                        UserDao.deleteUser(id);
                    }
                }
            } while (choix != 0);
        }

        static void menuUser(User user) throws Exception {
            int choix;
            do {
                System.out.println("\n=== MENU UTILISATEUR ===");
                System.out.println("1. Ajouter produit");
                System.out.println("2. Lister mes produits");
                System.out.println("0. Déconnexion");

                choix = Integer.parseInt(scanner.nextLine());

                if (choix == 1) {
                    Product p = new Product();
                    System.out.print("Libelle : ");
                    p.setLibelle(scanner.nextLine());
                    System.out.print("Prix : ");
                    p.setPrix(Double.parseDouble(scanner.nextLine()));
                    System.out.print("Quantité : ");
                    p.setQuantite(Integer.parseInt(scanner.nextLine()));
                    p.setUserId(user.getId());

                    ProductDao.addProduct(p);
                }

                if (choix == 2) {
                    for (Product p : ProductDao.getProductsByUser(user.getId())) {
                        System.out.println(p.getLibelle() + " | " + p.getPrix() + " | " + p.getQuantite());
                    }
                }

            } while (choix != 0);
        }
    }


