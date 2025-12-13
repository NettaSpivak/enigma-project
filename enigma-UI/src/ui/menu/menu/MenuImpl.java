package ui.menu.menu;

public class MenuImpl implements Menu {

    @Override
    public void displayMenu(MenuState menuState) {
        System.out.println("===== ENIGMA MENU =====");
        switch (menuState) {
            case XML_NOT_LOADED:
                System.out.println("(1) Load XML");
                System.out.println("(2) Exit");
                break;

            case XML_LOADED:
                System.out.println("(1) Load XML");
                System.out.println("(2) Show machine data");
                System.out.println("(3) Set code manually");
                System.out.println("(4) Set code automatically");
                System.out.println("(5) Show statistics");
                System.out.println("(6) Exit");
                break;
            case CODE_SET:
                System.out.println("(1) Load XML");
                System.out.println("(2) Show machine data");
                System.out.println("(3) Set code manually");
                System.out.println("(4) Set code automatically");
                System.out.println("(5) Process message");
                System.out.println("(6) Reset code");
                System.out.println("(7) Show statistics");
                System.out.println("(8) Exit");
                break;
        }
        System.out.print("Your choice: ");
    }
}
