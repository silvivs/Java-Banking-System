    import java.util.Scanner;

    class Account{
        private int accountID;
        private String accountName;
        private double accountBalance = 0.0;

        public void setAccountID(int number){
            accountID = number;
        }
        public int getAccountID(){
            return accountID;
        }
        public void setAccountName(String name){
            accountName = name;
        }
        public String getAccountName(){
            return accountName;
        }
        public void setAccountBalance(double balance){
            accountBalance = balance;
        }
        public double getAccountBalance(){
            return accountBalance;
        }

        public void deposit(double value){
            accountBalance += value;
        }

        public void withdraw(double value){
            accountBalance -= value;
        }
    }

    class Agency{
        private String agencyName;
        private int agencyNumber;
        private int LEN = 10, index = 0; 
        private Account[] accounts = new Account[LEN];

        public void setAgencyName(String name){
            agencyName = name;
        }
        public String getAgencyName(){
            return agencyName;
        }
        public void setAgencyNumber(int number){
            agencyNumber = number;
        }
        public int getAgencyNumber(){
            return agencyNumber;
        }
        public int getIndex(){
            return index;
        }
        public int getLEN(){
            return LEN;
        }

        public boolean checkInput(int input){
            for(int i = 0 ; i < index ; i++){
                if(accounts[i].getAccountID() == input) {
                    return true;
                }
            }
            return false;
        }

        public void reorderAccounts(int positionDeleted){
            for(int i = positionDeleted ; i < index - 1 ; i++){
                if(accounts[i + 1] != null){
                    accounts[i] = accounts[i + 1];
                }
            }
            accounts[index - 1] = null;
        }

        public boolean checkNull(){
            return index == 0;
        }

        public String searchNameAccount(int number){
            String accountName = null;
            for(Account object : accounts){
                if(object.getAccountID() == number){
                    accountName = object.getAccountName();
                    break;
                }
            }
            return accountName;
        }

        public double searchBalance(int number){
            double balance = 0;
            for(Account object : accounts){
                if(object.getAccountID() == number){
                    balance = object.getAccountBalance();
                    break;
                }
            }
            return balance;
        }

        public void updateAccount(int op, int number, double value, int recipient){
            if(op == 1){
                for(Account object : accounts){
                    if(object.getAccountID() == number){
                        object.deposit(value);
                        break;
                    }
                }
                System.out.println("Operacao concluida\n");
            }else if(op == 2){
                for(Account object : accounts){
                    if(object.getAccountID() == number){
                        object.withdraw(value);
                        break;
                    }
                }
                System.out.println("Operacao concluida\n");
            }else if(op == 3){
                for(Account object : accounts){
                    if(object.getAccountID() == recipient){
                        object.deposit(value);
                        break;
                    }
                }
                for(Account object : accounts){
                    if(object.getAccountID() == number){
                        object.withdraw(value);
                        break;
                    }
                }
                System.out.println("Operacao concluida\n");
            }
            
        }

        public void createAccount(String name, int number){
            if(checkInput(number)){
                System.out.println("Numero ja existe\n");
                return;
            }
            accounts[index] = new Account();
            accounts[index].setAccountName(name);
            accounts[index].setAccountID(number);
            System.out.println("Operacao concluida\n");
            index++;
        }

        public void deleteAccount(int number){
            int positionDeleted = -1;
            for(int i = 0 ; i < index ; i++){
                if(accounts[i].getAccountID() == number){
                    accounts[i] = null;
                    System.out.println("Operacao concluida\n");
                    positionDeleted = i;
                }
            }
            reorderAccounts(positionDeleted);
            index--;
        }

        public void listAccounts(){
            System.out.println("----CONTAS----");
            for(int i = 0 ; i < accounts.length ; i++){
                if(accounts[i] != null){
                    System.out.printf("%d. Nome: %s - Numero: %d\n", i+1, accounts[i].getAccountName(), accounts[i].getAccountID());
                }
            }
            System.out.println("\n");
        }

    }

    class Bank{
        private int index = 0, LEN = 10;
        private String bankName;
        private Agency[] agencies = new Agency[LEN];
        

        public Bank(String name){
            bankName = name;
        }

        public String getBankName(){
            return bankName;
        }
        public int getIndex(){
            return index;
        }
        public int getLEN(){
            return LEN;
        }

        public boolean checkNull(){
            return index == 0;
        }

        public boolean checkInput(int input){
            for(int i = 0 ; i < index ; i++){
                if(agencies[i].getAgencyNumber() == input) {
                    return true;
                }
            }
            return false;
        }

        public void reorderAgencies(int positionDeleted){
            for(int i = positionDeleted ; i < index - 1 ; i++){
                if(agencies[i + 1] != null){
                    agencies[i] = agencies[i + 1];
                }
            }
            agencies[index - 1] = null;
        }

        public String searchNameAgency(int number){
            String agencyName = null;
            for(Agency object : agencies){
                if(object.getAgencyNumber() == number){
                    agencyName = object.getAgencyName();
                    break;
                }
            }
            return agencyName;
        }

        public void createAgency(String name, int number){
            if(checkInput(number)){
                System.out.println("Numero ja existe\n");
                return;
            }
            agencies[index] = new Agency();
            agencies[index].setAgencyName(name);
            agencies[index].setAgencyNumber(number);
            System.out.println("Operacao concluida\n");
            index++;
        }

        public void deleteAgency(int number){
            int positionDeleted = -1;
            for(int i = 0 ; i < index ; i++){
                if(agencies[i].getAgencyNumber() == number){
                    agencies[i] = null;
                    System.out.println("Operacao concluida\n");
                    positionDeleted = i;
                } 
            }
            reorderAgencies(positionDeleted);
            index--;
        }

        public void listAgencies(){
            System.out.println("----AGENCIAS----");
            for(int i = 0 ; i < agencies.length ; i++){
                if(agencies[i] != null){
                    System.out.printf("%d. Nome: %s - Numero: %d\n", i+1, agencies[i].getAgencyName(), agencies[i].getAgencyNumber());
                }
            }
        }
    }

    class MyException extends Exception{
        public MyException(String message){
            super(message);
        }
    }

    public class Main {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            Agency icompAgencies = new Agency();
            Bank icomp = new Bank("Icomp");
            int mainOp;

            System.out.println("Bem vindo ao sistema de gerenciamento do Banco " + icomp.getBankName() + "\n");

            while(true){
                mainOp = 0;
                System.out.println("----MENU----");
                System.out.println("1. Criar agencia");
                System.out.println("2. Remover agencia");
                System.out.println("3. Listar agencias");
                System.out.println("4. Selecionar agencia");
                System.out.println("5. Sair");

                try{
                    mainOp = sc.nextInt();
                    sc.nextLine();
                    if((mainOp < 1) || (mainOp > 5)){
                        throw new MyException("Opcao nao encontrada\n");
                    }
                }catch(MyException e){
                    System.out.println(e.getMessage());
                }catch(java.util.InputMismatchException e){
                    System.out.println("Insira um valor valido");
                    sc.nextLine();
                }

                if(mainOp == 5){
                    System.out.println("Volte sempre :)");
                    break;
                }

                mainMenu:
                switch(mainOp){
                    case 1:
                    if(icomp.getIndex() != icomp.getLEN()){
                        try{
                            System.out.println("Digite o nome da agencia");
                            String agencyName = sc.nextLine();
                            System.out.println("Digite o numero da agencia");
                            int agencyNumber = sc.nextInt();
                            icomp.createAgency(agencyName, agencyNumber);
                        }catch(java.util.InputMismatchException e){
                            System.out.println("Insira um valor valido\n");
                            sc.nextLine();
                        }
                    }else{
                        System.out.println("Capacidade maxima alcancada\n");
                    }
                    break;

                    case 2:
                    if(icomp.checkNull() == false){
                        try{
                            System.out.println("Digite o numero da agencia");
                            int agencyNumber = sc.nextInt();
                            if(icomp.checkInput(agencyNumber) == true){
                                icomp.deleteAgency(agencyNumber);
                            }else{
                                System.out.println("Numero nao encontrado\n");
                            }
                        }catch(java.util.InputMismatchException e){
                            System.out.println("Insira um valor valido\n");
                            sc.nextLine();
                        }
                    }else{
                        System.out.println("Nao eh possivel remover, lista vazia\n");
                    }
                    break;

                    case 3:
                    icomp.listAgencies();
                    break;

                    case 4:
                    if(icomp.checkNull() == false){
                        try{
                            System.out.println("Digite o numero da agencia");
                            int agencyNumber = sc.nextInt();
                            if(icomp.checkInput(agencyNumber) == true){
                                int innerOption;
                                
                                while(true){
                                    innerOption = 0;
                                    System.out.println("----Menu da agencia " + icomp.searchNameAgency(agencyNumber) + "----");
                                    System.out.println("1. Criar conta");
                                    System.out.println("2. Remover Conta");
                                    System.out.println("3. Listar Contas");
                                    System.out.println("4. Selecionar Contas");
                                    System.out.println("5. Voltar");

                                    try{
                                        innerOption = sc.nextInt();
                                        sc.nextLine();
                                        if((innerOption < 1) || (innerOption > 5)){
                                            throw new MyException("Opcao nao encontrada\n");
                                        }
                                    }catch(MyException e){
                                        System.out.println(e.getMessage());
                                    }catch(java.util.InputMismatchException e){
                                        System.out.println("Insira um valor valido\n");
                                        sc.nextLine();
                                    }
                                    
                                    secondMenu:
                                    switch(innerOption){
                                        case 1:
                                        if(icompAgencies.getIndex() != icompAgencies.getLEN()){
                                            try{
                                                System.out.println("Digite o nome do titular");
                                                String accountName = sc.nextLine();
                                                System.out.println("Digite o numero da conta");
                                                int accountID = sc.nextInt();
                                                icompAgencies.createAccount(accountName, accountID);
                                            }catch(java.util.InputMismatchException e){
                                                System.out.println("Insira um valor valido\n");
                                                sc.nextLine();
                                            }
                                        }else{
                                            System.out.println("Capacidade maxima alcancada\n");
                                        }
                                        break;

                                        case 2:
                                        if(icompAgencies.checkNull() == false){
                                            try{
                                                System.out.println("Digite o numero da conta");
                                                int accountNumber = sc.nextInt();
                                                if(icompAgencies.checkInput(accountNumber) == true){
                                                    icompAgencies.deleteAccount(accountNumber);
                                                }else{
                                                    System.out.println("Numero nao encontrado\n");
                                                }
                                            }catch(java.util.InputMismatchException e){
                                                System.out.println("Insira um valor valido\n");
                                                sc.nextLine();
                                            }
                                        }else{
                                            System.out.println("Nao eh possivel remover, lista vazia\n");
                                        }
                                        break;

                                        case 3:
                                        icompAgencies.listAccounts();
                                        break;

                                        case 4:
                                        if(icompAgencies.checkNull() == false){
                                            try{
                                                System.out.println("Digite o numero da conta");
                                                int accountNumber = sc.nextInt();
                                                if(icompAgencies.checkInput(accountNumber) == true){
                                                                                            
                                                    int lastInnerOption;
                                
                                                    while(true){
                                                        lastInnerOption = 0;
                                                        System.out.println("Menu da conta " + icompAgencies.searchNameAccount(accountNumber));
                                                        System.out.println("1. Depositar");
                                                        System.out.println("2. Sacar");
                                                        System.out.println("3. Transferir");
                                                        System.out.println("4. Ver Saldo");
                                                        System.out.println("5. Voltar");

                                                        try{
                                                            lastInnerOption = sc.nextInt();
                                                            sc.nextLine();
                                                            if((lastInnerOption < 1) || (lastInnerOption > 5)){
                                                                throw new MyException("Opcao nao encontrada\n");
                                                            }
                                                        }catch(MyException e){
                                                            System.out.println(e.getMessage());
                                                        }catch(java.util.InputMismatchException e){
                                                            System.out.println("Insira um valor valido\n");
                                                            sc.nextLine();
                                                        }
                                                        
                                                        switch(lastInnerOption){
                                                            case 1:
                                                            try{
                                                                System.out.println("Insira o valor que deseja depositar");
                                                                double value = sc.nextDouble();
                                                                icompAgencies.updateAccount(lastInnerOption, accountNumber, value, 1);
                                                            }catch(java.util.InputMismatchException e){
                                                                System.out.println("Insira um valor valido\n");
                                                                sc.nextLine();
                                                            }
                                                            break;

                                                            case 2:
                                                            if(icompAgencies.searchBalance(accountNumber) > 0){
                                                                try{
                                                                    System.out.println("Insira o valor que deseja sacar");
                                                                    double value = sc.nextDouble();
                                                                    if(icompAgencies.searchBalance(accountNumber) < value){
                                                                        throw new MyException("Valor invalido, operacao cancelada\n");
                                                                    }else{
                                                                        icompAgencies.updateAccount(lastInnerOption, accountNumber, value, 1);
                                                                    }
                                                                }catch(MyException e){
                                                                    System.out.println(e.getMessage());
                                                                }catch(java.util.InputMismatchException e){
                                                                    System.out.println("Insira um valor valido\n");
                                                                    sc.nextLine();
                                                                }
                                                            }else{
                                                                System.out.println("Nao eh possivel sacar, saldo zerado\n");
                                                            }
                                                            break;                                                            

                                                            case 3:
                                                            try{
                                                                System.out.println("Insira o numero da conta que deseja transferir");
                                                                int recipient = sc.nextInt();
                                                                if(icompAgencies.checkInput(recipient) == false){
                                                                    throw new MyException("Conta nao encontrada, opcao cancelada\n");
                                                                }
                                                                System.out.println("Insira o valor que deseja transferir");
                                                                double value = sc.nextDouble();
                                                                if((icompAgencies.searchBalance(accountNumber) < value) || (value <= 0)){
                                                                    throw new MyException("Valor invalido, operacao cancelada\n");
                                                                }else{
                                                                    icompAgencies.updateAccount(lastInnerOption, accountNumber, value, recipient);
                                                                }
                                                            }catch(MyException e){
                                                                System.out.println(e.getMessage());
                                                            }catch(java.util.InputMismatchException e){
                                                                System.out.println("Insira um valor valido\n");
                                                                sc.nextLine();
                                                            }
                                                            break;

                                                            case 4:
                                                            double balance = icompAgencies.searchBalance(accountNumber);
                                                            String accountName = icompAgencies.searchNameAccount(accountNumber);
                                                            System.out.printf("Nome - %s | Numero da conta - %d | Saldo - %.2f\n", accountName, accountNumber, balance);
                                                            break;

                                                            case 5:
                                                            break secondMenu;
                                                        }
                                                    }
                                                }else{
                                                    System.out.println("Numero nao encontrado, opcao cancelada\n");
                                                }
                                            }catch(java.util.InputMismatchException e){
                                                System.out.println("Insira um valor valido\n");
                                                sc.nextLine();
                                            }
                                        }else{
                                            System.out.println("Nao eh possivel selecionar conta, lista vazia\n");
                                        }
                                        break;

                                        case 5:
                                        break mainMenu;
                                    }
                                }
                            }else{
                                System.out.println("Numero nao encontrado, operacao cancelada\n");
                            }
                        }catch(java.util.InputMismatchException e){
                            System.out.println("Insira um valor valido\n");
                            sc.nextLine();
                        }
                    }else{
                        System.out.println("Nao eh possivel encontrar agencia, lista vazia\n");
                    }
                    break; 
                }
            }
            sc.close();
        }
    }