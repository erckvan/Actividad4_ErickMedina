import java.io.*;
import java.util.*;


public class AddressBook {
    private Map<String, String> contacts;


    public AddressBook() {
        contacts = new HashMap<>();
    }

    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                contacts.put(parts[0], parts[1]);
            }
            System.out.println("Contactos cargados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al cargar los contactos: " + e.getMessage());
        }
    }

    public void save(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            System.out.println("Contactos guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los contactos: " + e.getMessage());
        }
    }


    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name);
        System.out.println("Contacto creado: " + number + " : " + name);
    }

    public void delete(String number) {
        if (contacts.containsKey(number)) {
            contacts.remove(number);
            System.out.println("Contacto eliminado: " + number);
        } else {
            System.out.println("No se encontró el contacto con el número: " + number);
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.load("contacts.txt");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar y salir");
            System.out.print("Seleccione una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    System.out.print("Ingrese el número de teléfono: ");
                    String number = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    addressBook.create(number, name);
                    break;
                case 3:
                    System.out.print("Ingrese el número de teléfono a eliminar: ");
                    String numToDelete = scanner.nextLine();
                    addressBook.delete(numToDelete);
                    break;
                case 4:
                    addressBook.save("C:\\Users\\jaivg\\IdeaProjects\\Actividad4_ErickMedina\\contacts.txt");
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (choice != 4);
        scanner.close();
    }
}
