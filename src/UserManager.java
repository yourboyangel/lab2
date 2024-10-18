import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class User implements Comparable<User> {
    private String userName; // User's name

    private int userAge; // User's age

    public User(String name, int age) {
        this.userName = name;
        this.userAge = age;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    @Override
    public int compareTo(User other) {
        return other.userAge - this.userAge;
    }
}

public class UserManager {

    private List<User> usersList; // List to store users

    public UserManager() {
        usersList = new ArrayList<>();
        initializeDefaultUsers(); // Populate with default users
    }

    private void initializeDefaultUsers() {
        addUser("DefaultUser", 18); // Adding a default user
    }

    public void addUser(String name, int age) {
        if(name==null || name.length()==0){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(age < 0){
            throw new IllegalArgumentException("Age cannot be negative");
        }
        usersList.add(new User(name, age)); // Add a new user to the list
    }

    public void printUserDetails() {
        for (User u : usersList) {
            System.out.println(u.getUserName() + " is " + u.getUserAge() + " years old."); // Print user details
        }
    }

    public void locateUser(String name) {
        User foundUser = null;
        for (User u : usersList) {
            if (u.getUserName().equals(name)) {
                foundUser = u;
                break; // User found, exit loop
            }
        }

        if (foundUser == null) {
            System.out.println("User not found: " + name);
        } else {
            System.out.println("Found user: " + foundUser.getUserName() + ", Age: " + foundUser.getUserAge());
        }
    }

    public void changeUserAge(String name, int newAge) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name for update");
            return;
        }

        for (User u : usersList) {
            if (u.getUserName().equals(name)) {
                u = new User(u.getUserName(), newAge); // Create a new user with updated age
                System.out.println("Updated age for " + name + " to " + newAge);
                return;
            }
        }
        System.out.println("User not found for update: " + name);
    }

    public void printUsersAverageAge() {
        if(usersList.size()==0){
            return;
        }
        double total = 0;
        for (User user : this.usersList) {
            total += user.getUserAge();
        }
        System.out.println("User mean age: " + total / usersList.size());
    }

    public void printoldestUser() {
        User oldestUser = null;
        for (User u : usersList) {
            if (u.compareTo(oldestUser)>0){
                oldestUser = u;
            }
        }
        if(oldestUser==null){
            return;
        }
        System.out.println("Oldest user: " + oldestUser.getUserName());
    }


    public void clear(){
        this.usersList.clear();
    }

    public static void main(String[] args) {
        /// you can not make changes to main
        UserManager userManager = new UserManager();
        userManager.addUser("Alice", 25);
        userManager.addUser("Bob", -5);
        userManager.addUser(null, 30);
        userManager.addUser("Charlie", 30);
        userManager.printUserDetails();
        userManager.locateUser("Alice");
        userManager.changeUserAge("Alice", 26);
        userManager.changeUserAge("Dave", 40);
        userManager.printUserDetails();
        userManager.printoldestUser();
        userManager.printUsersAverageAge();
        userManager.clear();
        userManager.changeUserAge("Dave", 40);
        userManager.printUserDetails();
        userManager.printoldestUser();
        userManager.printUsersAverageAge();

    }
}