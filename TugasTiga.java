package tugas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TugasTiga {
    // Data globalll
    // Buat datanya -> counter panjang data dibuat variable (auto inc)
    
    //data customer
    static Integer[] customerId = new Integer[50];
    static String[] customerFirstName = new String[50];
    static String[] customerLastName = new String[50];
    static String[] customerTelephone = new String[50];
    static String[] customerAddress = new String[50];
    static Integer customerCount = 0;

    //data vehicle
    static String[] vehicleId = new String[50];
    static String[] vehicleType = new String[50];
    static double[] vehiclePrice = new double[50];
    static Integer vehicleCount = 0;

    //data purchase
    static Integer[] purchaseId = new Integer[50];
    static Integer[] purchaseCustomerId = new Integer[50];
    static String[] purchaseDate = new String[50];
    static double[] purchaseTotalPrice = new double[50];
    static String[] purchasePaymentMethod = new String[50];
    static Integer purchaseCount = 0;

    //data purchase detail
    static Integer[] detailPurchaseId = new Integer[100];
    static Double[] detailAfterTaxPrice = new Double[100];
    static String[] detailVehicleId = new String[100];
    static Integer detailCount = 0;

    public static void main(String[] args) {
        System.out.println("///////////////////////////////Welcome!///////////////////////////////");
        
        //buat masuk2in data awal
        startProgram();
        //displayRegister();
        chooseMenu();
        //displayAddPurchase();
        //displayVehicle();
        //displayInvoiceDetail(1);
        //displayInvoice();
    }

    public static void startProgram() {
        //customer
        String message;
        
        addCustomer("Jane", "Doe", "08123456789", "2894 Ferrell Street");
        addCustomer("betty", "lange", "08456781923", "2590 Flower Street");

        //vehicle
        // addVehicle(String id, String type, double price)
        addVehicle("1-72393282", "vario", 19000000);
        addVehicle("2-77239325", "hrv", 300000000);
        addVehicle("3-47239323", "ertiga", 200000000);
        addVehicle("4-47333281", "pajero", 500000000);
        addVehicle("5-47723930", "avanza", 100000000);
        addVehicle("6-47723932", "fuso", 700000000);
        addVehicle("7-47239281", "scoopy", 12000000);

        //purchase
        //addPurchase(int customerTelephone, String date, String paymentMethod)
        addPurchase("08123456789", "24-11-17","debit");
        addPurchase("08123456789", "24-11-20", "debit");
        addPurchase("08456781923", "24-11-28", "debit");

        //detail purchase
        addDetail("1-72393282", 1);
        addDetail("2-77239325", 2);
        addDetail("5-47723930", 3);        

        //System.out.println("message");
        //addVehicle(1, "SUV", 2023, 350000000);
        //addVehicle(2, "Sedan", 2022, 250000000);
    }

    public static void chooseMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n"+"-----     Menu     -----");

        Integer menu;
        do {
            System.out.println("Pilih menu (masukkan angka):");
            System.out.println("1. Registrasi");
            System.out.println("2. Pembelian");
            System.out.println("3. Invoice");
            System.out.println("0. Keluar");
            System.out.println("Pilih: ");
            menu = scanner.nextInt();
        } while ((menu != 0) && (menu != 1) && (menu != 2) && (menu != 3)); 

        switch (menu) {
            case 1: displayRegister();
                break;
            case 2: displayAddPurchase();
                break;
            case 3: displayInvoice();
                break;
            default: System.out.println("////////////////////////////Program selesai///////////////////////////");
                break;
        }
    }

// Registrasi
    public static void displayRegister(){
        System.out.println("\n" + "-----     Registrasi     -----");
        Scanner custScanner = new Scanner(System.in);

        String[] atributCustomer = {"Nama Depan", "Nama Belakang", "Nomor Telepon", "Alamat"};

        String[] inputCustomer = new String[4];
        for (Integer i = 0; i < 4; i++) {
            System.out.println("Masukkan " + atributCustomer[i] + ": " );
            inputCustomer[i] = custScanner.nextLine();
        } 
        
        int hasil = checkCustomerData(inputCustomer[2]);
        if(checkCustomerData(inputCustomer[2]) == -2){
            String message;
            message = addCustomer(inputCustomer[0], inputCustomer[1], inputCustomer[2], inputCustomer[3]);
            System.out.println(message);
        }
        else{
            System.out.println("Anda sudah pernah melakukan registrasi"); 
        }
        chooseMenu();
    }

    public static Integer checkCustomerData(String inputCustomer){
        Integer x = -2;

        for (Integer i = 0; i < customerCount; i++) {
            if(customerTelephone[i].equals(inputCustomer)){
                return customerId[i];
            }
            else{
                x = -2;
            }
        }
        return x;
    }

    public static String addCustomer(String firstName, String lastName, String telephone, String address){
        String messageSuccess = "Data customer berhasil ditambahkan";

        customerId[customerCount] = customerCount+1;
        customerFirstName[customerCount] = firstName;
        customerLastName[customerCount] = lastName;
        customerTelephone[customerCount] = telephone;
        customerAddress[customerCount] = address;
        customerCount++;

        return messageSuccess;
    }

