import java.util.Scanner; //I have imported a utility scanner to take in input from the user.
import java.io.FileWriter; //This is imported to be able to save the question and answers to a file
import java.io.IOException; //This is imported to handle exceptions

public class AISurvey {        //Creating a class called AISurvey
    public static void main(String[] args) {
        // Defining the questions and options using Arrays.
        String[] questions = {
                "What do you think about gun control laws?",
                "What economic system do you believe in?",
                "What are your views on abortion laws?",
                "Do you support mandated Vaccinations?",
                "Should the U.S. raise taxes on the Wealthy?",
                "What is your stance on free speech?",
                "What do you feel about funding for the Police Force?",
                "Should the federal government have any say in State affairs?",
                "What do you think the role of media should be in shaping public opinion?",
                "Do you support universal healthcare?",
                "What is your opinion on foreign policy?",
                "What is your opinion on foreign funding?",
                "What is your view on immigration?",
        };

        String[][] options ={

                {"I support gun rights and oppose strict gun controls","I support stricter gun control laws and regulations",
                        "I advocate for strict gun laws and bans on firearms and promoting public safety","I oppose most gun laws" +
                        ", and significant restriction on individual's right to own firearms"},
                {"A free-market capitalist economic system with minimal government intervention","A mixed-market economy combining capitalism and government intervention",
                        "A sustainable economic system and ensuring environmental stability", "Advocate for free market with no government intervention"},
                {"Opposing abortion rights","Supports abortion rights","Supports abortion rights and oppose restriction on access to" +
                        " abortion services","Supports abortion rights and minimal government involvement"},
                {"No, It takes away from personal freedom and individual choice","Yes, it is essential to protect public health","It depends " +
                        "how essential and researched it is","I oppose mandated vaccinations and I prioritize personal choice"},
                {"I oppose raising tax on the wealthy","I support raising tax on the wealthy to address income inequality","I strongly support " +
                        "raising taxes on the wealthy to fund social and environmental issues","I oppose raising taxes on anyone including the wealthy"},
                {"I advocate for free speech no mather how controversial","I support free speech but hate speech should be restricted","I support free " +
                        "speech but prioritize combating hate speech"," I advocate for absolute free speech, opposing any restrictions"},
                {"I support funding for the police for public safety","I advocate for police reform and defunding to allocate funds for social services"," " +
                        "I advocate for reduction in police funding and reallocating for sustainability practices","I support reducing police spending " +
                        "and advocate for community based policing"},
                {"I support limited government and more power for states","I believe government involvement is important to assist citizens"," " +
                        "I support the decentralization of the government","I believe the government should focus on national defense"},
                {"I believe the media should provide unbiased reporting without agenda","I advocate for the media to hold those in power accountable and promote" +
                        " progressive views","I advocate for the media to prioritize environmental issues","I believe the media should be free from government" +
                        " intervention"},
                {"I prefer market-based solution and limited government involvement","I support universal healthcare and access for all","I strongly support" +
                        " universal healthcare and believe it's a fundamental right for all"," I slightly oppose universal healthcare and prefer minimal government" +
                        " involvement in the free-market"},
                {"I support strong military presence abroad to protect citizens","I support diplomacy and cooperation","I support foreign policies that are focused " +
                        "on humanitarian aid","I oppose military intervention abroad"},
                {"I support foreign aid to allies","I support foreign aid for humanitarian purposes","I advocate for sustainability initiatives instead of foreign aids",
                "I oppose foreign aid and prefer focusing on domestic priorities"},
                {"Border security should be a priority and immigration should be merit-based","The immigration system needs a reform allowing a less complex pathway " +
                        "to citizenship","Advocate for open borders for all immigrants","Supports individual rights regardless of nationality"},
        };

        // Taking user input and recording answers using the Scanner utility.
        Scanner scanner = new Scanner(System.in);
        int totalQuestions = questions.length;
        String[] answers = new String[totalQuestions];

        //Using the for loop with if and else statements to set the conditions for what to do with the input.
        for (int i = 0; i < totalQuestions; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            System.out.println("Options:");

            if (options[i].length >= 4) {
                System.out.println("a) " + options[i][0]);
                System.out.println("b) " + options[i][1]);
                System.out.println("c) " + options[i][2]);
                System.out.println("d) " + options[i][3]);
            }
            else {
                System.out.println("Error: Insufficient options for question " + (i + 1));
                continue; // This helps us catch an error if the number of options inserted is less than expected.

            }

            /*Here I am setting a restriction to only allow the user to enter valid options. If the user enters anything other than
            * abcdABCD, they'll be asked to enter a valid entry and the system will loop until a valid entry is made.*/
            String answer;
            do {
                System.out.print("Your answer: ");
                answer = scanner.nextLine();
                if(!answer.matches("abcdABCD")){
                    System.out.println("Please enter a valid option: a, b, c or d");
                }
            } while (!answer.matches("[abcdABCD]")); // This will cause the system to loop until the input matches a, b, c, or d

            answers[i] = answer;
        }

        // The method predictParty here will be responsible for predicting the user's political affiliation based on input
        String predictedParty = predictingParty.predictParty(answers);
        System.out.println("Predicted Political Party Affiliation: " + predictedParty);

        /*These question will be displayed after the user answers the series of questions and the prediction has been made.
        */
        System.out.println("What political party do you affiliate with?");
        System.out.println("Options: \na. Republican Party\n b. Democratic Party\n c. Green Party \nd. Libertarian Party");
        //Here I am saving the user's answers into the partyAffiliation
        String partyAffiliation = scanner.nextLine();

        /*The partyAffiliation above only saves the letters the user enters and since I need to explicitly state what
        * party the user entered, I am using switch statements for different cases. */
        String fullPartyAffiliation = "";
        switch (partyAffiliation.toLowerCase()) {
            case "a":
                fullPartyAffiliation = "Republican Party";
                break;
            case "b":
                fullPartyAffiliation = "Democratic Party";
                break;
            case "c":
                fullPartyAffiliation = "Green Party";
                break;
            case "d":
                fullPartyAffiliation = "Libertarian Party";
                break;
            default:
                System.out.println("Invalid input. Please enter a valid option (a, b, c, or d).");
                break;
        }
        // This closes the scanner
        scanner.close();

        //Here I am printing out the political party the user has put in.
        System.out.println("Your political party affiliation is: " + fullPartyAffiliation);
        writeInputsToPartyFiles(questions, answers,options, predictedParty);

    }


