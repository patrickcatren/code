import java.util.*;
import java.io.*;

public class Vault {

    
    /** 
     * @param args The user will enter a file as an argument, adding '-au' before the filename
     * to enter add user mode.
     */
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("usage: java Vault [-au] <filename>");
            System.exit(1);
        }

        ArrayList<Encryptor> E = new ArrayList<Encryptor>();
        E.add(new ShiftClear());
        E.add(new ShiftCaesar());
        E.add(new ShiftVigenere());
        E.add(new Caesar());
        E.add(new Vigenere());

        String filename = null;
        if(args[0].equals("-au")){
            filename = args[1];
        }
        else {
            filename = args[0];
        }
        //set up a char array for error checking
        char[] vc = new char[80];
        for(int i = 0; i < 80; i++){
            vc[i] = (char)(i+42);
        }


        Scanner sc = null;
        try {
            File fileObj = new File(filename);
            sc = new Scanner(fileObj);
        } catch (FileNotFoundException e) {
            System.out.println("Error! File '" + filename + "' could not be opened.");
            System.exit(1);
        }
        ArrayList<User> userArray = new ArrayList<User>();
        ArrayList<Label> labelArray = new ArrayList<Label>();
        if(!(sc.hasNextLine())){
            System.out.println("Error! File '" + filename + "' improperly formatted.");
            System.exit(1);
        }
        while(sc.hasNextLine()){
            String input = sc.nextLine();
            String[] splits = input.split(" ");
            if((splits.length == 4) && ((splits[0].equals("user")) || (splits[0].equals("data")))   ){
                if(splits[0].equals("data")){
                    labelArray.add(new Label(splits[1], splits[2], splits[3]));
                }
                else{
                    userArray.add(new User(splits[1], splits[2], splits[3]));
                }
            }
            else{
                System.out.println("Error! File '" + args[0] + "' improperly formatted.");
                System.exit(1);
            }
        }


        if(args[0].equals("-au")){
            sc.close();
            // Opens an existing file, and appends to the end of it.
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            } catch (IOException e) {
                System.out.println("IO Exception");
                System.exit(1);
            }

            // Do whatever you need to do
            String givenName = null, givenPass = null, givenAlg = null;
            System.out.print("username: ");
            givenName = System.console().readLine();
            System.out.print("password: ");
            givenPass = System.console().readLine();
            char[] gpArr = givenPass.toCharArray();
            System.out.print("Hash algorithm: ");
            givenAlg = System.console().readLine();



            for(int j = 0; j < gpArr.length; j++){
                try{
                    if((int)vc[((int)gpArr[j]-42)] > 0);
                } catch(IndexOutOfBoundsException e){
                    System.out.println("Error! Invalid symbol '" + gpArr[j] + "' in password.");
                    System.exit(1);
                }
            }
            int testAlg = -1;
            try {
                while( !E.get(++testAlg).getAlgName().equals(givenAlg) ) ;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Error! Hash algorithm '" + givenAlg + "' not supported.");
                System.exit(1);
            }
            for(int i = 0; i < userArray.size(); i++){
                if(givenName.equals(userArray.get(i).getName())){
                    System.out.println("Error! Username '" + givenName + "' already in use.");
                    System.exit(1);
                }
            }
            String gToCipher = E.get(testAlg).encrypt(givenPass);
            pw.println("user " + givenName + " " + givenAlg + " " + gToCipher);



            if (pw != null) pw.close();
            System.exit(1);
        }

        System.out.print("username: ");
        String user = System.console().readLine();
        System.out.print("password: ");
        char[] pswd = System.console().readPassword();
        for(int j = 0; j < pswd.length; j++){
            try{
                if((int)vc[((int)pswd[j]-42)] > 0);
            } catch(IndexOutOfBoundsException e){
                System.out.println("Access denied!");
                System.exit(1);
            }
        }
        String password = new String(pswd);


        int userNum = -1;
        try {
            while( !userArray.get(++userNum).getName().equals(user) ) ;
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Access denied!");
            System.exit(1);
        }
        int k = -1;
        try {
            while( !E.get(++k).getAlgName().equals(userArray.get(userNum).getAlgo()) ) ;
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Error! Hash algorithm '" + userArray.get(userNum).getAlgo() + "' not supported.");
            System.exit(1);
        }
        for(int i = 0; i < E.size(); i++){
            E.get(i).init(pswd);
        }
        String ciphertext = E.get(k).encrypt(password);

        if(userArray.get(userNum).getPswd().equals(ciphertext)){
            System.out.println("Access granted!");
            String command = "start";
            while(!command.equals("quit")){
                System.out.print("> ");
                command = System.console().readLine();
                if(command.equals("labels")){
                    for(int i = 0; i < labelArray.size(); i++){
                        if(user.equals(labelArray.get(i).getName())){
                            int algNum = 0;
                            for(int j = 0; j < E.size(); j++){
                                if((labelArray.get(i).getAlgo()).equals(E.get(j).getAlgName())){
                                    algNum = j;
                                }
                            }
                            String data = labelArray.get(i).getData();
                            data = E.get(algNum).decrypt(data);
                            String decryptedData[] = data.split("_", 2);
                            System.out.println(decryptedData[0]);
                        }
                    }
                }
                String[] splitCommand = command.split(" ", 2);
                if(splitCommand[0].equals("get")){
                    for(int i = 0; i < labelArray.size(); i++){
                        if(user.equals(labelArray.get(i).getName())){
                            int algNum = 0;
                            for(int j = 0; j < E.size(); j++){
                                if((labelArray.get(i).getAlgo()).equals(E.get(j).getAlgName())){
                                    algNum = j;
                                }
                            }
                            String data = labelArray.get(i).getData();
                            data = E.get(algNum).decrypt(data);
                            String decryptedData[] = data.split("_", 2);
                            if(decryptedData[0].equals(splitCommand[1])){
                                System.out.println(decryptedData[1]);
                            }
                        }
                    }
                }
                else if(!command.equals("quit") && !command.equals("labels")){
                    System.out.println("Unknown command '" + command + "'.");
                }
            }
        }
        else{
            System.out.println("Access denied!");
        }
        sc.close();
    }
}
