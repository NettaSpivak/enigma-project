package ui;

import dto.MessageDto;
import enigmaManager.EnigmaManager;
import ui.menu.actions.SetCodeManual;
import ui.menu.actions.ShowHistoryAndStatistics;
import ui.menu.actions.ShowMachineData;
import ui.menu.menu.Menu;
import ui.menu.menu.MenuImpl;
import ui.menu.menu.MenuState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnigmaManager enigmaManager = new EnigmaManager();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new MenuImpl();
        MenuState menuState = MenuState.XML_NOT_LOADED;

        System.out.println("Welcome to the Enigma Machine Simulator!");
        boolean exit = false;

        while (!exit){
            menu.displayMenu(menuState);
            String choice = scanner.nextLine().trim();
            try {
                switch (menuState) {
                    case XML_NOT_LOADED -> {
                        switch (choice) {
                            case "1":
                                System.out.print("Enter XML file path: ");
                                String path = scanner.nextLine();
                                enigmaManager.loadXml(path);

                                menuState = MenuState.XML_LOADED;
                                System.out.println("XML loaded successfully.");
                                break;

                            case "2":
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    case XML_LOADED -> {
                        switch (choice) {
                            case "1":
                                System.out.print("Enter XML file path: ");
                                String path = scanner.nextLine();
                                enigmaManager.loadXml(path);

                                menuState = MenuState.XML_LOADED;
                                System.out.println("XML loaded successfully.");
                                break;

                            case "2":
                                System.out.println(ShowMachineData.showMachineData(enigmaManager.getMachineData()));
                                break;

                            case "3":
                                System.out.print("Enter rotors: ");
                                String rotors = scanner.nextLine();
                                System.out.print("Enter rotors positions: ");
                                String rotorsPositions = scanner.nextLine();
                                System.out.print("Enter reflector: ");
                                String reflector = scanner.nextLine();
                                enigmaManager.setCodeManual(SetCodeManual.setCodeMenual(rotors, rotorsPositions, reflector));
                                System.out.println("Manual code set successfully.");
                                menuState = MenuState.CODE_SET;
                                break;

                            case "4":
                                enigmaManager.setCodeAutomatic();
                                System.out.println("Automatic code set successfully.");
                                menuState = MenuState.CODE_SET;
                                break;
                            case "5":
                                System.out.println(ShowHistoryAndStatistics.showHistoryAndStatistics(enigmaManager.historyAndStatistics()));
                                break;

                            case "6":
                                exit = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    case CODE_SET -> {
                        switch (choice) {
                            case "1":
                                System.out.print("Enter XML file path: ");
                                String path = scanner.nextLine();
                                enigmaManager.loadXml(path);

                                menuState = MenuState.XML_LOADED;
                                System.out.println("XML loaded successfully.");
                                break;

                            case "2":
                                System.out.println(ShowMachineData.showMachineData(enigmaManager.getMachineData()));
                                break;

                            case "3":
                                System.out.print("Enter rotors: ");
                                String rotors = scanner.nextLine();
                                System.out.print("Enter rotors positions: ");
                                String rotorsPositions = scanner.nextLine();
                                System.out.print("Enter reflector: ");
                                String reflector = scanner.nextLine();
                                enigmaManager.setCodeManual(SetCodeManual.setCodeMenual(rotors, rotorsPositions, reflector));
                                System.out.println("Manual code set successfully.");
                                menuState = MenuState.CODE_SET;
                                break;

                            case "4":
                                enigmaManager.setCodeAutomatic();
                                System.out.println("Automatic code set successfully.");
                                menuState = MenuState.CODE_SET;
                                break;

                            case "5":
                                System.out.print("Enter message to process: ");
                                String message = scanner.nextLine();
                                MessageDto processedMessageDto = enigmaManager.processMessage(new MessageDto(message));
                                System.out.println("Processed Message: " + processedMessageDto.getMessage());
                                break;

                            case "6":
                                enigmaManager.resetCode();
                                System.out.println("Code reset successfully.");
                                break;

                            case "7":
                                System.out.println(ShowHistoryAndStatistics.showHistoryAndStatistics(enigmaManager.historyAndStatistics()));
                                break;

                            case "8":
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