    /*Here I am writing a file that stores the result of the Predicted political party, the questions asked and the option
    * the user has selected. the try catch statements help us catch an error if something happens while the file is being written.*/
    private static void writeInputsToPartyFiles(String[] questions, String[] answers, String[][] options, String predictedParty) {
        String filename = "user_input_" + predictingParty.predictParty(answers) + ".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Predicted Party Affiliation: " + predictedParty + "\n\n");
            for (int i = 0; i < questions.length; i++) {
                writer.write("Question " + (i + 1) + ": " + questions[i] + "\n");
                writer.write("Answer: " + answers[i] + " - " + options[i][answers[i].toLowerCase().charAt(0) - 'a'] + "\n\n");
            }
            writer.close();
            System.out.println("User input written to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to " + filename);
            e.printStackTrace();
        }
    }

    /* Here I am making a static class to predict political party affiliation of the user. I am declaring the
    * integer variables to hold the count for the four parties corresponding with options selected by the user. The political party
    * assigned to each option is consistent throughout. E.g. Option A - Republican. */
    static class predictingParty{
        private static String predictParty(String[] answers) {
            int republicanCount = 0;
            int democraticCount = 0;
            int greenCount = 0;
            int libertarianCount = 0;

            /*This for loop loops through the answers and count occurrences of the four parties. the ++ ensures that all
            the answers to all the questions are counted. */
            for (String answer : answers) {
                if (answer != null) {

                if (answer.equalsIgnoreCase("a"))  {
                    republicanCount++;
                }
                if (answer.equalsIgnoreCase("b")) {
                    democraticCount++;
                }
                if (answer.equalsIgnoreCase("c")) {
                    greenCount++;
                }
                if (answer.equalsIgnoreCase("d")) {
                    libertarianCount++;
                }
                }
            }

            /* Based on the counts made above, Here is where the political party affiliation count is weighted*/
            if (republicanCount > democraticCount && republicanCount > greenCount && republicanCount > libertarianCount) {
                return " Republican Party";
            }
            if (democraticCount > republicanCount && democraticCount > greenCount && democraticCount > libertarianCount) {
                return " Democratic Party";
            }
            if (greenCount > republicanCount && greenCount > democraticCount && greenCount > libertarianCount) {
                return " Green Party";
            }
            if (libertarianCount > republicanCount && libertarianCount > democraticCount && libertarianCount > greenCount) {
                return " Libertarian Party";
            }
            else {
                return " An Error has occurred. Please Try Again!";
            }
        }
    }


    }