// vehicle    
    public static void addVehicle(String id, String type, double price) {
        vehicleId[vehicleCount] = id;
        vehicleType[vehicleCount] = type;
        vehiclePrice[vehicleCount] = price;
        vehicleCount++;
    }

//hapus vehicle
    public static void deleteVehicle(String id) {
        for (int i = 0; i < vehicleCount; i++) {
            if(id.equals(vehicleId[i])){
                vehicleId[i] = "-2";
                vehicleType[i] = "-2";
                vehiclePrice[i] = -2;
            }
        }
    }

// add vehicle
    public static void displayVehicle(){
        System.out.println("-----     Produk     -----");

        
        for (int i = 0; i < vehicleCount; i++) {
            if(!vehicleId[i].equals("-2")){
                System.out.println("Vehicle ID: " + vehicleId[i] +
                               ", Type: " + vehicleType[i] +
                               ", Price: " + "Rp" +String.format("%.2f", vehiclePrice[i])) ;
            }
        }
    }

    public static void displayAddPurchase(){
        System.out.println("\n" + "-----     Tambah Pembelian     -----");

        Scanner purchaseScanner = new Scanner(System.in);

        System.out.println("Masukkan No Telephone: " );
        String inputPurchaseCustomerTelephone = purchaseScanner.nextLine();

        // System.out.println("Masukkan Tanggal (yy-mm-dd): " );
        // String inputPurchaseDate = purchaseScanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDate now = LocalDate.now(); 
        String inputPurchaseDate = now.format(formatter); 

        System.out.println("Masukkan Payment Method(Debit/Credit card): " );
        String inputPurchasePaymentMethod = purchaseScanner.nextLine();
        
        String message = addPurchase(inputPurchaseCustomerTelephone, inputPurchaseDate, inputPurchasePaymentMethod);
        System.out.println(message);

        // ngelooping purchase detail
        Integer num;
        do { 
            System.out.println("\nKetik 1 untuk menambahkan produk dan Ketik 0 untuk checkout:" );
            num = purchaseScanner.nextInt();
            if(num == 1){
                displayAddDetail(purchaseCount);
            }
            //display invoice
        } while (num != 0);

        displayInvoiceDetail(purchaseCount);
        chooseMenu();
    }


