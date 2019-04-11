import java.util.ArrayList;

/**
 * Created by martiniu on 11.04.2019.
 */
public class Stars {
    ArrayList<String> starList = new ArrayList<>();
    ArrayList<Integer> starLength = new ArrayList<>();
    int maxLength;

    public static void main(String[] args) {
        Stars stars = new Stars();
        stars.makeStars(20);
        stars.orderStars(4);
        stars.printStars();

    }

    /**
     * Creates stars.
     * @param amount: Amount of star groups wanted.
     */
    private void makeStars(int amount){
        starList.add(0, "*");
        for (int i = 1; i < amount; i++) starList.add(i, starList.get(i-1)+"*");
    }

    /**
     * Orders all star groups and creates multiple lists/variables for further use
     * when printing later on.
     * @param spacing: Spacing wanted between each "star group"
     */
    private void orderStars(int spacing){
        //- Variables to use when placing stars on rows and finding the center for further placement
        int curRow = 1;
        int prevStars = 1;
        int curStars = 0;
        int rowLength = 0;

        //- Assigns first star element to the first pos
        starList.set(0, starList.get(0) + "\n");
        starLength.add(0, 1);

        //- Assigns every following star element to their
        //  pos, also adds spacing for each element
        for (int i = 1; i < starList.size(); i++){

            //- Current row is longer than previous --> we have a new line
            //  Resets all values that are determined on the individual rows
            if (curStars > prevStars){
                starList.set(i-1, starList.get(i-1).substring(0, starList.get(i-1).length()-spacing) + "\n");
                prevStars = curStars;
                curStars = 0;
                starLength.add(curRow, rowLength-spacing); // The end element of a row does not need spacing
                rowLength = 0;
                curRow++;
            }

            //- Finds the current length of the row
            rowLength += starList.get(i).length() + spacing;

            //- Decides max length, which is later used to center "star tree"
            if (rowLength > maxLength) maxLength = rowLength;
            starList.set(i, starList.get(i) + getSpacing(spacing));

            //- The last element of all of them does not need padding
            if (i+1 == starList.size()){
                starList.set(i, starList.get(i).substring(0, starList.get(i).length()-spacing) + "\n");
                starLength.add(curRow, rowLength-spacing);
            }
            curStars++;
        }
    }

    /**
     * Prints all stars and centers them.
     */
    private void printStars(){
        int row = 0;

        for (int i = 0; i < starList.size(); i++){
            if (i==0){
                System.out.print(getSpacing((maxLength/2)-(starLength.get(row)/2)) + starList.get(i));
                row++;
            }
            else if (starList.get(i-1).contains("\n")){
                System.out.print(getSpacing((maxLength/2)-(starLength.get(row)/2)) + starList.get(i));
                row++;
            }
            else {
                System.out.print(starList.get(i));
            }
        }
    }

    /**
     * Method that returns spacing of given size.
     * @param spacing: size of space to be returned
     * @return space: blank space of size spacing
     */
    private String getSpacing(int spacing){
        String space = "";
        for (int i = 0; i < spacing; i++){
            space += " ";
        }
        return space;
    }
}
