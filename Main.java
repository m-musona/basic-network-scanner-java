import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Define the subnet (e.g., "192.168.1")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Subnet (Example 192.168.8):");
        String subnet = sc.nextLine(); // Example 192.168.8
        sc.close();
        NetworkScanner ns = new NetworkScanner(subnet);

        // Print the list of active hosts
        System.out.println("\nActive hosts with device info in the network:");
        for (String host : ns.getActivHosts()) {
            System.out.println(host);
        }
    }
}
