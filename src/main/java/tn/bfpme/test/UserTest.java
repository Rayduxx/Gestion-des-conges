package tn.bfpme.test;

import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.time.LocalDate;

public class UserTest {
    public static void main(String[] args) {
        Connection cnx = MyDataBase.getInstance().getCnx();
        ServiceUtilisateur serviceUser = new ServiceUtilisateur(cnx);

        // Create a new user with all necessary fields
        LocalDate creationDate = LocalDate.now();
        User user = new User(0, "John", "Doe", "john.doe@example.com", "password", "image.png", creationDate, 1, 0);
        user.setSoldeAnnuel(0.0); // Set initial solde values
        user.setSoldeMaladie(0);
        user.setSoldeExceptionnel(0);
        user.setSoldeMaternite(0);

        // Add the user to the database
        serviceUser.addUser2(user);

        // Fetch the user from the database to ensure it was added correctly
        User fetchedUser = serviceUser.getUserById(user.getIdUser());
        if (fetchedUser != null) {
            System.out.println("Initial Solde Annuel: " + fetchedUser.getSoldeAnnuel());
        } else {
            System.out.println("User not found in the database.");
            return;
        }

        // Simulate a month passing
        LocalDate newDate = creationDate.plusMonths(1);
        fetchedUser.setCreationDate(newDate);

        // Recalculate solde
        serviceUser.recalculateSolde(fetchedUser);
        serviceUser.updateUser(fetchedUser);

        // Fetch the updated user
        User updatedUser = serviceUser.getUserById(fetchedUser.getIdUser());
        System.out.println("Updated Solde Annuel after 1 month: " + updatedUser.getSoldeAnnuel());

        // Simulate taking 5 days of annual leave
        serviceUser.updateSoldeAnnuel(updatedUser.getIdUser(), updatedUser.getSoldeAnnuel() - 5);

        // Fetch the updated user after leave
        User updatedUserAfterLeave = serviceUser.getUserById(fetchedUser.getIdUser());
        System.out.println("Updated Solde Annuel after taking 5 days leave: " + updatedUserAfterLeave.getSoldeAnnuel());
    }
}