// pembelian
    public static String addPurchase(String inputPurchaseCustomerTelephone, String inputPurchaseDate, String inputPurchasePaymentMethod){
        String message;

        //dapetin id customer

        Integer idCustomer = checkCustomerData(inputPurchaseCustomerTelephone);

        if(idCustomer == -2){
            message = "Silahkan registrasi terlebih dahulu";
        }else{
            // id array nya dikurang 1
            purchaseId[purchaseCount] = purchaseCount + 1;
            purchaseCustomerId[purchaseCount] = idCustomer;
            purchaseDate[purchaseCount] = inputPurchaseDate;
            // purchaseTotalPrice[purchaseCount] = 0.0;
            purchasePaymentMethod[purchaseCount] = inputPurchasePaymentMethod;
            purchaseCount++;
            message = "Data purchase berhasil ditambahkan";
        }
        return message;
    }

    public static void displayAddDetail(Integer inputPurchaseId){
        Scanner detailScanner = new Scanner(System.in);
        displayVehicle();

        System.out.println("Masukkan ID Vehicle yang ingin dibeli: " );
        String inputVehicleId = detailScanner.nextLine();
        
        String message = addDetail(inputVehicleId, inputPurchaseId);
        System.out.println(message);
    }

    public static String addDetail(String inputVehicleId, Integer inputPurchaseId){
        String message = "Tidak ada data purchase";
        Double afterTax;
        
        //untuk dapet id array kurangi 1
        detailPurchaseId[detailCount] = inputPurchaseId;
        detailVehicleId[detailCount] = inputVehicleId;
        afterTax = countTax(inputVehicleId);
        if(afterTax == -2){
            return "Kendaraan tidak tersedia";
        }
        else{
            detailAfterTaxPrice[detailCount] = countTax(inputVehicleId);
            //delete kendaraan
            deleteVehicle(inputVehicleId);
            //displayVehicle();
        }
        // update total price juga
        purchaseTotalPrice[inputPurchaseId-1] = countTotalPrice(inputPurchaseId);
        detailCount++;
        message = "Data detail berhasil ditambahkan";      
            
        return message;
    }

    public static Double countTax(String inputVehicleId) {
        Double after_tax_price = 0.0;
        //get tax
        //pake looping -> idvehicle
        for (int i = 0; i < vehicleCount; i++) {
            //System.out.println(inputVehicleId + "==" + vehicleId[i]);
            if(inputVehicleId.equals(vehicleId[i])){
                after_tax_price = vehiclePrice[i] * 1.1;
                //System.out.println(after_tax_price);
                return after_tax_price;
            }
            else{
                after_tax_price = -2.0;
            }
        }

        return after_tax_price;
    }

    public static Double countTotalPrice(Integer inputPurchaseId) {
        Double total_price = purchaseTotalPrice[inputPurchaseId-1];
    
        total_price += detailAfterTaxPrice[detailCount];
        //System.out.println(total_price);
        return total_price;
    }

    public static void displayInvoice(){
        System.out.println("\n-----     Data Pembelian     -----");
        Scanner invoiceScanner = new Scanner(System.in);
        // String message = "Error";
        System.out.println("Purchase ID \t| Customer ID \t| Date \t\t| Payment Method \t| Total Price");
        //System.out.println("\t"+(i+1) +"\t| "+ detailVehicleId[i] + "\t| "+ "Rp" +String.format("%.2f", detailAfterTaxPrice[i]) + "\t");
        
        for (int i = 0; i < purchaseCount; i++) {
            System.out.println((i+1) +
                                "\t\t| "+ purchaseCustomerId[i] + 
                                "\t\t| "+ purchaseDate[i]+
                                "\t| "+ purchasePaymentMethod[i]+
                                "\t\t\t| "+ "Rp" +String.format("%.2f", detailAfterTaxPrice[i]))
                                ;
        }

        int menuInvoice;
        do {
            System.out.println("Pilih menu (masukkan angka):");
            System.out.println("1. Detail invoice");
            System.out.println("0. Kembali");
            System.out.println("Pilih: ");
            menuInvoice = invoiceScanner.nextInt();
            invoiceScanner.nextLine();
        } while ((menuInvoice != 0) && (menuInvoice != 1)); 

        switch (menuInvoice) {
            case 1: 
                System.out.println("Masukkan Id Purchase untuk Melihat Invoice:");
                Integer inputInvoiceTransaksi = invoiceScanner.nextInt();
                invoiceScanner.nextLine();
                for (int i = 0; i < purchaseCount; i++) {
                    if(inputInvoiceTransaksi == purchaseId[i]){    
                        displayInvoiceDetail(inputInvoiceTransaksi);
                        break;
                    }
                    System.out.println("Id Purchase Tidak Ditemukan");
                }
                break;
            default: chooseMenu();
                break;
        }
    }  

    public static void displayInvoiceDetail(Integer inputInvoiceTransaksi) {
        Integer idCustomer = purchaseCustomerId[inputInvoiceTransaksi-1];
        System.out.println("\n-----     Invoice Purchase ID " + inputInvoiceTransaksi + "     -----");
        System.out.println("Tanggal: " + purchaseDate[inputInvoiceTransaksi-1]);
        System.out.println("\nCustomer ID: " + purchaseCustomerId[inputInvoiceTransaksi-1]);
        System.out.println("\nCustomer name: " + customerFirstName[idCustomer-1] + " " + customerLastName[idCustomer-1]);
        System.out.println("\nDetail Barang: ");
        System.out.println("\tNo \t| Vehicle ID \t| Harga  ");

        for (int i = 0; i < detailCount; i++) {
            if(inputInvoiceTransaksi == detailPurchaseId[i]){
                //header
                System.out.println("\t"+(i+1) +"\t| "+ detailVehicleId[i] + "\t| "+ "Rp" +String.format("%.2f", detailAfterTaxPrice[i]) + "\t");
            }
        }
        System.out.println("\nTotal Harga: " + String.format("%.2f", purchaseTotalPrice[inputInvoiceTransaksi-1]));
        chooseMenu();
    }
}

